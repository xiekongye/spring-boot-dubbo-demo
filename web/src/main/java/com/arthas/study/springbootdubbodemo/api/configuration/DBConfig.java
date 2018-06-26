package com.arthas.study.springbootdubbodemo.api.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库相关配置
 * @author : lieying
 * date : 2018/6/26 19:06
 */
@Configuration
public class DBConfig {

	@Bean
	public DataSource tencentDS(){
		HikariDataSource ds = new HikariDataSource();
		return ds;
	}

}
