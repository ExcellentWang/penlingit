<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>BusPj</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">

    <link rel="stylesheet" href="<%=path%>/app/css/index.css" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<%=path%>/static/newassets/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=path%>/static/newassets/css/font-awesome.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="<%=path%>/static/newassets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="<%=path%>/static/newassets/css/ace.css"
          class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
			<link rel="stylesheet" href="<%=path%>/static/newassets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

    <!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=path%>/static/newassets/css/ace-ie.css" />
		<![endif]-->

    <!-- ace settings handler -->
    <script src="<%=path%>/static/newassets/js/ace-extra.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lte IE 8]>
		<script src="<%=path%>/static/newassets/js/html5shiv.js"></script>
		<script src="<%=path%>/static/newassets/js/respond.js"></script>
		<![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left"
                id="menu-toggler" data-target="#sidebar"></button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand"> <small>
                Smart Shower
            </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right"
             role="navigation">
            <ul class="nav ace-nav">


                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue"><a data-toggle="dropdown" href="#"
                                          class="dropdown-toggle"> <%-- <img class="nav-user-photo" src="<%=path%>/static/newassets/avatars/user.jpg" alt="Jason's Photo" /> --%>
                    <span> 您好, admin
						</span> <i class="ace-icon fa fa-caret-down"></i>
                </a>

                    <ul
                            class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <!-- 							<li><a href="#settingModal" data-toggle="modal"> <i -->
                        <!-- 									class="ace-icon fa fa-cog"></i> 设置 -->
                        <!-- 							</a></li> -->


                        <li class="divider"></li>

                        <li><a href="<%=path%>/rest/page/logout"> <i
                                class="ace-icon fa fa-power-off"></i> 退出
                        </a></li>
                    </ul></li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar                  responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>


        <ul class="nav nav-list">


                <li class=""><a href="#" class="dropdown-toggle"><i
                        class="menu-icon fa"></i> <span
                        class="menu-text">设备管理
						</span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>
                    <ul class="submenu">
                        <li class="">
                            <a href="admin/device/devices" target="mainFrame"> <i class="menu-icon fa fa-caret-right"></i>
                                <span class="menu-text">设备列表</span>
                            </a><b class="arrow"></b>
                        </li>
                        <li class="">
                            <a href="#" target="mainFrame"> <i class="menu-icon fa fa-caret-right"></i>
                                <span class="menu-text">在线设备查询</span>
                            </a><b class="arrow"></b>
                        </li>
                        <li class="">
                            <a href="#" target="mainFrame"> <i class="menu-icon fa fa-caret-right"></i>
                                <span class="menu-text">设备异常日志</span>
                            </a><b class="arrow"></b>
                        </li>
                        <li class="">
                            <a href="#" target="mainFrame"> <i class="menu-icon fa fa-caret-right"></i>
                                <span class="menu-text">用水量、节水量</span>
                            </a><b class="arrow"></b>
                        </li>
                    </ul>
                </li>

        </ul>
        <!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse"
             onclick="sidebarChange()">
            <i class="ace-icon fa fa-angle-double-left"
               data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <!--
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                </ul>


            </div> -->

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">

            <div class="page-content-area">
                <!-- ajax content goes here -->
                <iframe id="mainFrame" name="mainFrame" scrolling="no" src=""
                        frameborder="0" style="padding: 0px; width: 100%; height: 90%;"></iframe>
            </div>
            <!-- /.page-content-area -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">

						Application &copy; 2017


            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up"
       class="btn-scroll-up btn btn-sm btn-inverse"> <i
            class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<div class="modal fade" id="settingModal" role="dialog" tabindex="-1"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="form-horizontal" id="validationForm" role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="label_delModal">修改密码</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">旧密码</label>
                        <div class="col-sm-10">
                            <input type="password" id="password" name="password"
                                   placeholder="旧密码" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
                            <input type="password" id="newPassword" name="newPassword"
                                   placeholder="新密码" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="repeatPassword" class="col-sm-2 control-label">确认新密码</label>
                        <div class="col-sm-10">
                            <input type="password" id="repeatPassword" name="repeatPassword"
                                   placeholder="确认密码" />
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button id="okBtn" type="button" class="btn btn-primary">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>


<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=path%>/static/newassets/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=path%>/static/newassets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='<%=path%>/static/newassets/js/jquery.mobile.custom.js'>"
        + "<"+"/script>");
    var browserVersion = window.navigator.userAgent.toUpperCase();
    var isOpera = false;
    var isFireFox = false;
    var isChrome = false;
    var isSafari = false;
    var isIE = false;
    var iframeTime;
    function reinitIframe(iframeId, minHeight) {
        try {
            var iframe = document.getElementById(iframeId);
            var bHeight = 0;
            if (isChrome == false && isSafari == false)
                bHeight = iframe.contentWindow.document.body.scrollHeight;

            var dHeight = 0;
            if (isFireFox == true)
                dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
            else if (isIE == false && isOpera == false)
                dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            else
                bHeight += 3;
            var height = Math.max(bHeight, dHeight);
            if (height < minHeight)
                height = minHeight;
            iframe.style.height = height + "px";
        } catch (ex) {
        }
    }
    function startInit(iframeId, minHeight) {
        isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
        isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
        isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
        isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
        if (!!window.ActiveXObject || "ActiveXObject" in window)
            isIE = true;
        reinitIframe(iframeId, minHeight);
        if (iframeTime != null)
            clearInterval(iframeTime)
        iframeTime = window.setInterval("reinitIframe('" + iframeId + "'," + minHeight + ")",
            100);
    }
    function sidebarChange() {
        mainFrame.window.reDrawTable();
    }
</script>
<script type="text/javascript">
    startInit('mainFrame', 600);
</script>
<script src="<%=path%>/static/newassets/js/bootstrap.js"></script>

<!-- ace scripts -->
<script src="<%=path%>/static/newassets/js/ace/elements.scroller.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.colorpicker.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.fileinput.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.typeahead.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.wysiwyg.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.spinner.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.treeview.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.wizard.js"></script>
<script src="<%=path%>/static/newassets/js/ace/elements.aside.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.ajax-content.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.touch-drag.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.sidebar.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.submenu-hover.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.widget-box.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.settings.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.settings-rtl.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.settings-skin.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.widget-on-reload.js"></script>
<script src="<%=path%>/static/newassets/js/ace/ace.searchbox-autocomplete.js"></script>
<script src="<%=path%>/static/newassets/js/jquery.validate.js"></script>
<script src="<%=path%>/static/newassets/js/additional-methods.js"></script>
<script src="<%=path%>/static/newassets/js/bootbox.js"></script>
<script type="text/javascript">
    var contextPath = ctx = '<%=path%>';
    function initValidation() {
        $('#validationForm').validate({
            doNotHideMessage : true,
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                password : {
                    required : true,
                    maxlength : 20,
                    minlength : 6,
                },
                newPassword : {
                    required : true,
                    maxlength : 20,
                    minlength : 6,
                },
                repeatPassword : {
                    required : true,
                    equalTo : "#newPassword",
                    maxlength : 20,
                    minlength : 6,
                }
            },

            messages : {
                password : {
                    required : "请输入旧密码！",
                    maxlength : "密码最多输入20位！",
                    minlength : "密码最少为6位！",
                },
                newPassword : {
                    required : "请输入新密码！",
                    maxlength : "密码最多输入20位！",
                    minlength : "密码最少为6位！",
                },
                repeatPassword : {
                    required : "请再次输入新密码！",
                    maxlength : "密码最多输入20位！",
                    minlength : "密码最少为6位！",
                    equalTo : "密码输入不一致！",
                }
            },

            highlight : function(e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },

            success : function(e) {
                $(e).closest('.form-group').removeClass('has-error');// .addClass('has-info');
                $(e).remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element);
            },

            submitHandler : function(form) {
            },
            invalidHandler : function(form) {
            }
        });
    }

    function clearModal() {
        $("#password").val("");
        $("#newPassword").val("");
        $("#repeatPassword").val("");
        $(".form-group").removeClass('has-info');
        $(".form-group").removeClass('has-error');
        $(".form-group .help-block").remove();
    }

    $("#okBtn").on('click', function() {
        if (!$('#validationForm').valid()) {
            return;
        }

        $.ajax({
            type : "POST",
            url : ctx + "/rest/user/pwd",
            data : {
                password : $("#password").val(),
                newPassword : $("#newPassword").val()
            },
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    bootbox.alert("修改成功！");
                    $("#settingModal").modal('hide');
                } else {
                    if (data.result) {
                        bootbox.alert(data.result);
                    } else {
                        bootbox.alert("操作失败！请稍后再试！");
                        $("#settingModal").modal('hide');
                    }
                }
            }
        });
    });

    $("#settingModal").on("shown.bs.modal", function() {
        clearModal();
        initValidation();
    });
</script>
</body>
</html>
