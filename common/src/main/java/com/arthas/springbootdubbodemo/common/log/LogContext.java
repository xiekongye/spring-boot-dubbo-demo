package com.arthas.springbootdubbodemo.common.log;

import com.arthas.study.springbootdubbodemo.model.log.IndexedLogTag;
import com.arthas.study.springbootdubbodemo.model.log.StoredLogTag;

import java.util.HashMap;
import java.util.Map;

/**
 * Log上下文（非线程安全）
 * @author : lieying
 * date : 2018/7/3 21:47
 */
public class LogContext {

	//region --成员变量--
	private StringBuilder requestContentSB = new StringBuilder();

	private StringBuilder responseContentSB = new StringBuilder();

	private Map<IndexedLogTag,String> indexedLogTags = new HashMap<>();

	private Map<StoredLogTag,String> storedLogTags = new HashMap<>();

	//endregion

	//region --实例方法--
	/**
	 * 追加请求文本
	 * @param requestContent 请求文本
	 * */
	public void appendRequestContent(String requestContent){
		requestContentSB.append(String.format("%s\n",requestContent));
	}

	/**
	 * 追加响应文本
	 * @param responseContent 响应文本
	 * */
	public void appendResponseContent(String responseContent){
		responseContentSB.append(String.format("%s\n",responseContent));
	}

	public void addIndexedTag(IndexedLogTag tagKey, String tagValue){
		if (tagValue == null)tagValue = "";
		indexedLogTags.put(tagKey,tagValue);
	}

	public void addStoredTag(StoredLogTag tagKey, String tagValue){
		if (tagValue == null)tagValue = "";

		if (tagKey == StoredLogTag.REQUEST_CONTENT){
			requestContentSB.append(String.format("%s\n",tagValue));
		}else if (tagKey == StoredLogTag.RESPONSE_CONTENT){
			responseContentSB.append(String.format("%s\n",tagValue));
		}else {
			storedLogTags.put(tagKey,tagValue);
		}
	}

	/**
	 * 获取索引Tag
	 * */
	public Map<IndexedLogTag,String> getIndexedLogTags(){
		return indexedLogTags;
	}

	/**
	 * 获取非索引Tag
	 * */
	public Map<StoredLogTag,String> getStoredLogTags(){
		String requestContentKey = StoredLogTag.REQUEST_CONTENT.toString();
		String responseContentKey = StoredLogTag.RESPONSE_CONTENT.toString();
		if (storedLogTags.containsKey(StoredLogTag.REQUEST_CONTENT)){
			for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
				if (requestContentKey.equalsIgnoreCase(entry.getKey().toString())){
					entry.setValue(entry.getValue().concat("\n" + requestContentSB.toString()));
				}
			}
		}else {
			storedLogTags.put(StoredLogTag.REQUEST_CONTENT,requestContentSB.toString());
		}

		if (storedLogTags.containsKey(StoredLogTag.RESPONSE_CONTENT)){
			for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
				if (responseContentKey.equalsIgnoreCase(entry.getKey().toString())){
					entry.setValue(entry.getValue().concat("\n" + responseContentSB.toString()));
				}
			}
		}else {
			storedLogTags.put(StoredLogTag.RESPONSE_CONTENT,responseContentSB.toString());
		}

		return storedLogTags;
	}
	//endregion

	private LogContext(){

	}

	/**
	 * LogContextBuilder
	 * */
	public static class Builder {

		public static LogContext create(){
			return new LogContext();
		}

	}

}
