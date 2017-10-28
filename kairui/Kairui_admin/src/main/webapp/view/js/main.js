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
			"url": "./Modal/task/userManage/system/user/inittest.jsp"
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
	},
	 	{
			"menuName": "设备管理",
			"sysMenuList": [
			    {
				"menuName": "设备列表",
				"url": "./Modal/device/devicelist/index.html"
			    },
				{
					"menuName": "设备异常日志",
					"url": "./Modal/device/deviceerror/index.html"
				},
				{
					"menuName": "用水量/节水量统计",
					"url": "./Modal/device/deviceusewater/index.html"
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
	 	},
		{
	 		"menuName": "保修服务",
	 		"sysMenuList": [
	 		     {
					"menuName": "保修卡",
					"url": "./Modal/guarantee/guaranteecard/index.html"
	 		     },
	 		     {
					"menuName": "用户报修",
					"url": "./Modal/guarantee/guaranteecustomer/index.html"
		 		 },
		 		{
						"menuName": "报修类型",
						"url": "./Modal/guarantee/guaranteetype/index.html"
		 		},
		 		{
					"menuName": "维修员工管理",
					"url": "./Modal/guarantee/staff/index.html"
	 		}
	 		 ]
	 	},
	 	{
	 		"menuName": "资讯管理",
	 		"sysMenuList": [
	 		     {
					"menuName": "轮播管理",
					"url": "./Modal/newsmanager/lunbomanager/index.html"
	 		     },
	 		     {
					"menuName": "资讯列表",
					"url": "./Modal/newsmanager/newslist/index.html"
		 		 }
	 		 ]
	 	},
	 	{
	 		"menuName": "消息管理",
	 		"sysMenuList": [
	 		     {
					"menuName": "消息列表",
					"url": "./Modal/xiaoximanager/xiaoxi/index.html"
		 		 }
	 		 ]
	 	},
	]);

	$("#imageSwitch").on("hide.bs.modal", function(){ 
		if(imgIds && imgIds.length > 0){
			recordDocQuery(imgIds.join(",")); 
		}
	});


	$.ajax({
		url: interUrl.basic + "user/getMenuByUser",
		data: {
		},
		type: "POST",
		success: function(data) {
			if (data.code === 30000) {
				return location.href = "./index.html";
			} else if (data.code === 2) {
				$("#dialogTip").nameValues({
					content: data.message
				});
				return $("#dialogTip").modal("show");
			} else {
//				initMenu(data.data);
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
	
});

