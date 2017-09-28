package com.ontheroad.path.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.PathRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.path.model.PathUserModel;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/6/29 0029.
 */
@RemoteService
public interface PathService extends BaseService<PathUserModel, Integer> {

    /**
     * 上传运动轨迹
     *
     * @param request
     * @return
     */
    Response addPath(PathRequest request) throws Exception;

    /**
     * 获取轨迹详情
     *
     * @param request
     * @return
     */
    Response getPath(PathRequest request) throws Exception;

    /**
     * 获取系统轨迹列表
     *
     * @param request
     * @return
     */
    Response getSystemPathList(PathRequest request) throws Exception;


}
