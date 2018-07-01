package com.arthas.springbootdubbodemo.common.utils;

import javax.validation.constraints.NotNull;

/**
 * @author : lieying
 * date : 2018/7/1 12:28
 */
public class CheckerUtils {

	/**
	 * 检查是否满足条件，满足则执行Action
	 * @param condition 条件
	 * @param action 满足条件需要执行的操作
	 * */
	public static void checkWithAction(boolean condition,@NotNull Action action){
		if (condition){
			action.execute();
		}
	}

	/**
	 * 检查是否满足条件，满足则执行Action
	 * @param condition 条件
	 * @param conditionMeetAction 满足条件需要执行的操作
	 * @param conditionNotMeetAction 条件不满足需要执行的操作
	 * */
	public static void checkWithAction(boolean condition,@NotNull Action conditionMeetAction,@NotNull Action conditionNotMeetAction){
		if (condition){
			conditionMeetAction.execute();
		} else {
			conditionNotMeetAction.execute();
		}
	}

	/**
	 * 检查是否满足条件,满足condition则抛出异常
	 * @param condition 是否满足的条件
	 * @param t 自定义异常
	 * @throws T 满足condition抛出的异常
	 * */
	public static <T extends Throwable> void checkWithThrowable(boolean condition,@NotNull T t) throws T{
		if (condition){
			throw t;
		}
	}

	/**
	 * 检查是否满足条件,满足condition则先执行回调方法，再抛出异常
	 * @param condition 是否满足的条件
	 * @param t 异常
	 * @param action 回调函数
	 * @throws T 满足condition抛出的异常
	 * */
	public static <T extends Throwable> void checkWithThrowable(boolean condition, T t, @NotNull Action action) throws T{
		if (condition){
			action.execute();
			throw t;
		}
	}

}
