package com.ontheroad.mysql.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ontheroad.mysql.system.mapper.SysRoleMenuMapper;
import com.ontheroad.mysql.system.mapper.SysRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.system.entity.SysRole;
import com.ontheroad.system.entity.SysRoleMenu;
import com.ontheroad.system.entity.SysRoleUser;
import com.ontheroad.system.service.SysRoleService;
import com.ontheroad.mysql.system.mapper.SysRoleMapper;

@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long>
		implements SysRoleService {
	
	@Autowired
	private SysRoleMenuMapper sRoleMenuMapper;
	@Autowired
	private SysRoleUserMapper sRoleUserMapper;
	@Autowired
	private SysRoleMapper sRoleMapper;

	@Autowired
	public void setMapper(SysRoleMapper mapper) {
		setGenericMapper(mapper);
	}

	@Override
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sRoleMenuMapper.findModelsByCondition(condititon);
	}

	
	@Override
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sRoleUserMapper.findModelsByCondition(condititon);
	}
		
	public List<SysRole> selectRolesByUserId(Long userId){
		return sRoleMapper.selectRolesByUserId(userId);
	}
	
	
    public void  bindRoleMenu(Long roleId,String[] menuIds){
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("roleId", roleId);
		sRoleMenuMapper.deleteByCondition(condition);
		for(int i=0;i<menuIds.length;i++){
			SysRoleMenu record = new SysRoleMenu();
			record.setRoleId(roleId);
			record.setMenuId(Long.valueOf(menuIds[i]));
			sRoleMenuMapper.insertModel(record);
		}		
    }
	
	public void  bindRoleUser(Long roleId,String[] userIds){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("roleId", roleId);
		sRoleUserMapper.deleteByCondition(condition);
		for(int i=0;i<userIds.length;i++){
			SysRoleUser record = new SysRoleUser();
			record.setRoleId(roleId);
			record.setUserId(Long.valueOf(userIds[i]));
			sRoleUserMapper.insertModel(record);
		}		
	}
	
	public  void  deleteRoleByIds(String[] roleIds){
		for(String roleId : roleIds){
			//删除角色
			removeById(Long.parseLong(roleId));	
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("roleId", roleId);					
			//删除角色和菜单的绑定关系
			sRoleMenuMapper.deleteByCondition(condition);
			//删除角色和用户的绑定关系
			sRoleUserMapper.deleteByCondition(condition);
		}		
	}
}
