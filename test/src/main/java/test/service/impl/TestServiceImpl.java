package test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServiceImpl {
	Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	private String name;
	

	public TestServiceImpl() {
		logger.debug("TestServiceImplConstuctor");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
