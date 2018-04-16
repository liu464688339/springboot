package com.dao;

import com.bean.MongoTest;
import com.mongodb.WriteResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param user
     */
    public void save(MongoTest test) {
        mongoTemplate.save(test);
    }
    
    public void saveAll(List<MongoTest> list){
    	 mongoTemplate.insertAll(list);
    }
    
    public List<MongoTest> findAll() {
        List<MongoTest> list=  mongoTemplate.findAll(MongoTest.class);
        return list;
    }

    /**
     * 根据mainCode查询对象
     * @param mainCode
     * @return
     */
    public MongoTest findByCode(String mainCode) {
        Query query=new Query(Criteria.where("mainCode").is(mainCode));
        MongoTest test =  mongoTemplate.findOne(query , MongoTest.class);
        return test;
    }
    
    

    /**
     * 更新对象
     * @param user
     */
    public int update(MongoTest test) {
        Query query=new Query(Criteria.where("id").is(test.getId()));
        Update update= new Update().set("mainCode", test.getMainCode())
        		                   .set("mainCodeName", test.getMainCodeName())
                                   .set("subCode",test.getSubCode())
                                   .set("subCodeName", test.getSubCodeName());
        //更新查询返回结果集的第一条
        WriteResult result =mongoTemplate.updateFirst(query,update,MongoTest.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,MongoTest.class);
        if(result!=null)
            return result.getN();
        else
            return 0;
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,MongoTest.class);
    }
}
