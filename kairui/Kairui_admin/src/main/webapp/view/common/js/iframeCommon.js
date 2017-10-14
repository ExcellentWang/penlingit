var comn, queryParams, ref, tableData, tip;
comn = {};

tip = null;
function isArray(o){
	return Object.prototype.toString.call(o)=='[object Array]';
}
function initDropdown(o){
	var selectObj=$("#"+o.id);
	comn.ajax({url:"cache/getDropDown",
				data:{"type":o.type},
				success: function (res) {
				 var data=res.data; 
				 if(o.noSelect){
					 selectObj.append("<option value=''>--请选择--</option>");
				 }
				 data=JSON.parse(data);
				 for( var  key in data){
					 selectObj.append("<option value='"+key+"'>"+data[key]+"</option>");
				 }
			 }
			 });
}


(function() {
  tip = function(o) {
    var base;
    return typeof (base = window.parent.comn).tip === "function" ? base.tip(o) : void 0;
  };
  return comn = {
    user: window.parent.user,
    cache: window.parent.cache,
    table: {
      "undefinedText": "--",
      "classes": "table-striped table-hover table",
      "pagination": true,
      "sidePagination": "server",
      "queryParams": "queryParams",
      "paginationFirstText": "第一页",
      "paginationPreText": "上一页",
      "paginationNextText": "下一页",
      "paginationLastText": "最后一页",
      "clickToSelect": true,
      "height": "500"
    },
    toUrl: function(o) {
      var base;
      if (o.url.indexOf(".html") > -1) {
        return typeof (base = window.parent).toUrl === "function" ? base.toUrl(o.url) : void 0;
      }
    },
	closeTab: function(){
		window.parent.closeTab();
	},
    addTab: function(o) {
      if (o.href) {
        return window.parent.menuItemClick.call(o);
      }
    },
	accAdd: function(arg1, arg2){ //js精度问题(加法) 
		var r1,r2,m; 
		try{
			r1=arg1.toString().split(".")[1].length
		}catch(e){r1=0} 
		try{
			r2=arg2.toString().split(".")[1].length
		}catch(e){r2=0} 
		m=Math.pow(10,Math.max(r1,r2)) 
		return (arg1*m+arg2*m)/m 
	},
	accSub: function(arg1, arg2){ //js精度问题(减法) 
		var r1,r2,m,n; 
		try{
			r1=arg1.toString().split(".")[1].length
		}catch(e){r1=0} 
		try{
			r2=arg2.toString().split(".")[1].length
		}catch(e){r2=0} 
		m=Math.pow(10,Math.max(r1,r2)); 
		//last modify by deeka 
		//动态控制精度长度 
		n=(r1>=r2)?r1:r2; 
		return ((arg1*m-arg2*m)/m).toFixed(n); 
	},
	accMul: function(arg1, arg2){  //js精度问题(乘法) 
		var m=0,s1=arg1.toString(),s2=arg2.toString(); 
		try{m+=s1.split(".")[1].length}catch(e){} 
		try{m+=s2.split(".")[1].length}catch(e){} 
		return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m); 
	},
	accDiv: function(arg1, arg2){  //js精度问题(除法) 
		var t1=0,t2=0,r1,r2; 
		try{t1=arg1.toString().split(".")[1].length}catch(e){} 
		try{t2=arg2.toString().split(".")[1].length}catch(e){} 
		with(Math){ 
			r1=Number(arg1.toString().replace(".","")) 
				r2=Number(arg2.toString().replace(".","")) 
				return (r1/r2)*pow(10,t2-t1); 
		} 
	},
    ajax: function(o) {
      var _this, mask;
      //console.log((o.url + " -->") + JSON.stringify(o.data));
      mask = layer.load();
      _this = this;
      if (o.url) {
        return $.ajax({
          url: interUrl.basic + o.url,
          type: o.type || "POST",
          dataType: "json",
          async: o.async || true,
          data: o.data || {},
          complete: function(jqXHR, textStatus) {
            return layer.close(mask);
          },
          success: function(data) {
            if (data.code === 20000) {
              return tip({
                content: data.message || "<code>" + o.url + "</code><br /> 接口异常！！！"
              });
            } else if (data.code === 30000) {
              return window.parent.location.href = "../../../index.html";
            } else {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              return typeof o.success === "function" ? o.success(data) : void 0;
            }
          },
          error: function(jqXHR, textStatus, errorThrown) {
            return typeof o.error === "function" ? o.error(textStatus) : void 0;
          }
        });
      }
    },
    getArgs: function() {
      var args, i, item, items, name, qs, value;
      qs = (location.search.length > 0 ? location.search.substring(1) : "");
      items = (qs.length ? qs.split("&") : []);
      args = {};
      i = 0;
      while (i < items.length) {
        item = items[i].split("=");
        name = decodeURIComponent(item[0]);
        value = decodeURIComponent(item[1]);
        if (name.length) {
          args[name] = value;
        }
        i++;
      }
      return args;
    },
	/*
	 *省市区三级联动,传递参数
	 *{type: "car", level: [{
	 *    el: $("#carBrandID")  渲染对象
	 *    key: code  选中值
	 *    target: $("#id") 中文赋值对象
	 *},{
	 *    el: $("#carMakeID")
	 *    key: code
	 *    target: $("#id")
	 *},{
	 *    el: $("#carModelID")
	 *    key: code
	 *}]}
	 */
    linkage: function(o) {
      var o0, o1, o2;
      if (o.type === "car") {
        o0 = o.level[0];
        o1 = o.level[1];
        o2 = o.level[2];
        if (o1.key) {
          o1.el.getCarList(o0.key, o1.key).unbind("change").change(function() {
            if (o1.target) {
              o1.target.val($(this).find("option:selected").text());
            }
            o2.el.val("");
            if (this.value) {
              return o2.el.getCarModel(this.value);
            }
          });
        }
        if (o2.key) {
          o2.el.getCarModel(o1.key, o2.key).unbind("change").change(function() {
            if (o1.target) {
              return o2.target.val($(this).find("option:selected").text());
            }
          });
        }
        return o0.el.getBrand(o0.key || "").unbind("change").change(function() {
          if (o0.target) {
            o0.target.val($(this).find("option:selected").text());
          }
          if (this.value) {
            o2.el.val("");
            return o1.el.getCarList(this.value).unbind("change").change(function() {
              if (o1.target) {
                o1.target.val($(this).find("option:selected").text());
              }
              if (this.value) {
                return o2.el.getCarModel(this.value).unbind("change").change(function() {
                  if (o2.target) {
                    return o2.target.val($(this).find("option:selected").text());
                  }
                });
              }
            });
          }
        });
      }
    },
    getDropDown:function(o){
    	if(isArray(o)){
    		$.each(o,function(i,obj){
    			initDropdown(obj);
    		});
    	}else{
    		initDropdown(o);
    	}
    }
  };
})();

$.fn.extend({
  nameValues: function() {
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
  },

  flexBtnInit: function(value){//flexBtn初始化，value取值0或1，0代表展开状态，1代表折叠状态
      value=value?value:0;
      var pbody=$(this).parents('.collapseFlex').find('.panel-body');
      $(this).off('click').click(function(){
      var status=$(this).attr('data-status');
      if(status=='0'){
      pbody.slideUp();
      $(this).attr('data-status',1).css('transform','rotate(0)');
      }else{
      pbody.slideDown();
      $(this).attr('data-status',0).css('transform','rotate(90deg)');
      }
      });
      if(value==0){
      pbody.show();
      $(this).css('transform','rotate(90deg)');
      }else{
      pbody.hide();
      $(this).css('transform','rotate(0)');
      }
      }
});

$(function() {
  $(window).resize(function() {
    var base;
    return typeof (base = $("table")).bootstrapTable === "function" ? base.bootstrapTable('resetView') : void 0;
  });
  $("body").on("click", "a", function(e) {
    var ref;
    if (((ref = $(this).href) != null ? ref.index(".html") : void 0) > -1) {
      e.preventDefault();
      return comn.toUrl({
        "url": $(this).href
      });
    }
  }).on("focus", ".date", function() {
    //if(!$(this).is(":disabled")){ $(this).attr("readonly", true).css("background-color", "#FFF"); }
    var base;
    return typeof (base = $(this)).datetimepicker === "function" ? base.datetimepicker({
      format: "yyyy-mm-dd",
      pickerPosition: "bottom-right",
      language: "zh-CN",
      minView: 2,
      todayHighlight: true,
      autoclose: true,
      todayBtn: true,
      show: true
    }) : void 0;
  }).on("show.bs.tab", "[data-toggle='tab']", function(e) {
    return $($(this).attr("href")).find("[data-url]").each(function(){ $(this).getLoad(); });
  }).on("click", ".btn[modal='reset']", function() {
    var ref;
    return (ref = $(this).parents("form")[0]) != null ? ref.reset() : void 0;
  }).on("keyup", ".number, .mobile", function(){
	  //if(!/^\d*(?:\.\d{0,2})?$/.test(this.value)){
		  //this.value = ''; 
	  //}
	  //this.value = this.value.replace(/\D+.]/g,''); 
  });
  $(".modal").on("show.bs.modal", function() {
    if ($(this).find("form").length) {
      return $(this).find("form")[0].reset();
    }
  });
  return $("#btn-search").click(function() {
    return $("#table").bootstrapTable('refresh', {url: "..."});
  });
});



if ((ref = $.validator) != null) {
  ref.setDefaults({
    highlight: function(e) {
      return $(e).closest(".input-tip").removeClass("has-success").addClass("has-error");
    },
    success: function(e, r) {
      return $(r).closest(".input-tip").removeClass("has-error").addClass("has-success");
    },
    errorPlacement: function(e, r) {
	  if(r.parent('.input-group').length) {
		  e.insertAfter(r.parent());
	  } else {
		  if (e.text()) {
			return e.appendTo((r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent()));
		  }
	  }
    }
  });
}

tableData = function(params, data, url, callback) {
  var p;
  p = params.data;
  if (url) {
    return comn.ajax({
      url: url,
      data: $.extend(data, p),
      success: function(res) {
        params.success({
          'total': res.totalItem,
          'rows': res.data
        });
        params.complete();
        return typeof callback === "function" ? callback(res) : void 0;
      }
    });
  }
};

queryParams = function(params) {
  return {
    search: params.search,
    page: (params.limit + params.offset) / params.limit,
    pageSize: params.limit
  };
};
$.fn.extend({
	initCacheDropDown:function(o){
		var  dropdown_type=[];
		var  dropdown_nd={};
		var  dropdown_nd_v={};//如果有不选择的情况的默认值
		var  dropdown_exclu=[];//排除某一些选择
		var dropdownele={};
		$(this).each(function(){
			dropdown_type.push($(this).attr("data-type"));//获取redis的key
			dropdown_nd[$(this).attr("data-type")]=$(this).attr("data-nd");
			dropdownele[$(this).attr("data-type")]=$(this);
			dropdown_nd_v[$(this).attr("data-type")]=$(this).attr("data-nd-val")||"";//如果有不选择的情况的默认值
			dropdown_exclu.push($(this).attr("data-exclu")||" ");//排除某一些选择
		});
		comn.ajax({url:"cache/getDropDown",
			data:{"type":dropdown_type.join("|"),"exclu":dropdown_exclu.join("|")},
			success:(function (_this) {
                return function (res) {
                	 var data=JSON.parse(res.data);
                	 console.log(data);
                	 for(var key in data){
                		 results = [];
                		 dropjson=data[key];
                		 if(!dropdown_nd[key]){
    						 results.push("<option value='"+dropdown_nd_v[key]+"'>--请选择--</option>");
    					 }
                		 for( var  k in dropjson){
                			 results.push("<option value='"+k+"'>"+dropjson[k]+"</option>");
    					 }
                		 dropdownele[key].html(results.join(""));
                	 }
                	 o();
                };
            })(this)
		 });
	}
}); 
/**
 * 
 * 获取当前时间
 */
function getNowTime(){
	function p(s) {
	    return s < 10 ? '0' + s: s;
	}
	var myDate = new Date();
	//获取当前年
	var year=myDate.getFullYear();
	//获取当前月
	var month=myDate.getMonth()+1;
	//获取当前日
	var date=myDate.getDate(); 
	var h=myDate.getHours();       //获取当前小时数(0-23)
	var m=myDate.getMinutes();     //获取当前分钟数(0-59)
	var s=myDate.getSeconds();  

	var now=year+'-'+p(month)+"-"+p(date)/*+" "+p(h)+':'+p(m)+":"+p(s);*/
	return now;
}

