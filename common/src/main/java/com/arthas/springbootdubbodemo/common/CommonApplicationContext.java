package com.arthas.springbootdubbodemo.common;

import com.arthas.study.springbootdubbodemo.model.ModelApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : lieying
 * date : 2018/7/6 14:29
 */
@Configuration
@ComponentScan(value = {"com.arthas.springbootdubbodemo.common"})
@Import(value = {ModelApplicationContext.class})
public class CommonApplicationContext {
}
