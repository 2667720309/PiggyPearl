package com.pig.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.POJO.DO.PigRecord;
import com.pig.service.PigRecordService;
import com.pig.mapper.PigRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author Blackke
 * @description 针对表【pig_record】的数据库操作Service实现
 * @createDate 2023-03-22 19:00:09
 */
@Service
public class PigRecordServiceImpl extends ServiceImpl<PigRecordMapper, PigRecord>
        implements PigRecordService {

}
