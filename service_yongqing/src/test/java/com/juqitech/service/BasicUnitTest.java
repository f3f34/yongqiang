package com.juqitech.service;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juqitech.service.utils.PropertyRepository;
import com.juqitech.service.utils.TicketdashiLog;
import com.juqitech.service.utils.net.CommonResult;
import com.juqitech.service.utils.query.result.ResultSetFormatter;



@SpringApplicationConfiguration(classes = AppBoot.class)
@DirtiesContext
public class BasicUnitTest {
	
	protected String result;
	protected CommonResult cResult;
	
	@BeforeClass
	public static void beforeClass(){
		try {
			PropertyRepository.init("message.properties");
		} catch (Exception e) {
			TicketdashiLog.error(e.getMessage(), e);
			System.exit(0);
		}
	}
	@After
	public void after(){
		if(cResult != null){
			result = cResult.toString();
		}
		System.out.println("result="+result);
	}
}
