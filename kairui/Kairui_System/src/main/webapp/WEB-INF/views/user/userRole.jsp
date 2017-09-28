<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/system/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style>
        #search {
            height: 50px;
        }

        .left, .right {
            height: 100%;
        }

        .right {
            position: relative;
            width: 29%;
            float: right;
        }

        .left {
            width: 70%;
            float: left;
        }

        ul {
            position: absolute;
            width: 100%;
            height: 85%;
            overflow-y: auto;
        }

        ul li {
            height: 20px;
            background: #f5f5f5;
            padding: 4px 4px 4px 10px;
            line-height: 20px;
            margin-top: 4px;
        }
    </style>
</head>
<body>
<div class="left">
    <div id="toolbar">
        <form id="list" action="list">
            <div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0">
                <table class="searchTable">
                    <tr>
                        <td>角色名称：</td>
                        <td><input type="text" id="roleName" name="condition['roleName']"></td>
                        <td>
                            <button id="queryBtn" type="button" class="button">查询</button>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <div class="operate">
            <div class="om-panel-header">角色管理列表</div>
        </div>
    </div>
    <table id="grid" data-options="fit:true,border:false"></table>
</div>
<div class="right">
    <form id="form1" action="../bindRole">
        <input type="hidden" name="userId" value="${userId}"/>
        <input type="hidden" name="roleIds" id="roleIds"/>

        <div class="bindUser">
            <a href="#" onclick="doSubmit()">
                <span class="menu4"></span>绑定
            </a>
        </div>
    </form>

    <div>
        <div class="panel-title">绑定角色</div>
        <ul id="roleList"></ul>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $('#grid').datagrid({
            url: '../getRole/${userId}',
            pageSize: 20,
            pageList: [20, 30, 40, 50, 100],
            striped: true,
            rownumbers: true,
            pagination: true,
            toolbar: '#toolbar',
            columns: [[
                {field: 'ck', checkbox: true},
                {title: '角色名称', field: 'roleName'},
                {title: '角色描述', field: 'roleDesc'},
                {
                    title: '状态', field: 'roleStatus',
                    formatter: function (v, r) {
                        return JSON.parse('${statusMap}')[v];
                    }
                }
            ]],
            onSelect: function (index, data) {
                addLi([data]);
            },
            onUnselect: function (index, data) {
                removeLi(data);
            }
        });

        function addLi(roleList) {
            if (roleList && roleList.length > 0) {
                for (var i = 0, count = roleList.length; i < count; i++) {
                    var role = roleList[i];
                    var id = role.roleId;
                    var r = $("#" + id);
                    if (!r || r.length == 0) {
                        var li = $("<li></li>");
                        li.attr("id", role.roleId);
                        li.data("role", role);
                        li.text(role.roleName);
                        $("#roleList").append(li);
                    }
                }
            }
        }

        function removeLi(role) {
            var id = role.roleId;
            $("#" + id).remove();
        }

        var roleList = ${roleList};
        addLi(roleList);
    });


    function doSubmit() {
        var roleIds = [];
        $("#roleList li").each(function () {
            var role = $(this).data('role');
            roleIds.push(role.roleId);
        });

        console.log(roleIds);
        $("#roleIds").val(roleIds.join(","));
        $("#form1").submit();
    }
</script>
</html>