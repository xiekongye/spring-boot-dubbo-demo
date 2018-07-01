package com.arthas.study.springbootdubbodemo.api.httpexport;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.arthas.springbootdubbodemo.common.utils.SerializeUtils;
import com.arthas.study.springbootdubbodemo.model.annotations.DubboService;
import com.arthas.study.springbootdubbodemo.model.annotations.DubboServiceMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出Dubbo服务的Controller
 * @author : lieying
 * date : 2018/6/29 19:39
 */
@RestController
@RequestMapping(value = {"/dubboOpenApi"})
public class DubboExportController implements ApplicationContextAware {

	private final static Logger LOGGER = LoggerFactory.getLogger(DubboExportController.class);

	//服务实例及缓存
	private static Map<String,DubboServiceInstance<?>> serviceInstanceCache = new HashMap<>();

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		init();
	}

	/**
	 * 暴露出的HttpApi接口
	 * @param service 服务名
	 * @param method 方法名
	 * @param servletRequest HttpServletRequest
	 * */
	@RequestMapping(value = {"/{service}/{method}"},method = RequestMethod.POST)
	public String httpApi(@PathVariable String service,@PathVariable String method, HttpServletRequest servletRequest) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));

		String requestBody = IOUtils.read(reader);

		//调用服务
		return invoke(service,method,requestBody);
	}

	/**
	 * 调用服务
	 * @param serviceName 服务名
	 * @param serviceMethod 方法名
	 * @param jsonParam 请求JSON参数
	 * */
	private String invoke(String serviceName,String serviceMethod,String jsonParam){

		String serviceKey = getServiceKey(serviceName,serviceMethod);

		String retVal;
		if (serviceInstanceCache.containsKey(serviceKey)){
			try {
				retVal = SerializeUtils.toJson(serviceInstanceCache.get(serviceKey).invoke(jsonParam));
			} catch (InvocationTargetException | IllegalAccessException e) {
				retVal = "系统异常";
			}
		} else {
			retVal = String.format("没有发现serviceName = %1$s,serviceMethod = %2$s的服务注册",serviceName,serviceMethod);
		}

		return retVal;
	}

	/**
	 * 初始化
	 * */
	public void init(){

		String[] exportHttpDubboServiceArray = this.applicationContext.getBeanNamesForAnnotation(DubboService.class);
		
		if (exportHttpDubboServiceArray.length > 0){

			for (String serviceBeanName : exportHttpDubboServiceArray) {

				//服务Bean
				Object origServiceBean = this.applicationContext.getBean(serviceBeanName);
				Object serviceBean = origServiceBean;

				//是否是CGLIB代理的对象
				if (AopUtils.isAopProxy(origServiceBean) && AopUtils.isCglibProxy(origServiceBean)) {
					try {
						Field field = origServiceBean.getClass().getDeclaredField("CGLIB$CALLBACK_0");
						if (!field.isAccessible()){
							field.setAccessible(true);
						}

						Object dynamicAdvisedInterceptor = field.get(origServiceBean);

						Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
						advised.setAccessible(true);

						serviceBean = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

					} catch (NoSuchFieldError | IllegalAccessException e){
						serviceBean = null;
					} catch (Exception e){
						serviceBean = null;
					}
				}

				if (serviceBean == null){
					continue;
				}

				Class<?> serviceBeanClazz = serviceBean.getClass();

				DubboService dubboServiceAnno = serviceBeanClazz.getAnnotation(DubboService.class);

				if (!dubboServiceAnno.exportHttp()){
					continue;
				}

				Method[] beanMethods = serviceBeanClazz.getDeclaredMethods();
				for (Method serviceMethod : beanMethods) {

					//方法参数类型(仅考虑有一个参数的情况)
					Class<?> paramType = serviceMethod.getParameterTypes()[0];
					DubboServiceMethod dubboServiceMethodAnno = serviceMethod.getAnnotation(DubboServiceMethod.class);
					if (dubboServiceMethodAnno != null && dubboServiceMethodAnno.export()){

						//获取服务Key
						String serviceKey = getServiceKey(StringUtils.isBlank(dubboServiceAnno.serviceName()) ? serviceBeanClazz.getSimpleName() : dubboServiceAnno.serviceName(),
								StringUtils.isBlank(dubboServiceMethodAnno.methodName()) ? serviceMethod.getName() : dubboServiceMethodAnno.methodName());

						this.serviceInstanceCache.putIfAbsent(serviceKey,new DubboServiceInstance(serviceBean,serviceMethod,paramType));
					}
				}
			}
		}
		
	}

	private String getServiceKey(String serviceName,String methodName){
		return String.format("%1$s-%2$s",serviceName,methodName).toLowerCase();
	}

	class DubboServiceInstance<T> {

		private Object serviceInstance;

		private Method serviceMethod;

		private Class<T> paramType;

		public DubboServiceInstance(Object serviceInstance,Method serviceMethod,Class<T> paramType) {
			this.serviceInstance = serviceInstance;
			this.serviceMethod = serviceMethod;
			this.paramType = paramType;
		}

		public Object invoke(String jsonRequest) throws InvocationTargetException, IllegalAccessException {
			Object request = null;
			try {
				request = SerializeUtils.fromJson(jsonRequest,this.paramType);
			} catch (Exception e){
				LOGGER.error("仅支持JSON格式，请查看JSON请求的合法性",e);
			}

			if (request == null){
				return "仅支持JSON格式，请查看JSON请求的合法性";
			}

			return this.serviceMethod.invoke(serviceInstance,request);
		}

	}

	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	private String getIP(HttpServletRequest request) {
		if (request == null)
			return null;
		String s = request.getHeader("X-Forwarded-For");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

			s = request.getHeader("Proxy-Client-IP");
		}
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

			s = request.getHeader("WL-Proxy-Client-IP");
		}
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
			s = request.getHeader("HTTP_CLIENT_IP");
		}
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

			s = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

			s = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s))
			try {
				s = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
				return "";
			}
		return s;
	}
}
