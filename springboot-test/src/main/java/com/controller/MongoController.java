package com.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bean.MongoTest;
import com.service.MongoService;


@Controller
@RequestMapping(value = "/mongo")
public class MongoController {

	@Autowired
	private MongoService mongoService;


	@RequestMapping(value = "/findAll")
	@ResponseBody
	public Object findAll() {			
		return mongoService.findAll();
	}
	
	@RequestMapping(value = "/findAllRepository")
	@ResponseBody
	public Object findAllRepository() {			
		return mongoService.findAllRepository();
	}
	
	
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(MongoTest test) {
		try {
			mongoService.save(test);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	@RequestMapping(value = "/saveAll")
	@ResponseBody
	public Object saveAll() {
		try {
			mongoService.saveAll(new ArrayList<>());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping(value = "/saveRepository")
	@ResponseBody
	public Object saveRepository(MongoTest test) {
		try {	
			return mongoService.saveRepository(test);
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping(value = "/saveAllRepository")
	@ResponseBody
	public Object saveAllRepository() {
		try {	
			return mongoService.saveAllRepository(new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(MongoTest test) {
		try {
			return mongoService.update(test);
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(MongoTest test) {
		try {
			mongoService.delete(test);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping(value = "/deleteRepository")
	@ResponseBody
	public Object deleteRepository(MongoTest test) {
		try {
			mongoService.deleteRepository(test);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
}
