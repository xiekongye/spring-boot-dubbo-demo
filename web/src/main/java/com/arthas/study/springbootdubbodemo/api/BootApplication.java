package com.arthas.study.springbootdubbodemo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 应用启动类
 * @author : lieying
 * date : 2018/6/26 18:20
 */
@SpringBootApplication(scanBasePackages = {"com.arthas.study.springbootdubbodemo.*"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BootApplication {

	public static void main(String[] args) {
 		SpringApplication.run(BootApplication.class,args);
	}
}
