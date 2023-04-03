package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName pig_farm_environment
 */
@TableName(value = "pig_farm_environment")
@Data
public class PigFarmEnvironment implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @TableId(type = IdType.AUTO)
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
     * 数据隔离字段:猪场id:Fid
     */
    private String isolation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
