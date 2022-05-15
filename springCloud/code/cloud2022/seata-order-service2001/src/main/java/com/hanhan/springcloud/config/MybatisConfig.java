package com.hanhan.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.hanhan.springcloud.dao"})
public class MybatisConfig {
}
