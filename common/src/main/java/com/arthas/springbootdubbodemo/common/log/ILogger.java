package com.arthas.springbootdubbodemo.common.log;

import com.arthas.study.springbootdubbodemo.model.log.IndexedLogTag;
import com.arthas.study.springbootdubbodemo.model.log.StoredLogTag;

import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/4 20:31
 */
public interface ILogger {

	/**
	 * 记录日志
	 * @param indexedLogTags 可索引Tag
	 * @param storedLogTags 非索引Tag
	 * */
	void logTags(Map<IndexedLogTag,String> indexedLogTags, Map<StoredLogTag,String> storedLogTags);

}
