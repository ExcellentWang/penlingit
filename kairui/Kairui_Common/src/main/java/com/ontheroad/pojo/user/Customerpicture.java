package com.ontheroad.pojo.user;

import java.io.Serializable;

public class Customerpicture implements Serializable{
	
	
	/**问题图片
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerPicture_id;  //图片id
	private int customer_id;   //售后id
	private String pictureAddress;  //地址
	
	
	public int getCustomerPicture_id() {
		return customerPicture_id;
	}
	public void setCustomerPicture_id(int customerPicture_id) {
		this.customerPicture_id = customerPicture_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
	
	
	
	
}
