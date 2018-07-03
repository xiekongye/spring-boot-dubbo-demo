package com.arthas.springbootdubbodemo.common.utils;

import javax.validation.constraints.NotNull;

/**
 * @author : lieying
 * date : 2018/7/3 19:32
 */
public class ThrowableUtils {

	private ThrowableUtils() throws IllegalAccessException{
		throw new IllegalAccessException("Can not be instantiated!");
	}

	private final static String THROWABLE_DESC_PATTERN = "%s,异常链:%s\n";

	/**
	 * 获取异常描述(按照[%s,异常链:%s]个数输出)
	 * @param ex 异常
	 * */
	public static <T extends Throwable> String getExceptionDesc(T ex){
		if (ex == null){
			return null;
		}

		return String.format(THROWABLE_DESC_PATTERN,
				org.apache.commons.lang3.exception.ExceptionUtils.getMessage(ex),
				org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex));
	}

	/**
	 * 抛出一个新异常，保持异常链
	 * @param throwable 需要抛出的新异常
	 * @param cause Cause
	 * */
	public static <T extends Throwable,C extends Throwable> T getThrowWithCause(@NotNull T throwable,@NotNull C cause){
		throwable.initCause(cause);
		return throwable;
	}

}
