package com.tom.school.web.listener;

import java.util.Properties;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tom.school.web.core.ServerContext;

public class InitListener implements ServletContextListener {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Properties properties = new Properties();
		try{
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (Exception e) {
			this.logger.error("Load application.properties failed");
			throw new RuntimeErrorException(new Error(e.getMessage()), "Load application.properties failed");
		}
		
		ServerContext.BaseRESTUrl = properties.getProperty("base.rest.url");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
