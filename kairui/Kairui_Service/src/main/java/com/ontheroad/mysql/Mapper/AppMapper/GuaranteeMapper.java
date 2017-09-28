package com.ontheroad.mysql.Mapper.AppMapper;

import java.util.Map;

import com.ontheroad.pojo.user.Guarantee;

public interface GuaranteeMapper {

	Guarantee getGuaranteeDetail(Map<Object, Object> fieldMap);

	void submitGuaranteeDetail(Guarantee guarantee);

}
