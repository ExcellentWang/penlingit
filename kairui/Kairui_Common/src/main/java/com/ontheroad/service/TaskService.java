package com.ontheroad.service;

import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public interface TaskService {
    void syncTime();

    void checkOnline();

    void syncWeather();
}
