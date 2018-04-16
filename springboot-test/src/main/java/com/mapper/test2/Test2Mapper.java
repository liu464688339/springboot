package com.mapper.test2;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bean.Test;


@Mapper
public interface Test2Mapper {

	public abstract List<Test> findAll();
	
	public void addSub(Test test);
}
