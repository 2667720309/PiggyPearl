package com.pig.mapper;

import com.pig.POJO.DO.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Blackke
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-03-22 19:00:09
 * @Entity com.pig.POJO.DO.User
 */
public interface UserMapper extends BaseMapper<User> {
    User getUserByUsername(@Param("account") String account);
}
