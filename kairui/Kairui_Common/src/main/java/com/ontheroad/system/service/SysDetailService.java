package com.ontheroad.system.service;

import com.ontheroad.api.request.Request;
import com.ontheroad.system.entity.SysDetail;
import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;

@RemoteService
public interface SysDetailService extends BaseService<SysDetail,Long>{

    void addNews(Request request);
}
