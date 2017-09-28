package com.ontheroad.mysql.like.mapper;

import com.ontheroad.like.model.CircleLikeModel;
import com.ontheroad.user.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * CircleLikeDAO接口
 *
 * @author admin
 */
public interface CircleLikeMapper extends GenericMapper<CircleLikeModel,Integer>{
    void deleteByUserIdAndCircleId(@Param("userId") Integer userId, @Param("circleId") Integer circleId);

    //------------------请在此添加自定义方法（开始）------------------
	//------------------请在此添加自定义方法（结束）------------------

    
}
