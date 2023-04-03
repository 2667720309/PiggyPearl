package com.pig.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.POJO.DO.PigFarmEnvironment;
import com.pig.service.PigFarmEnvironmentService;
import com.pig.mapper.PigFarmEnvironmentMapper;
import org.springframework.stereotype.Service;

/**
 * @author Blackke
 * @description 针对表【pig_farm_environment】的数据库操作Service实现
 * @createDate 2023-03-22 19:00:09
 */
@Service
public class PigFarmEnvironmentServiceImpl extends ServiceImpl<PigFarmEnvironmentMapper, PigFarmEnvironment>
        implements PigFarmEnvironmentService {

}
