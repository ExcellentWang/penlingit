package com.ontheroad.system.service;

import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.system.entity.SysLog;

@RemoteService
public interface SysLogService extends BaseService<SysLog,Long>{

}
