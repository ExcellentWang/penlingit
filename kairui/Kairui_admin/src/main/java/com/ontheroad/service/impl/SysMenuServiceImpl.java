package com.ontheroad.service.impl;


import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.util.TreeUtil;
import com.ontheroad.dao.SysMenuMapper;
import com.ontheroad.dao.SysRoleMenuMapper;
import com.ontheroad.entity.MenuTree;
import com.ontheroad.entity.SysMenu;
import com.ontheroad.service.SysMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long>
		implements SysMenuService {
	
	@Autowired
	private SysMenuMapper mapper;
	
	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	
	@Autowired
	public void setMapper(SysMenuMapper mapper) {
		setGenericMapper(mapper);
	}
	
	@Override
	public int removeById(Long id){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("pMenuId", id);
		int flag = mapper.deleteByCondition(condition);
		return flag;
	}
	
	/**
	 * 删除选中的菜单，并删除关联表数据（菜单绑定角色）
	 */
	public void deleteMenuByIds(String[] menuIds){
		for(String menuId : menuIds){
			int count = 0;
			deleteMenuById(Long.parseLong(menuId),count);
		}		
	}
	
	/**
	 * 递归第删除菜单和子菜单
	 * @param menuId
	 */
	private void deleteMenuById(long menuId,int count){
		if(count>=5){
			return;
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("pMenuId", menuId);
		List<SysMenu> list = mapper.findModelsByCondition(condition); //查询当前菜单和下一层子菜单
		count++;
		if(list!=null && list.size()>0){
			for(SysMenu menu : list){
				if(menu.getMenuId()==menuId){ //删除当前菜单
					mapper.deleteById(menuId);
					//删除菜单和角色的绑定关系
					Map<String, Object> relativeCondition = new HashMap<String, Object>();
					relativeCondition.put("menuId", menuId);
					roleMenuMapper.deleteByCondition(relativeCondition);
				}
				else if(menu.getMenuPid()== menuId ){ //子菜单，递归删除
					deleteMenuById(menu.getMenuId(),count);
				}
			}
		}
	}
	
	@Override
	public String listTree(boolean expanded,boolean hideDisableMenus) {
		Map<String, Object> condition = new HashMap<String, Object>();
		//查询显示所有的菜单，包括被禁用的
		condition.put("orderByClause", "MENU_LEVEL,MENU_ORDER asc");
		List<SysMenu> allMenuList = findModelsByCondition(condition);
		//删除父节点不存在或父节点被禁用的子节点
	    removeDisableNodes(allMenuList);
		List<MenuTree> nodeList = new ArrayList<MenuTree>();
		for(SysMenu menu : allMenuList){
			boolean showNode = true;
			if(hideDisableMenus && menu.getMenuStatus()!=1){
				showNode = false;
			}
			if(showNode && menu.getMenuPid()!=null){  //过滤掉父节点不存在的脏数据
				MenuTree node = new MenuTree(menu);
				if(menu.getMenuId()==1){
					node.setRoot(true);
				}
				node.setExpanded(expanded);
				nodeList.add(node);	
			}			
		}
		List<MenuTree> tree = TreeUtil.getResult(nodeList);
		return JSONUtils.toJson(tree);
	}

	@Override
	public String listUserTree(Long userId, boolean expanded) {
		List<SysMenu> allMenuList = mapper.getAllMenus(userId);
		//删除父节点不存在或父节点被禁用的子节点
		removeDisableNodes(allMenuList);
		List<MenuTree> nodeList = new ArrayList<MenuTree>();
		for(SysMenu menu : allMenuList){	
			if(menu.getMenuPid()!=null && menu.getMenuStatus()==1){ //屏蔽掉父节点不存在的脏数据，且屏蔽掉被禁用的菜单
				MenuTree node = new MenuTree(menu);
				if(menu.getMenuId()==1){
					node.setRoot(true);
				}
				node.setExpanded(expanded);
				nodeList.add(node);		
			}				
		}
		List<MenuTree> tree = TreeUtil.getResult(nodeList);
		return JSONUtils.toJson(tree);
	}
	
	/**
	 * 删除父节点不存在或父节点被禁用的子节点
	 * @param allMenuList
	 */
	private void removeDisableNodes(List<SysMenu> allMenuList){
		for(int i=0;i<allMenuList.size();i++){
			SysMenu menu = allMenuList.get(i);
			checkParentNode(menu,allMenuList);					
		}
	}
	
		
	private  void  checkParentNode(SysMenu menu, List<SysMenu> menuList){
		boolean hasParent=false;
		long pid= menu.getMenuPid();		
		if(menu.getMenuId()==1){
			return ;
		}
		for(SysMenu m : menuList){
			if(m.getMenuId()==pid){
				hasParent=true;				
			}
			if(hasParent && m.getMenuStatus()!=1){
				menu.setMenuStatus(0); //附菜单被禁用，自动禁用子菜单
			}
			if(hasParent){
				break;
			}
		}
		if(!hasParent){
			menu.setMenuPid(null); //屏蔽掉父节点不存在的脏数据
		}
	}
	
	public  List<SysMenu>  selectMenusByUserId(Long userId){
		return mapper.getAllMenus(userId);
	}


	
	
}
