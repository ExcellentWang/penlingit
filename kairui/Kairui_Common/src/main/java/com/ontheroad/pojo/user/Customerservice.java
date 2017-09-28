package com.ontheroad.pojo.user;

import com.ontheroad.utils.DateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/*
 * 售后详情
 * 
 */
public class Customerservice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customer_id;  //保修ID
	private int equipment_id;  //设备ID
	private int user_id;   //用户ID
	private String equipmentNum;  //设备编号
	private String repairType;  //报修类型
	private String repairTypeText; // 报修类型 - 文字描述
	private String phenomenon;  //故障现象
	private Date appointmentTime; //预约时间
	private String area;   //服务地区
	private String address; //详细地址
	private String phone;  //联系电话
	private String status;  //状态 0待处理 1已受理 2完成
	private Date createTime;  //填写时间
	private String orderNum;   //报修单号
	private String equipmentName; //设备名称
	private String type;    //设备类型
	private List<String> pictureAdd; //图片

	private List<ServiceDetail> serviceDetails;
	

	public List<String> getPictureAdd() {
		return pictureAdd;
	}
	public void setPictureAdd(List<String> pictureAdd) {
		this.pictureAdd = pictureAdd;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getRepairType() {
		return repairType;
	}
	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	public String getPhenomenon() {
		return phenomenon;
	}
	public void setPhenomenon(String phenomenon) {
		this.phenomenon = phenomenon;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getRepairTypeText() {
		repairTypeText = "不通电";
//		switch (this.getRepairType()) {
//			case "1": repairTypeText = "不通电"; break;
//			case "2": repairTypeText = "不出水"; break;
//			case "3": repairTypeText = "不加热"; break;
//			default: break;
//		}
		return repairTypeText;
	}

	public void setRepairTypeText(String repairTypeText) {
		this.repairTypeText = repairTypeText;
	}

	public List<ServiceDetail> getServiceDetails() {

		List<ServiceDetail> list = new ArrayList<>();
		ServiceDetail d1 = new ServiceDetail();
		ServiceDetail d2 = new ServiceDetail();
		ServiceDetail d3 = new ServiceDetail();

		d1.setId(1);
		d1.setDetail("售后申请已经批准");
		d1.setTime(DateUtil.addDay(Calendar.getInstance().getTime(), -7));

		d2.setId(2);
		d2.setDetail("申请已经受理");
		d2.setTime(DateUtil.addDay(Calendar.getInstance().getTime(), -1));

		d3.setId(3);
		d3.setDetail("已经安排上门");
		d3.setTime(Calendar.getInstance().getTime());

		list.add(d1);
		list.add(d2);
		list.add(d3);
		this.serviceDetails = list;

		return serviceDetails;
	}

	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
}
