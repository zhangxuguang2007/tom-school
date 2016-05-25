package com.tom.school.dao.system.test;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SystemUserDaoTest {
	
	private ApplicationContext applicationContext = null;
	
	@Before
	public void set(){
		this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testGet(){
		SessionFactory sessionFactory = this.applicationContext.getBean("sessionFactory", SessionFactory.class);
		System.out.println(sessionFactory);
	}
	
}
