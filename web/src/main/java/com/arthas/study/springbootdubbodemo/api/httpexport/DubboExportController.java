package com.arthas.study.springbootdubbodemo.api.httpexport;

import com.arthas.springbootdubbodemo.common.utils.SerializeUtils;
import com.arthas.study.springbootdubbodemo.model.annotations.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 导出Dubbo服务的Controller
 * @author : lieying
 * date : 2018/6/29 19:39
 */
@RestController
@RequestMapping(value = {"/dubboOpenApi"})
public class DubboExportController implements ApplicationContextAware {

	private final static Logger LOGGER = LoggerFactory.getLogger(DubboExportController.class);

	protected ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		init();
	}

	@RequestMapping(value = {"/{service}/method"},method = RequestMethod.POST)
	public String httpApi(HttpRequest httpRequest, HttpServletRequest servletRequest,
	                      @PathVariable String service,
	                      @PathVariable String method){

		LOGGER.info("ip:{}-httpRequest:{}",getIP(servletRequest),SerializeUtils.toJson(httpRequest));

		return null;
	}

	/**
	 * 初始化
	 * */
	// TODO: 2018/6/29 初始化一个映射，通过Service,Method可以调用正确的实例方法   
	public void init(){
		String[] exportHttpDubboServiceArray = this.applicationContext.getBeanNamesForAnnotation(DubboService.class);
		
		if (exportHttpDubboServiceArray != null && exportHttpDubboServiceArray.length > 0){
			
			int serviceCnt = exportHttpDubboServiceArray.length;
			for (int i = 0;i < serviceCnt;i++){
				Object bean = this.applicationContext.getBean(exportHttpDubboServiceArray[i]);
				Class<?> beanClazz = bean.getClass();
				DubboService dubboServiceAnno = beanClazz.getAnnotation(DubboService.class);
				Method[] beanMethods = beanClazz.getMethods();
				if (beanMethods != null && beanMethods.length > 0){
					int methodCnt = beanMethods.length;
					for (int j = 0;j < methodCnt;j++){
						
					}
				}
			}
			
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
