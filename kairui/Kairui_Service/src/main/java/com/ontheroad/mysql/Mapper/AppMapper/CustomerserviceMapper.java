package com.ontheroad.mysql.Mapper.AppMapper;

import com.ontheroad.pojo.user.Customerpicture;
import com.ontheroad.pojo.user.Customerservice;

import java.util.List;
import java.util.Map;

public interface CustomerserviceMapper {

	List<Customerservice> getCustomerList(Map<Object, Object> fieldMap);

	List<Customerservice> getCustomerListByDevice(int equipment_id);

	Customerservice getCustomerDetail(int customer_id);

	void deleteCustomerDetail(Customerservice customerservice);

	void insertCustomer(Customerservice c);

	void insertPicture(List<Customerpicture> pl);

	void insertSinglePicture(Customerpicture cp);

	List<Customerpicture> getPics(int customer_id);
	
	Integer getIndexCustomerService();
	

}
