package com.pig.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static final int SIGN_NUM = 1;
    /**
     * hex值
     */
    private static final int HEX_FLAG = 16;
    /**
     * 签名的长度
     */
    private static final int SIGN_LENGTH = 32;
    /**
     * 填充值
     */
    private static final String FILL_CHAR = "0";

    /**
     * md5 32位加密方法
     *
     * @param userName,password 用户名，密码
     * @return md5
     */
    public static String getMd5UserPwd(String userName, String password) {
        try {
            String input = userName + password;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(SIGN_NUM, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(HEX_FLAG));
            while (hashtext.length() < SIGN_LENGTH) {
                hashtext.insert(0, FILL_CHAR);
            }
            return hashtext.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 根据用户名和时间戳生成中间密钥
     *
     * @param userName 用户名
     * @param timeStamp 时间戳
     * @return 中间密钥
     */
    public static String getMd5Middle(String userName, String timeStamp) {
        try {
            String input = userName + timeStamp;

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(SIGN_NUM, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(HEX_FLAG));
            while (hashtext.length() < SIGN_LENGTH) {
                hashtext.insert(0, FILL_CHAR);
            }
            return hashtext.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
