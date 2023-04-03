package com.pig.POJO.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName schedule
 */
@TableName(value = "schedule")
@Data
public class Schedule implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 待办事项内容
     */
    private String content;

    /**
     * 判断是否完成，默认0没有完成，1完成
     */
    private Integer isNotFinish;

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
     * 数据隔离字段，根据Wid或Uid确认数据
     */
    private String isolation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
