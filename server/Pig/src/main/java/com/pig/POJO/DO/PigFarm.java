package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName pig_farm
 */
@TableName(value = "pig_farm")
@Data
public class PigFarm implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @TableId(type = IdType.AUTO)
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
     * 数据隔离字段：Uid:农场主
     */
    private String isolation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
