package com.ontheroad.dto;

import java.util.List;
import java.util.Map;

public class IndexInfo {
	private Integer guaranteeSize;
	private Integer customerserviceSize;
	private Integer allSize;
	private Integer onlineSize;
	List<Map<String, String>> zaixianlv;
	
	public Integer getAllSize() {
		return allSize;
	}
	public void setAllSize(Integer allSize) {
		this.allSize = allSize;
	}
	public Integer getOnlineSize() {
		return onlineSize;
	}
	public void setOnlineSize(Integer onlineSize) {
		this.onlineSize = onlineSize;
	}
	public List<Map<String, String>> getZaixianlv() {
		return zaixianlv;
	}
	public void setZaixianlv(List<Map<String, String>> zaixianlv) {
		this.zaixianlv = zaixianlv;
	}
	public Integer getGuaranteeSize() {
		return guaranteeSize;
	}
	public void setGuaranteeSize(Integer guaranteeSize) {
		this.guaranteeSize = guaranteeSize;
	}
	public Integer getCustomerserviceSize() {
		return customerserviceSize;
	}
	public void setCustomerserviceSize(Integer customerserviceSize) {
		this.customerserviceSize = customerserviceSize;
	}
	
}
