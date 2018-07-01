package com.arthas.study.springbootdubbodemo.api.httpexport;

import lombok.Data;

/**
 * Http请求
 * @author : lieying
 * date : 2018/6/29 19:47
 */
@Data
public class HttpRequest {

	/**
	 * 请求参数
	 * */
	private String param;

	/**
	 * 服务名
	 * */
	private String service;

	/**
	 * 方法名
	 * */
	private String method;

}
