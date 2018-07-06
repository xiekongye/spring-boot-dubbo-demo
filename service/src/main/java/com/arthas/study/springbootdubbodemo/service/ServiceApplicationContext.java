package com.arthas.study.springbootdubbodemo.service;

import com.arthas.study.springbootdubbodemo.repository.RepositoryApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : lieying
 * date : 2018/7/6 14:25
 */
@Configuration
@ComponentScan(value = {"com.arthas.study.springbootdubbodemo.service"})
@Import(value = {RepositoryApplicationContext.class})
public class ServiceApplicationContext {
}
