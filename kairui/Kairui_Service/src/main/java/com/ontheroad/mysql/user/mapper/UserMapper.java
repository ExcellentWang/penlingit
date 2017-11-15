package com.ontheroad.mysql.user.mapper;

import com.ontheroad.system.entity.ApiMethodModel;
import com.ontheroad.user.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * UserDAO接口
 *
 * @author admin
 */
public interface UserMapper extends GenericMapper<UserModel,Integer>{

    UserModel findByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    UserModel getUserByMobile(String mobile);

    void updateFansCount(Integer freindId);

    void updateFriendCount(Integer userId);

    Integer getSportTimes(Integer userId);

    Integer getUnReadMsgCount(Integer userId);

    List<UserModel> getUserByIds(Integer[] friendIds);

    List<UserModel> getLikeListByCircleId(@Param("circleId") Integer circleId, @Param("rowBounds") RowBounds rowBounds);
}
