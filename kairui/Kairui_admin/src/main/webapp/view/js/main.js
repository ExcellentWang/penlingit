var angle, cache, cardType,  imgArrString, initMenu, swp, swpImage, user, recordDocQuery, allImg, imgIds, toVal;

recordDocQuery = null;

imgIds = [];

swp = null;

user = null;

imgArrString = "";

angle = 0;

allImg = [];

retprotUrl = [];

cache = {};

cardType = function(v) {
	return ['', '身份证', '军官证', '侨胞证', '外籍人士'][v] || "";
};

$.fn.nameValues = function() {
	var arg;
	arg = arguments[0];
	return $(this).find("[data-name]").each(function(index, item) {
		var key, keySwitch, value;
		key = $(this).data("name");
		keySwitch = $(this).data("formatter");
		if (keySwitch) {
			value = window[keySwitch](arg[key]) || "";
		}
		if (key) {
			return $(item).html(value || arg[key] || "");
		}
	});
};

var a = function toggle() {
	var flag = true;
	function get() {
		return flag = !flag; 
	}
	return get; 
}();

toggleTopNav = function(){
	$("#topNav").toggleClass("hide")
	var o = {};
	if(a()){
		o['height'] = "calc(100% - 110px)";
	}else{
		o['height'] = "calc(100% - 0px)";
	}
	$("#content-main").css(o); 
}

swpImage = function(o) {
	if(o.title){ $("#dirTitle").text(o.title); }
	return $.ajax({
		url: interUrl.basic + interUrl.gr.getPhoto,
		type: 'POST',
		data: {
			loanApplyId: o.loanApplyId,
			dirId: o.dirId,
			fileNamespace: o.fileNamespace,
			releventFlow: o['releventFlow'],
			releventFlowNode: o['releventFlowNode']
		},
		success: function(res) {
			  var arr = [], _index = 0, ref = res.data.loanDocumentList, results = [];
			  for (k = j = 0, len = ref.length; j < len; k = ++j) {
				i = ref[k];
				arr.push(i.filePath);
				if (i.id === o.id) {
				  results.push(_index = k);
				} else {
				  results.push(void 0);
				}
			  }
			  allImg = res.data.loanDocumentList;
			  switchImage(arr, _index, 1);
			  recordDocQuery = function(ids) {
				  var obj = {}, ids = ids.split(",");
				  for(var i=0; i< allImg.length; i++){
					  for(var j=0; j< ids.length; j++){
						  if(allImg[i].id == ids[j]){
							  if(!obj[allImg[i].dirId]){obj[allImg[i].dirId] = []};
							  obj[allImg[i].dirId].push(ids[j]); 
						  } 
					  }
				  }
				  for(item in obj){
					  $.ajax({
						  url: interUrl.basic + interUrl.gr.recordDocQueryHistory,
						  type: "post",
						  data: {
							  loanApplyId: o.loanApplyId,
							  dirId: item,
							  fileNamespace: o.fileNamespace,
							  releventFlow: o['releventFlow'],
							  releventFlowNode: o['releventFlowNode'],
							  docIds: obj[item].join(",")
						  },
						  success: function(res){ 
							  if(res.code == 20000){return comn.tip({content: res.message || "<code>" + o.url + "</code><br /> 接口异常！！！"})};
							  if(typeof(o.callback == "function")){ 
								  imgIds = [];
								  o.callback(ids.join(","));
							  } 
						  }
					  });

				  
				  }

			  }
			if(res.data.loanDocumentList[_index].hasRead == 1){ imgIds.push(res.data.loanDocumentList[_index].id); }
			toVal = function(dirId){
				var dirId = dirId || allImg[_index].dirId;
				$("#guarantor")[0].innerHTML = "暂无数据!";
				if(res.data && res.data.photoInfo && res.data.photoInfo[dirId].Guarantor_Info){
					$.each(res.data.photoInfo[dirId].Guarantor_Info, function(i, item){
						$("#guarantor").append($("#guarantorTPL").html()).children().eq(i).nameValues(item); 
					});
				}
				if (res.data.photoInfo && res.data.photoInfo[dirId].Customer_Info) {
					$(".customer").show().nameValues(res.data.photoInfo[dirId].Customer_Info);
				}else{
					$(".customer").hide();
				}
				if (res.data.photoInfo && res.data.photoInfo[dirId].Spouse_Info) {
					$(".spouse").show().nameValues(res.data.photoInfo[dirId].Spouse_Info);
				}else{
					$(".spouse").hide();
				}
				if ((ref2 = res.data.photoInfo) != null ? ref2.Credit_Info : void 0) {
					$(".coBank").show().nameValues(res.data.photoInfo[dirId].Credit_Info);
				} else {
					$(".coBank").hide();
				} 
			}
			toVal();

			/*
			 *   参数1：图片列表
			 *   参数2：显示图片下标
			 *   参数3：是否显示图片信息(1显示、2不显示)
			 */
			/*
			 switchImage(['http://zacdn.cgw360.com/cgw360/cls/loan/2cf1539f-a554-4085-acb2-aac5ad6a08e4.png','http://zacdn.cgw360.com/cgw360/cls/loan/6eaec911-ba58-4992-88c6-45a75d58e2f1.png','http://zacdn.cgw360.com/cgw360/cls/loan/a661304c-753b-4a4d-a058-77d7fbacee83.png','http://zacdn.cgw360.com/cgw360/cls/loan/f432845b-3706-4712-8179-2db073658153.png'], 2, 1);
			*/
		}
	});
};

function switchImage(arr, _index, type){
	if(type == 1){
		$("#picInfo")[0].className = "col-md-8";
		$("#picImage")[0].className = "col-md-16"
	}else{
		$("#picInfo")[0].className = "hide";
		$("#picImage")[0].className = "col-md-24"
	}
	$("#imageSwitch").modal("show")
	$.openPhotoGallery(arr, _index);
}

function curImg(index) { 
	if(index){
		$("#curImg").text(index) 
	}else{
		return parseInt($("#curImg").text());
	}
}
function totalImg(total) { $("#totalImg").text(total); }

initMenu = function(data) {
	var itemLi, menu, ulNav;
	ulNav = ['second', 'third'];
	itemLi = function(o, level, k) {
		var ref;
		if (! ((ref = o.sysMenuList) != null ? ref.length: void 0) > 0) {
			
			return ["<li><a class='J_menuItem' href='" + o.url + "' data-index='" + o.id + "'>" + o.menuName + "</a>"].join("");
			
		} else {
			return ["<li>" + "<a>" + ("<i class='fa iconfont " + o.logoTag + "'></i>") + ("<span class='nav-label'>" + o.menuName + "</span>") + "<span class='fa arrow'></span>" + "</a>" + menu(o.sysMenuList, level + 1, k) + "</li>"].join("");
		}
	};
	menu = function(arr, level) {
		var a, i, k, len, o;
		a = [];

		if(arr[arr.length-1]['sysMenuList']){
			$.each(arr[arr.length-1]['sysMenuList'], function(i, item){
				if(item.url.indexOf("?") != -1){
					url = item.url.split("?")[0];
				}else{
					url = item.url;
				}
				retprotUrl.push(url); 
			});
		}
		if (level !== 0) {
			a.push("<ul class='nav nav-" + ulNav[level - 1] + "-level collapse'>");
		}
		if (arr.length > 0) {
			for (k = i = 0, len = arr.length; i < len; k = ++i) {
				o = arr[k];
				a.push(itemLi(o, level, k));
			}
		}
		if (level !== 0) {
			a.push("</ul>");
		}
		return a.join("");
	};	
	$("#side-menu").append(menu(data, 0));
	$("#side-menu").metisMenu();
	return $(".sidebar-collapse").slimScroll({
		height: "100%",
		railOpacity: .9,
		alwaysVisible: ! 1
	});
};

$(function() {
	initMenu([{
		"menuName": "系统设置",
		"sysMenuList": [{
			"menuName": "用户角色管理",
			"url": "./Modal/task/userManage/system/role/init.jsp"
		},{
			"menuName": "系统用户管理",
			"url": "./Modal/task/userManage/system/user/init.jsp"
		},{
			"menuName": "系统参数配置",
			"url": "./Modal/task/userManage/system/dict/init.jsp"
		},{
			"menuName": "用户协议",
			"url": "./Modal/task/userManage/system/yhxy/init.jsp"
		},{
			"menuName": "常见问题管理",
			"url": "./Modal/task/userManage/system/yhwt/init.jsp?dictId=6"
		},{
			"menuName": "修改登录密码",
			"url": "./Modal/task/userManage/system/password.jsp"
		},{
			"menuName": "操作日志",
			"url": "./Modal/task/userManage/system/log/init.jsp"
		}]
	},{
		"menuName": "设备管理",
		"sysMenuList": [{
			"menuName": "设备列表",
			"url": "./Modal/device/devicelist/index.html"
		},
		{
			"menuName": "设备异常日志",
			"url": "./Modal/device/deviceerror/index.html"
		},
		{
			"menuName": "设备类型管理",
			"url": "./Modal/device/devicetype/index.html"
		},
		{
			"menuName": "设备入库",
			"url": "./Modal/device/deviceadd/index.html"
		},
		{
			"menuName": "固件版本",
			"url": "./Modal/device/firmversion/index.html"
		}
		]
	}

	]);

	$("#imageSwitch").on("hide.bs.modal", function(){ 
		if(imgIds && imgIds.length > 0){
			recordDocQuery(imgIds.join(",")); 
		}
	});


	$.ajax({
		url: interUrl.basic + interUrl.user.menu,
		data: {
			st: "CLS_WEB_BEFORE"
		},
		type: "POST",
		data: {
			st: 'CLS_WEB_BEFORE'
		},
		dataType: "json",
		success: function(data, textStatus, jqXHR) {
			if (typeof data === "string") {
				data = JSON.parse(data);
			}
			if (data.code === 30000) {
				return location.href = "./index.html";
			} else if (data.code === 20000) {
				$("#dialogTip").nameValues({
					content: data.message
				});
				return $("#dialogTip").modal("show");
			} else {
				initMenu(data.data);
			}
		}
	});

	$(".J_tabExit").click(function() {
		return $("#logOut").modal("show");
	});
	return $("#exitSure").click(function() {
		$.ajax({
			url: interUrl.basic + interUrl.user.logOut,
			type: "POST",
			dataType: "json",
			success: function(data, textStatus, jqXHR) {
			  if (typeof data === "string") {
				data = JSON.parse(data);
			  }
			  if(data.code == 10000 || data.code == 30000){
					location.href = "./index.html";
			  }
			}
		  }); 
	});
	// 报表菜单加载
	var hrefs = $("#side-menu").find("li");
});

