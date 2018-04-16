package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MongoTest;
import com.bean.Test;
import com.dao.MongoDao;
import com.dao.MongoTestRepository;
import com.mapper.test1.Test1Mapper;
import com.mapper.test2.Test2Mapper;

@Service
public class MongoService {
	
	
	
	@Autowired
	private MongoDao mongoDao;
	@Autowired
	private Test1Mapper test1Mapper;
	
	@Autowired
	private MongoTestRepository mongoTestRepository;
	
	public List<MongoTest> findAll(){
		return mongoDao.findAll();
	}
	
	public List<MongoTest> findAllRepository(){
		return mongoTestRepository.findAll();
	}
    	
	public void saveAll(List<MongoTest> list){
		list=test1Mapper.find();
		mongoDao.saveAll(list);
	}
	public void save(MongoTest test){
		mongoDao.save(test);
	}
	
	public List<MongoTest> saveAllRepository(List<MongoTest> list){
		list=test1Mapper.find();
		return mongoTestRepository.save(list);
	}
	public MongoTest saveRepository(MongoTest test){
		return mongoTestRepository.save(test);
	}
	
    public int update(MongoTest test){
    	return mongoDao.update(test);
	}
      
    public void delete(MongoTest test){
    	mongoDao.deleteById(test.getId());
	}
    
    public void deleteRepository(MongoTest test){
    	mongoTestRepository.delete(test);
	}
}
