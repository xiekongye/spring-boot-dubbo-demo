package com.arthas.springbootdubbodemo.common.utils;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/4 21:06
 */
public class ClassUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

	/**
	 * 动态生成一个Class
	 * */
	public static Class<?> generateDynamicClass(String simpleClassName, Map<String,Type> fields,boolean withInit){

		ClassPool classPool = ClassPool.getDefault();

		String classFullName = generateFullClassName(simpleClassName);

		CtClass ctClass = classPool.makeClass(classFullName);

		try {
			if (!CollectionUtils.isEmpty(fields)){
				for (Map.Entry<String,Type> field : fields.entrySet()){
					CtField ctField = new CtField(classPool.getCtClass(field.getValue().getTypeName()),field.getKey(),ctClass);
					ctField.setModifiers(Modifier.PUBLIC);
					ctClass.addField(ctField);
				}
			}

			Class clazz = ctClass.toClass();

			//加载类
			Class.forName(clazz.getName());

			return clazz;

		} catch (Exception e){
			LOGGER.warn(e.getMessage());
		}

		return null;
	}

	private static String generateFullClassName(String simpleClassName){
		return String.format("%s.%s",ClassUtils.class.getPackage().getName(),simpleClassName);
	}
}
