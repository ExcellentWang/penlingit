package com.ontheroad.mysql.collect.service;

import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.response.Response;
import com.ontheroad.collect.modle.CollectModel;
import com.ontheroad.collect.service.CollectService;
import com.ontheroad.mysql.collect.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by kedong on 2017/7/2 0002.
 */
public class CollectServiceImpl extends BaseServiceImpl<CollectModel, Integer> implements CollectService {

    @Autowired
    public void setMapper(CollectMapper mapper) {
        setGenericMapper(mapper);
    }

    @Override
    public Response getCollections(Request request) throws Exception {
        Integer userId = request.getUserId();
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        List<CollectModel> list = findModelsByCondition(po);
        Response response = Response.SUCCESS();
        response.addResultData("collections", list);
        return response;
    }
}
