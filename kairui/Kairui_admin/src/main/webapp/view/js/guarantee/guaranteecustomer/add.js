var table_2;
	table_2 = function (params) {
		tableData(params, $.extend({}, {
			isProcessed: false
		}), "selGuaranteeCustomerTail");
	};
$(function(){
	var args=comn.getArgs();
	var isUpload=false;
	
	$.ajax({
		url: "/selGuaranteeCustomerId",
		data:{"id":args['id']},
		success: function(item){
			$("#lunboForm").values(item.data)
			if(item.status==0){
				$("[name='status']").val("待受理")
			}
			if(item.status==1){
				$("[name='status']").val("已受理")
			}
			if(item.status==0){
				$("[name='status']").val("维修完成")
			}
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
    		$("#anpaibu").modal('hide')
    		//选中维修人员
    		$.ajax({
    			url: "/saveGuaranteeCustomerTail",
    			data:{"personname":$("[name='stafflist']").text()},
    			success: function(item){
    				if(item.code==0){
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
			data:{"personname":$("[name='stafflist']").text()},
			success: function(item){
				if(item.code==0){
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
		comn.closeTab()
	})
	
})

