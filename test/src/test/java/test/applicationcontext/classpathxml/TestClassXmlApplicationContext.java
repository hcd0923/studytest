package test.applicationcontext.classpathxml;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

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

	
	@Test
	public void testXmlBeanDefinitionReader() throws IOException{
		ClassPathXmlApplicationContext ac  = new ClassPathXmlApplicationContext();
		Resource[] resources = ((ResourcePatternResolver) ac).getResources("spring/xmlclasspath/application-config.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(new DefaultListableBeanFactory(null));
		for (Resource resource : resources) {
			logger.debug(resource.getDescription());
		}
		
		beanDefinitionReader.loadBeanDefinitions(resources);
		
	}
	
	@Test
	public void testClassPathBeanDefinitionScanner(){
		ClassPathBeanDefinitionScanner cpsccp = new ClassPathBeanDefinitionScanner(new DefaultListableBeanFactory(null));
		cpsccp.scan("test");
	}
	
	@Test
	public void testClassPathScanningCandidateComponentProvider(){
		ClassPathScanningCandidateComponentProvider cpsccp = new ClassPathScanningCandidateComponentProvider(true);
		Set<BeanDefinition> beanDefinitions = cpsccp.findCandidateComponents("test");
	}
	
	
	
	
	

}
