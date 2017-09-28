package com.ontheroad.controller;

import cn.modoumama.page.GridDataModel;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.web.BaseController;
import com.ontheroad.system.service.SysDictService;
import com.ontheroad.user.model.UserModel;
import com.ontheroad.user.service.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ontheroad.core.util.SysInitConfig.CfgProp;
import static com.ontheroad.core.util.SysInitConfig.getInstance;

/**
 * Created by kedong on 2017/6/27 0027.
 */
@Controller
@RequestMapping("user/")
public class AppUserController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SystemUserService userService;

    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String, String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("initPassword", getInstance().get(CfgProp.DABOO_DEFAULTPASSWORD));
        request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        return "user/list";
    }

    @RequestMapping("getData")
    @ResponseBody
    public GridDataModel getData(HttpServletRequest request) {
        return getGridData(request, userService);
    }


    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request) {
        return "user/add";
    }

    @RequestMapping(value = "insert")
    public String insert(HttpServletRequest request, UserModel user) {
        userService.insert(user);

        return "system/success";
    }

    @RequestMapping(value = "showEdit")
    public String showEdit(HttpServletRequest request, Long id) {
        UserModel sUser = userService.findById(id.intValue());
        request.setAttribute("sUser", sUser);

        Map<String, String> industryTypes = sysDictService.getDetailValueMap("industryType");
        request.setAttribute("industryTypes", industryTypes);

        return "user/edit";
    }

    @RequestMapping(value = "update")
    public String update(UserModel user) {
        userService.updateById(user);
        return "system/success";
    }

    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request, PrintWriter out) {
        log.debug("method: delete() ");
        String msg = "操作成功";
        boolean result = true;
        try {
            String ids = request.getParameter("ids");
            String[] userIds = ids.split(",");
            userService.deleteUserByIds(userIds);
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
                Integer userId = Integer.valueOf(id);
                UserModel sUser = userService.findById(userId);
                sUser.setId(userId);

                String phone = sUser.getMobile();
                // 密码取手机号后六位
                String password = phone.substring(phone.length() - 6);
                sUser.setPassword(password);

                userService.updateUserPwd(sUser);
            }
        } catch (Exception e) {
            msg = "系统发生异常！";
            result = false;
        }
        ajaxJsonResponse(out, result, msg);
    }

    @RequestMapping(value = "/checkPhone")
    @ResponseBody
    public Boolean checkPhone(String oldNo, String newNo) {
        if (Objects.equals(oldNo, newNo)) {
            return true;
        }
        Map condition = new HashMap();
        condition.put("mobile", newNo);
        condition.put("notMobile", oldNo);
        List<UserModel> userList = userService.findModelsByCondition(condition);
        return userList == null || userList.size() == 0;
    }
}
