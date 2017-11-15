package com.ontheroad.mysql.sport.service;

import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.request.SprotRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.api.response.SportResponse;
import com.ontheroad.mysql.sport.mapper.SportMapper;
import com.ontheroad.sport.model.SportModel;
import com.ontheroad.sport.service.SportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kedong on 2017/6/27 0027.
 */
@Service
@Transactional
public class SportServiceImpl extends BaseServiceImpl<SportModel, Integer> implements SportService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SportMapper mapper;

    @Autowired
    public void setMapper(SportMapper mapper) {
        setGenericMapper(mapper);
    }

    @Override
    public Response uploadSport(SprotRequest request) throws Exception {
        SportModel sportModel = new SportModel();
        sportModel.setUserid(request.getUserId());
        sportModel.setDuration(request.getDuration());
        sportModel.setEndTime(request.getEndTime());
        sportModel.setKilometre(request.getKilometre());
        sportModel.setType(request.getType());
        mapper.insertModel(sportModel);
        return Response.SUCCESS();
    }

    @Override
    public Response getSportList(Request request) throws Exception {
        Integer userId = request.getUserId();

        PageObject pageObject = new PageObject();
        pageObject.addCondition("userid", userId);
        pageObject.setCurrPage(request.getPageNum());
        pageObject.setPageSize(request.getPageSize());
        List<SportModel> list = findModelsByCondition(pageObject);
        return new SportResponse(list);
    }
}
