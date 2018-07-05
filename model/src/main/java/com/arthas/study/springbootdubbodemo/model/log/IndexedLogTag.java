package com.arthas.study.springbootdubbodemo.model.log;

import com.arthas.study.springbootdubbodemo.model.common.enums.BaseEnum;

/**
 * 可索引日志Tag
 * @author : lieying
 * date : 2018/7/4 20:20
 */
public enum IndexedLogTag implements BaseEnum {

	LOG_LEVEL(0,"日志级别"),

	UID(1,"UID"),

	ORDER_SN(2,"订单编号"),

	ORDER_ID(3,"订单号"),

	GOODS_ID(4,"商品编号"),

	REVIEW_ID(5,"评论ID"),

	RPC_CLIENT_NAME(100,"RPC调用方名称"),

	RPC_CLIENT_IP(101,"RPC调用方IP"),

	USER_AGENT(102,"RPC调用方User-Agent"),

	RPC_PROCESSOR(103,"RPC Processor"),

	RPC_PROCESSOR_NAME(104,"RPC Processor名称");

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
