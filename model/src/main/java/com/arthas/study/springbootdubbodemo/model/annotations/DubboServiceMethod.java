package com.arthas.study.springbootdubbodemo.model.annotations;

import java.lang.annotation.*;

/**
 * Dubbo服务的方法注解，用于导出Dubbo服务到Http服务的控制
 * @author : lieying
 * date : 2018/6/29 19:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DubboServiceMethod {

	/**
	 * 方法名称（不填使用方法名，忽略大小写）
	 * */
	String methodName() default "";

	/**
	 * 是否导出服务（默认导出）
	 * */
	boolean export() default true;

}
