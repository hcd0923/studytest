package test.applicationContxt;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClassXmlApplicationContext {
	Logger logger = LoggerFactory.getLogger(TestClassXmlApplicationContext.class);
	
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-config.xml");
	}

}
