package com.arthas.springbootdubbodemo.common.components;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author : lieying
 * date : 2018/6/27 17:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Service
public @interface ExportService {

	@AliasFor(annotation = Component.class,attribute = "value")
	String value() default "";

	/**
	 * 是否导出Http服务(因为是测试用，默认导出)
	 * */
	boolean exportHttp() default true;

	/**
	 * Http服务前缀
	 * */
	String[] httpPrefix() default {};

	/**
	 * 是否导出Dubbo服务
	 * */
	boolean exportDubbo() default true;

	@AliasFor(annotation = Service.class,attribute = "version")
	String version() default "";

}
