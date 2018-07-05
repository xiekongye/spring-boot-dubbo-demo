package com.arthas.study.springbootdubbodemo.api.serviceprocessor;

import com.arthas.springbootdubbodemo.common.components.RPCContext;
import com.arthas.springbootdubbodemo.common.log.LogHelper;
import com.arthas.springbootdubbodemo.common.utils.RPCUtils;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseRequest;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseResponse;
import com.arthas.study.springbootdubbodemo.model.annotations.RPCServiceProcessor;
import com.arthas.study.springbootdubbodemo.model.errorcode.rpc.RPCErrorCode;
import com.arthas.study.springbootdubbodemo.model.log.IndexedLogTag;
import com.arthas.study.springbootdubbodemo.model.log.LogLevel;
import com.arthas.study.springbootdubbodemo.model.log.StoredLogTag;
import org.springframework.util.StopWatch;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;

/**
 * 服务处理类基类
 * @author : lieying
 * date : 2018/6/27 16:38
 */
public abstract class BaseServiceProcessor<TRequest extends ServiceBaseRequest, TResponse extends ServiceBaseResponse> {

	protected abstract boolean validateRequest(TRequest request) throws IllegalArgumentException;

	protected abstract TResponse process(TRequest request);

	public TResponse processSoa(TRequest request){

		RPCContext.clear();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		//获取Processor注解信息
		RPCServiceProcessor processorMeta = RPCUtils.getRPCServiceProcessor(this.getClass());

		initLog(processorMeta,request);

		TResponse response = null;

		try {

			validateRequest(request);

			response = process(request);

			response = postProcessResponse(response,RPCErrorCode.SUCCESS);

		} catch (IllegalArgumentException e){
			postProcessResponse(response,RPCErrorCode.PARAM_ILLEGAL);
		} catch (Exception e){
			postProcessResponse(response,RPCErrorCode.SYSTEM_ERROR);
		} catch (Throwable e){
			postProcessResponse(response,RPCErrorCode.FATAL_ERROR);
		} finally {
			stopWatch.stop();

			postProcessLog(response);

			//记录日志
			LogHelper.doLog();

			RPCContext.clear();
		}

		return response;
	}

	/**
	 * 初始化Log
	 * @param processorMeta Processor信息
	 * @param request 请求中的信息
	 * */
	private void initLog(RPCServiceProcessor processorMeta,TRequest request){

		//indexed tags
		LogHelper.addIndexedLogTag(IndexedLogTag.LOG_LEVEL,LogLevel.INFO.toString());
		LogHelper.addIndexedLogTag(IndexedLogTag.RPC_PROCESSOR,processorMeta.value());
		LogHelper.addIndexedLogTag(IndexedLogTag.RPC_PROCESSOR_NAME,processorMeta.name());
		LogHelper.addIndexedLogTag(IndexedLogTag.RPC_CLIENT_NAME,request.getAppName());
		LogHelper.addIndexedLogTag(IndexedLogTag.RPC_CLIENT_IP,request.getClientIp());
		LogHelper.addIndexedLogTag(IndexedLogTag.USER_AGENT,request.getUserAgent());

		//stored tags
		LogHelper.addStoredLogTag(StoredLogTag.RPC_PROCESSOR_DESC,processorMeta.desc());
	}

	/**
	 * 后处理Log
	 * */
	private void postProcessLog(TResponse response){

	}

	/**
	 * 后处理Response
	 * @param response 响应
	 * @param rpcErrorCode RPC错误码
	 * */
	private TResponse postProcessResponse(TResponse response, RPCErrorCode rpcErrorCode){

		if (response == null){

			//获取Response的真实类型
			Class<TResponse> responseClass = (Class<TResponse>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

			//Response必须要有默认构造函数
			try {
				response = responseClass.newInstance();

				response.setTimestamp(Calendar.getInstance());
				response.setResultCode(rpcErrorCode.errorCode());
				response.setResultMsg(rpcErrorCode.errorMsg());
			} catch (InstantiationException | IllegalAccessException e) {
				LogHelper.addIndexedLogTag(IndexedLogTag.LOG_LEVEL,LogLevel.ERROR.toString());
				LogHelper.appendResponseContent(String.format("Response类%s必须有无餐构造函数",responseClass.getName()));
			}
		}

		return response;
	}

}
