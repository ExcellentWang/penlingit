package com.ontheroad.mysql.group.mapper;

import com.ontheroad.group.model.GroupUserModel;
import org.mybatis.mapper.interfaces.GenericMapper;

import java.util.List;


/**
 * GroupUserDAO接口
 *
 * @author admin
 */
public interface GroupUserMapper extends GenericMapper<GroupUserModel, Integer> {
    void bacthAdd(List<GroupUserModel> list);

    //------------------请在此添加自定义方法（开始）------------------
    //------------------请在此添加自定义方法（结束）------------------


}
