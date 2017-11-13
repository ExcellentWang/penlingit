package com.ontheroad.controller.UserManage;

import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.HypyUtil;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.util.SpringUtils;
import com.ontheroad.dao.SysDetailMapper;
import com.ontheroad.dao.SysDictMapper;
import com.ontheroad.entity.SysDetail;
import com.ontheroad.entity.SysDict;
import com.ontheroad.entity.SysRole;
import com.ontheroad.entity.SysRoleMenu;
import com.ontheroad.entity.SysRoleUser;
import com.ontheroad.entity.SysUser;
import com.ontheroad.service.SysDictService;
import com.ontheroad.service.SysManageUserService;
import com.ontheroad.service.SysMenuService;
import com.ontheroad.service.SysRoleService;

import com.ontheroad.core.annotation.OpLog;
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
@RequestMapping(value = "/system/role")
@OpLog(logDesc="角色控制")
public class RoleController extends BaseController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysManageUserService sysUserService;
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "Modal/task/userManage/system/role/init";
	}
	
	public Hashtable<String, Map<String, String>>loadData(){
		SysDictMapper sDictMapper = (SysDictMapper) SpringUtils.getBean(SysDictMapper.class);
		SysDetailMapper SysDetailMapper = (SysDetailMapper) SpringUtils.getBean(SysDetailMapper.class); 
		Hashtable<String, Map<String, String>> dict = new Hashtable<String, Map<String, String>>();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		List<SysDict>sDictList = sDictMapper.findModelsByCondition(null);
		List<SysDetail> SysDetailList = SysDetailMapper.findModelsByCondition(para);
		Map<String, String> dictDetailMap = null;
		for(SysDict sd:sDictList){
			String dictId = sd.getDictId().toString();
			dictDetailMap = new HashMap<String, String>();
			for (SysDetail SysDetail : SysDetailList) {
				if(dictId.equals(SysDetail.getDictId().toString())){
					dictDetailMap.put(SysDetail.getDetailValue(),SysDetail.getDetailName());
				}
			}
			dict.put(sd.getDictName(), dictDetailMap);
		}
		return dict;
	}
	
	@RequestMapping(value = "dictStatusMap")
    @ResponseBody
    public void dictStatusMap(HttpServletRequest request, HttpServletResponse response) {
		Hashtable<String, Map<String, String>> dict = loadData();
		Map<String, String> statusMap = dict.get("状态");
		Map<String, String> sexMap = dict.get("地区");
		
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("statusMap", JSONUtils.toJson(statusMap));
		returnMap.put("statusCombo", JSONUtils.toJson(JSONUtils.toCombo(statusMap)));
		returnMap.put("districtMap", JSONUtils.toJson(sexMap));
		returnMap.put("districtCombo", JSONUtils.toJson(JSONUtils.toCombo(sexMap)));
		
		writeToPage(JSONUtils.toJson(returnMap), response);
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
		
		Hashtable<String, Map<String, String>> dict = loadData();
		Map<String, String> statusMap = dict.get("状态");
		Map<String, String> sexMap = dict.get("地区");
		
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		request.setAttribute("districtMap", JSONUtils.toJson(sexMap));
		request.setAttribute("districtMapCombo", JSONUtils.toCombo(sexMap));
		
		writeToPage(JSONUtils.toJson(sysRoleService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		Hashtable<String, Map<String, String>> dict = loadData();
		Map<String, String> statusMap = dict.get("状态");
		Map<String, String> sexMap = dict.get("地区");
		
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		request.setAttribute("districtMap", JSONUtils.toJson(sexMap));
		request.setAttribute("districtMapCombo", JSONUtils.toCombo(sexMap));
		return "Modal/task/userManage/system/role/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,SysRole sRole){
		sRole.setCreateTime(new Date());
		sRole.setUpdateTime(new Date());
		sysRoleService.insert(sRole);
		return "Modal/task/userManage/system/success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		SysRole sRole = sysRoleService.findById(roleId);
		request.setAttribute("sRole", sRole);
		
		Hashtable<String, Map<String, String>> dict = loadData();
		Map<String, String> statusMap = dict.get("状态");
		Map<String, String> sexMap = dict.get("地区");
		
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		request.setAttribute("districtMap", JSONUtils.toJson(sexMap));
		request.setAttribute("districtMapCombo", JSONUtils.toCombo(sexMap));
		return "Modal/task/userManage/system/role/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,SysRole sRole){
		sRole.setUpdateTime(new Date());
		sysRoleService.updateById(sRole);
		return "Modal/task/userManage/system/success";
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
			e.printStackTrace();
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
		return "Modal/task/userManage/system/role/menuTree";
	}
	@RequestMapping(value = "bindMenu")
	public String bindMenu(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String menuIds = request.getParameter("menuIds");
		if(menuIds!=null&&menuIds!=""){
			String aMenuIds[] = menuIds.split(",");
			sysRoleService.bindRoleMenu(roleId, aMenuIds);
		}
		return "Modal/task/userManage/system/success";
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
		return "Modal/task/userManage/system/role/roleUser";
	}
	
	@RequestMapping(value = "bindUser")
	public String bindUser(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String userIds = request.getParameter("userIds");
		if(userIds!=null&&userIds!=""){
			String aUserIds[] = userIds.split(",");
			sysRoleService.bindRoleUser(roleId, aUserIds);
		}
		return "Modal/task/userManage/system/success";
	}
	
}
