package com.arthas.study.springbootdubbodemo.model.annotations;

import java.lang.annotation.*;

/**
 * Dubbo服务注解,用于Dubbo服务导出为Http服务相关的功能
 * @author : lieying
 * date : 2018/6/29 19:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DubboService {

	/**
	 * 服务名称（不填使用类名，忽略大小写）
	 * */
	String serviceName() default "";

	/**
	 * 是否导出Http服务（默认导出）
	 * */
	boolean exportHttp() default true;

}
