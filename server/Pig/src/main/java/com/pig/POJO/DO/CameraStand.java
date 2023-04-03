package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName camera_stand
 */
@TableName(value = "camera_stand")
@Data
public class CameraStand implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @TableId(type = IdType.AUTO)
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
     * 数据隔离字段：猪场id：Fid
     */
    private String isolation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
