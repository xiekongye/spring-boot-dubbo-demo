package com.arthas.springbootdubbodemo.common.log;

import com.arthas.study.springbootdubbodemo.model.log.IndexedLogTag;
import com.arthas.study.springbootdubbodemo.model.log.StoredLogTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : lieying
 * date : 2018/7/4 20:32
 */
public class SimpleLoggerImpl implements ILogger {

	private final static Logger LOGGER = LoggerFactory.getLogger("GlobalLogger");

	@Override
	public void logTags(Map<IndexedLogTag, String> indexedLogTags, Map<StoredLogTag, String> storedLogTags) {
		Map<String,String> indexedTagsStr = new HashMap<>();
		Map<String,String> storedTagsStr = new HashMap<>();

		if (indexedLogTags != null && !indexedLogTags.isEmpty()){
			for (Map.Entry<IndexedLogTag,String> entry : indexedLogTags.entrySet()){
				indexedTagsStr.put(entry.getKey().toString().toLowerCase(),entry.getValue());
			}
		}

		if (storedLogTags != null && !storedLogTags.isEmpty()){
			for (Map.Entry<StoredLogTag,String> entry : storedLogTags.entrySet()){
				storedTagsStr.put(entry.getKey().toString().toLowerCase(),entry.getValue());
			}
		}

		//记录Clog
		LOGGER.info("", formatLogMsg(storedTagsStr),indexedTagsStr);

		//记录ES
		//Cat.logTags(LogConst.Elk.SCENRIO,indexedTagsStr,storedTagsStr);
	}

	/**
	 * 格式化日志
	 * @param storedLogTags 非索引日志Tag
	 * */
	private String formatLogMsg(Map<String,String> storedLogTags){
		StringBuilder logMsg = new StringBuilder();

		if (storedLogTags != null && !storedLogTags.isEmpty()){
			for (Map.Entry<String,String> entry : storedLogTags.entrySet()){
				//logMsg.append(String.format("%s\n%s:\n%s\n", LOG_MSG_SEPARATOR,entry.getKey(),entry.getValue()));
			}
		}

		return logMsg.toString();
	}

	private void initLog(){
		for (IndexedLogTag tag : IndexedLogTag.values()){

		}
	}
}
