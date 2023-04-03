package com.pig.service;

import com.pig.POJO.DO.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig.POJO.DTO.UserDTO;

/**
 * @author Blackke
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-03-22 19:00:09
 */
public interface UserService extends IService<User> {

    UserDTO getUserByUsername(String username);

    String register(UserDTO userDTO);

    String login(String account, String password);
}
