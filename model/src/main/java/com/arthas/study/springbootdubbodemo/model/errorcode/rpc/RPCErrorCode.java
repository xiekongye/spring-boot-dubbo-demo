package com.arthas.study.springbootdubbodemo.model.errorcode.rpc;

import com.arthas.study.springbootdubbodemo.model.common.errorcode.BaseErrorCode;

/**
 * 对外RPC错误码
 * @author : lieying
 * date : 2018/7/3 15:20
 */
public enum RPCErrorCode implements BaseErrorCode {

	SUCCESS(0,"调用成功"),

	PARAM_ILLEGAL(100,"参数不合法"),

	SYSTEM_ERROR(200,"系统错误"),

	FATAL_ERROR(300,"系统严重故障");

	private final int errorCode;

	private final String errorMsg;

	RPCErrorCode(Integer errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 错误码
	 */
	@Override
	public int errorCode() {
		return this.errorCode;
	}

	/**
	 * 错误描述
	 */
	@Override
	public String errorMsg() {
		return this.errorMsg;
	}
}
