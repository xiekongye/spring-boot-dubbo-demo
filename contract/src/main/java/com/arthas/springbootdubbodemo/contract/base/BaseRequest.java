package com.arthas.springbootdubbodemo.contract.base;

/**
 * 请求基类
 * @author : lieying
 * date : 2018/6/27 16:20
 */
public class BaseRequest {

	/**
	 * 客户端代理类型
	 * */
	private String userAgent;

	/**
	 * 客户端Ip
	 * */
	private String clientIp;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
