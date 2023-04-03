package com.pig.service;

import com.pig.POJO.DO.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig.POJO.DTO.ScheduleDTO;

import java.util.List;

/**
 * @author Blackke
 * @description 针对表【schedule】的数据库操作Service
 * @createDate 2023-03-22 19:00:09
 */
public interface ScheduleService extends IService<Schedule> {

    String addSchedule(Schedule schedule, int id);

    String updateSchedule(Schedule schedule);

    List<ScheduleDTO> showSchedules(int id);
}
