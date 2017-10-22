package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.Staff;

public interface GuaranteeService {
	/**
	 * 报修
	 * @param t
	 * @return
	 */
	List<GuaranteeType> getTypeList(GuaranteeType t);
	GuaranteeType addOrUpdateType(GuaranteeType t);
	void delType(Long id);
	GuaranteeType selType(Long id);
	/**
	 * 员工
	 */
	List<Staff> getStaffList(Staff t);
	Staff addOrUpdateStaff(Staff t);
	void delStaff(Long id);
	Staff selStaff(Long id);
}
