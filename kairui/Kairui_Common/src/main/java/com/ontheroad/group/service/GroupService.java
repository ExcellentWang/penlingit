package com.ontheroad.group.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.GroupRequest;
import com.ontheroad.api.request.GroupRequest.createGroup;
import com.ontheroad.api.response.Response;
import com.ontheroad.core.annotation.ValidateGroup;
import com.ontheroad.group.model.GroupModel;
import org.springframework.remoting.service.annotation.RemoteService;

/**
 * Created by kedong on 2017/7/4 0004.
 */
@RemoteService
public interface GroupService extends BaseService<GroupModel, Integer> {


    /**
     * 创建分组
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = createGroup.class)
    Response createGroup(GroupRequest request) throws Exception;

    /**
     * 添加好友
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response addFriends(GroupRequest request) throws Exception;

    /**
     * 从分组里面删除好友
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response deleteFriend(GroupRequest request) throws Exception;

    /**
     * 退出分组
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response exitGroup(GroupRequest request) throws Exception;
}
