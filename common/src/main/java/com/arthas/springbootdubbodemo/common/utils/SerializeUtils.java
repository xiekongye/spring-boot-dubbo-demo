package com.arthas.springbootdubbodemo.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author : lieying
 * date : 2018/6/29 19:45
 */
public class SerializeUtils {

	private final static Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

	public static <T> String toJson(T obj){
		return GSON.toJson(obj);
	}

}
