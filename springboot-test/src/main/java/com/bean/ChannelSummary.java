package com.bean;

import java.util.Map;

public class ChannelSummary {
	/*private Map<String, Double> channelMoney;*/
	private Double total;
	private int month;
	private Double wxMoney;
	private Double zfbMoney;
	private Double jdMoney;
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Double getWxMoney() {
		return wxMoney;
	}
	public void setWxMoney(Double wxMoney) {
		this.wxMoney = wxMoney;
	}
	public Double getZfbMoney() {
		return zfbMoney;
	}
	public void setZfbMoney(Double zfbMoney) {
		this.zfbMoney = zfbMoney;
	}
	public Double getJdMoney() {
		return jdMoney;
	}
	public void setJdMoney(Double jdMoney) {
		this.jdMoney = jdMoney;
	}
	

}
