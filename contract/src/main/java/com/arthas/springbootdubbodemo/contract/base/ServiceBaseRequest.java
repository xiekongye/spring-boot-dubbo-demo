package com.arthas.springbootdubbodemo.contract.base;

/**
 * 服务相关的请求基类
 * @author : lieying
 * date : 2018/6/27 16:20
 */
public class ServiceBaseRequest extends BaseRequest {

	/**
	 * 调用方应用名
	 * */
	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
