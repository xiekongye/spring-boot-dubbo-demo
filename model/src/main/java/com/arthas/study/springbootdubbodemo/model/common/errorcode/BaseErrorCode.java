package com.arthas.study.springbootdubbodemo.model.common.errorcode;

/**
 * ErrorCode接口
 * @author : lieying
 * date : 2018/7/3 15:06
 */
public interface BaseErrorCode {

	/**
	 * 错误码
	 * */
	int errorCode();

	/**
	 * 错误描述
	 * */
	String errorMsg();

}
