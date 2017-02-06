package test.applicationcontext.annotation;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
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
import org.springframework.core.env.StandardEnvironment;

import test.controller.TestController;
import test.service.impl.TestServiceImpl;

public class TestAnnotationApplicationContext {
	Logger logger = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);
	private DefaultListableBeanFactory beanFactory;
	
	@Before
	public void setUp() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		QualifierAnnotationAutowireCandidateResolver acr = new QualifierAnnotationAutowireCandidateResolver();
		acr.setBeanFactory(bf);
		bf.setAutowireCandidateResolver(acr);
		this.beanFactory = bf;
	}
	
	@Test
	public void diriectRegsterAnnotatedBeanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(TestController.class);
		acac.register(TestServiceImpl.class);
		acac.refresh();
	}
	
	@Test
	public void configrationRegsterAnnotatedBeanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(TestAnnotationConfigue.class);
		acac.refresh();
	}
	
	@Test
	public void scanAnnoatedBeanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.scan("test");
		acac.refresh();
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
	@Test
	public void getAnnotatedBeanDefinitionReaderTest(){
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(new AnnotationConfigApplicationContext());
		reader.register(TestAnnotationConfigue.class);
		BeanDefinitionRegistry br = reader.getRegistry();
		for (String beanDefinitionName : br.getBeanDefinitionNames()) {
			logger.debug(beanDefinitionName);
		}
		
		logger.debug(String.valueOf(br.getBeanDefinitionCount()));
		
		ConfigurationClassPostProcessor ccpp = new ConfigurationClassPostProcessor();
		ccpp.setEnvironment(new StandardEnvironment());
		ccpp.postProcessBeanFactory(beanFactory);
		ccpp.postProcessBeanDefinitionRegistry(br);
		
		//TODO : more study
		beanFactory.preInstantiateSingletons();
//		beanFactory.getBean(TestController.class);
		
		
	}
	
	@Test
	public void getClassPathBeanDefinitionScannerTest(){
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(new AnnotationConfigApplicationContext());
		scanner.scan("test");
	}
	

	
	
	
	
}

