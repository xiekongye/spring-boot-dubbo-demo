package com.arthas.study.springbootdubbodemo.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Dubbo provider配置类
 * @author : lieying
 * date : 2018/7/9 15:39
 */
@Configuration
@ImportResource(value = {"classpath:META-INF/dubbo/dubbo-provider.xml"})
//@PropertySource(value = {"classpath:META-INF/dubbo/dubbo-provider.properties"})
public class DubboProviderConfig {
}
