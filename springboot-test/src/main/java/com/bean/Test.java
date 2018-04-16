package com.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Test {
	private String mainCode;//父节点编号
	private String mainCodeName;//父节点名称
    private String subCode;//子节点编号
    private String subCodeName;//子节点名称
    
    public String getMainCode() {
		return mainCode;
	}
	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}
	public String getMainCodeName() {
		return mainCodeName;
	}
	public void setMainCodeName(String mainCodeName) {
		this.mainCodeName = mainCodeName;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
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
