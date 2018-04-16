package com.controller;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.bean.Test;
import com.service.TestService;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	private Logger LOG;
	@Autowired
	private TestService testService;

	@Autowired
	private RedisTemplate redisTemplate;
	
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

	/**
	 * mybatis
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public Object findAll() {		
		 List<Test> list1=testService.test1FindAll();
		 List<Test> list2=testService.test2FindAll();
		 List<Test> list=new ArrayList<Test>();
		 list.addAll(list1);
		 list.addAll(list2);
		 return list;
	}

	/**
	 * redis
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toRedis")
	@ResponseBody
	public Object toRedis() {
		List<Test> list=new ArrayList<Test>();
		try {
			List<Test> list1=testService.test1FindAll();
			List<Test> list2=testService.test2FindAll();		
			list.addAll(list1);
			list.addAll(list2);
			String str1=JSON.toJSONString(list);
			redisTemplate.opsForValue().set("TestList",str1);
			return "success";
		} catch (Exception e) {
			LOG.error("toRedis error:" + e.getMessage());
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRedis")
	@ResponseBody
	public Object getRedis() {	
		List<Test> list = new ArrayList<Test>();
		try {
			String jsons = redisTemplate.opsForValue().get("TestList").toString();
			list = (List<Test>)JSON.parse(jsons);
		} catch (Exception e) {
			LOG.error("getRedis error:" + e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value = "/toKafka")
	@ResponseBody
	public Object toKafka() {
		try {
		    kafkaTemplate.send("testlh2","toKafka success ^_^");
		    System.out.println("toKafka success ^_^");
		    kafkaTemplate.sendDefault("toKafka default success ^_^");
		    System.out.println("toKafka default success ^_^");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	
}
