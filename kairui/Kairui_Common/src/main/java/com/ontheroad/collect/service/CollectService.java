package com.ontheroad.collect.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.response.Response;
import com.ontheroad.api.validate.BaseGroup;
import com.ontheroad.collect.modle.CollectModel;
import com.ontheroad.core.annotation.ValidateGroup;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/7/2 0002.
 */
@RemoteService
public interface CollectService extends BaseService<CollectModel, Integer> {

    /**
     * 我的收藏列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = BaseGroup.class)
    Response getCollections(Request request) throws Exception;
}
