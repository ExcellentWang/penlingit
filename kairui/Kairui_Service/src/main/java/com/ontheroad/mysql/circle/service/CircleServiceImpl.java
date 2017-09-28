package com.ontheroad.mysql.circle.service;

import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.api.request.CircleRequest;
import com.ontheroad.api.response.CircleResponse;
import com.ontheroad.api.response.Response;
import com.ontheroad.api.response.UserResponse;
import com.ontheroad.circle.model.CircleModel;
import com.ontheroad.circle.model.CommentModel;
import com.ontheroad.circle.model.ReplyModel;
import com.ontheroad.circle.service.CircleService;
import com.ontheroad.like.model.CircleLikeModel;
import com.ontheroad.mysql.circle.mapper.CircleMapper;
import com.ontheroad.mysql.circle.mapper.CommentMapper;
import com.ontheroad.mysql.circle.mapper.ReplyMapper;
import com.ontheroad.mysql.like.mapper.CircleLikeMapper;
import com.ontheroad.mysql.path.mapper.PathUserMapper;
import com.ontheroad.mysql.user.mapper.UserMapper;
import com.ontheroad.path.model.PathUserModel;
import com.ontheroad.user.model.UserModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by kedong on 2017/6/29 0029.
 */
@Service
@Transactional
public class CircleServiceImpl extends BaseServiceImpl<CircleModel, Integer> implements CircleService {

    @Autowired
    private CircleMapper mapper;

    @Autowired
    private CircleLikeMapper likeMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PathUserMapper pathMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CircleLikeMapper circleLikeMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    public void serMapper(CircleMapper mapper) {
        setGenericMapper(mapper);
    }


    /**
     * todo 带轨迹的动态暂未处理
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Response publish(CircleRequest request) throws Exception {
        CircleModel circleModel = new CircleModel();
        circleModel.setUserid(request.getUserId());
        circleModel.setContent(request.getContent());
        circleModel.setImages(request.getImages());
        circleModel.setAddress(request.getAddress());
        circleModel.setLng(request.getLng());
        circleModel.setLat(request.getLat());
        circleModel.setCreateTime(new Date());
        mapper.insertModel(circleModel);
        Response response = Response.SUCCESS();
        response.addResultData("circleId", circleModel.getId());
        return response;
    }

    /**
     * 附近的动态列表 2017/6/29 0029
     *
     * @param request
     * @return
     */
    @Override
    public Response getNearCircleList(CircleRequest request) {
        // TODO somthing 2017/7/9 0009
        return null;
    }

    @Override
    public Response getUserCircleList(CircleRequest request) throws Exception {
        Integer targetId = request.getTargetId();
        // 分页
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
        List<CircleModel> list = mapper.getListByUserId(targetId, rowBounds);
        List<CircleResponse> circleResponses = new ArrayList<>();
        if (list.size() > 0) {
            for (CircleModel model : list) {
                CircleResponse response = new CircleResponse();
                response.setId(model.getId());
                response.setAddress(model.getAddress());
                response.setContent(model.getContent());
                response.setImages(model.getImages());
                response.setCreateTime(model.getCreateTime());
                PathUserModel path = model.getPath();
                if (path != null) {
                    response.setKilometer(path.getKilometer());
                    response.setPathIconUrl(path.getPathIconUrl());
                    response.setTime(path.getTime());
                    response.setPathid(path.getId());
                }
                circleResponses.add(response);
            }

        }
        list.add(new CircleModel());
        Response response = Response.SUCCESS();
        response.addResultData("list", circleResponses);
        return response;

    }

    @Override
    public Response getIndex(CircleRequest request) throws Exception {
        Integer userId = request.getUserId();
        // 查出好友列表
        // 分页
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
        List<CircleModel> list = mapper.getIndexListByUserId(userId, rowBounds);
        Response response = Response.SUCCESS();
        response.addResultData("list", list);
        return response;
    }

    @Override
    public Response getCircleDetail(CircleRequest request) throws Exception {
        Integer circleId = request.getCircleId();
        CircleModel circleModel = mapper.getObjectById(circleId);
        Integer pathId = circleModel.getPathid();
        if (pathId != null) {
            PathUserModel path = pathMapper.getObjectById(pathId);
            circleModel.setPath(path);
        }
        if (circleModel == null) {
            return Response.FAILED("该动态已被删除。");
        }
        // 获取四条顶级评论
        request.setPageSize(4);
        // 查询评论列表
        List<CommentModel> commentList = commentList(request);
        // 获取7个点赞的用户
        List<UserResponse> likeList = likeList(circleId, request);
        request.setPageSize(7);
        Response response = Response.SUCCESS();
        response.addResultData("circle", circleModel);
        response.addResultData("commentList", commentList);
        response.addResultData("likeList", likeList);
        return response;
    }

    @Override
    public Response like(CircleRequest request) {
        CircleLikeModel likeModel = new CircleLikeModel();
        likeModel.setCircleId(request.getCircleId());
        likeModel.setUserId(request.getUserId());
        likeMapper.insertModel(likeModel);
        return Response.SUCCESS();
    }

    @Override
    public Response unLike(CircleRequest request) {
        likeMapper.deleteByUserIdAndCircleId(request.getUserId(), request.getCircleId());
        return Response.SUCCESS();
    }

    /**
     * 评论
     *
     * @param request
     * @return
     */
    @Override
    public Response comment(CircleRequest request) {
        String content = request.getContent();
        Integer circleId = request.getCircleId();

        CommentModel commentModel = new CommentModel();
        commentModel.setCreateTime(new Date());
        commentModel.setUserid(request.getUserId());
        commentModel.setContent(content);
        commentModel.setCircleid(circleId);
        commentMapper.insertModel(commentModel);
        Response response = Response.SUCCESS();
        response.addResultData("commentId", commentModel.getId());
        return response;
    }

    /**
     * 评论
     *
     * @param request
     * @return
     */
    @Override
    public Response reply(CircleRequest request) {
        String content = request.getContent();
        Integer circleId = request.getCircleId();
        Integer commentId = request.getCommentId();
        Integer replyUserId = request.getReplyUserId();
        Integer userId = request.getUserId();

        ReplyModel reply = new ReplyModel();
        reply.setUserId(userId);
        reply.setContent(content);
        reply.setCircleId(circleId);
        reply.setCommentId(commentId);
        reply.setReplyUserId(replyUserId);
        reply.setCreateTime(new Date());

        replyMapper.insertModel(reply);
        Response response = Response.SUCCESS();
        response.addResultData("replyId", reply.getId());
        return response;
    }

    @Override
    public Response getCommentList(CircleRequest request) {
        List<CommentModel> commentList = commentList(request);
        Response response = Response.SUCCESS();
        response.addResultData("commentList", commentList);
        return response;
    }

    private List<CommentModel> commentList(CircleRequest request) {
        Integer circleId = request.getCircleId();
        // 分页
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
        po.addCondition("circleid", circleId);
        po.addCondition("noParentid", true);
        // 查询评论列表
        List<CommentModel> commentList = commentMapper.findModelsByCondition(po.getCondition(), rowBounds);
        commentList = replyList(circleId, commentList);
        return commentList;
    }

    /**
     * 获取回复列表
     *
     * @param circleId
     * @param commentList
     * @return
     */
    private List<CommentModel> replyList(Integer circleId, List<CommentModel> commentList) {
        if (commentList.size() > 0) {
            List<Integer> commentIds = new ArrayList<>();
            for (CommentModel commentModel : commentList) {
                commentIds.add(commentModel.getId());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("circleId", circleId);
            map.put("commentIds", commentIds);
            // 查询这些评论的回复列表
            List<ReplyModel> replyList = replyMapper.findModelsByCondition(map);
            // 将回复与评论对应起来
            for (CommentModel commentModel : commentList) {
                for (int i = 0; i < replyList.size(); i++) {
                    ReplyModel reply = replyList.get(i);
                    if (Objects.equals(reply.getCommentId(), commentModel.getId())) {
                        commentModel.getReplyList().add(reply);
                        replyList.remove(i);
                        i--;
                    }
                }
            }
        }
        return commentList;
    }

    @Override
    public Response likeList(CircleRequest request) {
        Integer circleId = request.getCircleId();
        List<UserResponse> list = likeList(circleId, request);
        Response response = Response.SUCCESS();
        response.addResultData("likeList", list);
        return response;
    }

    /**
     * 查询点赞列表
     *
     * @param circleId
     * @param request
     * @return
     */
    private List<UserResponse> likeList(Integer circleId, CircleRequest request) {
        // 分页
        PageObject po = new PageObject();
        po.setCurrPage(request.getPageNum());
        po.setPageSize(request.getPageSize());
        RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
        // 查询点赞的用户列表
        List<UserModel> list = userMapper.getLikeListByCircleId(circleId, rowBounds);
        List<UserResponse> users = new ArrayList<>();
        for (UserModel userModel : list) {
            UserResponse user = new UserResponse();
            user.setUserId(userModel.getId());
            user.setNick(userModel.getNick());
            user.setPic(userModel.getPic());
            users.add(user);
        }
        return users;
    }

}