package com.jules.ipam.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jules.ipam.dao")
public class MyBatisConfig {
}
