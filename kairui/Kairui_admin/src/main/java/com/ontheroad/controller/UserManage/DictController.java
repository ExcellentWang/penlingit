package com.ontheroad.controller.UserManage;

import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.entity.SysDict;
import com.ontheroad.service.SysDictService;
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
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping(value = "/system/dict")
@OpLog(logDesc="系统参数配置")
public class DictController extends BaseController {
	Logger log = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private SysDictService sysDictService;
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		request.setAttribute("statusMap", JSONUtils.toJson(sysDictService.getDetailValueMap("状态")));
		return "Modal/task/userManage/system/dict/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysDict sDict) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"UPDATE_TIME desc");
		po.getCondition().put("dictNameLike", sDict.getDictName());
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysDictService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "listyhxy")
	@ResponseBody
	public void listyhxy(HttpServletRequest request,HttpServletResponse response,SysDict sDict) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"UPDATE_TIME desc");
		po.getCondition().put("dictId", "5");
		po.getCondition().put("dictNameLike", "用户协议");
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysDictService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request,HttpServletResponse response){
		return "Modal/task/userManage/system/dict/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,SysDict sDict){
		sysDictService.insert(sDict);
		return "Modal/task/userManage/system/success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long dictId = Long.valueOf(request.getParameter("dictId"));
		SysDict sDict = sysDictService.findById(dictId);
		request.setAttribute("sDict", sDict);
		request.setAttribute("statusMap", sysDictService.getDetailValueMap("状态"));
		return "Modal/task/userManage/system/dict/edit";
	}
	
	//用户协议指向
	@RequestMapping(value = "showEditYhxy")
	public String showEditYhxy(HttpServletRequest request){
		Long dictId = Long.valueOf(request.getParameter("dictId"));
		SysDict sDict = sysDictService.findById(dictId);
		request.setAttribute("sDict", sDict);
		request.setAttribute("statusMap", sysDictService.getDetailValueMap("状态"));
		return "Modal/task/userManage/system/yhxy/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,SysDict sDict){
		sysDictService.updateById(sDict);
		return "Modal/task/userManage/system/success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,PrintWriter out){
		log.debug("method: delete() ");
		String msg = "操作成功";
		boolean result = true;
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sysDictService.removeById(Long.valueOf(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统发生异常！";
			result = false;
		}
		ajaxJsonResponse(out, result, msg);
	}
	@RequestMapping(value = "checkDictName")
	public void checkDictName(HttpServletRequest request,HttpServletResponse response,SysDict dict) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictName", dict.getDictName());
		condititon.put("notDictId", dict.getDictId());
		int flag = sysDictService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}
