var table_1, table_2,handle_pic;

table_1 = function (params) {
	tableData(params, $.extend($("#taskForm").values(), {
		isProcessed: false
	}), "selectByExampleGuarantee");
};


var handle,tableEvent;

handle = function (value, row, index) {
	var modifyMenu = "";
	if(row.status==2){
		$.ajax({
			url: "/user/getAccess",
			data: {
				"menuName":"保修卡审核"
			},
			async: false,
			success: function(item){
				if(item.data>0){
					modifyMenu+="<li><a class='send'>审核</a></li>"
				}
			}
		})
	}
	var a=["<div class='btn-group btn-group-xs'>", "<button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>操作", "<span class='caret'></span>", "<span class='sr-only'>下拉切换</span>", "</button>", "<ul class='dropdown-menu' role='menu'>", "<li><a class='view'>查看详情</a></li>", modifyMenu, "</ul>", "</div>"].join("");
	return a;
};

tableEvent = {
	"click .update" : function(e, a, item, index) {
		comn.addTab({
			title: '修改消息',
			href: 'Modal/xiaoximanager/xiaoxi/add.html?id='+item.id
		});
	},
	"click .del" : function(e, a, item, index) {
		oppSureModal("确定删除该消息？");
   	 	$("#sureOption").unbind("click").click(function () {
   	 		$.ajax({
   	 			url: "/delTbNewsinformation",
   	 			data: {
   	 				"id":item.id
   	 			},
   	 			success: function(item){
   	 				$("#table1").bootstrapTable("refresh", {url: "..."});
   	 				if(item.code!=0){
	   	 				tip({
	   	 					content:item.msg
	   	 				})
   	 				}else{
	   	 				tip({
	   	 					content:"删除成功！"
	   	 				})
   	 				}
   	 				$("#sureModal").modal('hide')
   	 			}
   	 		});
   	 	})
	},
	"click .view" : function(e, a, item, index) {
		comn.addTab({
			title: '保修卡详情',
			href: 'Modal/guarantee/guaranteecard/add.html?id='+item.guaranteeId+'&type=1'
		});
	},
	"click .send" : function(e, a, item, index) {
		comn.addTab({
			title: '保修卡审核',
			href: 'Modal/guarantee/guaranteecard/add.html?id='+item.guaranteeId+'&type=2'
		});
	}
	
};



$("#btn-search").click(function () {
    $("#table1").bootstrapTable("refresh", {url: "..."});
});
//添加消息
$("#addDeviceType").click(function(){
	comn.addTab({
		title: '添加消息',
		href: 'Modal/xiaoximanager/xiaoxi/add.html'
	});
})

handle_status= function (value, row, index) {
	return [null,null,"待审核","已通过","已拒绝"][value];
};
var deviceType;
deviceType=function(value, row, index){
	if (value==null)return
	return [null,"燃气热水器","储水式电热水器","空气能热水器","壁挂炉","太阳能"][value.substring(1)]
}

var daoqitime;
var shenyutime;

shenyutime=function(value, row, index){
	if(isnull(row.guaranteetime)||isnull(row.buytime))return
	var a=new Date().getTime()-new Date(row.buytime).getTime()
	var time1 = parseInt(a / (1000 * 60 * 60 * 24));
	console.log("parseInt(row.guaranteeTime*365) "+row.guaranteetime)
	return parseInt(row.guaranteetime*365)-time1;
}

daoqitime=function(value, row, index){
	if(isnull(row.guaranteetime)||isnull(row.buytime))return
	var a=DateAdd(row.guaranteetime,new Date(row.buytime))
	return FormatDate(a)
}

function DateAdd( number, date) {
	 date.setFullYear(parseInt(date.getFullYear()) + parseInt(number));
     return date;
}

function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

