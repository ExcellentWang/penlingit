package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.FirmVersion;

public interface FirmVersionService {
	void add(FirmVersion FirmVersion);
	List<FirmVersion> selectByExample(FirmVersion FirmVersion);
}
