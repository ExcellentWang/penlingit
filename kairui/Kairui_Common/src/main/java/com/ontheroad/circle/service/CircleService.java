package com.ontheroad.circle.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.CircleRequest;
import com.ontheroad.api.request.CircleRequest.*;
import com.ontheroad.api.response.Response;
import com.ontheroad.circle.model.CircleModel;
import com.ontheroad.core.annotation.ValidateGroup;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/6/29 0029.
 * 发现模块接口
 */
@RemoteService
public interface CircleService extends BaseService<CircleModel, Integer> {

    /**
     * 发布动态
     *
     * @param request
     * @return
     */
    Response publish(CircleRequest request) throws Exception;

    /**
     * 附近动态列表
     *
     * @param request
     * @return
     */
    Response getNearCircleList(CircleRequest request);


    /**
     * 通过用户ID查看某人的动态列表
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = getUserCircleList.class)
    Response getUserCircleList(CircleRequest request) throws Exception;

    /**
     * 查看我的主页
     *
     * @param request
     * @return
     */
    Response getIndex(CircleRequest request) throws Exception;

    /**
     * 查看动态详情
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = getCircleDetail.class)
    Response getCircleDetail(CircleRequest request) throws Exception;

    /**
     * 给动态点赞
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = like.class)
    Response like(CircleRequest request);

    /**
     * 撤销点赞
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = like.class)
    public Response unLike(CircleRequest request);

    /**
     * 评论
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = comment.class)
    Response comment(CircleRequest request);

    /**
     * 回复
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = reply.class)
    Response reply(CircleRequest request);

    /**
     * 获取评论列表
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = getCommentList.class)
    Response getCommentList(CircleRequest request);

    /**
     * 点赞列表
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = likeList.class)
    Response likeList(CircleRequest request);

}
