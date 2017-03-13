package test.beanFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanFactory {
	Logger logger = LoggerFactory.getLogger(TestBeanFactory.class);
	
	@Test
	public void DefaultBeanTest(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/beanfactory/application-config.xml");
		
		ConfigurableListableBeanFactory dlbf = ac.getBeanFactory();
		BeanDefinition bd = dlbf.getBeanDefinition("testBean");
		logger.debug("getConstructorArgumentValues : " + String.valueOf(bd.getConstructorArgumentValues().getArgumentCount()));
		logger.debug("getScope : " + String.valueOf(bd.getScope()));
		logger.debug("getRole : " + String.valueOf(bd.getRole()));
		logger.debug("getBeanClassName : " + String.valueOf(bd.getBeanClassName()));
		logger.debug("getDescription : " + String.valueOf(bd.getDescription()));
		logger.debug("getFactoryBeanName : " + String.valueOf(bd.getFactoryBeanName()));
		logger.debug("isLazyInit : " + String.valueOf(bd.isLazyInit()));
		logger.debug("isAutowireCandidate : " + String.valueOf(bd.isAutowireCandidate()));
		logger.debug("isPrototype : " + String.valueOf(bd.isPrototype()));
		logger.debug("isSingleton : " + String.valueOf(bd.isSingleton()));
	
	}
	
	@Test
	public void testDefaultBeanName(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/beanfactory/application-config.xml");
		
		ConfigurableListableBeanFactory clbf = ac.getBeanFactory();
		
		BeanDefinition bd = clbf.getBeanDefinition("test.service.impl.TestBean#0");
	}
	
	@Test
	public void testSameBeanNames(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/beanfactory/application-config.xml");
		
		ConfigurableListableBeanFactory clbf = ac.getBeanFactory();
		clbf.getBean("testSameName1");
		clbf.getBean("testSameName2");
		clbf.getBean("testSameName3");
	}
	
	@Test
	public void testBeanScope(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/beanfactory/application-config.xml");
		
		final ConfigurableListableBeanFactory clbf = ac.getBeanFactory();
		logger.debug(clbf.getBean("testBeanSingleton").toString());
		logger.debug(clbf.getBean("testBeanSingleton").toString());
		
		logger.debug(clbf.getBean("testBeanPrototype").toString());
		logger.debug(clbf.getBean("testBeanPrototype").toString());
		
		logger.debug(clbf.getBean("testBeanThreadtype").toString());
		logger.debug(clbf.getBean("testBeanThreadtype").toString());
		
		for(int i = 0; i < 10 ; i++){
			
			Thread thread = new Thread(new Runnable() {
				public void run() {
					logger.debug(clbf.getBean("testBeanThreadtype").toString());
				}
			});
			thread.start();
		}
	}
	
	@Test
	public void testBeanFactoryMethod(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/beanfactory/application-config.xml");
		
		ConfigurableListableBeanFactory clbf = ac.getBeanFactory();
		clbf.getBean("testBeanSingletonByFactoryMethod");
		clbf.getBean("testBeanSingletonByFactoryMethod");
		
		clbf.getBean("testBeanPrototypeByFactoryMethod");
		clbf.getBean("testBeanPrototypeByFactoryMethod");
		
		clbf.getBean("testBeanByFactoryBean");
	}
	
	
	
	

}


