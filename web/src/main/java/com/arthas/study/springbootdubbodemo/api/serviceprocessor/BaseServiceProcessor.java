package com.arthas.study.springbootdubbodemo.api.serviceprocessor;

import com.arthas.springbootdubbodemo.contract.base.BaseErrorCode;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseRequest;
import com.arthas.springbootdubbodemo.contract.base.ServiceBaseResponse;
import com.google.common.base.Stopwatch;

import java.lang.reflect.ParameterizedType;

/**
 * @author : lieying
 * date : 2018/6/27 16:38
 */
public abstract class BaseServiceProcessor<TRequest extends ServiceBaseRequest, TResponse extends ServiceBaseResponse> {

	protected abstract boolean validateRequest(TRequest request) throws IllegalArgumentException;

	protected abstract TResponse process(TRequest request);

	public TResponse processSoa(TRequest request){

		Stopwatch stopwatch = Stopwatch.createStarted();

		TResponse response = null;

		try {
			//获取Response的真实类型
			Class<TResponse> responseClass = (Class<TResponse>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

			validateRequest(request);

			response = process(request);

			postProcessResponse(response,BaseErrorCode.SUCCESS.errorCode(),BaseErrorCode.SUCCESS.errorMsg());

		} catch (IllegalArgumentException e){
			postProcessResponse(response,BaseErrorCode.PARAM_ILLEGAL.errorCode(),BaseErrorCode.PARAM_ILLEGAL.errorMsg());
		} catch (Exception e){
			postProcessResponse(response,BaseErrorCode.SYSTEM_ERROR.errorCode(),BaseErrorCode.SYSTEM_ERROR.errorMsg());
		} catch (Throwable e){
			postProcessResponse(response,BaseErrorCode.FATAL_ERROR.errorCode(),BaseErrorCode.FATAL_ERROR.errorMsg());
		} finally {
			stopwatch.stop();
		}

		return response;
	}

	/**
	 * 后处理Response
	 * */
	private void postProcessResponse(TResponse response,int errorCode,String errorMsg){

	}

}
