//基本信息-借款人信息和配偶信息
getApprovalBaseInfo();

//基本信息-紧急联系人
//getContacter();

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

sex = function (value, row, index) {
    if (typeof value === "string") {
        value = parseInt(value);
    }
    console.log(value);
    
    return (value === 1 && "男") || (value === 2 && "女") || null;

};