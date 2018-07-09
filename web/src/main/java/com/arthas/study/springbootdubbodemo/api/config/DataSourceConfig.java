package com.arthas.study.springbootdubbodemo.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 数据源配置
 * @author : lieying
 * date : 2018/7/9 21:45
 */
@Configuration
@ImportResource(value = {"classpath:dao/data-source.xml"})
public class DataSourceConfig {

}
