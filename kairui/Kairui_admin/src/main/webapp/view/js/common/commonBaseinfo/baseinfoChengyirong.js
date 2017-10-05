//基本信息-借款人信息和配偶信息
getApprovalBaseInfoChengyirong();

table_contacter = function (params) {
    var p=params.data;
    return comn.ajax({
        url: interUrl.myTask.customerContacter,
        data: $.extend(loanApplyId,p),
        success: function (res) {
            params.success({
                'total': res.totalItem,
                rows: res.data
            });
            return params.complete();
        }
    });
};

$("#table_contacter").bootstrapTable(comn.table);
