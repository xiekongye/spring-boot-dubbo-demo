package com.arthas.study.springbootdubbodemo.model.log;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * @author : lieying
 * date : 2018/7/4 20:44
 */
public enum LogLevel implements BaseEnum {

	DEBUG(0,"Debug级别"),

	INFO(1,"Info级别"),

	WARN(2,"警告级别"),

	ERROR(3,"错误级别"),

	FATAL(4,"故障级别");

	private final int code;

	private final String desc;

	LogLevel(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}


	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String desc() {
		return this.desc;
	}
}
