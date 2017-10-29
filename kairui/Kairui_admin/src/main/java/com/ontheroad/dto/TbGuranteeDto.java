package com.ontheroad.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class TbGuranteeDto {
	private Integer guaranteeId;

	private Integer equipmentId;

	private Integer userId;

	private String model;

	private String productnumber;

	private Date buytime;

	private String guaranteetime;

	private String phone;

	private String area;

	private String address;

	private String invoice;

	private Integer status;

	private String remark;
	private String equipmentNum;
	private String type;
	private String product;
	@DateTimeFormat(pattern="yy-mm-dd hh:ss")
	private Date submit_time;
	
	
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	public Integer getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(Integer guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProductnumber() {
		return productnumber;
	}
	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}
	public Date getBuytime() {
		return buytime;
	}
	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}
	public String getGuaranteetime() {
		return guaranteetime;
	}
	public void setGuaranteetime(String guaranteetime) {
		this.guaranteetime = guaranteetime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}

}
