package com.ontheroad.mysql.user.service;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.danga.MemCached.MemCachedClient;
import com.ontheroad.api.request.CircleRequest;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.CircleResponse;
import com.ontheroad.api.response.Response;
import com.ontheroad.circle.service.CircleService;
import com.ontheroad.core.util.Md5Util;
import com.ontheroad.mysql.concern.mapper.ConcernMapper;
import com.ontheroad.mysql.user.mapper.UserMapper;
import com.ontheroad.service.SmsService;
import com.ontheroad.user.model.UserModel;
import com.ontheroad.user.service.UserService;
import com.ontheroad.utils.StringUtilsCommon;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*import com.ontheroad.utils.SmsUtil;*/

/**
 * Created by kedong on 2017/6/25 0021.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserModel, Integer> implements UserService {

    private Logger log = LoggerFactory.getLogger(getClass());

    // TODO 临时验证码
    private final String TEMP_CODE = "test";

//    private final boolean TEST = true;
    private final boolean TEST = false;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConcernMapper concernMapper;

    @Autowired
    private MemCachedClient memCachedClient;

    @Autowired
    private CircleService circleService;

    @Autowired
    private SmsService smsService;

    @Override
    public Response regist(UserRequest request) throws Exception {
        log.info("用户注册");

        String mobile = request.getMobile();
        // 验证码
        String verificache;
        if (TEST) {
            // TODO: 临时测试用，上线后要删除该代码 2017/6/24 0024
            verificache = TEMP_CODE;
        } else {
            verificache = (String) memCachedClient.get(mobile);
            // 用完之后就删除
            memCachedClient.delete(mobile);
        }
        String verification = request.getVerification();
        if (!verification.equals(verificache)) {
            return Response.FAILED("验证码错误。");
        }
        // 手机号是否已被注册
        UserModel userModel = userMapper.getUserByMobile(mobile);
        if (userModel != null) {
            return Response.FAILED("手机号已被注册");
        }
        //密码加密存储
        String md5Pwd = Md5Util.makeMd5Sum(request.getPassword().getBytes());

        // 插入用户基本信息
        UserModel user = new UserModel();
        user.setPassword(md5Pwd);
        user.setMobile(mobile);
        user.setIsEnable(true);
        user.setCreateTime(Calendar.getInstance().getTime());
        userMapper.insertModel(user);

        return Response.SUCCESS();
    }

    @Override
    public Response login(UserRequest request) throws Exception {
        Response response = null;

        String mobile = request.getMobile();
        String password = Md5Util.makeMd5Sum(request.getPassword().getBytes());
        UserModel user = userMapper.findByMobileAndPassword(mobile, password);
        if (user == null) {
            return Response.FAILED("用户或密码错误");
        }
        // 更新用户信息
        user.setPhoneModel(request.getPhoneModel());
        user.setSystemVersion(request.getSystemVersion());
        user.setAppVersion(request.getAppVersion());
        user.setClientId(request.getClientId());
        user.setSystemType(request.getSystemType());
        user.setLoginTime(new Date());

        userMapper.updateModelById(user);

        //用户id
        Integer userId = user.getId();
        // 查询该用户是否已登录，如果已登录则需要干掉之前的token，生成新的token
        String oldToken = (String) memCachedClient.get(String.valueOf(userId));
        if (StringUtils.isNotBlank(oldToken)) {
            // 存在token，说明用户处于登录状态中
            // 解除之前的token与userId的对应关系
            clearToken(userId);
            // TODO: 发送推送将之前设备下线 2017/6/29 0029
        }

        //生成token
        String token = StringUtilsCommon.getToken();

        // 设置token有效时间为2天
        Date express = new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000);
        //建立token和userId的对应关系（双向）
        memCachedClient.add(token, userId);
        memCachedClient.set(String.valueOf(userId), token);

        // 将token返回给app
        response = Response.SUCCESS();
        response.addResultData("token", token);
        // 手机号隐藏中间4位数字
        user.setMobile(StringUtilsCommon.changePhone(mobile));
        // 返回结果里面不需要包含密码
        user.setPassword(null);
        response.addResultData("user", user);

        return response;
    }

    @Override
    public Response getVerification(UserRequest request) throws Exception {
        // 生成验证码
        String verification = StringUtilsCommon.getRandNum(6);

        Map<Object, Object> sendResult = new HashMap<>(smsService.sendVerificationCode(request.getMobile(), verification));

        if (sendResult.get("resultMap") != null) {
            // 设置验证码有效时间为10分钟
            Date express = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
            // 短信发送成功，将验证放入缓存中，以手机号为key
            memCachedClient.set(request.getMobile(), verification, express);
        } else {
            return Response.FAILED("短信发送失败");
        }
        return Response.SUCCESS();
    }

    @Override
    public Response resetPassword(UserRequest request) throws Exception {
        Integer userId = request.getUserId();
        UserModel user = userMapper.getObjectById(userId);
        //密码加密存储
        String md5Pwd = Md5Util.makeMd5Sum(request.getPassword().getBytes());
        user.setPassword(md5Pwd);
        userMapper.updateModelById(user);

        // 清除原来的token,重新登录
        clearToken(userId);
        return Response.SUCCESS();
    }

    @Override
    public Response forgotPassword(UserRequest request) throws Exception {
        String mobile = request.getMobile();
        // 验证码
        String verificache;
        if (TEST) {
            // TODO: 临时测试用，上线后要删除该代码 2017/6/24 0024
            verificache = TEMP_CODE;
        } else {
            verificache = (String) memCachedClient.get(mobile);
        }
        String verification = request.getVerification();
        if (verification.equals(verificache)) {
            return Response.FAILED("验证码错误。");
        }
        UserModel user = userMapper.getUserByMobile(mobile);
        //密码加密存储
        String md5Pwd = Md5Util.makeMd5Sum(request.getPassword().getBytes());
        user.setPassword(md5Pwd);
        userMapper.updateModelById(user);
        return Response.SUCCESS();
    }

    @Override
    public Response logout(UserRequest request) throws Exception {
        Integer userId = request.getUserId();
        if (userId == null) {
            return Response.FAILED("该用户并未登录。");
        }

        boolean isSuccess = clearToken(userId);

        return isSuccess ? Response.SUCCESS() : Response.FAILED("退出登录失败");
    }

    @Override
    public Response editUserInfo(UserRequest request) throws Exception {
        Integer userId = request.getUserId();
        UserModel user = userMapper.getObjectById(userId);
        if (!user.getIsEnable()) {
            return Response.FAILED("您的账号已经被禁用，请尽快联系工作人员");
        }
        // 昵称
        if (StringUtils.isNotEmpty(request.getNick())) {
            user.setNick(request.getNick());
        }
        // 性别
        if (request.getSex() != null) {
            user.setSex(request.getSex());
        }
        // 年龄
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        // 地址
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        // 头像
        if (request.getPic() != null) {
            user.setPic(request.getPic());
        }
        userMapper.updateModelById(user);
        return Response.SUCCESS();
    }

    /**
     * APP 我 模块主页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Response myIndex(Request request) throws Exception {
        Response response = null;
        Integer userId = request.getUserId();
        UserModel userModel = userMapper.getObjectById(userId);
        response = Response.SUCCESS();
        // 头像
        response.addResultData("pic", userModel.getPic());
        // 昵称
        response.addResultData("nick", userModel.getNick());
        // 等级 todo 目前还没有等级机制
        response.addResultData("level", userModel.getLevel());
        // 粉丝数
        response.addResultData("fansCount", userModel.getFansCount());
        // 关注数
        response.addResultData("friendCount", userModel.getFriendCount());
        // todo 总里程数
        // todo 总积分
        // 运动次数
        Integer sportTimes = userMapper.getSportTimes(userId);
        response.addResultData("sportTimes", sportTimes);
        // 是否有系统消息
        Integer unReadMsgCount = userMapper.getUnReadMsgCount(userId);
        response.addResultData("unReadMsgCount", unReadMsgCount);
        return response;
    }

    /**
     * 好友主页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Response friendIndex(UserRequest request) throws Exception {
        Response response = null;
        Integer targetId = request.getTargetId();
        Integer userId = request.getUserId();

        UserModel userModel = userMapper.getObjectById(targetId);
        response = Response.SUCCESS();
        // 头像
        response.addResultData("pic", userModel.getPic());
        // 昵称
        response.addResultData("nick", userModel.getNick());
        // 简介
        response.addResultData("intro", userModel.getIntro());
        // 等级 todo 目前还没有等级机制
        response.addResultData("level", userModel.getLevel());
        // 粉丝数
        response.addResultData("fansCount", userModel.getFansCount());
        // 关注数
        response.addResultData("friendCount", userModel.getFriendCount());
        // 地区
        response.addResultData("address", userModel.getAddress());
        // 性别
        response.addResultData("sex", userModel.getSex());
        // 年龄
        response.addResultData("age", userModel.getAge());
        // 是否已关注
        List<Integer> friendIds = new ArrayList<>();
        friendIds.add(targetId);
        List<Integer> list = concernMapper.findMutualConcernByFriendIds(friendIds, userId);
        response.addResultData("isFollow", list.size() > 0);

        // 获取好友的动态列表
        CircleRequest circleRequest = new CircleRequest();
        circleRequest.setTargetId(targetId);
        Response circleList = circleService.getUserCircleList(circleRequest);
        List<CircleResponse> circleResponses = (List<CircleResponse>) circleList.getResultMap().get("list");
        response.addResultData("list", circleResponses);
        return response;
    }

    /**
     * 删除token
     *
     * @param userId
     * @return
     */
    private boolean clearToken(Integer userId) {
        try {
            String toke = (String) memCachedClient.get(userId + "");
            memCachedClient.delete(toke);
            memCachedClient.delete(userId + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
