package com.ontheroad.mysql.path.service;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.PathRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.mysql.path.mapper.PathUserMapper;
import com.ontheroad.path.model.PathUserModel;
import com.ontheroad.path.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kedong on 2017/6/29 0029.
 */
@Service
@Transactional
public class PathServiceImpl extends BaseServiceImpl<PathUserModel, Integer> implements PathService {

    @Autowired
    private PathUserMapper mapper;

    @Autowired
    public void setMapper(PathUserMapper mapper) {
        setGenericMapper(mapper);
    }


    @Override
    public Response addPath(PathRequest request) throws Exception {
        return null;
    }

    @Override
    public Response getPath(PathRequest request) throws Exception {
        return null;
    }

    @Override
    public Response getSystemPathList(PathRequest request) throws Exception {
        return null;
    }
}
