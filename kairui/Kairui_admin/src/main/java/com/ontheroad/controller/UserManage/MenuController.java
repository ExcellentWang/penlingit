package com.ontheroad.controller.UserManage;

import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.Globals;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.entity.SysMenu;
import com.ontheroad.service.SysDictService;
import com.ontheroad.service.SysMenuService;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
    Logger log = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysDictService sysDictService;

    @RequestMapping(value = "init")
    public String init(HttpServletRequest request) {
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        request.setAttribute(Globals.OP_MSG_KEY, "菜单管理功能");
        return "Modal/task/userManage/system/menu/init";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response, SysMenu menu) {
        log.debug("method: list() ");
        PageObject po = getPageObject(request, "MENU_LEVEL asc,MENU_ORDER asc");
        po.getCondition().put("pMenuId", menu.getMenuId());
        po.getCondition().put("menuNameLike", menu.getMenuName());
        po.getCondition().put("menuLevel", menu.getMenuLevel());
        po.getCondition().put("menuStatus", menu.getMenuStatus());
        po.getCondition().put("updateTimeStart", request.getParameter("updateTimeStart"));
        po.getCondition().put("updateTimeEnd", request.getParameter("updateTimeEnd"));
        writeToPage(JSONUtils.toJson(sysMenuService.getGridDataModelByCondition(po)), response);
    }

    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request, HttpServletResponse response) {
        Long menuPid = Long.valueOf(request.getParameter("menuPid"));
        SysMenu sMenuP = sysMenuService.findById(menuPid);
        request.setAttribute("sMenuP", sMenuP);
        Map<String, String> menuTypeMap = sysDictService.getDetailValueMap("菜单类型");
        request.setAttribute("menuTypeCombo", JSONUtils.toCombo(menuTypeMap));
        return "Modal/task/userManage/system/menu/add";
    }

    @RequestMapping(value = "insert")
    public String insert(HttpServletRequest request, SysMenu menu) {
        Integer menuPid = Integer.valueOf(request.getParameter("menuPid"));
        Integer menuLevel = Integer.valueOf(menu.getMenuLevel()) + 1;
        menu.setMenuPid(menuPid);
        menu.setMenuLevel(menuLevel);
        menu.setMenuStatus(1);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        sysMenuService.insert(menu);
        return "Modal/task/userManage/system/success";
    }

    @RequestMapping(value = "showEdit")
    public String showEdit(HttpServletRequest request) {
        Long menuId = Long.valueOf(request.getParameter("menuId"));
        SysMenu sMenu = sysMenuService.findById(menuId);
        SysMenu sMenuP = sysMenuService.findById(Long.valueOf(sMenu.getMenuPid()));
        request.setAttribute("sMenu", sMenu);
        request.setAttribute("sMenuP", sMenuP);
        Map<String, String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        return "Modal/task/userManage/system/menu/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request, SysMenu sMenu) {
        sMenu.setUpdateTime(new Date());
        sysMenuService.updateById(sMenu);
        return "Modal/task/userManage/system/success";
    }

    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request, PrintWriter out) {
        log.debug("method: delete() ");
        String msg = "操作成功";
        boolean result = true;
        try {
            String ids = request.getParameter("ids");
            String[] menuIds = ids.split(",");
            sysMenuService.deleteMenuByIds(menuIds);
        } catch (Exception e) {
            msg = "系统发生异常！";
            result = false;
        }
        ajaxJsonResponse(out, result, msg);
    }

    @RequestMapping(value = "tree")
    public void tree(HttpServletRequest request, HttpServletResponse response) {
        String resourceTree = sysMenuService.listTree(true, false);
        writeToPage(resourceTree, response);
    }

    @RequestMapping(value = "checkMenuName")
    public void checkMenuName(HttpServletRequest request, HttpServletResponse response, SysMenu menu) {
        Map<String, Object> condititon = new HashMap<String, Object>();
        condititon.put("menuName", menu.getMenuName());
        condititon.put("notMenuId", menu.getMenuId());
        int flag = sysMenuService.countByCondition(condititon);
        writeToPage(flag > 0 ? "false" : "true", response);
    }

    @RequestMapping(value = "getMenu")
    public void getMenu(HttpServletRequest request, HttpServletResponse response) {
        Long menuId = Long.valueOf(request.getParameter("menuId"));
        SysMenu sysMenu = sysMenuService.findById(menuId);
        writeToPage(JSONUtils.toJson(sysMenu), response);
    }
}
