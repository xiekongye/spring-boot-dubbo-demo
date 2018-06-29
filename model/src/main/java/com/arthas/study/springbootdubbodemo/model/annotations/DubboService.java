package com.arthas.study.springbootdubbodemo.model.annotations;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author : lieying
 * date : 2018/6/29 19:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
public @interface DubboService {

	String serviceName() default "";

	/**
	 * 是否导出Http服务（默认：否）
	 * */
	boolean exportHttp() default false;

	@AliasFor(annotation = Service.class,attribute = "interfaceClass")
	Class<?> interfaceClass() default void.class;
}
