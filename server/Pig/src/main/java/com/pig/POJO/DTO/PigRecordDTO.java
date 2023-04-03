package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName pig_record
 */
@Data
public class PigRecordDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */
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
}
