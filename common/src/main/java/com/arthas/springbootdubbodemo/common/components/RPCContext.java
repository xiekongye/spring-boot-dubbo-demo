package com.arthas.springbootdubbodemo.common.components;

import com.arthas.springbootdubbodemo.common.log.LogContext;

import java.util.HashMap;
import java.util.Map;

/**
 * RPC上下文
 * @author : lieying
 * date : 2018/7/3 21:45
 */
public class RPCContext {

	private static final int CONTEXT_DEFAULT_SIZE = 1 << 6;

	//日志上下文
	private static final String LOG_CONTEXT = "LOG_CONTEXT";

	//上下文内容
	private static final ThreadLocal<Map<String,Object>> CONTEXT = ThreadLocal.withInitial(() -> new HashMap<>(CONTEXT_DEFAULT_SIZE));

	/**
	 * 获取当前线程日志上下文
	 * */
	public static LogContext getLogContext(){
		//todo:LogBuilder设置logcontext
		return (LogContext) CONTEXT.get().get(LOG_CONTEXT);
	}

	public static void clear(){
		CONTEXT.get().clear();
	}

}
