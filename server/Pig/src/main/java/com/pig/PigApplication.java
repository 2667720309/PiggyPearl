package com.pig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.pig.mapper")
@EnableScheduling
@EnableCaching
public class PigApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigApplication.class, args);
    }

}
