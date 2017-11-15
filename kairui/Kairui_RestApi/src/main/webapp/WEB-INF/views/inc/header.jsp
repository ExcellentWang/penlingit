<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!-- Loading Bootstrap css -->

<link rel="stylesheet" href="<%=path%>/static/newassets/css/bootstrap.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/font-awesome.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/jquery.dataTables.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/vaildForm/css/bootstrapValidator.css" />

<link rel="stylesheet" href="<%=path%>/static/newassets/css/ace-fonts.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/ace.css"
      class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/timepicker.css" />
<link rel="stylesheet" href="<%=path%>/static/newassets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="<%=path%>/static/css/app_common.css" />

<script src="<%=path%>/static/newassets/js/jquery.js"></script>
<script src="<%=path%>/static/newassets/js/jquery.cookie.min.js"></script>
<script src="<%=path%>/static/newassets/js/jquery.uniform.min.js"></script>
<script src="<%=path%>/static/newassets/js/jquery.form.js"></script>
<script src="<%=path%>/static/newassets/vaildForm/js/bootstrapValidator.js"></script>
<script src="<%=path%>/static/newassets/js/bootstrap.js"></script>
<script src="<%=path%>/static/newassets/js/bootbox.js"></script>
<script src="<%=path%>/static/newassets/js/jquery.dataTables.js"></script>
<script src="<%=path%>/static/newassets/js/date-time/bootstrap-timepicker.js"></script>
<script src="<%=path%>/static/newassets/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/static/newassets/js/date-time/moment.js"></script>


<script type="text/javascript">
    <!--
    var contextPath = ctx = '<%=path%>';
    //-->
</script>
