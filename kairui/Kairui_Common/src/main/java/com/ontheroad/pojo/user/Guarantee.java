package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class Guarantee implements Serializable {
	
    /**
	 * 保修卡信息
	 */
	private static final long serialVersionUID = 1L;
	private int guarantee_id;  //保修卡id
	private int equipment_id;  //设备ID
	private int user_id;  //用户ID
	private String model;  //设备型号
	private String deviceType;  //设备类型
	private String productNumber;  //产品编号
	private Date buyTime;        //购买日期
	private String guaranteeTime;   //保修期（年）
	private String phone;         //联系电话
	private String area; //服务地区
	private String address; //详细地址
	private String invoice; //发票
	private String status;  //保修卡状态 0待完善 1以完善 2审核中 3审核通过 4未通过
	private String remark;  //备注（原因）remark = remark;
	private String product_name;//产品
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getGuarantee_id() {
		return guarantee_id;
	}
	public void setGuarantee_id(int guarantee_id) {
		this.guarantee_id = guarantee_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	public String getGuaranteeTime() {
		return guaranteeTime;
	}
	public void setGuaranteeTime(String guaranteeTime) {
		this.guaranteeTime = guaranteeTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
