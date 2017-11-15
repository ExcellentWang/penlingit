package com.ontheroad.controller;

import com.danga.MemCached.MemCachedClient;
import com.ontheroad.system.entity.ApiMethodModel;
import com.ontheroad.system.service.ApiMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kedong on 2017/6/23 0023.
 */
@RestController
public class ApiMethodController {

    @Autowired
    private ApiMethodService apiMethodService;

    @Autowired
    private MemCachedClient memCachedClient;

    @RequestMapping("reloadApi")
    public String refreshApi() {
        try {
            ApiMethodModel apiMethodModel = new ApiMethodModel();
            apiMethodModel.setIsEnabled(true);
            List<ApiMethodModel> list = apiMethodService.getListByModel(apiMethodModel);
            if (list != null && list.size() > 0) {
                for (ApiMethodModel methodModel : list) {
                    memCachedClient.set(methodModel.getName(), methodModel);
                }
            }
            apiMethodModel.setIsEnabled(false);
            list = apiMethodService.getListByModel(apiMethodModel);
            if (list != null && list.size() > 0) {
                for (ApiMethodModel methodModel : list) {
                    memCachedClient.delete(methodModel.getName());
                }
            }
            return "刷新缓存成功。";
        } catch (Exception e) {
            e.printStackTrace();
            return "刷新缓存失败。";
        }
    }
}
