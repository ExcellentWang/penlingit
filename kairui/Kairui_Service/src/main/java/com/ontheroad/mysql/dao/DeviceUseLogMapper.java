package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.DeviceUseLog;
import com.ontheroad.mysql.entity.DeviceUseLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceUseLogMapper {
    int countByExample(DeviceUseLogExample example);

    int deleteByExample(DeviceUseLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceUseLog record);

    int insertSelective(DeviceUseLog record);

    List<DeviceUseLog> selectByExample(DeviceUseLogExample example);

    DeviceUseLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceUseLog record, @Param("example") DeviceUseLogExample example);

    int updateByExample(@Param("record") DeviceUseLog record, @Param("example") DeviceUseLogExample example);

    int updateByPrimaryKeySelective(DeviceUseLog record);

    int updateByPrimaryKey(DeviceUseLog record);
}