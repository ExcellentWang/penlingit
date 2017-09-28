package com.ontheroad.system.web;

import cn.modoumama.page.PageObject;
import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.web.BaseController;
import com.ontheroad.system.entity.SysDetail;
import com.ontheroad.system.service.SysDetailService;
import com.ontheroad.system.service.SysDictService;
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
@Scope("session")
@RequestMapping(value = "/system/prg/detail")
@OpLog(logDesc="系统参数明细配置")
public class DetailController extends BaseController {
	Logger log = LoggerFactory.getLogger(DetailController.class);
	@Autowired
	private SysDetailService sysDetailService;
	@Autowired
	private SysDictService sysDictService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		String dictId = request.getParameter("dictId");
		request.setAttribute("dictId", dictId);
		request.setAttribute("statusMap", JSONUtils.toJson(sysDictService.getDetailValueMap("状态")));
		return "system/detail/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysDetail sDetail) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"UPDATE_TIME desc");
		po.getCondition().put("dictId", sDetail.getDictId());
		po.getCondition().put("detailNameLike", sDetail.getDetailName());
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysDetailService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request,HttpServletResponse response){
		//Long dictId = Long.valueOf(request.getParameter("dictId"));
		request.setAttribute("dictId", request.getParameter("dictId"));
		return "system/detail/add";
	}
	
	@RequestMapping(value = "insert")
	public void insert(PrintWriter out,SysDetail sDetail){
		sysDetailService.insert(sDetail);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long detailId = Long.valueOf(request.getParameter("detailId"));
		SysDetail sDetail = sysDetailService.findById(detailId);
		request.setAttribute("sDetail", sDetail);
		request.setAttribute("statusMap", sysDictService.getDetailValueMap("状态"));
		return "system/detail/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,SysDetail sDetail){
		sysDetailService.updateById(sDetail);
		return "success";
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
				sysDetailService.removeById(Long.valueOf(id));
			}
		} catch (Exception e) {
			msg = "系统发生异常！";
			result = false;
		}
		ajaxJsonResponse(out, result, msg);
	}	
	@RequestMapping(value = "checkDetailName")
	public void checkDetailName(HttpServletRequest request,HttpServletResponse response,SysDetail detail) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("detailName", detail.getDetailName());
		condititon.put("dictId", detail.getDictId());
		condititon.put("notDetailId", detail.getDetailId());
		int flag = sysDetailService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}
