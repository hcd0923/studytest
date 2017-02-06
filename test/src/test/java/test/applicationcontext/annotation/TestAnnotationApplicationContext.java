package test.applicationcontext.annotation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.controller.TestController;
import test.service.impl.TestServiceImpl;

public class TestAnnotationApplicationContext {
	Logger logger = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);
	
	@Test
	public void regsterAnnotatedBeanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(TestController.class);
		acac.register(TestServiceImpl.class);
		acac.refresh();
	}
	
	@Test
	public void scanAnnoatedBeanTest(){
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.scan("test");
		acac.refresh();
	}
	
	
//	ClassPathScanningCandidateComponentProvider.findCandidateComponents
	@Test
	public void xmlAnnotationTest(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/annotation/application-config.xml");
		ac.refresh();
	}
	
	
	@Test
	public void getAnnotatedBeanDefinitionReaderTest(){
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(new AnnotationConfigApplicationContext());
		reader.register(TestAnnotationConfigue.class);
	}
	
	@Test
	public void getClassPathBeanDefinitionScannerTest(){
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(new AnnotationConfigApplicationContext());
		scanner.scan("test");
	}
	
	@Test
	public void getAnntationResolverTest(){
		ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
		AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(TestController.class);
		ScopeMetadata scopmeta = scopeMetadataResolver.resolveScopeMetadata(abd);
		logger.debug(abd.getBeanClassName());
		logger.debug(scopmeta.getScopedProxyMode().toString());
	}
	
	
	
	
}
