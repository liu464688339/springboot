package com.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public class MongoTest{
	
	@Id
	private String id;	
	private Integer mainCode;//父节点编号
	private String mainCodeName;//父节点名称
    private Integer subCode;//子节点编号
    private String subCodeName;//子节点名称
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public Integer getMainCode() {
		return mainCode;
	}
	public void setMainCode(Integer mainCode) {
		this.mainCode = mainCode;
	}
	public String getMainCodeName() {
		return mainCodeName;
	}
	public void setMainCodeName(String mainCodeName) {
		this.mainCodeName = mainCodeName;
	}
	public Integer getSubCode() {
		return subCode;
	}
	public void setSubCode(Integer subCode) {
		this.subCode = subCode;
	}
	public String getSubCodeName() {
		return subCodeName;
	}
	public void setSubCodeName(String subCodeName) {
		this.subCodeName = subCodeName;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
