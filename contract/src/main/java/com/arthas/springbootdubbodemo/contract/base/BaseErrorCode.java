package com.arthas.springbootdubbodemo.contract.base;

/**
 * @author : lieying
 * date : 2018/6/27 16:44
 */
public enum BaseErrorCode implements BaseEnum {

	SUCCESS(0,"成功"),

	PARAM_ILLEGAL(1,"参数不合法"),

	SYSTEM_ERROR(2,"系统错误"),

	FATAL_ERROR(3,"系统严重故障");

	private final int errorCode;

	private final String errorMsg;

	BaseErrorCode(int errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public int errorCode() {
		return this.errorCode;
	}

	@Override
	public String errorMsg() {
		return this.errorMsg;
	}
}
