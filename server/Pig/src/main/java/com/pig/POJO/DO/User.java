package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * user表的自增id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除，默认填充0存在，1用户注销
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
