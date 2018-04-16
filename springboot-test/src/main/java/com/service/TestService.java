package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Test;
import com.dao.MongoDao;
import com.mapper.test1.Test1Mapper;
import com.mapper.test2.Test2Mapper;

@Service
public class TestService {
	
	@Autowired
	private Test1Mapper test1Mapper;
	
	@Autowired
	private Test2Mapper test2Mapper;
		
	public List<Test> test1FindAll(){
		return test1Mapper.findAll();
	}
    
	public List<Test> test2FindAll(){
		return test2Mapper.findAll();
	}
	
	public void add(Test test){
		test1Mapper.addMain(test);
		test2Mapper.addSub(test);
	}
    
}
