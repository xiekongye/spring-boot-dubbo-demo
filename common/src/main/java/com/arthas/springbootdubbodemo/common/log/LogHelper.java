package com.arthas.springbootdubbodemo.common.log;

import com.arthas.springbootdubbodemo.common.components.RPCContext;
import com.arthas.springbootdubbodemo.common.utils.CheckerUtils;
import com.arthas.springbootdubbodemo.common.utils.SerializeUtils;
import com.arthas.study.springbootdubbodemo.model.log.IndexedLogTag;
import com.arthas.study.springbootdubbodemo.model.log.StoredLogTag;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/5 19:04
 */
public class LogHelper {

	/**
	 * 新增一条Indexed Tag
	 * @param logTag tag名称
	 * @param tagVal tag值
	 * */
	public static void addIndexedLogTag(IndexedLogTag logTag, String tagVal){
		RPCContext.getLogContext().addIndexedTag(logTag,tagVal);
	}

	/**
	 * 新增一条Stored Tag
	 * @param logTag tag名称
	 * @param tagVal tag值
	 * */
	public static void addStoredLogTag(StoredLogTag logTag, String tagVal){
		RPCContext.getLogContext().addStoredTag(logTag,tagVal);
	}

	/**
	 * 追加请求Request文本
	 * @param requestContent 请求文本信息
	 * */
	public static void appendRequestContent(String requestContent){
		RPCContext.getLogContext().appendRequestContent(requestContent);
	}

	/**
	 * 追加响应Response文本
	 * @param responseContent 响应文本信息
	 * */
	public static void appendResponseContent(String responseContent){
		RPCContext.getLogContext().appendResponseContent(responseContent);
	}

	/**
	 * 记录日志
	 * */
	public static void doLog(){
		CheckerUtils.checkWithAction(checkLogIllegal(),() -> RPCContext.getContextLogger().info(formatLogMsg(RPCContext.getLogContext().getIndexedLogTags(),RPCContext.getLogContext().getStoredLogTags())));
	}

	/**
	 * 格式化Log(包括将Tag小写，将非索引Tag放到日志下方等)
	 * */
	private static String formatLogMsg(Map<IndexedLogTag, String> indexedLogTags, Map<StoredLogTag, String> storedLogTags){

		Map<String,String> formatTags = new HashMap<>(1<<5);

		if (!CollectionUtils.isEmpty(indexedLogTags)){
			for (Map.Entry<IndexedLogTag,String> entry : indexedLogTags.entrySet()){
				formatTags.put(entry.getKey().toString().toLowerCase(),entry.getValue());
			}
		}

		if (!CollectionUtils.isEmpty(storedLogTags)){
			for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
				formatTags.put(entry.getKey().toString().toLowerCase(),entry.getValue());
			}
		}
		return SerializeUtils.toJson(formatTags);
	}

	private static boolean checkLogIllegal(){
		return RPCContext.getLogContext() != null && (!CollectionUtils.isEmpty(RPCContext.getLogContext().getIndexedLogTags()) || !CollectionUtils.isEmpty(RPCContext.getLogContext().getStoredLogTags()));
	}
}
