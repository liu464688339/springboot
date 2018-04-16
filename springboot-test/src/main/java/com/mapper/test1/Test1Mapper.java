package com.mapper.test1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bean.MongoTest;
import com.bean.Test;

@Mapper
public interface Test1Mapper {

	public abstract List<MongoTest> find();
	
	public abstract List<Test> findAll();
	
	public void addMain(Test test);
}
