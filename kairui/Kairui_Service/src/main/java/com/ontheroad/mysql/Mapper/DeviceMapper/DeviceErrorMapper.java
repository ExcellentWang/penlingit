package com.ontheroad.mysql.Mapper.DeviceMapper;

import com.ontheroad.pojo.TerminalDevice.DeviceError;

public interface DeviceErrorMapper {
    DeviceError getDeviceError(Integer equipment_id);

    void setDeviceError(DeviceError deviceError);
}
