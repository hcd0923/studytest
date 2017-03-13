package test.factory;

import test.service.impl.TestBean;

public class TestBeanFactory {
	
	public static TestBean getTestBean(){
		return new TestBean();
	
	}

}
