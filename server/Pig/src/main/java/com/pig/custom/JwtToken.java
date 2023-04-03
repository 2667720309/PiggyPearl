package com.pig.custom;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * @author Blackke
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private String timeStamp;

    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * 获取当前用户信息 返回的是UserDetails接口的实现类，框架中常用的接口
     */
    @Override
    public Object getPrincipal() {
        return null;
    }

    /**
     * 密码信息 用户输入密码字符串，认证后通常会被移除，用于保障安全
     */
    @Override
    public Object getCredentials() {
        return null;
    }
}
