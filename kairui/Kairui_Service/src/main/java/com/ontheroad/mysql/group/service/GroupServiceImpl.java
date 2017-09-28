package com.ontheroad.mysql.group.service;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.GroupRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.group.model.GroupModel;
import com.ontheroad.group.model.GroupUserModel;
import com.ontheroad.group.service.GroupService;
import com.ontheroad.mysql.group.mapper.GroupMapper;
import com.ontheroad.mysql.group.mapper.GroupUserMapper;
import com.ontheroad.mysql.user.mapper.UserMapper;
import com.ontheroad.user.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kedong on 2017/7/4 0004.
 * TODO 分组需求待明确具体细节
 */
@Service
@Transactional
public class GroupServiceImpl extends BaseServiceImpl<GroupModel, Integer> implements GroupService {

    @Autowired
    private GroupMapper mapper;

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setMapper(GroupMapper mapper) {
        setGenericMapper(mapper);
    }

    @Override
    public Response createGroup(GroupRequest request) throws Exception {
        Integer userId = request.getUserId();
        Integer type = request.getType();
        Integer[] friendIds = request.getFriendIds();
        UserModel admin = userMapper.getObjectById(userId);

        String groupName = request.getGroupName();
        // 设置分组名称为前3个好友的昵称
        if (StringUtils.isEmpty(groupName)) {
            groupName = getGroupName(friendIds, admin);
        }
        // 插入数据，创建分组记录
        GroupModel group = new GroupModel();
        group.setAdminId(userId);
        group.setName(groupName);
        group.setType(type);
        group.setCreateTime(new Date());
        mapper.insertModel(group);

        // 增加用户和分组关系
        Integer groupId = group.getId();
        List<GroupUserModel> list = new ArrayList<>();
        for (Integer friendId : friendIds) {
            GroupUserModel groupUserModel = new GroupUserModel();
            groupUserModel.setGroupId(groupId);
            groupUserModel.setUserId(friendId);
            list.add(groupUserModel);
        }
        groupUserMapper.bacthAdd(list);
        // todo 推送，其他人创建分组
        Response response = Response.SUCCESS();
        response.addResultData("groupId", groupId);
        return response;
    }

    private String getGroupName(Integer[] friendIds, UserModel admin) {
        StringBuilder name = new StringBuilder(admin.getNick());
        List<UserModel> friendList = userMapper.getUserByIds(friendIds);
        for (int i = 0; i < friendList.size(); i++) {
            // 取前面两个人的昵称组合就行
            if (i > 2) {
                break;
            }
            name.append(",");
            name.append(friendList.get(i).getNick());
            name.append(",");
        }
        name.append("...");
        return name.toString();
    }

    @Override
    public Response addFriends(GroupRequest request) throws Exception {
        return null;
    }

    @Override
    public Response deleteFriend(GroupRequest request) throws Exception {
        return null;
    }

    @Override
    public Response exitGroup(GroupRequest request) throws Exception {
        return null;
    }
}
