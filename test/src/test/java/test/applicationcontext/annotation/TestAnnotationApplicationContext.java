package test.applicationcontext.annotation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import test.controller.TestController;
import test.service.impl.TestServiceImpl;



/**
 * @author cdhong
 * {@link https://docs.google.com/presentation/d/1lgBe89kUOWdqe2YJYiLYDwMNiOmFKSSqJrHCjYRw9L0/edit#slide=id.p}
 */
public class TestAnnotationApplicationContext {
	Logger logger = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);
	private DefaultListableBeanFactory beanFactory;
	
	@Test
	public void testAnnotationConfigApplicationContextByDirectlyRegisteredClass(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(TestController.class);
		acac.register(TestServiceImpl.class);
		acac.refresh();
		acac.getBean(TestController.class);
	}
	
	@Test
	public void testAnnotatedBeanDefinitionReaderByDirectlyRegisteredClass(){
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(new AnnotationConfigApplicationContext());
		reader.register(TestController.class);
		reader.register(TestServiceImpl.class);
		BeanDefinitionRegistry br = reader.getRegistry();
		for (String beanDefinitionName : br.getBeanDefinitionNames()) {
			logger.debug(beanDefinitionName);
		}
		
		logger.debug(String.valueOf(br.getBeanDefinitionCount()));
		
		ConfigurableListableBeanFactory beanFacory  = ((GenericApplicationContext)br).getBeanFactory();
		
		beanFacory.preInstantiateSingletons();
		beanFacory.getBean(TestController.class);
	}
	
	@Test
	public void testAnnotationConfigApplicationContextByConfigurationClassComponentSacn(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(TestAnnotationConfigue.class);
		acac.refresh();
		acac.getBean(TestController.class);
	}
	
	@Test
	public void testAnnotatedBeanDefinitionReaderByConfigurationClassComponentSacn(){
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(new AnnotationConfigApplicationContext());
		reader.register(TestAnnotationConfigue.class);
		BeanDefinitionRegistry br = reader.getRegistry();
		for (String beanDefinitionName : br.getBeanDefinitionNames()) {
			logger.debug(beanDefinitionName);
		}
		
		logger.debug(String.valueOf(br.getBeanDefinitionCount()));
		
		ConfigurableListableBeanFactory beanFacory  = ((GenericApplicationContext)br).getBeanFactory();
		
		ConfigurationClassPostProcessor ccpp = new ConfigurationClassPostProcessor();
		ccpp.setEnvironment(new StandardEnvironment());
		ccpp.postProcessBeanFactory(beanFacory);
		
		beanFacory.preInstantiateSingletons();
		beanFacory.getBean(TestController.class);
	}
	
	
	
	@Test
	public void testsAnnotationConfigApplicationContextByScanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.scan("test.controller", "test.service");
		acac.refresh();
		acac.getBean(TestController.class);
	}
	
	@Test
	public void testClassPathBeanDefinitionScanner(){
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(new AnnotationConfigApplicationContext());
		scanner.scan("test.controller", "test.service");
		BeanDefinitionRegistry br = scanner.getRegistry();
		for (String beanDefinitionName : br.getBeanDefinitionNames()) {
			logger.debug(beanDefinitionName);
		}
		
		logger.debug(String.valueOf(br.getBeanDefinitionCount()));
		ConfigurableListableBeanFactory beanFacory  = ((GenericApplicationContext)br).getBeanFactory();
		beanFacory.preInstantiateSingletons();
		beanFacory.getBean(TestController.class);
	}
	
	@Test
	public void getAnntationResolverTest(){
		ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
		AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(TestController.class);
		ScopeMetadata scopmeta = scopeMetadataResolver.resolveScopeMetadata(abd);
		logger.debug(abd.getBeanClassName());
		logger.debug(scopmeta.getScopedProxyMode().toString());
	}
	
	
//	ClassPathScanningCandidateComponentProvider.findCandidateComponents
	@Test
	public void xmlAnnotationTest(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/annotation/application-config.xml");
		ac.refresh();
	}
	
	
//	AbstractApplicationContext.invokeBeanFactoryPostProcessors

	

	

	
	
	
	
}

