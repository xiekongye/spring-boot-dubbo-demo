package com.arthas.springbootdubbodemo.common.utils;

import com.arthas.study.springbootdubbodemo.model.enums.system.OSType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 公共帮助类
 * @author : lieying
 * date : 2018/7/1 14:21
 */
public class CommonUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * 获取操作系统名称
	 * */
	public static String getOsName(){
		return System.getProperty("os.name");
	}

	/**
	 * 判断Map中是否存在指定的Key
	 *
	 * @param mapSet     源Map
	 * @param key        指定的Key
	 * @param ignoreCase 是否忽略大小写
	 * @return 是否存在
	 */
	public static <T> boolean containsKey(Map<String, T> mapSet, String key, Boolean ignoreCase) {
		if (mapSet == null || mapSet.isEmpty()) {
			return false;
		}

		boolean exist = false;

		if (key == null) {
			if (mapSet.containsKey(null)) {
				exist = true;
			}
		} else {
			for (Map.Entry<String, T> entry : mapSet.entrySet()) {
				if (ignoreCase) {
					if (key.equalsIgnoreCase(entry.getKey())) {
						exist = true;
						break;
					}
				} else {
					if (key.equals(entry.getKey())) {
						exist = true;
						break;
					}
				}
			}
		}
		return exist;
	}

	/**
	 * 获取指定Key的数据
	 */
	public static <T> T getValue(Map<String, T> mapSet, String key, Boolean ignoreCase) {
		if (mapSet == null || mapSet.isEmpty()) {
			return null;
		}

		for (Map.Entry<String, T> entry : mapSet.entrySet()) {
			if (key == null) {
				if (entry.getKey() == null) {
					return entry.getValue();
				}
			} else {
				if (ignoreCase) {
					if (key.equalsIgnoreCase(entry.getKey())) {
						return entry.getValue();
					}
				} else {
					if (key.equals(entry.getKey())) {
						return entry.getValue();
					}
				}
			}
		}

		return null;
	}

	/**
	 * List转换为数组
	 * @param origList 原始List
	 * @param elemType List中元素的类型
	 * @return 指定元素类型的数组
	 * */
	public static <T> T[] convertListToArray(List<T> origList, Class<T> elemType){
		if (origList == null){
			return null;
		}

		int size = origList.size();
		T[] array = (T[]) Array.newInstance(elemType,size);
		for (int i = 0;i < size;i++){
			array[i] = (T) origList.get(i);
		}
		return array;
	}

	/**
	 * 筛选列表元素
	 * @param origList 原始列表
	 * @param condition 筛选条件
	 * */
	public static <T> List<T> selectList(List<T> origList, @NotNull Predicate<T> condition){
		if (origList == null || origList.size() == 0){
			return null;
		}

		return origList.stream().filter(condition).collect(Collectors.toList());
	}

	/**
	 * 重新设置Null的Field为空字符串
	 * @param obj 需要重置属性的对象
	 * */
	public static <T> void resetNullField(T obj){
		if (obj != null && obj.getClass() != null && obj.getClass().getDeclaredMethods() != null && obj.getClass().getDeclaredMethods().length > 0){

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields){
				try {
					if (!field.isAccessible()){
						field.setAccessible(true);
					}
					if (String.class.equals(field.getType()) && field.get(obj) == null){
						field.set(obj,"");
					}
				} catch (Exception e) {
					LOGGER.warn(e.getMessage());
				}

			}

		}
	}

	/**
	 * 转换List
	 *
	 * @param origList 需要转换的List
	 * @param func     列表的每个对象的转换方法
	 * @return 根据func指定的单个对象转换方法转换List
	 */
	public static <T1, T2> List<T2> convertList(List<T1> origList, Function<T1, T2> func) {

		if (origList == null || func == null) {
			return null;
		}

		List<T2> result = new ArrayList<>();
		origList.forEach(origVal -> {
			result.add(func.apply(origVal));
		});

		return result;
	}

	/**
	 * 获取操作系统名称
	 * */
	public static OSType getOsType(){
		final String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")){
			return OSType.WINDOWS;
		}
		if (osName.contains("mac")){
			return OSType.OS_X;
		}
		if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")){
			return OSType.UNIX;
		}
		if (osName.contains("sunos")){
			return OSType.SOLARIS;
		}
		return OSType.UNKNOWN;
	}
}
