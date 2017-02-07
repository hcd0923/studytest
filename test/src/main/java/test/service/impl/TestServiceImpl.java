package test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import test.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	public TestServiceImpl() {
		logger.debug("testControllerConstructor");
	}

	public String getTest(){
		return "test";
	}

}
