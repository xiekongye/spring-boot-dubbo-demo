package com.arthas.study.springbootdubbodemo.model.log;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * @author : lieying
 * date : 2018/7/4 20:20
 */
public enum IndexedLogTag implements BaseEnum {

	LOG_LEVEL(0,"日志级别"),

	UID(1,"UID"),

	ORDER_SN(2,"订单编号"),

	ORDER_ID(3,"订单号"),

	GOODS_ID(4,"商品编号"),

	REVIEW_ID(5,"评论ID");

	private final int code;

	private final String name;

	IndexedLogTag(Integer code,String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String desc() {
		return this.name;
	}
}
