package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName pig_farm_environment
 */
@Data
public class PigFarmEnvironmentDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */
    private Long id;

    /**
     * 气温
     */
    private Integer airTemperature;

    /**
     * 空气湿度
     */
    private Integer airHumidity;

    /**
     * 饲料消耗量
     */
    private Double feedConsumption;
}
