package com.ontheroad.mysql.Mapper.DeviceMapper;

import java.util.List;

import com.ontheroad.pojo.TerminalDevice.DeviceError;

public interface DeviceErrorMapper {
    DeviceError getDeviceError(Integer equipment_id);

    void setDeviceError(DeviceError deviceError);
    
    List<DeviceError> getDeviceErrorList();
}
