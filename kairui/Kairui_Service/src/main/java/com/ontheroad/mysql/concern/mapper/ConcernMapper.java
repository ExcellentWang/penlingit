package com.ontheroad.mysql.concern.mapper;

import com.ontheroad.concern.modle.ConcernModel;
import com.ontheroad.user.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * ConcernDAO接口
 *
 * @author admin
 */
public interface ConcernMapper extends GenericMapper<ConcernModel, Integer> {
    /**
     * @param userId
     * @param rowbounds
     * @return
     */
    List<UserModel> getFansList(@Param("userId") Integer userId, @Param("rowbounds") RowBounds rowbounds);

    List<UserModel> getFriendList(@Param("userId") Integer userId, @Param("rowbounds") RowBounds rowbounds);

    List<Integer> findMutualConcernByFansIds(@Param("fansIds") List<Integer> fansIds, @Param("userId") Integer userId);

    List<Integer> findMutualConcernByFriendIds(@Param("friendIds") List<Integer> friendIds, @Param("userId") Integer userId);

    Integer getfansCount(Integer userId);

    Integer getFriendCount(Integer userId);

    //------------------请在此添加自定义方法（开始）------------------
    //------------------请在此添加自定义方法（结束）------------------


}
