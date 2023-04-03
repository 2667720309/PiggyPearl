package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */

@Data
public class UserDTO implements Serializable {
    /**
     * user表的自增id
     */
    private Long id;

    /**
     * 用户名，不超过八个汉字
     */
    private String account;

    /**
     * 昵称，不超过12个汉字
     */
    private String nickName;

    /**
     * 不超过16位的密码
     */
    private String password;

    /**
     * 用户头像url
     */
    private String headerThumb;

    /**
     * 身份id，工人W+序列号,猪场主任U+序列号，不超过18
     */
    private String identityId;

    /**
     * 电子邮箱（可选填）
     */
    private String email;

    /**
     * 手机号码（可选填）
     */
    private String mobile;
}
