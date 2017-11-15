<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../inc/header.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>
                <small> <i class="ace-icon fa fa-angle-double-right"></i>
                    设备列表
                </small>
            </h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <div class="row btn-area"></div>
                <div class="row search-area">
                    <form id="searchForm" class="form-inline">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label class="align-right"> 绑定日期：</label>
                                <div class="input-group binded-at" id="binded-at-from">
                                    <input class="form-control" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label> 到：</label>
                                <div class="input-group binded-at" id="binded-at-end">
                                    <input class="form-control" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                        <div class="form-group">
                            <label for="type" style="width: 80px" class="align-right"> 设备类型：</label> <select id="type"
                                                                                                             class="form-control input-sm" placeholder="类型">
                            <option value="">设备类型</option>
                        </select>
                        </div>
                        </div>
                        <div class="col-md-3">
                        <div class="form-group">
                            <label for="searchEquipmentNum" class="align-right"> 设备编号：</label> <input
                                id="searchEquipmentNum" type="text" class="form-control input-sm"
                                placeholder="设备编号">
                        </div>
                        </div>

                        <div class="form-group">
                            <label for="searchPhone" class="align-right"> 绑定手机号：</label> <input
                                id="searchPhone" type="text"
                                class="form-control input-sm" placeholder="手机号">
                        </div>
                    </form>
                </div>
            </div>
            <div id="div_line_list" class="dataTables_wrapper form-inline">
                <table id="devices" class="display">
                    <thead>
                    <tr role="row">
                        <th id="thck" class="center sorting_disabled"><label class="position-relative">
                            <input id="headCheck" type="checkbox" class="ace"> <span class="lbl"></span>
                         								</label></th>
                        <th>序号</th>
                        <th>设备编号</th>
                        <th>设备类型</th>
                        <th>手机号</th>
                        <th>设备名</th>
                        <th>状态</th>
                        <th>固件版本</th>
                        <th>最后心跳时间</th>
                        <th>绑定时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<%=path%>/static/app/js/devices.js"></script>
</body>
</html>