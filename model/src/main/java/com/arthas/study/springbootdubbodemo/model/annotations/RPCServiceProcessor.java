package com.arthas.study.springbootdubbodemo.model.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * RPC服务处理器注解
 * @author : lieying
 * date : 2018/7/3 15:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface RPCServiceProcessor {

	/**
	 * Bean名称
	 * */
	@AliasFor(annotation = Component.class,attribute = "value")
	String value() default "";

	/**
	 * Processor名称
	 * */
	String name();

	/**
	 * Processor描述
	 * */
	String desc() default "";

}
