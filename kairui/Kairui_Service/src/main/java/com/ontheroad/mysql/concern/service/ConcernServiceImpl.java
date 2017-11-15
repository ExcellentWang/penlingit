package com.ontheroad.mysql.concern.service;

import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.ConcernRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.concern.modle.ConcernModel;
import com.ontheroad.concern.service.ConcernService;
import com.ontheroad.mysql.concern.mapper.ConcernMapper;
import com.ontheroad.mysql.user.mapper.UserMapper;
import com.ontheroad.user.model.UserModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by kedong on 2017/7/2 0002.
 */
@Service
@Transactional
public class ConcernServiceImpl extends BaseServiceImpl<ConcernModel, Integer> implements ConcernService {

    @Autowired
    private ConcernMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void serMapper(ConcernMapper mapper) {
        setGenericMapper(mapper);
    }

    @Override
    public Response follow(ConcernRequest request) throws Exception {
        Integer freindId = request.getFreindId();
        UserModel freind = userMapper.getObjectById(freindId);
        if (freind == null) {
            return Response.FAILED("您要关注的用户不存在");
        } else if (!freind.getIsEnable()) {
            return Response.FAILED("您要关注的用户已被禁用");
        }

        Integer userId = request.getUserId();
        ConcernModel model = new ConcernModel(userId, freindId);
        // 关注成功后更新我的关注数量
        userMapper.updateFriendCount(userId);
        // 更新被关注用户的粉丝数量
        userMapper.updateFansCount(freindId);
        return Response.SUCCESS();
    }

    @Override
    public Response freindList(ConcernRequest request) throws Exception {
        List<UserModel> friends = getUerList(request, 2);
        List<UserModel> fans = getUerList(request, 1);
        Response response = Response.SUCCESS();
        response.addResultData("friends", friends);
        response.addResultData("fans", fans);
        return response;
    }


    /**
     * @param request
     * @param type    1:粉丝列表；2:好友列表
     * @return
     */
    private List<UserModel> getUerList(ConcernRequest request, Integer type) {
        Integer userId = request.getUserId();
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        RowBounds bounds = new RowBounds(po.getOffset(), po.getPageSize());
        List<UserModel> users = null;
        if (type == 1) {
            users = mapper.getFansList(userId, bounds);
        } else if (type == 2) {
            users = mapper.getFriendList(userId, bounds);
        }

        // 是否有相互关注的
        if (users != null & users.size() > 0) {
            List<Integer> ids = new ArrayList<>();
            for (UserModel user : users) {
                ids.add(user.getId());
            }
            List<Integer> userIds = null;
            if (type == 1) {
                userIds = mapper.findMutualConcernByFansIds(ids, userId);
            } else if (type == 2) {
                userIds = mapper.findMutualConcernByFriendIds(ids, userId);
            }
            if (userIds != null && userIds.size() > 0) {
                for (UserModel user : users) {
                    for (Integer id : userIds) {
                        if (Objects.equals(id, user.getId())) {
                            user.setIsMutualConcern(true);
                            break;
                        }
                    }
                }
            }
        }

        return users;
    }
}
