package com.arthas.study.springbootdubbodemo.model.log;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * @author : lieying
 * date : 2018/7/4 20:27
 */
public enum StoredLogTag implements BaseEnum {

	REQUEST_CONTENT(0,"请求体"),

	RESPONSE_CONTENT(1,"响应体");

	private final int code;

	private final String name;

	StoredLogTag(Integer code,String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public int code() {
		return 0;
	}

	@Override
	public String desc() {
		return null;
	}
}
