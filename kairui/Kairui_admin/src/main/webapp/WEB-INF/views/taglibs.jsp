<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<c:set  var="iconfont"  value="${ctx}/static/iconfont"/>
<c:set  var="svg"  value="${ctx}/static/svg"/>

<link href="${css}/common.css?version=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${css}/style.css?version=${jsVersion}" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${js}/zepto.min.js?version=${jsVersion}"></script>

<script>
	ctx= '<c:url value = "/" />';
	var share_link = window.location.href;
	//用户数据初始化
	eval('var userInfo=<c:choose><c:when test="${empty user}">{}</c:when><c:otherwise>${user}</c:otherwise></c:choose>');
	
	//诸葛统计需要的数据
	<c:choose>
	<c:when test="${empty user}">
	var zhugeGroup ={};
	</c:when>
	<c:otherwise>
	var zhugeGroup ={ 
			"avatar" :  userInfo.headimgurl,                  // 用户的属性
			"name" : userInfo.nickname,
		    "gender" : userInfo.sex==1?"男":"女",
		    "location" : userInfo.city
		};
	</c:otherwise>
	</c:choose>
</script>
