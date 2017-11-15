package com.ontheroad.mysql.Mapper.DeviceMapper;

import com.ontheroad.pojo.TerminalDevice.DeviceLog;

import java.util.List;

public interface DeviceLogMapper {
    void insertDeviceLog(DeviceLog log);
    List<DeviceLog> getDeviceLogs();
    DeviceLog getLastDeviceLog(String device_id);
}