package com.ontheroad.concern.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.ConcernRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.concern.modle.ConcernModel;
import com.ontheroad.core.annotation.ValidateGroup;
import org.springframework.remoting.service.annotation.RemoteService;
import org.springframework.stereotype.Repository;

/**
 * Created by kedong on 2017/7/2 0002.
 */
@RemoteService
public interface ConcernService extends BaseService<ConcernModel, Integer> {

    /**
     * 关注别人(添加好友)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup
    Response follow(ConcernRequest request) throws Exception;

    /**
     * 好友列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response freindList(ConcernRequest request) throws Exception;


}
