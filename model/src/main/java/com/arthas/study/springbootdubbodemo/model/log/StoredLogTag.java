package com.arthas.study.springbootdubbodemo.model.log;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * 非索引日志Tag
 * @author : lieying
 * date : 2018/7/4 20:27
 */
public enum StoredLogTag implements BaseEnum {

	REQUEST_CONTENT(0,"请求体"),

	RESPONSE_CONTENT(1,"响应体"),

	RPC_PROCESSOR_DESC(2,"RPC Processor描述信息");

	private final int code;

	private final String name;

	StoredLogTag(Integer code,String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String desc() {
		return this.name;
	}
}
