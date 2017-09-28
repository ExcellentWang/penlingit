package com.ontheroad.system.web;

import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.web.BaseController;
import com.ontheroad.system.entity.SysUser;
import com.ontheroad.system.service.SysMenuService;
import com.ontheroad.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@Scope("session")
@RequestMapping(value = "/index")
@OpLog(logDesc = "系统操作员登陆")
public class IndexController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "tree")
    public void index(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        SysUser user = (SysUser) session.getAttribute("user");
        String menuTree = sysMenuService.listUserTree(Long.valueOf(user.getUserId()), false);
        writeToPage(menuTree, response);
    }

    @RequestMapping(value = "password")
    public String password() {
        return "password";
    }

    @RequestMapping(value = "checkUserPwd")
    public void checkUserPwd(HttpServletRequest request, PrintWriter out) {
        SysUser sUser = (SysUser) request.getSession().getAttribute("user");
        String password = MD5Encoder.encode(request.getParameter("password"));
        String msg = "操作成功";
        boolean result = false;
        if (!password.equals(sUser.getUserPwd())) {
            result = true;
            msg = "原始密码不正确";
        }
        ajaxJsonResponse(out, result, msg);
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request) {
        String userPwd = request.getParameter("newPwd");
        SysUser sUser = (SysUser) request.getSession().getAttribute("user");
        sUser.setUserPwd(userPwd);
        sysUserService.updateUserPwd(sUser);
        return "success";
    }
}
