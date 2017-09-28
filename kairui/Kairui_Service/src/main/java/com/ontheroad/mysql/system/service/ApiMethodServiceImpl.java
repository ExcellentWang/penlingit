package com.ontheroad.mysql.system.service;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.mysql.system.mapper.ApiMethodMapper;
import com.ontheroad.system.entity.ApiMethodModel;
import com.ontheroad.system.service.ApiMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kedong on 2017/6/23 0023.
 */
@Service
@Transactional
public class ApiMethodServiceImpl extends BaseServiceImpl<ApiMethodModel, Integer> implements ApiMethodService {

    @Autowired
    public void setMapper(ApiMethodMapper mapper) {
        setGenericMapper(mapper);
    }
}
