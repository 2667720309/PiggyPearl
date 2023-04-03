package com.pig.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.POJO.DO.PigFarm;
import com.pig.service.PigFarmService;
import com.pig.mapper.PigFarmMapper;
import org.springframework.stereotype.Service;

/**
 * @author Blackke
 * @description 针对表【pig_farm】的数据库操作Service实现
 * @createDate 2023-03-22 19:00:09
 */
@Service
public class PigFarmServiceImpl extends ServiceImpl<PigFarmMapper, PigFarm>
        implements PigFarmService {

}
