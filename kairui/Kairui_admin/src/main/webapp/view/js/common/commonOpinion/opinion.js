/**
 * Created by hyb on 16/1/6.
 */
////通用流程意见获取
var dataLoad_opinion;
dataLoad_opinion = function (params) {
    var p;
    p = params.data;
    if(args['businessTypeCode'] == 'DOCUMENT_TRANSMIT_FLOW'){
    	p['boId'] = args['businessId'];
    }else if(args['businessTypeCode']=="INSURANCE_DISPATCHN_FLOW"){
        p['boId'] = args['projectId']
    }else if((args['type']==2||args['type']==1)&&args['businessTypeCode'] == 'CREDIT_FLOW'){
    	p['boId']=args["businessId"];
    }else if(args['type']==3&&args['businessTypeCode'] == 'CREDIT_FLOW'){//征信开始
    	p['boId']=args["creditId"];
    } else {
	    p['boId'] = args['loanApplyId'];
    }
    p['businessType'] = args['businessTypeCode'];
	tableData(params, p, interUrl.mockList || interUrl.gr.flow);
};
opinion=function(value){
	if(value=="(null)"){
		return "--";
	}else{
		return value
	}
}
$("#table").bootstrapTable({
    "undefinedText": "--",
    "classes": "table-striped table-hover table",
    "pagination": true,
    "sidePagination": "server",
    "queryParams": "queryParams",
    "paginationFirstText": "第一页",
    "paginationPreText": "上一页",
    "paginationNextText": "下一页",
    "paginationLastText": "最后一页",
    "clickToSelect": true
  });
