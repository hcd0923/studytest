package test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean {
	Logger logger = LoggerFactory.getLogger(TestBean.class);
	
	private String name;
	

	public TestBean() {
		logger.debug("TestServiceImplConstuctor");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
