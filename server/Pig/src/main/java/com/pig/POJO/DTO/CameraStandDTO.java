package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName camera_stand
 */
@Data
public class CameraStandDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */
    private Long id;

    /**
     * 机号
     */
    private Integer machineId;

    /**
     * 机型
     */
    private String machineType;

    /**
     * 机型序列号
     */
    private String machineSerialNumber;
}
