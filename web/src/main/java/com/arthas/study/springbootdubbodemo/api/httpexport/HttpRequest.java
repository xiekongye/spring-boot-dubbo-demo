package com.arthas.study.springbootdubbodemo.api.httpexport;

import lombok.Data;

/**
 * @author : lieying
 * date : 2018/6/29 19:47
 */
@Data
public final class HttpRequest {

	private String param;

	private String service;

	private String method;

}
