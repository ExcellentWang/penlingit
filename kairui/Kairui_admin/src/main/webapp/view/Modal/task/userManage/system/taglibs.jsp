<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<c:set var="ctx" value=""/>
<c:set var="js" value="${ctx}/static/js"/>
<c:set var="css" value="${ctx}/static/css"/>
<c:set var="img" value="${ctx}/static/images"/>
<c:set var="view" value="${ctx}"/>
<link href="${js}/easyui1.4.1/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${js}/easyui1.4.1/themes/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.min.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.easyui.min.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/easyui-lang-zh_CN.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/easyui-validate.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.form.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/artDialog/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript" src="${js}/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript" src="${js}/common.js?version=${jsVersion}"></script>
<script type="text/javascript" src="${js}/JSON.js?version=${jsVersion}"></script>
<link href="${css}/init.css" rel="stylesheet" type="text/css"/>
<link href="${css}/jquery.extend.self.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${js}/jquery.extend.self.js?version=${jsVersion}"></script>


<%-- <link href="${js}/jquery-easyui-1.5.3/themes/insdep/easyui.css" rel="stylesheet" type="text/css"> --%>
<%-- <link href="${js}/jquery-easyui-1.5.3/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="${js}/jquery-easyui-1.5.3/themes/insdep/insdep_tables.css" rel="stylesheet" type="text/css"> --%>

<%-- <script type="text/javascript" src="${js}/jquery-easyui-1.5.3/themes/insdep/jquery.insdep-extend.min.js"></script> --%>

    <meta name="/viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" href="${view}/view/common/plugs/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${view}/view/common/css/style.min.css"/>
    <link rel="stylesheet" href="${view}/view/common/plugs/layer/skin/layer.css?rev=433a1dd8df8c4851be764e907f6d758a"/>
    <link rel="stylesheet" href="${view}/view/common/plugs/bootstrap-table/dist/bootstrap-table.min.css">
    
<script src="${view}/view/common/plugs/bootstrap-3.3.5/js/bootstrap.min.js?rev=4becdc9104623e891fbb9d38bba01be4"></script>
<script src="${view}/view/common/plugs/layer/layer.js?rev=8962f047eeb03c06cf8200de1bf8ab99"></script>
<script src="${view}/view/common/js/mock.js"></script>
<script src="${view}/view/common/js/jquery.values.js"></script>
<script src="${view}/view/common/plugs/bootstrap-table/dist/bootstrap-table.min.js"></script>
<script src="${view}/view/common/plugs/bootstrap-table/dist/extensions/cookie/bootstrap-table-cookie.min.js"></script>
<script src="${view}/view/common/plugs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>

<script>
    ctx = '${ctx}';
    $("head").append([
        '<script src="${view}/view/common/js/URL.js"><\/script>',
        '<script src="${view}/view/common/js/iframeCommon.js"><\/script>'
      ].join(""));

</script>

