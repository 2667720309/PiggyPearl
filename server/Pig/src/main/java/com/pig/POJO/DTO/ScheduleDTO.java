package com.pig.POJO.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Blackke
 * @TableName schedule
 */
@Data
public class ScheduleDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */
    @JsonProperty("id")
    private Long id;

    /**
     * 待办事项内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * 判断是否完成，默认0没有完成，1完成
     */
    @JsonProperty("isNotFinish")
    private Integer isNotFinish;
}
