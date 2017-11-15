package com.ontheroad.mysql.circle.mapper;

import com.ontheroad.circle.model.CommentModel;
import org.apache.ibatis.annotations.Param;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * CommentDAO接口
 *
 * @author admin
 */
public interface CommentMapper extends GenericMapper<CommentModel, Integer> {

}
