var table_2;
var args=comn.getArgs();
	table_2 = function (params) {
//		param={"height": "500"}
		tableData(params, $.extend({}, {
			isProcessed: false,
			"customerId":args['id']
		}), "selGuaranteeCustomerTail");
	};
	$("#table2").bootstrapTable({
	    "undefinedText": "--",
	    "classes": "table-striped table-hover table",
	    "pagination": false,
	    "height": "150",
	    "sidePagination": "server",
	    "queryParams": "queryParams",
	    "clickToSelect": true
	  });

$(function(){
	var isUpload=false;
	$.ajax({
		url: "/selGuaranteeCustomerId",
		data:{"id":args['id']},
		success: function(item){
			$("#lunboForm").values(item.data)
			if(item.data.status==0){
				$("[id='status']").val("待受理")
			}
			if(item.data.status==1){
				$("[id='status']").val("已受理")
			}
			if(item.data.status==2){
				$("[id='status']").val("维修完成")
			}
			$("[name=type]").val([null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][args['type'].substring(1)])
			//故障类型text
			$.ajax({
				url: "/selGuaranteeTypeById",
				data:{"id":item.data.repairtype},
				success: function(item){
					$("[name='repairtype']").val(item.data.typeName)
				}
			})
		}
	});
	//报修图片
	$.ajax({
		url: "/getCustomerPics",
		data:{"customer_id":args['id']},
		success: function(item){
			var json=item.data;
			var lis="";
			if(!item.data)
				return
			for(var i=0;i<json.length;i++){
			    lis+="<span><img height='200px' width='200px' src='"+json[0].pictureAddress+"' </span>";
			}
			console.log(lis)
			$("#img").append(lis);
		}
	});
	
	//所有维修人员
	$.ajax({
		url: "/selectByExampleStaff",
		data:{},
		success: function(item){
			if(item.data){
				var da=item.data;
				console.log(da)
				for( var i=0;i<da.length; i++){
					var option=" <option value="+da[i].id+">"+da[i].name+"</option>";
					$("[name='stafflist']").append(option)
				}
			}
			
		}
	});
	//安排维修
	$("#anpaibu").click(function(){
		$("#anpai").modal("show")
		$("#confirm").unbind("click").click(function () {
    		$("#anpai").modal('hide')
    		//选中维修人员
    		$.ajax({
    			url: "/saveGuaranteeCustomerTail",
    			data:{
    				"id":$("[name='stafflist']").val(),
    				"logType":1,
    				"customerId":args['id']
    				},
    			success: function(item){
    				$("#table2").bootstrapTable("refresh", {url: "..."});
    				if(item.code==0){
    					$("#anpaibu").addClass("hide")
    					tip({
    						content:"操作成功！"
    					})
    				}else{
    					tip({
    						content:"操作失败！"
    					})
    				}
    			}
    		});
    	})
	})
	//安排维修
	$("#comple").click(function(){
		$.ajax({
			url: "/saveGuaranteeCustomerTail",
			data:{
				"id":$("[name='stafflist']").val(),
				"logType":2,
				"customerId":args['id']
			},
			success: function(item){
				if(item.code==0){
					comn.closeTab()
					tip({
						content:"操作成功！"
					})
				}else{
					tip({
						content:"操作失败！"
					})
				}
			}
		});
		
	})
	
	//按钮显示隐藏
	if(args['status']==0){
		$("#anpaibu").removeClass("hide")
	}
	if(args['status']==1){
		$("#comple").removeClass("hide")
	}
})

