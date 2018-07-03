package com.arthas.study.springbootdubbodemo.api.serviceprocessor;

import com.arthas.springbootdubbodemo.common.components.RPCContext;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseRequest;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseResponse;
import com.arthas.study.springbootdubbodemo.model.errorcode.rpc.RPCErrorCode;
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

		TResponse response = null;

		try {
			//获取Response的真实类型
			Class<TResponse> responseClass = (Class<TResponse>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

			//Response必须要有默认构造函数
			response = responseClass.newInstance();

			validateRequest(request);

			response = process(request);

			postProcessResponse(response,RPCErrorCode.SUCCESS);

		} catch (IllegalArgumentException e){
			postProcessResponse(response,RPCErrorCode.PARAM_ILLEGAL);
		} catch (Exception e){
			postProcessResponse(response,RPCErrorCode.SYSTEM_ERROR);
		} catch (Throwable e){
			postProcessResponse(response,RPCErrorCode.FATAL_ERROR);
		} finally {
			stopWatch.stop();
			RPCContext.clear();
		}

		return response;
	}

	/**
	 * 后处理Response
	 * @param response 响应
	 * @param rpcErrorCode RPC错误码
	 * */
	private void postProcessResponse(TResponse response, RPCErrorCode rpcErrorCode){
		response.setTimestamp(Calendar.getInstance());
		response.setResultCode(rpcErrorCode.errorCode());
		response.setResultMsg(rpcErrorCode.errorMsg());
	}

}
