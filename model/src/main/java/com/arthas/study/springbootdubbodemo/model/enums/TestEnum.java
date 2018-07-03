package com.arthas.study.springbootdubbodemo.model.enums;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * @author : lieying
 * date : 2018/7/3 15:08
 */
public enum TestEnum implements BaseEnum {

	TEST_ENUM(0,"");

	private final int code;

	private final String desc;

	TestEnum(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 枚举Code
	 */
	@Override
	public int code() {
		return this.code;
	}

	/**
	 * 描述
	 */
	@Override
	public String desc() {
		return this.desc;
	}
}
