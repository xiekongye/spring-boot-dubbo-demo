package com.arthas.springbootdubbodemo.common.utils;

import com.google.gson.Gson;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/4 21:56
 */
public class TestDynamicClassGenerate {

	@Test
	public void test(){

		Map<String,Type> fields = new HashMap<>();
		fields.put("uid",String.class);

		Class clazz = ClassUtils.generateDynamicClass("Test",fields);

		try {
			Object instance = clazz.newInstance();

			System.out.println(new Gson().toJson(instance));

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
