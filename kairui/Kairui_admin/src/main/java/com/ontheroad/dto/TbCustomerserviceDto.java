package com.ontheroad.dto;

import java.util.Date;

public class TbCustomerserviceDto {
	private Integer customer_id;

    private Integer equipmentId;

    private Integer userId;

    private String equipmentnum;

    private String repairtype;

    private String phenomenon;

    private Date appointmenttime;

    private String area;

    private String address;

    private String phone;

    private Integer status;

    private Date createtime;

    private String ordernum;
    /**
     * 报修类型
     * @return
     */
    private String type;
    private String username;
    

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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

	public String getEquipmentnum() {
		return equipmentnum;
	}

	public void setEquipmentnum(String equipmentnum) {
		this.equipmentnum = equipmentnum;
	}

	public String getRepairtype() {
		return repairtype;
	}

	public void setRepairtype(String repairtype) {
		this.repairtype = repairtype;
	}

	public String getPhenomenon() {
		return phenomenon;
	}

	public void setPhenomenon(String phenomenon) {
		this.phenomenon = phenomenon;
	}

	public Date getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(Date appointmenttime) {
		this.appointmenttime = appointmenttime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
    
}
