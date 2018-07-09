package com.arthas.study.springbootdubbodemo.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Mybatis配置
 * @author : lieying
 * date : 2018/7/9 21:29
 */
@Configuration
@ImportResource(value = {"classpath:mybatis/mybatis-spring.xml"})
public class MybatisConfig {

}
