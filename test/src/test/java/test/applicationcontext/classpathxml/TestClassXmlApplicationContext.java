package test.applicationcontext.classpathxml;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.impl.TestBean;

public class TestClassXmlApplicationContext {
	Logger logger = LoggerFactory.getLogger(TestClassXmlApplicationContext.class);
	
	@Test
	public void getBeanTest(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-config.xml");
		TestBean testBean = ac.getBean("testService", TestBean.class);
		logger.debug("contain testBean".concat(" : ").concat(String.valueOf(ac.containsBean("testService"))));
		logger.debug("Bean Definition Count".concat(" : ").concat(String.valueOf(ac.getBeanDefinitionCount())));
		logger.debug("contain testService1".concat(" : ").concat(String.valueOf(ac.containsBean("testService1"))));
	}
	
	
	@Test
	public void testConfigurableListableBeanFactory(){
		ClassPathXmlApplicationContext ac  = new ClassPathXmlApplicationContext();
		ConfigurableListableBeanFactory configurableListableBeanFactory = ac.getBeanFactory();
		configurableListableBeanFactory.registerSingleton("testService", TestBean.class);
		TestBean testBean = configurableListableBeanFactory.getBean("testService", TestBean.class);
		
		logger.debug(String.valueOf(ac.containsBean("testService")));
		
		logger.debug(String.valueOf(ac.getBeanDefinitionCount()));
	}
	
	

}
