package com.pig.mapper;

import com.pig.POJO.DO.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Blackke
 * @description 针对表【schedule】的数据库操作Mapper
 * @createDate 2023-03-22 19:00:09
 * @Entity com.pig.POJO.DO.Schedule
 */
public interface ScheduleMapper extends BaseMapper<Schedule> {

    List<Schedule> selectByidentityId(@Param("identityId")String identityId);


}
