package com.arthas.study.springbootdubbodemo.api;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类
 * @author : lieying
 * date : 2018/6/26 18:20
 */
@SpringBootApplication(scanBasePackages = {"com.arthas.study.springbootdubbodemo.*"})
@EnableDubboConfig
public class BootApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(BootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class,args);
	}

//	@Bean
//	public CustomerTomcatConnector customerTomcat(){
//		return new CustomerTomcatConnector(8089);
//	}
//
//	private class CustomerTomcatConnector implements TomcatConnectorCustomizer,ApplicationListener<ContextClosedEvent> {
//
//		private int port;
//		private Connector connector;
//
//		public CustomerTomcatConnector(int port){
//			this.port = port;
//		}
//
//		@Override
//		public void customize(Connector connector) {
//			this.connector = connector;
//			connector.setPort(this.port);
//			connector.setURIEncoding("UTF-8");
//		}
//
//		@Override
//		public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
//			this.connector.pause();
//			Executor executor = this.connector.getProtocolHandler().getExecutor();
//
//			if (executor instanceof ThreadPoolExecutor){
//				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//				threadPoolExecutor.shutdown();
//
//				try {
//					if (!threadPoolExecutor.awaitTermination(30,TimeUnit.SECONDS)){
//						LOGGER.info("线程池关闭异常");
//					}
//				} catch (InterruptedException e) {
//					LOGGER.warn("线程池关闭异常",e);
//					Thread.currentThread().interrupt();
//				}
//			}
//		}
//	}
}
