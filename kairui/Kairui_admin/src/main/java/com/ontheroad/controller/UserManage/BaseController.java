package com.ontheroad.controller.UserManage;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.modoumama.page.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ontheroad.core.exception.BusinessException;
import com.ontheroad.core.util.StringUtils;
import cn.modoumama.page.GridDataModel;
import cn.modoumama.service.base.BaseService;


public class BaseController{
	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected static final String SUCCESS = "Modal/task/userManage/system/success";
	protected static final String FAILD = "faild";
	protected static final String OPTION_RESULT = "optionResult";
	protected static final String SUCCESS_RESULT = "1";
	protected static final String FAILD_RESULT = "2";
	protected static final String FAILD_MESSAGE= "faildMessage";
	
	/**
	 * 	
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	protected PageObject getPageObject(HttpServletRequest request){
		return getPageObject(request,null);
	}
	
	/**
	*order asc
	 *page	1
	 *rows	10
	 *sort	roleId
	 * @param request
	 * @param orderByClause
	 * @return
	 */
	protected PageObject getPageObject(HttpServletRequest request,
			String orderByClause){
		PageObject pageObject = new PageObject();
		String currPageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String property = request.getParameter("sort");
		if (StringUtils.isEmpty(orderByClause)) {
			if (!StringUtils.isEmpty(property)) {
				orderByClause = getColumn(property) + " "
						+ request.getParameter("order");
			}
		}
		try{
			pageObject.setCurrPage(Integer.valueOf(currPageStr));
			pageObject.setPageSize(Integer.valueOf(pageSizeStr));			
		}catch(NumberFormatException e){
			pageObject.setCurrPage(1);
			pageObject.setPageSize(Integer.MAX_VALUE);
		}
		pageObject.addCondition("orderByClause", orderByClause);
		return pageObject;
	}

	private String getColumn(String property) {
		StringBuffer buffer = new StringBuffer(property);
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			if(Character.isUpperCase(c)){
				buffer.insert(i++, '_');
			}
		}
		return buffer.toString();
	}

	protected static String makeJsonResponse(boolean result, String errMsg) {
		if (result) {
			return "{\"success\":"+result+",\"msg\":\"" + errMsg + "\"}";
		}
		return "{\"success\":"+result+",\"msg\":\"" + errMsg + "\"}";
	}
	
	public void ajaxJsonResponse(HttpServletResponse response,boolean result,String errMsg){
		writeToPageForResponseBody(makeJsonResponse(result,errMsg),response);
	}
	
	public static void ajaxJsonResponse(PrintWriter out,boolean result,String errMsg){
		writeToPage(makeJsonResponse(result,errMsg),out);
	}
	public static void ajaxJsonResponse(PrintStream out,boolean result,String errMsg){
		writeToPage(makeJsonResponse(result,errMsg),out);
	}
	public static void ajaxJsonResponseSuccess(PrintWriter out){
		ajaxJsonResponse(out,true,"操作成功");
	}
	public static void ajaxJsonResponseSuccess(PrintStream out){
		ajaxJsonResponse(out,true,"操作成功");
	}
	public static void writeToPage(String content,PrintStream out){
		out.print(content);
		out.flush();
		out.close();
	}
	public static void writeToPage(String content,PrintWriter out){
		out.print(content);
		out.flush();
		out.close();
	}
	
	/**
	 * 有ResponseBody注解时输出错误信息
	 * @param content
	 * @param response
	 */
	public static void writeToPageForResponseBody(String content, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(content.getBytes("UTF-8"));
		} catch (IOException e) {
			logger.warn("write to page error: ", e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeToPage(String content, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(content);
		} catch (IOException e) {
			logger.warn("write to page error: ", e);
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

	protected <T extends BaseService> GridDataModel getGridData(HttpServletRequest request, PageObject poCondition, T service) {
		GridDataModel gridDataModel = null;
		try {

			String orderByClause = null;
			String property = request.getParameter("sort");
			if (com.ontheroad.core.util.StringUtils.isEmpty(property)) {
				orderByClause = " CREATE_TIME DESC ";
			}
			
			PageObject po = getPageObject(request,orderByClause);
			
			Map<String, Object> map = poCondition.getCondition();
			for (String key : map.keySet()) {
				po.addCondition(key, map.get(key));
			}
			gridDataModel = service.getGridDataModelByCondition(po);
		} catch (Exception e) {
			logger.error("获取数据失败", e);
		}
		return gridDataModel;
	}
	
	
	protected <T extends BaseService> GridDataModel getGridData(HttpServletRequest request, T service) {
		GridDataModel gridDataModel = null;
		try {

			PageObject po = createPageObject(request);
			gridDataModel = service.getGridDataModelByCondition(po);
		} catch (Exception e) {
			logger.error("获取数据失败", e);
		}
		return gridDataModel;
	}
	
	protected PageObject createPageObject(HttpServletRequest request){
		
		
		String orderByClause = null;
		String property = request.getParameter("sort");
		if (com.ontheroad.core.util.StringUtils.isEmpty(property)) {
			orderByClause = " CREATE_TIME DESC ";
		}
		
		PageObject po = getPageObject(request,orderByClause);
		@SuppressWarnings("unchecked")
		Map<String, Object> selectmap = request.getParameterMap();
		for (String key : selectmap.keySet()) {
			po.addCondition(key, request.getParameter(key));
		}
		return po;
	}
}