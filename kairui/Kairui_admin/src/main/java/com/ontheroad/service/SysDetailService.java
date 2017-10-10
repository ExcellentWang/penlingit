package com.ontheroad.service;

import com.ontheroad.api.request.Request;
import com.ontheroad.entity.SysDetail;

import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;

public interface SysDetailService extends BaseService<SysDetail,Long>{

    void addNews(Request request);
}
