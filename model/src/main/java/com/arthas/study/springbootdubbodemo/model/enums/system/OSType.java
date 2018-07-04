package com.arthas.study.springbootdubbodemo.model.enums.system;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * @author : lieying
 * date : 2018/7/4 17:03
 */
public enum OSType implements BaseEnum {

	UNKNOWN(-1,"未知"),

	WINDOWS(0,"Windows系统"),

	OS_X(100,"Max_OS_X系统"),

	UNIX(200,"Unix系统"),

	SOLARIS(300,"Solaris系统");

	private final int type;

	private final String name;

	OSType(Integer type,String name){
		this.type = type;
		this.name = name;
	}

	@Override
	public int code() {
		return this.type;
	}

	@Override
	public String desc() {
		return this.name;
	}
}
