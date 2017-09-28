<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglibs.jsp"%>
<html>
<head> <meta http-equiv="X-UA-Compatible" content="IE=Edge"><title>Exception!</title></head>
<body>
<%	
	Exception ex = (Exception)request.getAttribute("exception"); 
	if(ex.getMessage()==null){
%>
<H2>Exception: 系统错误，请联系管理员！</H2>
<%
	} else {
%>
<H2>Exception: <%= ex.getMessage()%></H2>
<%
	}
%>
${uri}
<a href="#" onclick="
	<c:choose>
		<c:when test="${uri!=null}">
			window.location='${uri}'
		</c:when>
		<c:otherwise>
			history.go(-1);
		</c:otherwise>
	</c:choose>">
	<img border=0 title="返回" src="<c:url value='/static/images/btback.png'/>" /></a>
     <% ex.printStackTrace(response.getWriter());  %>
</body>
</html>