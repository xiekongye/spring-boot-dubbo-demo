package com.arthas.springbootdubbodemo.common.components;

import com.arthas.springbootdubbodemo.common.log.LogContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	//线程上下文日志记录器
	private static final String LOGGER = "LOGGER";

	//上下文内容
	private static final ThreadLocal<Map<String,Object>> CONTEXT = ThreadLocal.withInitial(() -> new HashMap<>(CONTEXT_DEFAULT_SIZE));

	/**
	 * 获取当前线程日志上下文
	 * */
	public static LogContext getLogContext(){

		if (!CONTEXT.get().containsKey(LOG_CONTEXT)){
			CONTEXT.get().put(LOG_CONTEXT,LogContext.Builder.create());
		}

		return (LogContext) CONTEXT.get().get(LOG_CONTEXT);
	}

	public static Logger getContextLogger(){
		if (!CONTEXT.get().containsKey(LOGGER)){
			CONTEXT.get().putIfAbsent(LOGGER,LoggerFactory.getLogger(getLoggerName()));
		}
		return (Logger)CONTEXT.get().get(LOGGER);
	}

	public static void clear(){
		CONTEXT.get().clear();
	}


	private static String getLoggerName(){
		return String.format("RPCContext-slf4j-logger-for-thread:%s",Thread.currentThread().getName());
	}
}
