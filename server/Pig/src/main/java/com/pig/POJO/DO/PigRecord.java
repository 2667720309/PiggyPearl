package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName pig_record
 */
@TableName(value = "pig_record")
@Data
public class PigRecord implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 档案id:R+序列号
     */
    private String recordId;

    /**
     * 猪的图片
     */
    private String pigImg;

    /**
     * 日龄
     */
    private Integer ageInDays;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 今日饮水
     */
    private String drinkingOfDay;

    /**
     * 今日吃饭
     */
    private String eatingOfDay;

    /**
     * 疫苗接种情况
     */
    private String vaccination;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除字段，默认0存在，1删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 数据隔离字段，Fid:猪场id
     */
    private String isolation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
