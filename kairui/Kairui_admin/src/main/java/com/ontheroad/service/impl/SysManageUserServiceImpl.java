package com.ontheroad.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.mapper.interfaces.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.dao.SysRoleUserMapper;
import com.ontheroad.dao.SysUserMapper;
import com.ontheroad.entity.SysUser;
import com.ontheroad.service.SysManageUserService;

import cn.modoumama.page.GridDataModel;
import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.impl.BaseServiceImpl;

@Service
@Transactional
public class SysManageUserServiceImpl extends BaseServiceImpl<SysUser, Long>
		implements SysManageUserService {
	
	@Autowired
	private GenericMapper<SysUser, Long> genericMapper;
	
	@Autowired
	private SysRoleUserMapper roleUsermapper;
	@Autowired
	private SysUserMapper  sysUserMapper;
	@Autowired
	public void setMapper(SysUserMapper mapper) {
		setGenericMapper(mapper);
	}
	
	@Override
	public void insert(SysUser record){
		record.setUserStatus(1);
		record.setUserType(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		super.insert(record);
	}
	
	@Override
	public int updateById(SysUser record){
		record.setUpdateTime(new Date());
		int flag = super.updateById(record);
		return flag;
	}
	
	@Override
	public void updateUserPwd(SysUser record) {
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		record.setUpdateTime(new Date());
		super.updateById(record);
	}
	
	public  void deleteUserByIds(String[] userIds){
		for(String userId : userIds){
			//删除用户
			removeById(Long.parseLong(userId));
			//删除用户和角色的绑定关系
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("userId", userId);					
			roleUsermapper.deleteByCondition(condition);
		}
		
	}

	public cn.modoumama.page.GridDataModel getGridDataModelByCondition4UserRole(PageObject po) {
			    Integer totalCount = countByCondition(po.getCondition());
			    RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
			    List results = sysUserMapper.findModelsByCondition4UserRole(po.getCondition(), rowBounds);
			    return new GridDataModel(results, totalCount.intValue());

	}
	
	
	
}
