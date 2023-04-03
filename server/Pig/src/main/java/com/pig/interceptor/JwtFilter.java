package com.pig.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pig.custom.Constant;
import com.pig.custom.CustomerException;
import com.pig.custom.JwtToken;
import com.pig.custom.ResultVO;
import com.pig.utils.JwtUtils;
import com.pig.utils.RedisUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author Blackke
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 允许访问时，验证token，检查 token 第一阶段是否正确，正确true，错误抛出异常或返回false
     *
     * @param request     请求
     * @param response    响应
     * @param mappedValue 映射值
     * @return T/F
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果携带Token，说明要进行验证
        if (isLoginAttempt(request, response)) {
            try {
                //进入 executeLogin 方法执行登入，检查 token 第一阶段是否正确
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //若有异常，则说明该token是一定异常的，不可刷新直接响应
                responseError(response, e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 拒绝访问时
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 执行登录，获取token与主体，提交realm进行登入，然后进行token过期刷新检查
     *
     * @param request  请求
     * @param response 响应
     * @return true或者抛出异常
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        Subject subject = getSubject(request, response);
        subject.login(jwtToken);
        // 如果没有抛出异常则代表第一阶段登入成功，进行token过期刷新检查
        return this.onLoginSuccess(jwtToken, subject, request, response);
    }

    /**
     * 第一阶段登入成功时，进行token过期刷新检查
     *
     * @param token    身份令牌
     * @param subject  主体
     * @param request  请求
     * @param response 响应
     * @return true或者抛出异常
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        try {
            String jwtToken = (String) token.getCredentials();
            String userName = JwtUtils.getUserName(jwtToken);
            String accessTokenTimeStamp = JwtUtils.getTimeStamp(jwtToken);
            JwtUtils.verifyToken(jwtToken, userName, accessTokenTimeStamp);
            String refreshTokenTimeStamp = String.valueOf(RedisUtils.get(userName));
            if (refreshTokenTimeStamp == null || !Objects.equals(accessTokenTimeStamp, refreshTokenTimeStamp)) {
                throw new CustomerException(400, "登录信息异常");
            }
            return true;
        } catch (TokenExpiredException e) {
            //token 刷新校验
            if (refreshToken(request, response)) {
                return true;
            } else {
                throw new CustomerException(400, "用户登录状态已失效");
            }
        } catch (Exception e) {
            throw new CustomerException(400, "登录信息出错");
        }
    }

    /**
     * 登录尝试，验证请求头是否含有token，有返回true，没有返回false
     *
     * @param request  请求
     * @param response 响应
     * @return T/F
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        return token != null;
    }

    /**
     * servlet响应错误处理
     *
     * @param response 响应
     * @param message  错误信息
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().print(JSON.toJSONString(ResultVO.error(message)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 句柄执行前
     *
     * @param request  请求
     * @param response 响应
     * @return T/F
     * @throws Exception 抛出异常
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 尝试刷新 Token:判断Token是否过期，未过期就在请求头存入新的AccessToken并返回true，否则返回false
     *
     * @param request  请求
     * @param response 响应
     * @return T/F
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        String userName = JwtUtils.getUserName(token);
        String accessTokenTimeStamp = JwtUtils.getTimeStamp(token);
        String refreshTokenTimeStamp = String.valueOf(RedisUtils.get(userName));
        if (refreshTokenTimeStamp != null && Objects.equals(accessTokenTimeStamp, refreshTokenTimeStamp)) {
            //获取最新时间戳
            Date date = new Date();
            String timeStamp = date.toString();
            // 刷新refreshToken
            RedisUtils.put(userName, timeStamp, Constant.REFRESH_TOKEN_ACTIVE);
            // 刷新AccessToken，为当前最新时间戳
            token = JwtUtils.creatToken(userName, timeStamp);
            // 设置响应的Header头新Token
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return true;
        }
        return false;
    }
}
