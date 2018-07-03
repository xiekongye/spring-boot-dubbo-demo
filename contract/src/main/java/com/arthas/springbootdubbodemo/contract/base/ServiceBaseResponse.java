package com.arthas.springbootdubbodemo.contract.base;

/**
 * 服务相关的响应基类
 * @author : lieying
 * date : 2018/6/27 16:20
 */
public class ServiceBaseResponse extends BaseResponse {

	private int resultCode;

	private String resultMsg;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
