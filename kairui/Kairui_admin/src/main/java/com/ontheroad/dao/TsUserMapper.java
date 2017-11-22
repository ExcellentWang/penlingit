package com.ontheroad.dao;

import com.ontheroad.entity.TsUser;
import com.ontheroad.entity.TsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsUserMapper {
    int countByExample(TsUserExample example);

    int deleteByExample(TsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TsUser record);

    int insertSelective(TsUser record);

    List<TsUser> selectByExample(TsUserExample example);

    TsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TsUser record, @Param("example") TsUserExample example);

    int updateByExample(@Param("record") TsUser record, @Param("example") TsUserExample example);

    int updateByPrimaryKeySelective(TsUser record);

    int updateByPrimaryKey(TsUser record);
    /**
	 * 查询用户是否具有某资源权限
	 * @param user
	 * @return
	 */
	Integer getAccess(@Param("menuName")String menuName,@Param("userId")Integer userId);
}