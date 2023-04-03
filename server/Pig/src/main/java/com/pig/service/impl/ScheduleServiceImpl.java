package com.pig.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.POJO.DO.Schedule;
import com.pig.POJO.DO.User;
import com.pig.POJO.DTO.ScheduleDTO;
import com.pig.mapper.UserMapper;
import com.pig.service.ScheduleService;
import com.pig.mapper.ScheduleMapper;
import com.pig.utils.JwtUtils;
import com.pig.utils.PropertyUtils;
import com.pig.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Blackke
 * @description 针对表【schedule】的数据库操作Service实现
 * @createDate 2023-03-22 19:00:09
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
        implements ScheduleService {

    @Resource
    UserMapper userMapper;

    @Resource
    ScheduleMapper scheduleMapper;

    /**
     * user 前缀
     */
    public final String PREFIX_USER = "user-";

    @Override
    public String addSchedule(Schedule schedule, int id) {

        User user = userMapper.selectById(id);
        if (user==null){
            return "抱歉，当前您的用户状态有问题";
        }
        String identity = user.getIdentityId();

        Date currentTime = new Date();
        schedule.setCreateTime(currentTime);
        schedule.setUpdateTime(currentTime);
        schedule.setIsolation(identity);

        scheduleMapper.insert(schedule);
        return "添加成功";
    }

    @Override
    public String updateSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public List<ScheduleDTO> showSchedules(int id) {
        User user = userMapper.selectById(id);
        String identityId = user.getIdentityId();

        List<Schedule> schedules =  scheduleMapper.selectByidentityId(identityId);
        List<ScheduleDTO> scheduleDTOS = PropertyUtils.listToList(schedules,ScheduleDTO.class);
        System.out.println(scheduleDTOS);
        return scheduleDTOS;
    }
}
