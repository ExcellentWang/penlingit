package com.ontheroad.system.web;

import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.HypyUtil;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.util.SpringSecurityUtils;
import com.ontheroad.core.web.BaseController;
import com.ontheroad.system.entity.SysRole;
import com.ontheroad.system.entity.SysRoleMenu;
import com.ontheroad.system.entity.SysRoleUser;
import com.ontheroad.system.entity.SysUser;
import com.ontheroad.system.service.SysDictService;
import com.ontheroad.system.service.SysMenuService;
import com.ontheroad.system.service.SysRoleService;
import com.ontheroad.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/role")
@OpLog(logDesc="角色控制")
public class RoleController extends BaseController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "system/role/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysUser user) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"ROLE_ORDER asc");
		po.getCondition().put("roleNameLike", request.getParameter("roleName"));
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysRoleService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		return "system/role/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,SysRole sRole){
		sRole.setCreateTime(new Date());
		sRole.setUpdateTime(new Date());
		sysRoleService.insert(sRole);
		return "success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		SysRole sRole = sysRoleService.findById(roleId);
		request.setAttribute("sRole", sRole);
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "system/role/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,SysRole sRole){
		sRole.setUpdateTime(new Date());
		sysRoleService.updateById(sRole);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,PrintWriter out){
		log.debug("method: delete() ");
		String msg = "操作成功";
		boolean result = true;
		try {
			String ids = request.getParameter("ids");
			String[] roleIds = ids.split(",");
			sysRoleService.deleteRoleByIds(roleIds);
		} catch (Exception e) {
			msg = "系统发生异常！";
			result = false;
		}
		ajaxJsonResponse(out, result, msg);
	}
	
	@RequestMapping(value = "checkRoleName")
	public void checkRoleName(HttpServletRequest request,HttpServletResponse response,SysRole role) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleName", role.getRoleName());
		condititon.put("notRoleId", role.getRoleId());
		int flag = sysRoleService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
	
	@RequestMapping(value = "menuTree")
	public String menuTree(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SysRoleMenu> SRoleMenu = sysRoleService.selectRoleMenuByCondition(roleId);
		request.setAttribute("roleId", roleId);
		request.setAttribute("SRoleMenuJson", JSONUtils.toJson(SRoleMenu));
		String resourceTree = sysMenuService.listTree(true,false);
		request.setAttribute("resourceTree", resourceTree);
		return "system/role/menuTree";
	}
	@RequestMapping(value = "bindMenu")
	public String bindMenu(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String menuIds = request.getParameter("menuIds");
		if(menuIds!=null&&menuIds!=""){
			String aMenuIds[] = menuIds.split(",");
			sysRoleService.bindRoleMenu(roleId, aMenuIds);
			SpringSecurityUtils.clearAllCachedAuthorizationInfo();
		}
		return "success";
	}
	@RequestMapping(value = "userDialog")
	public String userDialog(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SysRoleUser> sUserRoleList = sysRoleService.selectSUserRoleByCondition(roleId);
		Map<String, Object> condition = new HashMap<String, Object>();
		List<SysUser>sUserList = sysUserService.findModelsByCondition(condition);
		request.setAttribute("roleId", roleId);
		request.setAttribute("sUserRoleList", JSONUtils.toJson(sUserRoleList));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String letter[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<letter.length;i++){
			List<HashMap<String,String>>lUserList = new ArrayList<HashMap<String,String>>();
			for(int j=0;j<sUserList.size();j++){
				SysUser sUser = sUserList.get(j);
				String userName = sUser.getUserName();
				String l = HypyUtil.cn2py(userName.substring(0,1));//用户名首字母
				HashMap<String,String> m = new HashMap<String,String>();
				m.put("id", sUser.getUserId().toString());
				m.put("name", userName+"/"+sUser.getRealName());
				if(letter[i].equals(l)){
					lUserList.add(m);
				}
			}
			if(lUserList.size()>0){
				dataMap.put(letter[i], lUserList);
			}
		}
		request.setAttribute("userData", JSONUtils.toJson(dataMap));
		return "system/role/roleUser";
	}
	
	@RequestMapping(value = "bindUser")
	public String bindUser(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String userIds = request.getParameter("userIds");
		if(userIds!=null&&userIds!=""){
			String aUserIds[] = userIds.split(",");
			sysRoleService.bindRoleUser(roleId, aUserIds);
			SpringSecurityUtils.clearAllCachedAuthorizationInfo();
		}
		return "success";
	}
	
}
