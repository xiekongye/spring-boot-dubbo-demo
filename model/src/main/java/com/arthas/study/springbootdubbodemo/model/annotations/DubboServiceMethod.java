package com.arthas.study.springbootdubbodemo.model.annotations;

import java.lang.annotation.*;

/**
 * @author : lieying
 * date : 2018/6/29 19:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DubboServiceMethod {

	String methodName() default "";

	boolean export() default true;

}
