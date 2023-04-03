package com.pig.custom;

import com.pig.POJO.DO.User;
import com.pig.mapper.UserMapper;
import com.pig.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * @author Blackke
 * 自定义域
 */
public class CustomRealm extends AuthorizingRealm {
    /**
     * 1.只要配置了在Spring里管理（@Bean），就可以使用Autowired注入
     * 2.@Lazy 延迟注入，解决Realm内调用Service 缓存和事务失效问题
     */
    @Resource
    @Lazy
    UserMapper userMapper;

    /**
     * 重写supports方法：支持自定义JWTToken的认证与授权
     *
     * @param token token
     * @return T/F
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权校验
     *
     * @param principalCollection principalCollection
     * @return SimpleAuthorizationInfo
     */
    @Override
    protected SimpleAuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 => 授权方法doGetAuthorizationInfo");
        //获取用户名（能执行到这一步，说明已经通过了认证，无需验证token）
        String username = JwtUtils.getUserName((String) principalCollection.getPrimaryPrincipal());
        //数据库查询角色权限信息
        User user = userMapper.getUserByUsername(username);
        //如果权限不为空
//        if (user.getRole() != null) {
//            //返回角色权限信息
//            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//            authorizationInfo.addRole(user.getRole());
//            return authorizationInfo;
//        }
        return new SimpleAuthorizationInfo();
//        return null;
    }

    /**
     * 认证校验
     *
     * @param authenticationToken authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了 => 认证方法doGetAuthenticationInfo");
        //从主体传过来的认证信息中，获取需要认证的token
        String token = (String) authenticationToken.getPrincipal();
        //获取token 携带的校验信息
        String userName = JwtUtils.getUserName(token);
        System.out.println("userName:" + userName);
        if (userName == null || JwtUtils.getTimeStamp(token) == null) {
            throw new UnsupportedTokenException("登录用户信息丢失");
        }
        //判断用户是否真实有效
        User user = userMapper.getUserByUsername(userName);
        if (user == null) {
            throw new UnknownAccountException("登录用户不存在");
        }
//        } else if (user.getState() == 1) {
//            throw new LockedAccountException("登录用户已被锁定");
//        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}
