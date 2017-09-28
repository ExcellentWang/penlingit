<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/system/taglibs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div id="toolbar">
    <form id="list" action="list">
        <div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0">
            <table class="searchTable">
                <tr>
                    <td>系统登录名：</td>
                    <td><input type="text" id="userName" name="loginName"></td>
                    <td>真实姓名：</td>
                    <td><input type="text" id="realName" name="name"></td>
                    <td>
                        <button id="queryBtn" type="button" class="button">查询</button>
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td><input id="userStatus" name="status" type="combo"></td>
                    <td>创建时间：</td>
                    <td><input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
                        至 <input id="createTimeEnd" name="createTimeEnd" type="datetime" class="easyui-datebox"/>
                    </td>
                    <td>
                        <button id="clearBtn" type="button" class="button">清空</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="operate">
        <div class="om-panel-header">用户管理列表</div>
        <div class="icon">
            <ul>
                <!--
                <li>
                    <a href="#" onclick="showAdd('/user/showAdd',800,600);">
                        <span class="menu1"></span>添加
                    </a>
                </li>

                <li>
                    <a href="#" onclick="showEdit('/user/showEdit','id',800,600);">
                        <span class="menu13"></span>修改
                    </a>
                </li>
                -->
                <li><a href="#" onclick="removeRow('id');"><span class="menu11"></span>删除</a></li>
                <li style="width:100px"><a href="#" onclick="initPwd('id');"><span class="menu9"></span>初始化密码</a></li>
                <%--<li><a href="#" onclick="bindRole()"><span class="menu4"></span>绑定角色</a></li>--%>
            </ul>
        </div>
    </div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
    $(function () {
        $('#grid').datagrid({
            url: 'list',
            pageSize: 15,
            pageList: [15, 30, 40, 50, 100, 200, 300, 400, 500, 1000],
            striped: true,
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            toolbar: '#toolbar',
            nowrap: false,
            columns: [
                [
                    {field: 'ck', checkbox: true},
                    {title: '系统登录名', field: 'loginName', sortable: "true"},
                    {title: '真实姓名', field: 'name', sortable: "true"},
                    {title: '手机号码', field: 'phone', sortable: "true"},
                    {
                        title: '性别', field: 'sex', sortable: "true",
                        formatter: function (v, r) {
                            return JSON.parse('${sexMap}')[v]
                        }
                    },
                    {title: '邮箱', field: 'email', sortable: "true"},
                    {
                        title: '状态', field: 'status', sortable: "true",
                        formatter: function (v, r) {
                            return JSON.parse('${statusMap}')[v]
                        }
                    },
                    {title: '排序', field: 'orders', sortable: "true"},
                    {title: '创建时间', field: 'createTime', sortable: "true"}
                ]
            ]
        });
        $('#userStatus').combobox({
            data: JSON.parse('${statusCombo}'),
            editable: false,
            panelHeight: 'auto'
        });
    });
    function initPwd(deleteId) {
        var selections = $('#grid').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示:', '请至少选择一行记录');
            return false;
        }
        var phone = selections[0].phone;
        var password = phone.substring(phone.length - 6);
        $.messager.confirm('提示:', '确定对所选数据进行初始化密码(<span style="color:red">' + password + '</span>)？', function (e) {
            if (e) {
                var ids = [];
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i][deleteId]);
                }
                $.post(ctx + '/system/prg/user/initPwd', {"ids": ids.toString()}, function (data) {
                    if (data.success) {
                        $("#grid").datagrid('reload');
                        $.messager.show({
                            title: '温馨提示:',
                            msg: '初始化密码成功!',
                            timeout: 1500,
                            showType: 'slide'
                        });
                    } else {
                        $.messager.alert('提示:', data.msg, 'warning');
                    }
                }, 'json');
            }
        });
    }


</script>
</html>