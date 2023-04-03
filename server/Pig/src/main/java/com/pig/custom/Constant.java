package com.pig.custom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Blackke
 */
@Component
public class Constant {

    /**
     * token 常量
     */
    public static int ACCESS_TOKEN_ACTIVE;
    public static int REFRESH_TOKEN_ACTIVE;

    @Value("${constant.token.accessTokenActive}")
    public void setAccessTokenActive(int accessTokenActive) {
        ACCESS_TOKEN_ACTIVE = accessTokenActive;
    }

    @Value("${constant.token.refreshTokenActive}")
    public void setRefreshTokenActive(int refreshTokenActive) {
        REFRESH_TOKEN_ACTIVE = refreshTokenActive;
    }


    /**
     * 响应状态 常量
     */
    public static final int RES_FAIL = 400;
    public static final int RES_SUCCESS = 200;
    public static final int RES_ERROR = 500;

    /**
     * 缓存相关 常量
     */
    public static int REDIS_INDEX_SERVICE;
    public static int REDIS_INDEX_UTILS;

    @Value("${constant.redis.serviceIndex}")
    public void setRedisIndexService(int redisIndexService) {
        REDIS_INDEX_SERVICE = redisIndexService;
    }

    @Value("${constant.redis.utilsIndex}")
    public void setRedisIndexUtils(int redisIndexUtils) {
        REDIS_INDEX_UTILS = redisIndexUtils;
    }

    /**
     * 加密 常量
     */
    public static int PWD_HASH_ITERATIONS;
    public static int JWT_HASH_ITERATIONS;

    @Value("${constant.hashIterations.pwd}")
    public void setPwdHashIterations(int pwdHashIterations) {
        Constant.PWD_HASH_ITERATIONS = pwdHashIterations;
    }

    @Value("${constant.hashIterations.jwt}")
    public void setJwtHashIterations(int jwtHashIterations) {
        Constant.JWT_HASH_ITERATIONS = jwtHashIterations;
    }

    /**
     * 业务数据缓存时间常量
     */
    public static int CACHE_TIME_ONE_DAY = 86400;

}
