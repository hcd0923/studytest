package test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean {
	static Logger logger = LoggerFactory.getLogger(TestBean.class);
	
	private String name;
	
	public static TestBean getTestBeanStatic(){
		logger.debug("TestBeanFactoryStatic");
		return new TestBean();
	}
	
	public TestBean getTestBean(){
		logger.debug("TestBeanFactory");
		return new TestBean();
	}
	

	public TestBean() {
		logger.debug("TestBeanConstructor");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
