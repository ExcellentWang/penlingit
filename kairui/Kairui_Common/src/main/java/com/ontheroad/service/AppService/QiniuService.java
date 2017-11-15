package com.ontheroad.service.AppService;

import org.springframework.remoting.service.annotation.RemoteService;

import java.io.InputStream;
import java.util.Map;

@RemoteService
public interface QiniuService {
    Map<Object, Object> upload(String localPath);
    void test();
}
