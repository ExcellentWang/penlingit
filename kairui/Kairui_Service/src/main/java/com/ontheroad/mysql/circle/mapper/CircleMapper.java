package com.ontheroad.mysql.circle.mapper;

import com.ontheroad.circle.model.CircleModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * CircleDAO接口
 *
 * @author admin
 */
public interface CircleMapper extends GenericMapper<CircleModel, Integer> {

    List<CircleModel> getListByUserId(@Param("userId") Integer userId, @Param("rowBounds") RowBounds rowBounds);

    List<CircleModel> getIndexListByUserId(@Param("userId") Integer userId, @Param("rowBounds") RowBounds rowBounds);
}
