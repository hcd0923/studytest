package test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import test.service.TestService;
import test.service.impl.TestBean;

@Controller
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	
	public TestController() {
		logger.debug("testControllerConstructor");
	}


	public void testController(){
		logger.debug("testController");
		logger.debug(testService.getTest());
		
		
	}
	
	

}
