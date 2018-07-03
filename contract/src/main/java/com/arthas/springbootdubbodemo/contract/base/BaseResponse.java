package com.arthas.springbootdubbodemo.contract.base;

import java.util.Calendar;

/**
 * Response基类
 * @author : lieying
 * date : 2018/6/27 16:20
 */
public class BaseResponse {

	private Calendar timestamp;

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
}
