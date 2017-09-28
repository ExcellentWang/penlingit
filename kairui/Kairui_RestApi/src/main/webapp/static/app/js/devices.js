var selected = [];
function initTable() {
    var paras = $("form").serialize();
    var url = ctx + "/admin/device/devicelist";
    $("#devices")
        .dataTable(
            {
                "bFilter" : true,
                "bAutoWidth": true, //自适应宽度
                "sPaginationType" : "full_numbers",
                // "sAjaxDataProp" : "aData",
                "bDestroy" : true,
                "bProcessing" : true,
                "sAjaxSource" : url,
                "sAjaxDataProp": "resultMap",
                "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
                    oSettings.jqXHR = $.ajax({
                        "dataType" : 'json',
                        "type" : "POST",
                        "url" : sSource,
                        "data" : aoData,
                        "success" : function(data) {
                            fnCallback(data);
                        }
                    });
                },
                "bServerSide" : true,
                "aoColumns" : [
                    {
                        "mDataProp" : "equipment_id"
                    }, {
                        "mDataProp" : "equipment_id"
                    }, {
                        "mDataProp" : "equipmentNum"
                    }, {
                        "mDataProp" : "type"
                    }, {
                        "mDataProp" : "userTelephone"
                    }, {
                        "mDataProp" : "equipmentName"
                    }, {
                        "mDataProp" : "workStatus"
                    }, {
                        "mDataProp" : "firmware_version"
                    }, {
                        "mDataProp" : "last_active_at"
                    }, {
                        "mDataProp" : "binded_at"
                    }],
                "aoColumnDefs" : [
                    // {
                    // "aTargets" : [ 0 ],
                    // "bSortable" : false,
                    // "mRender" : function(data, type, full) {
                    // var ck = '<label
                    // class="position-relative"><input id="'
                    // + data
                    // + '" type="checkbox" class="ace"><span
                    // class="lbl"></span></label>';
                    // return ck;
                    // }
                    // },
                    // {
                    //     "aTargets" : [ 2 ],
                    //     "bSortable" : false,
                    //     "mRender" : function(data, type, full) {
                    //         var areaName = full.areaName == undefined ? ""
                    //             : full.areaName;
                    //         var companyName = full.companyName == undefined ? ""
                    //             : full.companyName;
                    //         return areaName + " " + companyName;
                    //     }
                    // },
                    // {
                    //     "aTargets" : [ 5 ],
                    //     "mRender" : function(data, type, full) {
                    //         var date = full.lastUpdateTime;
                    //         return moment(date).format('YYYY-MM-DD HH:mm:ss');
                    //     }
                    // },
                    {
                        "aTargets" : [ 0 ],
                        "bSortable" : false,
                        "mRender" : function(data, type, full, meta) {
                            var start = $('#devices').DataTable().context[0]._iDisplayStart;
                            return parseInt(start) + parseInt(meta.row) + 1;
                        }
                    } ],
                "oLanguage" : {
                    "sSearch" : "搜索:",
                    "sProcessing" : "正在加载中......",
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "没有数据！",
                    "sEmptyTable" : "表中无数据存在！",
                    "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                    "sInfoEmpty" : "显示0到0条记录",
                    "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                    // "sSearch" : "搜索",
                    "oPaginate" : {
                        "sFirst" : "首页",
                        "sPrevious" : "上一页",
                        "sNext" : "下一页",
                        "sLast" : "末页"
                    }
                },
                "fnDrawCallback" : function(oSettings) {
                },
                "dom" : 'l<"total_tool">rtip',// '<"total_tool">rti'
                initComplete : function() {
                    $("div.total_tool")
                        .append(
                            '<div class="btn-group" role="group" aria-label="...">'
                            + '<button class="btn btn-success btn-sm" id="detail_search" onclick="searchUser()">'
                            + '<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索'
                            + '</button> '
                            + '<button class="btn btn-default btn-sm" id="detail_cancel" onclick="clearForm()">'
                            + '<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 重置'
                            + '</button> ' + '</div>');
                },
                "fnServerParams" : function(aoData) {
                    var searchUserId = document.getElementById("searchUserId");
                    if (searchUserId) {
                        aoData.push({
                            "name" : "userId",
                            "value" : searchUserId.value
                        });
                    }
                    var searchUserName = document.getElementById("searchUserName");
                    if (searchUserName) {
                        aoData.push({
                            "name" : "userName",
                            "value" : searchUserName.value
                        });
                    }
                    var area = document.getElementById("area");
                    if (area) {
                        aoData.push({
                            "name" : "area",
                            "value" : area.value
                        });
                    }
                    var company = document.getElementById("company");
                    if (company) {
                        aoData.push({
                            "name" : "company",
                            "value" : company.value
                        });
                    }
                }
            });

    // initCities([ "#area" ]);
    // initCompanies([ "#company" ]);
    // $('#sample-table-2 tbody').on('click', 'tr', function() {
    // var ck = $(this).find(":checkbox");
    // var c = ck.prop("checked");
    // ck.prop("checked", !c).trigger("change");
    // });
    //
    // $("#headCheck").on('click', function() {
    // $("tbody :checkbox").prop("checked", this.checked).trigger("change");
    // });
}

$(document).ready(function() {
    initTable();
});

function searchUser() {
    // selected = [];
    $("#sample-table-2").DataTable().ajax.reload();
}

function clearForm() {
    $('#keyword').clearInputs();
    $('#company').val("");
    $('#area').val("");
    $("#searchForm").resetForm();
}
