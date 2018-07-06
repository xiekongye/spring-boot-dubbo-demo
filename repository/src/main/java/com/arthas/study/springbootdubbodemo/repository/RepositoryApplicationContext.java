package com.arthas.study.springbootdubbodemo.repository;

import com.arthas.springbootdubbodemo.common.CommonApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : lieying
 * date : 2018/7/6 14:28
 */
@Configuration
@ComponentScan(value = {"com.arthas.study.springbootdubbodemo.repository"})
@EnableTransactionManagement(proxyTargetClass = true,mode = AdviceMode.ASPECTJ)
@Import(value = {CommonApplicationContext.class})
public class RepositoryApplicationContext {
}
