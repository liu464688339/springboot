package com.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.bean.MongoTest;

@Repository
public interface MongoTestRepository extends MongoRepository<MongoTest, String>{

}
