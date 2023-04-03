package com.pig.controller.user;

import com.pig.POJO.DO.Schedule;
import com.pig.POJO.DTO.ScheduleDTO;
import com.pig.POJO.DTO.UserDTO;
import com.pig.custom.ResultVO;
import com.pig.service.ScheduleService;
import com.pig.service.UserService;
import com.pig.utils.UrlUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Blackke
 * 为整个controller启用@CrossOrigin
 */
@CrossOrigin(value = {UrlUtils.CORS1, UrlUtils.CORS2}, maxAge = 3000, allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @Resource
    ScheduleService scheduleService;

    @GetMapping("/getUserByAccount/{account}")
    public ResultVO<UserDTO> getUserByAccount(@PathVariable("account") String username) {
        UserDTO user = userService.getUserByUsername(username);
        return ResultVO.success(user);
    }


    @PostMapping("/register")
    public ResultVO<String> login(@RequestBody UserDTO userDTO) {
        String account = userDTO.getAccount();
        // 检查是否有这个用户
        UserDTO checkUser = userService.getUserByUsername(account);
        if (checkUser != null) {
            return ResultVO.error("用户已存在");
        }
        String result = userService.register(userDTO);
        return ResultVO.success(result);
    }


    @PostMapping("/login")
    public ResultVO<String> login(String account, String password) {
        String token = userService.login(account, password);
        return ResultVO.success(token);
    }

    /**
     * 增加待办事项内容
     * 参数 前端传来的待办事项内容 当前登录的用户id
     */
    @PostMapping("/addSchedule/{id}")
    public ResultVO<String> addSchedule(@RequestBody Schedule schedule,@PathVariable("id") int id){
        return ResultVO.success(scheduleService.addSchedule(schedule, id));
    }

    /**
     * 修改待办事项
     * 参数 待办事项id
     */
    @PostMapping("/updateSchedule/{id}")
    public ResultVO<String> updateSchedule(@RequestBody Schedule schedule){
        return ResultVO.success(scheduleService.updateSchedule(schedule));
    }

    /**
     * 查看待办事项
     * 参数 用户id
     */
    @GetMapping("/showSchedules/{id}")
    public ResultVO<List<ScheduleDTO>> showSchedules(@PathVariable("id") int id){
        return ResultVO.success("success",scheduleService.showSchedules(id));
    }

}
