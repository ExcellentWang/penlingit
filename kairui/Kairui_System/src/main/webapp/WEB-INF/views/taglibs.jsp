<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<link href="${js}/easyui1.4.1/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${js}/easyui1.4.1/themes/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.min.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.easyui.min.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/easyui-lang-zh_CN.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/easyui-validate.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.form.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/artDialog/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript" src="${js}/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript" src="${js}/common.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/JSON.js?version=${jsVersion}"></script>
<link href="${css}/init.css" rel="stylesheet" type="text/css" />
<link href="${css}/jquery.extend.self.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${js}/jquery.extend.self.js?version=${jsVersion}"></script>
<script>
ctx= '<c:url value = "/" />';
//session 失效跳转
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    cache:false,
    dataType:"json",
    complete:function(XHR,TS){
       var resText=XHR.responseText;
       if(resText.indexOf("sessionState")!=-1 && resText.indexOf("loginUrl")!=-1){
	        var resJson=$.parseJSON(resText);
	        if(resJson.sessionState==0){
		        window.top.location.href=resJson.loginUrl;
		    }
       }
	 }
});

</script>

