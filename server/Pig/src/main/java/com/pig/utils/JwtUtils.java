package com.pig.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pig.custom.Constant;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

/**
 * @author Blackke
 * Json Web Token 工具类
 */
public class JwtUtils {
    /**
     * 根据要放入的有效荷载信息生成token
     *
     * @param userName  用户名
     * @param timeStamp 时间戳
     * @return token
     */
    public static String creatToken(String userName, String timeStamp) {
        String secretKey = MD5Utils.getMd5Middle(userName, timeStamp);
        //声明过期时间（以小时计算）
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR_OF_DAY, Constant.ACCESS_TOKEN_ACTIVE);
        // JWT.create() 方法创建一个新的 JWT 构建器
        // withClaim 方法将 userName 和 timeStamp 声明添加到构建器中
        // withExpiresAt 方法将令牌的过期时间设置为当前时间之后的几个小时。
        // sign 方法使用密钥对令牌进行签名并返回结果字符串
        return JWT.create()
                .withClaim("userName", userName)
                .withClaim("timeStamp", timeStamp)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secretKey));
    }


    /**
     * 验证token
     *
     * @param token token
     */
    public static void verifyToken(String token, String userName, String timeStamp) {
        // 根据当前的用户和当前时间戳生成密钥
        String secretKey = MD5Utils.getMd5Middle(userName, timeStamp);
        //验证token 签名有效 + 未过期
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        // 使用令牌作为参数（去掉前7个字符，因为它们是“Bearer”前缀）
        verifier.verify(token.substring(7));
    }

    /**
     * 获得token中的用户名信息，无需secret解密也能获得（不过可能是传输出错的信息）
     */
    public static String getUserName(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            // 使用JWT类的decode方法解码JWT令牌
            DecodedJWT jwt = JWT.decode(token.substring(7));
            // 使用getClaim方法从解码的JWT令牌中检索userName声明，并在声明上调用asString方法将userName作为字符串返回
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的时间戳信息
     */
    public static String getTimeStamp(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token.substring(7));
            return jwt.getClaim("timeStamp").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
