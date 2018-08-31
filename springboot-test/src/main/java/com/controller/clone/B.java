package com.controller.clone;

import java.io.Serializable;

public class B implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2127770556201337010L;

	private String name;  

	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public String toString() {
		return "age:"+age+" name:"+name;
	}
}
