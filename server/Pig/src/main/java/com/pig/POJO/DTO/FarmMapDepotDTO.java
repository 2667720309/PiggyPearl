package com.pig.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName farm_map_depot
 */
@Data
public class FarmMapDepotDTO implements Serializable {
    /**
     * 自增长id（无意义）
     */

    private Long id;

    /**
     * 猪猪图片
     */
    private String pigImg;
}
