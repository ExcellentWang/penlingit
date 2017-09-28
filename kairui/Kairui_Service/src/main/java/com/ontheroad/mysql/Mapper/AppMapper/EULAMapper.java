package com.ontheroad.mysql.Mapper.AppMapper;

import com.ontheroad.pojo.user.EULA;

public interface EULAMapper {
    void insertEULA(EULA eula);
    EULA getEULA();
    void updateEULA(EULA eula);
}
