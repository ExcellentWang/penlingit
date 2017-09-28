package com.ontheroad.sport.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.request.SprotRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.sport.model.SportModel;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/6/27 0027.
 */
@RemoteService
public interface SportService extends BaseService<SportModel, Integer> {

    /**
     * 运动数据上传
     *
     * @param request
     * @return
     */
    Response uploadSport(SprotRequest request) throws Exception;

    /**
     * 获取运动列表
     *
     * @param request
     * @return
     */
    Response getSportList(Request request) throws Exception;
}
