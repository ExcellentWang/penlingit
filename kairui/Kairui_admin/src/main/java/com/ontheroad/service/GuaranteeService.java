package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.GuaranteeType;

public interface GuaranteeService {
	List<GuaranteeType> getTypeList(GuaranteeType t);
	GuaranteeType addOrUpdateType(GuaranteeType t);
	void delType(Long id);
	GuaranteeType selType(Long id);
}
