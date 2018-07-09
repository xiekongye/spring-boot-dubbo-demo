package com.arthas.study.springbootdubbodemo.api;

import com.arthas.study.springbootdubbodemo.service.ServiceApplicationContext;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 应用启动类
 * @author : lieying
 * date : 2018/6/26 18:20
 */
@SpringBootApplication(scanBasePackages = {"com.arthas.study.springbootdubbodemo.api.*"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import(value = {ServiceApplicationContext.class})
public class BootApplication {

	public static void main(String[] args) {
 		SpringApplication.run(BootApplication.class,args);
	}

	@Bean
	public GracefulShutdow gracefulShutdow(){
		return new GracefulShutdow();
	}

	@Bean
	public ServletWebServerFactory servletContainer(){
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers(gracefulShutdow());
		return tomcat;
	}

	private class GracefulShutdow implements TomcatConnectorCustomizer,ApplicationListener<ContextClosedEvent> {

		private final Logger LOGGER = LoggerFactory.getLogger(GracefulShutdow.class);

		private final int waitTime = 30;

		private volatile Connector connector;

		@Override
		public void customize(Connector connector) {
			this.connector = connector;
		}

		@Override
		public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
			this.connector.pause();

			Executor executor = this.connector.getProtocolHandler().getExecutor();
			if (executor instanceof ThreadPoolExecutor){
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
				threadPoolExecutor.shutdown();

				try {
					if (!threadPoolExecutor.awaitTermination(waitTime,TimeUnit.SECONDS)){
						LOGGER.warn("");
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}
	}
}
