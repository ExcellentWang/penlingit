package com.ontheroad.controller.UserManage;


import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.util.SpringUtils;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.util.SysInitConfig;
import com.ontheroad.dao.SysDetailMapper;
import com.ontheroad.dao.SysDictMapper;
import com.ontheroad.dao.SysUserMapper;
import com.ontheroad.entity.SysDetail;
import com.ontheroad.entity.SysDict;
import com.ontheroad.entity.SysRole;
import com.ontheroad.entity.SysUser;
import com.ontheroad.service.SysDictService;
import com.ontheroad.service.SysManageUserService;
import com.ontheroad.service.SysRoleService;
import com.ontheroad.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.mapper.interfaces.GenericMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/system/user")
@OpLog(logDesc = "系统操作员管理")
public class UserManageController extends BaseController {
    Logger log = LoggerFactory.getLogger(UserManageController.class);
    @Autowired
    private SysManageUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysManageUserService sysManageUserService;
    @Autowired
	private GenericMapper<SysUser, Long> genericMapper;
    @Autowired
	private SysUserMapper  sysUserMapper;

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
    
    @RequestMapping(value = "init")
    public String init(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("initPassword", SysInitConfig.getInstance().get(SysInitConfig.CfgProp.DABOO_DEFAULTPASSWORD));
        request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        
        //writeToPage(JSONUtils.mapToJson(map)(statusMap), response);
        return "system/user/init";
    }
    
    @RequestMapping(value = "dictStatusMap")
    @ResponseBody
    public void dictStatusMap(HttpServletRequest request, HttpServletResponse response) {
		Hashtable<String, Map<String, String>> dict = loadData();
		Map<String, String> statusMap = dict.get("状态");
		Map<String, String> sexMap = dict.get("性别");
		
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("statusMap", JSONUtils.toJson(statusMap));
		returnMap.put("statusCombo", JSONUtils.toJson(JSONUtils.toCombo(statusMap)));
		returnMap.put("sexMap", JSONUtils.toJson(sexMap));
		returnMap.put("sexCombo", JSONUtils.toJson(JSONUtils.toCombo(sexMap)));
		
		writeToPage(JSONUtils.toJson(returnMap), response);
    }
    

    @RequestMapping(value = "list")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response, SysUser user, HttpSession session) {
        log.debug("method: list() ");
        PageObject po = getPageObject(request, "USER_ORDER asc");
        po.getCondition().put("userNameLike", user.getUserName());
        po.getCondition().put("realName", user.getRealName());
        po.getCondition().put("userStatus", user.getUserStatus());
        po.getCondition().put("createTimeStart", request.getParameter("createTimeStart"));
        po.getCondition().put("createTimeEnd", request.getParameter("createTimeEnd"));
        po.getCondition().put("updateTimeStart", request.getParameter("updateTimeStart"));
        po.getCondition().put("updateTimeEnd", request.getParameter("updateTimeEnd"));
        
        Hashtable<String, Map<String, String>> dict = loadData();
        Map<String, String> statusMap = dict.get("状态");
        
        session.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        session.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = dict.get("性别");
        session.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        session.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        
        Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("statusMap", JSONUtils.toJson(statusMap));
		returnMap.put("statusCombo", JSONUtils.toJson(JSONUtils.toCombo(statusMap)));
		returnMap.put("sexMap", JSONUtils.toJson(sexMap));
		returnMap.put("sexCombo", JSONUtils.toJson(JSONUtils.toCombo(sexMap)));
        
        //List<SysRole> roleList = sysRoleService.selectRolesByUserId(userId);
        Map<String, Object> condition = new HashMap<String, Object>();
        //List<SysRole> roleList = sys
        List<SysRole> roleList = sysRoleService.findModelsByCondition(condition);
        writeToPage(JSONUtils.toJson(sysManageUserService.getGridDataModelByCondition4UserRole(po)), response);
       /* SysDictMapper sDictMapper = (SysDictMapper) SpringUtils.getBean(SysDictMapper.class);
        
        Integer totalCount = genericMapper.countByCondition(po.getCondition());
        RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
	    List results = sysUserMapper.findModelsByCondition4UserRole(po.getCondition(), rowBounds);
        
        writeToPage(JSONUtils.toJson(new GridDataModel(results, totalCount.intValue())), response);*/
    }

    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request) {
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        request.setAttribute("initPassword", SysInitConfig.getInstance().get(SysInitConfig.CfgProp.DABOO_DEFAULTPASSWORD));
        return "Modal/task/userManage/system/user/add";
    }

    @RequestMapping(value = "insert")
    public String insert(HttpServletRequest request, SysUser user) {
        sysUserService.insert(user);
        return "Modal/task/userManage/system/success";
    }

    @RequestMapping(value = "showEdit")
    public String showEdit(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("user_id"));
        SysUser sUser = sysUserService.findById(userId);
        request.setAttribute("sUser", sUser);
        List<SysRole> thisrole = sysRoleService.selectRolesByUserId(userId);
        if(thisrole.size() > 0)
        request.setAttribute("sRole", thisrole.get(0).getRoleName());
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        Map<String, Object> arg0 = new HashMap();
        List<SysRole> sysroles  = sysRoleService.findModelsByCondition(arg0);
        Map<String, String> sysroleMap = new HashMap<>();;
        for(SysRole sysRole : sysroles){
        	sysroleMap.put(String.valueOf(sysRole.getRoleId()), sysRole.getRoleName());
        }
        request.setAttribute("sysroleCombo", JSONUtils.toCombo(sysroleMap));
        
        return "Modal/task/userManage/system/user/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request, SysUser user) {
    	
    	if(null == user){
    		Long i = Long.valueOf(request.getParameter("userId"));
    		user.setUserId(i);
    	}
    	
        SysUser sysUser = sysUserService.findById(user.getUserId());
        sysUser.setEmail(StringUtils.isNotBlank(user.getEmail())?user.getEmail():"");
        sysUser.setMobile(StringUtils.isNotBlank(user.getMobile())?user.getMobile():"");
        sysUser.setRealName(StringUtils.isNotBlank(user.getRealName())?user.getRealName():"");
        sysUser.setUserName(StringUtils.isNotBlank(user.getUserName())?user.getUserName():"" );
        sysUser.setSex(user.getSex());
        sysUser.setUserOrder(user.getUserOrder());
        sysUser.setUserType(user.getUserType());
        sysUser.setUserStatus(user.getUserStatus());//状态变更
        sysUserService.updateById(sysUser);
        return "Modal/task/userManage/system/success";
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public void delete(HttpServletRequest request, PrintWriter out) {
        log.debug("method: delete() ");
        String msg = "操作成功";
        boolean result = true;
        try {
            String ids = request.getParameter("ids");
            String[] userIds = ids.split(",");
            sysUserService.deleteUserByIds(userIds);
        } catch (Exception e) {
            msg = "系统发生异常！";
            result = false;
        }
        ajaxJsonResponse(out, result, msg);
    }

    @RequestMapping(value = "initPwd")
    public void initPwd(HttpServletRequest request, PrintWriter out) {
        log.debug("method: initPwd() ");
        String msg = "操作成功";
        boolean result = true;
        try {
            String ids = request.getParameter("ids");
            String[] aId = ids.split(",");
            for (String id : aId) {
                SysUser sUser = sysUserService.findById(Long.valueOf(id));
                sUser.setUserId(Long.valueOf(id));
                sUser.setUserPwd(SysInitConfig.getInstance().get(SysInitConfig.CfgProp.DABOO_DEFAULTPASSWORD));
                sUser.setUpdateTime(new Date());
                sysUserService.updateUserPwd(sUser);
            }
        } catch (Exception e) {
            msg = "系统发生异常！";
            result = false;
        }
        ajaxJsonResponse(out, result, msg);
    }

    @RequestMapping(value = "checkUserName")
    public void checkUserName(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SysUser user) {
        Map<String, Object> condititon = new HashMap<String, Object>();
        condititon.put("userName", user.getUserName());
        condititon.put("notUserId", user.getUserId());
        int flag = sysUserService.countByCondition(condititon);
        writeToPage(flag > 0 ? "false" : "true", response);
    }

}
