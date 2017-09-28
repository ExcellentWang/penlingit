package com.ontheroad.system.web;


import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.util.SysInitConfig;
import com.ontheroad.core.web.BaseController;
import com.ontheroad.system.entity.SysUser;
import com.ontheroad.system.service.SysDictService;
import com.ontheroad.system.service.SysUserService;
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
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/user")
@OpLog(logDesc = "系统操作员管理")
public class UserController extends BaseController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDictService sysDictService;

    @RequestMapping(value = "init")
    public String init(HttpServletRequest request) {
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("initPassword", SysInitConfig.getInstance().get(SysInitConfig.CfgProp.DABOO_DEFAULTPASSWORD));
        request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        return "system/user/init";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response, SysUser user) {
        log.debug("method: list() ");
        PageObject po = getPageObject(request, "USER_ORDER asc");
        po.getCondition().put("userNameLike", user.getUserName());
        po.getCondition().put("realName", user.getRealName());
        po.getCondition().put("userStatus", user.getUserStatus());
        po.getCondition().put("createTimeStart", request.getParameter("createTimeStart"));
        po.getCondition().put("createTimeEnd", request.getParameter("createTimeEnd"));
        po.getCondition().put("updateTimeStart", request.getParameter("updateTimeStart"));
        po.getCondition().put("updateTimeEnd", request.getParameter("updateTimeEnd"));
        writeToPage(JSONUtils.toJson(sysUserService.getGridDataModelByCondition(po)), response);
    }

    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request) {
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        request.setAttribute("initPassword", SysInitConfig.getInstance().get(SysInitConfig.CfgProp.DABOO_DEFAULTPASSWORD));
        return "system/user/add";
    }

    @RequestMapping(value = "insert")
    public String insert(HttpServletRequest request, SysUser user) {
        sysUserService.insert(user);
        return "success";
    }

    @RequestMapping(value = "showEdit")
    public String showEdit(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));
        SysUser sUser = sysUserService.findById(userId);
        request.setAttribute("sUser", sUser);
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        return "system/user/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request, SysUser user) {
        SysUser sysUser = sysUserService.findById(user.getUserId());
        sysUser.setEmail(user.getEmail());
        sysUser.setMobile(user.getMobile());
        sysUser.setRealName(user.getRealName());
        sysUser.setUserName(user.getUserName());
        sysUser.setSex(user.getSex());
        sysUser.setUserOrder(user.getUserOrder());
        sysUser.setUserType(user.getUserType());
        sysUser.setUserStatus(user.getUserStatus());//状态变更
        sysUserService.updateById(sysUser);
        return "success";
    }

    @RequestMapping(value = "delete")
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
