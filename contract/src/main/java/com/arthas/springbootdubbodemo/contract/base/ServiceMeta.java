package com.arthas.springbootdubbodemo.contract.base;

import java.lang.annotation.*;

/**
 * 服务Meta信息
 * @author : lieying
 * date : 2018/6/27 16:22
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMeta {

	String serviceName();

	String serviceNamespace();

	String version() default "1.0.0";

}
