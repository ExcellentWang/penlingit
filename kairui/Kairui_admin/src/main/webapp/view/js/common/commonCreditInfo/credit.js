(function(){
    var pictures = document.querySelector('#documentList_credit');
    var viewer;
    var options = {
        url: 'data-original',
        title: true,
        transition: false,
        build: function (e) {},
        built: function(e){},
        show:  function (e) {
            imgIds = {};
            window.parent.toggleTopNav();
        },
        viewed: function(e){},
        hide: function(e){
            window.parent.toggleTopNav();
        }
    };

getValue = function (o, key) { //处理undefine
    return o[key] ? o[key] : "";
}
loanVal = function (a, b) {
    for (var i = 0; i < a.length; i++) {
		var imgHtml = "";
		if(a[i]['creditFiles'] && a[i]['creditFiles'].length){
			for (var j = 0, len = a[i]['creditFiles'].length; j < len; j++) {
				var o =  a[i]['creditFiles'][j];
				imgHtml += "<img class='img-thumbnail' src= '" + o['creditFile'] + "@80h' data-original='" + o['creditFile'] + "' style='margin-right: 10px;' />"
			} 
		}
        setTimeout(function () {
            viewer = new Viewer(pictures, options);
		
        }, 500);
        var htmlArr = [
            '<div class="panel panel-default partyList party_List">',
            '<div class="panel-heading">',
            '<h3 class="panel-title">借款人 - ' + a[i].fullName + '</h3>',
            '</div>',
            '<div class="panel-body" style="padding-bottom:0;" id="accordion_'+ i +'" role="tablist" aria-multiselectable="true">',
            '<div class="panel panel-default collapseFlex">',
            '<div class="panel-heading">',
            '<div class="col-md-8">',
            '<h3 class="panel-title">银行征信信息</h3>',
            '</div>' ,
            '<div class="col-md-16 text-right">' +
            '<a href="javascript:;" class="btn flexBtn" data-status="0"><span class="glyphicon glyphicon-chevron-right"></span></a>',
            '</div>',
            '</div>',
            '<div class="panel-body">'+bankHtml(),
            '<fieldset class="disabled1Class">',
            '<div class="form-group form-group-sm">',
            '<div class="form-group form-group-sm">',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">与主贷人关系：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<select name="relavants[' + i + '].borrowerRelationship" value="'+a[i].borrowerRelationship+'" class="form-control">',
				'<option value="">--请选择--</option>',
				'<option value="1">本人</option>',
				'<option value="2">夫妻</option>',
				'<option value="3">父亲</option>',
				'<option value="4">母亲</option>',
				'<option value="5">姐妹</option>',
				'<option value="6">兄弟</option>',
				'<option value="7">子女</option>',
				'<option value="8">亲戚</option>',
				'<option value="9">朋友</option>',
				'<option value="10">合伙人</option>',
				'<option value="11">同事</option>',
				'<option value="12">女儿</option>',
				'<option value="13">姐夫</option>',
				'<option value="14">嫂子</option>',
				'<option value="15">儿媳</option>',
            '</select>',
            '</div>',
            '</div>',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">证件号码：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<input name="relavants[' + i + '].cardId" value="'+a[i].cardId+'" class="form-control">',
            '</div>',
            '</div>',
            '</div>',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">调查方式：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<select id="seHan" name="relavants[' + i + '].checkType" value="' + a[i].checkType + '" class="form-control" required="" aria-required="true">',
            '<option value="">--请选择--</option>',
            '<option value="1">电话</option>',
            '<option value="2">网络</option>',
            '</select>',
            '</div>',
            '</div>',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">调查结果：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<select name="relavants[' + i + '].checkResult" value="' + a[i].checkResult + '" class="form-control checkResult" required="" aria-required="true">',
            '<option value="">--请选择--</option>',
            '<option value="1">通过</option>',
            ' <option value="2">不通过</option>',
            '</select>',
            '</div>',
            '</div>',
            '<div class="input-tip hide">',
            '<label class="radio-inline"><input type="radio" fors="radio0" value="1" name="relavants[' + i + '].checkResultStatus" required="" aria-required="true" />关注</label>',
            '<label class="radio-inline"><input type="radio" fors="radio0" value="2" name="relavants[' + i + '].checkResultStatus" required="" aria-required="true" />禁入</label>',
            '<label class="radio-inline"><input type="radio" fors="radio0" value="3" name="relavants[' + i + '].checkResultStatus" required="" aria-required="true" />其他</label>',
            '</div>',
            '</div>',
            '<div class="form-group form-group-sm">',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">调查人：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<input type="text" maxlength="5" name="relavants[' + i + '].staffName" value="' + getValue(a[i], 'staffName') + '" for="staffName" placeholder="请输入调查人姓名" class="form-control" required="" aria-required="true" />',
            '<input type="hidden" name="relavants[' + i + '].staffId" value="' + getValue(a[i], 'staffId') + '" />',
            '</div>',
            '</div>',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">调查日期：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<input type="text" name="relavants[' + i + '].checkDate" placeholder="请输入调查日期" value="' + getValue(a[i], 'checkDate') + '" class="form-control date required dateISO" required="" aria-required="true" />',
            '</div>',
            '</div>',
            '</div>',
			'<div class="form-group form-group-sm">',
			'<label class="col-md-3 control-label">备注：</label>',
			'<div class="col-md-21">',
			'<textarea required="" aria-required="true" class="form-control" rows="3" name="relavants[' + i + '].creditRemark">' + getValue(a[i], 'creditRemark') + '</textarea>',
			'</div>',
			'</div>',
			'<div class="row" >',
			'<div class="col-md-21 col-md-offset-3">',
			imgHtml,
			'</div>',
			'</div>',
            '</fieldset>',
            '</div>',
            '</div>',
            //---网络征信信息开始
            '<div class="panel panel-default collapseFlex">',
            '<div class="panel-heading">',
            '<div class="col-md-8">',
            '<h3 class="panel-title">网络征信信息</h3>',
            '</div>',
            '<div class="col-md-16 text-right">',
            '<a href="javascript:;" class="btn flexBtn" data-status="1"><span class="glyphicon glyphicon-chevron-right"></span></a>',
            '</div>',
            '</div>',
            '<div class="panel-body">',
            '<fieldset class="disabled1Class">',
            '<div class="form-group form-group-sm">',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">报告日期：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<input type="hidden" name="relavants[' + i + '].netResult" value="1" />',
            '<input type="text" name="relavants[' + i + '].netReportDate" value="' + getValue(a[i], 'netReportDate') + '" placeholder="请输入报告日期" disabled="disabled" class="form-control date required dateISO" required="" aria-required="true" />',
            '</div>',
            '</div>',
            '<div class="input-tip">',
            '<label class="col-md-3 col-xs-3 col-sm-3 control-label">风险等级：</label>',
            '<div class="col-md-5 cl-sm-5 col-xs-5">',
            '<select name="relavants[' + i + '].riskStatus" value="' + a[i].riskStatus + '" class="form-control riskStatus" disabled="disabled" required="" aria-required="true">',
            '<option value="1">正常</option>',
            '<option value="2">黑名单</option>',
            '<option value="3">灰名单</option>',
            '</select>',
            '</div>',
            '</div>',
            '</div>',
            '<div class="form-group form-group-sm">',
            '<label class="col-md-3 control-label">网络征信：</label>',
            '<div class="col-md-21">',
            '<textarea disabled="disabled" required="" aria-required="true" class="form-control netReportDetailVal netReportDetail' + i + '" rows="3" name="relavants[' + i + '].creditRemark"></textarea>',
            '</div>',
            '</div>',
            '</fieldset>',
            '</div>',
            '</div>',
            //---网络征信信息结束
           
            //---网络征信信息开始
		/*	'<div class="panel panel-default collapseFlex">' +
			'<div class="panel-heading">' +
			'<div class="col-md-8">' +
			'<label class="panel-title">网络征信信息</label>' +
			'</div>' +
			'<div class="col-md-16 text-right">' +
			'<button class="btn btn-primary getNet" type="button" style="margin-right:5px;">获取网络征信</button><a href="javascript:;" class="btn flexBtn" data-status="1"><span class="glyphicon glyphicon-chevron-right"></span></a>' +
			'</div>' +
			'</div>' +
			'<div class="panel-body">' +
			$("#net").html()+
			'<div class="panel panel-default">'+
			'<div class="panel-heading">'+
			'<h3 class="panel-title">其他网络征信信息</h3>'+
				'</div>'+
				'<div class="panel-body">'+
				'<fieldset class="disabled1Class"><div class="form-group form-group-sm"><div class="input-tip"><label class="col-md-3 col-xs-3 col-sm-3 control-label">报告日期：</label><div class="col-md-5 cl-sm-5 col-xs-5"><input type="hidden" name="relavants['+i+'].netResult" value="1" /><input type="text" name="relavants['+i+'].netReportDate" value="' + getValue(a[i], 'netReportDate') + '" placeholder="请输入报告日期" disabled="disabled" class="form-control date required dateISO" required="" aria-required="true" /></div></div><div class="input-tip"><label class="col-md-3 col-xs-3 col-sm-3 control-label">风险等级：</label><div class="col-md-5 cl-sm-5 col-xs-5"><select name="relavants['+i+'].riskStatus" value="' + a[i].riskStatus + '" class="form-control riskStatus" disabled="disabled" required="" aria-required="true"><option value="1">正常</option><option value="2">黑名单</option><option value="3">灰名单</option></select></div></div></div><div class="form-group form-group-sm"><label class="col-md-3 control-label">网络征信：</label><div class="col-md-21"><textarea disabled="disabled" required="" aria-required="true" class="form-control netReportDetailVal netReportDetail' + i + '" rows="3" name="relavants['+i+'].creditRemark"></textarea></div></div>' +
				'</fieldset>' +
				'</div>'+
				'</div>'+
			'</div>' +
			'</div>' +*/
			//---网络征信信息结束
            '</div>',
            '</div>'
        ];
        if (a[i].borrowerRelationship == 1) {    //本人
            var oneself = htmlArr.join('');
            $(".partyBox").append(oneself);
            selectCreditResult(a[i].id,".partyBox")
        } else if (a[i].borrowerRelationship == 2) {    //妻子
            htmlArr[0] = '<div class="panel panel-default partyList">';
            htmlArr[2] = '<h3 class="panel-title">配偶 - ' + a[i].fullName + '</h3>';
            var wife = htmlArr.join('');
            $("#wife").append(wife);
            selectCreditResult(a[i].id,"#wife")
        } else {
            htmlArr[0] = '<div class="panel panel-default partyList party_mean">';
            htmlArr[2] = '<h3 class="panel-title">借款关系人 - ' + a[i].fullName + '</h3>';
            var Borrower = htmlArr.join('');
            $("#partyBox").append(Borrower);
            selectCreditResult(a[i].id,"#partyBox")
        }
        ;
        if (a[i].riskDetail) {
            $(".netReportDetail" + i + "").html(a[i].riskDetail);
        } else {
            $(".netReportDetail" + i + "").html('无记录');
        }
        $("select[name='relavants[" + i + "].checkResult']").attr("value", a[i].checkResult).change();    //调查结果
        $("input[name='relavants[" + i + "].checkResultStatus'][value='" + a[i].checkResultStatus + "']").attr("checked", true);    //调查结果状态
        viewCredit();
    }
    selectCheck();
    $(".disabledClass,.disabled1Class").attr("disabled", true);
    $('.flexBtn').each(function(){
        var value = $(this).attr("data-status");
        $(this).flexBtnInit(value);
    })
};

CustomerLoad = function (a, b, c) {
    comn.ajax({
        url: b,
        async: false,
        data: {
            projectId: a.projectId,
            loanApplyId: a.loanApplyId,
			flowType: a.flowType,
			conversion:a.conversion
        },
        success: function (res) {
            $("#creditInfoForm").values(res.data);
            if (res.data.source == 2) $(".Number").addClass('hide');
            args["creditApplyId"] = res.data.id;
            args["customerId"] = res.data.customerId;
            if ($('#loadCreditInfo')) {
                $('#loadCreditInfo').getLoad();
            }
            loanVal(res.data.relavants, c); //借款人、借款关系人信息处理
            $(".panel-body #net").removeClass('hide')
        }
    });
};

selectCheck = function () {
    var length = $("select").length;
    for (var i = 0; i < length; i++) {
        var val = $("select:eq(" + i + ")").attr('value');
        $("select:eq(" + i + ") option[value=" + val + "]").attr("selected", true);
    }
};


})()

$(function () {
	if(args["pageStatus"]=="1"||args["pageStatus"]=="2"||args["pageStatus"]=="3"||args["pageStatus"]=="4"){
		CustomerLoad({
	        flowType: args['releventFlow'] || "",
	        projectId: args["projectId"],
	        loanApplyId: args["loanApplyId"],
	        conversion:true
	    }, interUrl.credit.loanCreditInfo, '1');
	}else{
		CustomerLoad({
			flowType: args['releventFlow'] || "",
			projectId: args["projectId"],
			loanApplyId: args["loanApplyId"]
		}, interUrl.credit.loanCreditInfo, '1');
	}

    //展开关闭动画
    $(document).on("click", ".open", function () {
        $(this).removeClass("open").addClass("closeOp").parents(".openBox").next(".openValBox").stop().slideDown(1000);
    });
    $(document).on("click", ".closeOp", function () {
        $(this).removeClass("closeOp").addClass("open").parents(".openBox").next(".openValBox").stop().slideUp(1000);
    });
});

//工行征信结果html
function bankHtml(){
var bankHtml='<form class="creditResultForm hide">'+
			'<input name="creditRelavantId" type="hidden"/>'+
			'<div class="form-group form-group-sm">'+
					'<div class="input-tip">'+
						'<label class="col-md-3 col-xs-3 col-sm-3 control-label">客户信用等级：</label>'+
						'<div class="col-md-21">'+
							'<textarea required="" aria-required="true" class="form-control level" rows="3" name="result"></textarea>'+
						'</div>'+
					'</div>'+
			'</div>'+
			'<div class="form-group form-group-sm">'+
					'<div class="input-tip">'+
						'<label class="col-md-3 col-xs-3 col-sm-3 control-label">贷款逾期记录：</label>'+
						'<div class="col-md-21">'+
							'<textarea required="" aria-required="true" class="form-control loanRecode" rows="3" name="loancrdt"></textarea>'+
						'</div>'+
					'</div>'+
			'</div>'+
			'<div class="form-group form-group-sm">'+
				'<div class="input-tip">'+
						'<label class="col-md-3 col-xs-3 col-sm-3 control-label">信用卡逾期记录：</label>'+
						'<div class="col-md-21">'+
						'<textarea required="" aria-required="true" class="form-control cardRecode" rows="3" name="cardcrdt"></textarea>'+
						'</div>'+
					'</div>'+
			'</div>'+
			'<div class="form-group form-group-sm">'+
			'<div class="input-tip">'+
				'<label class="col-md-3 col-xs-3 col-sm-3 control-label">工行专项卡分期笔数：</label>'+
				'<div class="col-md-21">'+
					'<textarea required="" aria-required="true" class="form-control count" rows="3" name="leftnum"></textarea>'+
				'</div>'+
			'</div>'+
	'</div>'+
	'<div class="form-group form-group-sm">'+
			'<div class="input-tip">'+
				'<label class="col-md-3 col-xs-3 col-sm-3 control-label">未结清余额：</label>'+
				'<div class="col-md-21">'+
					'<textarea required="" aria-required="true" class="form-control money" rows="3" name="leftamount"></textarea>'+
				'</div>'+
			'</div>'+
	'</div>'+
	'<div class="form-group form-group-sm">'+
		'<div class="input-tip">'+
				'<label class="col-md-3 col-xs-3 col-sm-3 control-label">备注：</label>'+
				'<div class="col-md-21">'+
				'<textarea required="" aria-required="true" class="form-control note" rows="3" name="creditRemark"></textarea>'+
				'</div>'+
			'</div>'+
	'</div></form>';
return bankHtml
}
//查询银行征信结果
function selectCreditResult(revalantId,item){
	$.ajax({
		url:"/customer/credit/selectCreditResult.html",
		data:{
			"creditRelavantId":revalantId
		},
		async:false,
		success:function(res){
			if(res.code=="20001"){
				$(item).find(".level").html("查询中")
				$(item).find(".loanRecode").html("查询中")
				$(item).find(".cardRecode").html("查询中")
				$(item).find(".count").html("查询中")
				$(item).find(".money").html("查询中")
				$(item).find(".note").html("查询中")
				//失败也要置灰
				$(".creditResultForm").find("textarea").attr("disabled",true)
			}else{
				data=res.data
				//把银行征信信息放到页面上
				$(item).find(".level").html(data.result)
				$(item).find(".loanRecode").html(data.loancrdt)
				$(item).find(".cardRecode").html(data.cardcrdt)
				$(item).find(".count").html(data.leftnum)
				$(item).find(".money").html(data.leftamount)
				$(item).find(".note").html(data.note)
				$(".creditResultForm").find("textarea").attr("disabled",true)
			}
		}
	})
}

//如果拥有相应节点权限，则显示工行征信结果，否则不显示
function viewCredit(){
	$.ajax({
		url:"/customer/credit/selectRight.html",
		async:false,
		success:function(item){
			if(item.data){
				$(".creditResultForm").removeClass("hide")
			}
		}
	})
}