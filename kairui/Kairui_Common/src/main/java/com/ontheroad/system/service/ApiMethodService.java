package com.ontheroad.system.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.system.entity.ApiMethodModel;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/6/23 0023.
 */
@RemoteService
public interface ApiMethodService extends BaseService<ApiMethodModel,Integer> {
}
