package com.ontheroad.service;

import org.springframework.remoting.service.annotation.RemoteService;

import java.util.Map;

@RemoteService
public interface SmsService {
    Map<Object, Object> sendVerificationCode(String phone, String code);
}
