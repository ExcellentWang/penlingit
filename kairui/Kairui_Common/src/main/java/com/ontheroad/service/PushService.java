package com.ontheroad.service;

import com.alibaba.fastjson.JSONObject;

public interface PushService {
	void pushInstallationId(Integer userId,JSONObject json);
}
