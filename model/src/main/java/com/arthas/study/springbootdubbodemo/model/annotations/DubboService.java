package com.arthas.study.springbootdubbodemo.model.annotations;

import java.lang.annotation.*;

/**
 * Dubbo服务注解
 * @author : lieying
 * date : 2018/6/29 19:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DubboService {

	String serviceName() default "";

	/**
	 * 是否导出Http服务（默认：否）
	 * */
	boolean exportHttp() default true;

}
