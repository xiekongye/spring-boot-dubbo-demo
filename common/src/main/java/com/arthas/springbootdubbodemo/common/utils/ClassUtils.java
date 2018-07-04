package com.arthas.springbootdubbodemo.common.utils;

import javassist.*;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/4 21:06
 */
public class ClassUtils {

	public static Class<?> generateDynamicClass(String simpleClassName, Map<String,Type> fields){

		ClassPool classPool = ClassPool.getDefault();

		CtClass ctClass = classPool.makeClass(generateFullClassName(simpleClassName));

		if (!CollectionUtils.isEmpty(fields)){
			for (Map.Entry<String,Type> field : fields.entrySet()){
				try {
					CtField ctField = new CtField(classPool.getCtClass(field.getValue().getTypeName()),field.getKey(),ctClass);
					ctField.setModifiers(Modifier.PUBLIC);
					ctClass.addField(ctField);
				} catch (CannotCompileException e) {
					e.printStackTrace();
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			Class destClass = ctClass.toClass();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String generateFullClassName(String simpleClassName){
		return String.format("%s.%s",ClassUtils.class.getPackage().getName(),simpleClassName);
	}
}
