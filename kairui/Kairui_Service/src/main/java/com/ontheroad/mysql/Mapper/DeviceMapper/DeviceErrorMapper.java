package com.ontheroad.mysql.Mapper.DeviceMapper;

import java.util.List;

import com.ontheroad.pojo.TerminalDevice.DeviceError;
import com.ontheroad.pojo.TerminalDevice.DeviceVo;

public interface DeviceErrorMapper {
    DeviceError getDeviceError(Integer equipment_id);

    void setDeviceError(DeviceError deviceError);
    
    List<DeviceVo> getDeviceErrorList(DeviceVo vo);
}
