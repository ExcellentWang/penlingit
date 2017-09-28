package com.ontheroad.service.AppService;

import org.springframework.remoting.service.annotation.RemoteService;

import java.util.Map;

@RemoteService
public interface AppService {
    Map<Object, Object> update();
}
