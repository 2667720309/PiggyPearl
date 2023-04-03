package com.pig.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.POJO.DO.User;
import com.pig.POJO.DTO.UserDTO;
import com.pig.service.UserService;
import com.pig.mapper.UserMapper;
import com.pig.utils.JwtUtils;
import com.pig.utils.PropertyUtils;
import com.pig.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.pig.custom.Constant.REFRESH_TOKEN_ACTIVE;

/**
 * @author Blackke
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-03-22 19:00:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public UserDTO getUserByUsername(String username) {
        return PropertyUtils.doToDto(userMapper.getUserByUsername(username), UserDTO.class);
    }

    @Override
    public String register(UserDTO userDTO) {
        User registUser = PropertyUtils.dtoToDo(userDTO, User.class);

        String defaultNickname = registUser.getAccount() + "Pig";
        String defaultHeaderThumb = "common_icon.jpg";
        String defaultIdentityId = "W" + registUser.getAccount();

        registUser.setNickName(defaultNickname);
        registUser.setHeaderThumb(defaultHeaderThumb);
        registUser.setIdentityId(defaultIdentityId);
        Date date = new Date();
        registUser.setCreateTime(date);
        registUser.setUpdateTime(date);
        int num = userMapper.insert(registUser);
        if (num == 0) {
            return "注册失败";
        }
        return "注册成功";
    }

    @Override
    public String login(String account, String password) {
        User user = userMapper.getUserByUsername(account);
        if (user == null) {
            return "该用户不存在";
        }
        if (!password.equals(user.getPassword())) {
            return "密码错误";
        }
        Date date = new Date();
        String time = date.toString();
        String token = JwtUtils.creatToken(account, time);
        RedisUtils.put(account, time, REFRESH_TOKEN_ACTIVE);
        return token;
    }
}
