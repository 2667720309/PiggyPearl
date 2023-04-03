package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName pig_farm
 */
@Data
public class PigFarmDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */
    private Long id;

    /**
     * 猪场id：F+序列号
     */
    private String fid;

    /**
     * 猪场名
     */
    private String fName;

    /**
     * 猪场营业执照，一般15位
     */
    private String fLicense;

    /**
     * 猪场地区
     */
    private String region;
}
