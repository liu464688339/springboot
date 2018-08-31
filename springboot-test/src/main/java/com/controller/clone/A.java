package com.controller.clone;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class A implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4206445273282769450L;

	private String id;

	private String name;
	
	private B b;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
	public String toString() {
		return "id:"+id+" name:"+name+" b:"+b.toString();
	}
	public Object clone() throws CloneNotSupportedException{
         return super.clone();
	}
}
