package com.arthas.springbootdubbodemo.common.utils;

import com.arthas.study.springbootdubbodemo.model.annotations.RPCServiceProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RPC服务工具类
 * @author : lieying
 * date : 2018/7/3 15:41
 */
public class RPCUtils {

	private final static Map<Class<?>,RPCServiceProcessor> SERVICE_PROCESSOR_MAP = new ConcurrentHashMap<>(1<<7);

	/**
	 * 获取RPCProcessor上的注解信息
	 * */
	public static <T> RPCServiceProcessor getRPCServiceProcessor(Class<T> processorClazz){
		if (processorClazz == null){
			return null;
		}

		RPCServiceProcessor processorMeta = SERVICE_PROCESSOR_MAP.get(processorClazz);
		if (processorMeta != null){
			return processorMeta;
		}

		processorMeta = processorClazz.getAnnotation(RPCServiceProcessor.class);
		if (processorMeta != null){
			SERVICE_PROCESSOR_MAP.putIfAbsent(processorClazz,processorMeta);
		}

		return processorMeta;
	}

}
