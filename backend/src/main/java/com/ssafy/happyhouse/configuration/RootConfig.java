package com.ssafy.happyhouse.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ssafy.happyhouse.model.repo")
public class RootConfig {

}
