package com.lh.springboottest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.service.TestService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

	@Autowired
	private TestService testService;
	
	
	@Test
	public void testInsert() throws Exception {
		com.bean.Test test=new com.bean.Test();
		test.setMainCode("100");
		test.setMainCodeName("lh");
		test.setSubCode("1");
		test.setSubCodeName("test");
		testService.add(test);
		System.out.println("testService.add success");
	}

	@Test
	public void testQuery() throws Exception {
		List<com.bean.Test> test1=testService.test1FindAll();
		List<com.bean.Test> test2=testService.test2FindAll();
		System.err.println("Test1:"+test1.toString());
		System.err.println("Test2:"+test2.toString());
		
	}
	

}