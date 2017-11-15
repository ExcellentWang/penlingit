$.fn.extend({
  menuTree: function(data) {
	 var htmlArray = [];
	 var oneMenus = data[0].children;	
	 htmlArray.push('<div class="west-menu-content"><ul>');
	 for(var i=0;i<oneMenus.length;i++){
		 var oneMenu = oneMenus[i];
		 var oneMenuName = oneMenu.text;
		 var twoMenus = oneMenu.children;
		 htmlArray.push(
			 '<li>'+
             	'<div class="one-li-div">'+
                     '<span class="one-left-icon" id="oneMenuIcon-'+(i+1)+'" ></span>'+
                     '<span class="one-left-title">'+oneMenuName+'</span>'+
                     '<span class="one-right-icon-add"></span>'+
                 '</div>');
		 if(twoMenus!=undefined){
			 htmlArray.push('<ul class="two-ul">'+
								'<div class="tow-ul-background"></div>' 
					   		);
			 for(var j=0;j<twoMenus.length;j++){
				 var twoMenu = twoMenus[j];
				 var twoMenuName = twoMenu.text;
				 htmlArray.push('<li onclick="openTab('+twoMenu.id+')"><span class="two-left-icon"></span><span class="two-left-title">'+twoMenuName+'</span></li>');
			 }
			 htmlArray.push('</ul>');
		 }
	 }
	 htmlArray.push('</ul></div>');
	 this.html(htmlArray.join(''));
	 var allLi = $('.west-menu-content').find('li');
	 $('.one-li-div').click(function(){
		 var $span = $(this).children(".one-right-icon-minus");		 
		 if($span.length){//收起二级菜单
			 $span.attr('class','one-right-icon-add');
			 $(this).next().slideUp();
		 }else{//展开二级菜单
			 $span = $(this).children(".one-right-icon-add");
			 $span.attr('class','one-right-icon-minus');
			 $(this).next().slideDown(); 
		 }	
	 });
	 $('.two-ul li').click(function(){
		allLi.filter('.selected').removeClass('selected');
		$(this).addClass('selected'); 
		//添加选中的当前二级菜单的上级菜单样式
		$('.one-li-div').filter('.oneMenuSelected').removeClass('oneMenuSelected');
		$(this).parent(".two-ul").prev(".one-li-div").addClass("oneMenuSelected");
		
	 });
  }
});

$.fn.extend({'userChoose':function(data,sUserRoleList){
	var aLetter = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];	
	var aCheckId = [];
	var _this = this;
	loadHtml(data);
	function loadHtml(data){
		var htmlArray = [];
		htmlArray.push('<div class="firstLetter">按拼音首字母选择<input type="text" id="search" name="search" class="search-text" placeholder="请输入登录名拼音首字母"></div>');
		htmlArray.push('<ul id="oneUl" class="oneUl">');
			for(var i=0;i<aLetter.length;i++){
				var aData = data[aLetter[i]];
				if(aData!=undefined&&aData.length>0){
					var line = aData.length%5==0?Math.floor(aData.length/5):(Math.floor(aData.length/5)+1);
					var iHeight = line*40;
					htmlArray.push(' <li class="oneLi" style="height:'+iHeight+'px"><div class="userDiv"><div class="letterDiv">'+aLetter[i]+'</div><ul class="twoUl">');
					for(var j=0;j<aData.length;j++){
						htmlArray.push('<li><input type="checkbox" value='+aData[j].id+' name="userId"');
						for(var k=0;k<sUserRoleList.length;k++){
							if(sUserRoleList[k].userId == aData[j].id){
								htmlArray.push('checked="checked"');
							}
						} 
						htmlArray.push('/>&nbsp;'+aData[j].name+'</li>');
					}
					htmlArray.push('</ul></div></li>');
				}
			}
		htmlArray.push('</ul>');
		$(_this).html(htmlArray.join(''));
	}
	function addIds(){
		$('input[name="userId"]:checked').each(function(){ 
			aCheckId.push($(this).val()); 
		}); 
	}
	function searchHtml(data,userName){
		var ulArray = [];
		for(var i=0;i<aLetter.length;i++){
			var aData = data[aLetter[i]];
			if(aData!=undefined&&aData.length>0){
				var line = aData.length%5==0?Math.floor(aData.length/5):(Math.floor(aData.length/5)+1);
				var iHeight = line*40;
				ulArray.push(' <li class="oneLi" style="height:'+iHeight+'px"><div class="userDiv"><div class="letterDiv">'+aLetter[i]+'</div><ul class="twoUl">');
				for(var j=0;j<aData.length;j++){					
					ulArray.push('<li><input type="checkbox" value='+aData[j].id+' name="userId"');
					if(aCheckId.indexOf(aData[j].id)!=-1){
						ulArray.push('checked="checked"');
					}
					ulArray.push('/>&nbsp;'+aData[j].name+'</li>');
				}
				ulArray.push('</ul></div></li>');
			}
		}
		$('#oneUl').html(ulArray.join(''));
	}
	$('#search').keyup(function(){
		addIds();
		var letter = $(this).val();
		if(letter.length == 0){
			searchHtml(data);
			return
		}
		var newData = {};
		if(data[letter.toUpperCase()]!=undefined||letter.length>0){
			newData[letter.toUpperCase()] =  data[letter.toUpperCase()];
			searchHtml(newData);
		}
	});
}});

$.extend({
  imgDrag: function(obj){
	  	//初始化
		var oDiv = document.getElementById(obj.id);
		oDiv.innerHTML="";
		var oUl = document.createElement('ul');
		var iWidth = obj.width||160;
		var iHight = obj.height||120;
		var iRowNum = obj.rowNum||4;
		oUl.className = 'imgUl';
		oUl.style.width = (iWidth+20)*iRowNum+'px';
		var aLiHtml = []; 
		for(var i=0;i<obj.aImg.length;i++){
			aLiHtml.push('<li style="width:'+iWidth+'px;height:'+iHight+'px"><img src="'+obj.aImg[i]+'" /></li>');
		}
		oUl.innerHTML = aLiHtml.join('');	
		oDiv.appendChild(oUl);
		
		//布局转换
		var aLi=oUl.getElementsByTagName('li');
		var aPos=[];
		var iMinZindex=2;
		var i=0;	
		for(i=0;i<aLi.length;i++){
			aPos[i]={left: aLi[i].offsetLeft, top: aLi[i].offsetTop};
		}
		for(i=0;i<aLi.length;i++){
			aLi[i].style.left=aPos[i].left+'px';
			aLi[i].style.top=aPos[i].top+'px';
			aLi[i].style.position='absolute';
			aLi[i].style.margin='0';
			aLi[i].index=i;
		}
		
		//拖拽
		for(i=0;i<aLi.length;i++){
			setDrag(aLi[i]);
		}
		
		function setDrag(obj){
			obj.onmousedown=function (ev){
				var oEvent=ev||event;
				obj.style.zIndex=iMinZindex++;
				var disX=oEvent.clientX-obj.offsetLeft;
				var disY=oEvent.clientY-obj.offsetTop;
				document.onmousemove=function (ev){
					var oEvent=ev||event;			
					obj.style.left=oEvent.clientX-disX+'px';
					obj.style.top=oEvent.clientY-disY+'px';
					for(i=0;i<aLi.length;i++){
						aLi[i].className='';
					}
					var oNear=findNearest(obj);
					if(oNear){
						oNear.className='active';
					}
				};
				document.onmouseup=function (){
					document.onmousemove=null;
					document.onmouseup=null;
					var oNear=findNearest(obj);
					if(oNear){
						oNear.className='';
						oNear.style.zIndex=iMinZindex++;
						obj.style.zIndex=iMinZindex++;
						startMove(oNear, aPos[obj.index]);
						startMove(obj, aPos[oNear.index]);
						var tmp=0;
						tmp=obj.index;
						obj.index=oNear.index;
						oNear.index=tmp;
					}else{
						startMove(obj, aPos[obj.index]);
					}
				};
				clearInterval(obj.timer);
				return false;
			};
		}
		
		//碰撞检测
		function cdTest(obj1, obj2){
			var l1=obj1.offsetLeft;
			var r1=obj1.offsetLeft+obj1.offsetWidth;
			var t1=obj1.offsetTop;
			var b1=obj1.offsetTop+obj1.offsetHeight;
			
			var l2=obj2.offsetLeft;
			var r2=obj2.offsetLeft+obj2.offsetWidth;
			var t2=obj2.offsetTop;
			var b2=obj2.offsetTop+obj2.offsetHeight;
			
			if(r1<l2 || l1>r2 || b1<t2 || t1>b2){
				return false;
			}else{
				return true;
			}
		}
		
		function getDis(obj1, obj2){
			var a=obj1.offsetLeft-obj2.offsetLeft;
			var b=obj1.offsetTop-obj2.offsetTop;
			return Math.sqrt(a*a+b*b);
		}
		//找到碰上的，并且最近的
		function findNearest(obj){
			var iMin=999999999;
			var iMinIndex=-1;
			for(i=0;i<aLi.length;i++){
				if(obj==aLi[i])continue;
				if(cdTest(obj, aLi[i])){
					var dis=getDis(obj, aLi[i]);
					if(iMin>dis){
						iMin=dis;
						iMinIndex=i;
					}
				}
			}
			
			if(iMinIndex==-1){
				return null;
			}else{
				return aLi[iMinIndex];
			}
		}
		
		function getStyle(obj, attr){
			if(obj.currentStyle){
				return obj.currentStyle[attr];
			}else{
				return getComputedStyle(obj, false)[attr];
			}
		}

		function startMove(obj, json, fn){
			clearInterval(obj.timer);
			obj.timer=setInterval(function (){
				var bStop=true;		//这一次运动就结束了——所有的值都到达了
				for(var attr in json){
					//1.取当前的值
					var iCur=0;
					if(attr=='opacity'){
						iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
					}else{
						iCur=parseInt(getStyle(obj, attr));
					}
					
					//2.算速度
					var iSpeed=(json[attr]-iCur)/8;
					iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
					
					//3.检测停止
					if(iCur!=json[attr]){
						bStop=false;
					}
					
					if(attr=='opacity'){
						obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
						obj.style.opacity=(iCur+iSpeed)/100;
					}else{
						obj.style[attr]=iCur+iSpeed+'px';
					}
				}
				if(bStop){
					clearInterval(obj.timer);
					if(fn){
						fn();
					}
				}
			}, 30)
		} 
  }
});

//地区组建封装 -DY
$.extend({
 	areaSelect:function(data){
		var aId = data.aId;
		var oProvice = $('#'+aId[0]);
		var oCity = $('#'+aId[1]);
		var oDistrict = $('#'+aId[2]);
		//生成数组
		var aCity=[],aDistrict=[];
		initArray();
		
		if(data.provice!=undefined){	
			oProvice.val(data.provice);
			initCity(data.city);
			initDistrict(data.district);
		}
		
		function initArray(){
			oProvice.append('<option value="0">请选择</option>');
			for(var i=0;i<aArea.length;i++){
				var area = aArea[i];
				area.d==1 && oProvice.append('<option value="'+area.a+'">'+area.c+'</option>');
				area.d==2 && aCity.push(area);
				area.d==3 && aDistrict.push(area);
			}	
			initCity();	
		}
		
		
		function initCity(city){
			oCity.empty(); 
			oCity.append('<option value="0">请选择</option>');
			for(var i=0;i<aCity.length;i++){	
				var d = aCity[i];
				d.b == $('#'+aId[0]).val() && oCity.append('<option value="'+d.a+'">'+d.c+'</option>');		
			}
			//oCity.val(city);
		}
		
		function initDistrict(district){
			oDistrict.empty(); 
			var _District=[];
			for(var i=0;i<aDistrict.length;i++){
				var d = aDistrict[i];
				if(d.b==oCity.val()){
					_District.push(d);
				}
			}
			if(_District.length>0){
				
				oDistrict.fadeIn();
				oDistrict.append('<option value="0">请选择</option>');
				for(var i=0;i<_District.length;i++){
					var d = _District[i];
					 oDistrict.append('<option value="'+d.a+'">'+d.c+'</option>');		
				}
			}else{
				oDistrict.fadeOut();
			}
			
			//oDistrict.val(district);
		}

		//绑定选择方法
		oProvice.change(function(){
			initCity();		
			initDistrict();
		});
		oCity.change(function(){
			initDistrict();					   
		});
	}	
	
});

//原版
//$.extend({
// 	areaSelect:function(data){
//		var aId = data.aId;
//		var oProvice = $('#'+aId[0]);
//		var oCity = $('#'+aId[1]);
//		var oDistrict = $('#'+aId[2]);
//		//生成数组
//		var aCity=[],aDistrict=[];
//		initArray();
//		function initArray(){
//			for(var i=0;i<aArea.length;i++){
//				var area = aArea[i];
//				area.d==1 && oProvice.append('<option value="'+area.a+'">'+area.c+'</option>');
//				area.d==2 && aCity.push(area);
//				area.d==3 && aDistrict.push(area);
//			}	
//			initCity();	
//		}
//		
//		if(data.provice!=undefined){	
//			oProvice.val(data.provice);
//			initCity(data.city);
//			initDistrict(data.district);
//		}
//		
//		function initCity(city){
//			oCity.empty(); 
//			for(var i=0;i<aCity.length;i++){	
//				var d = aCity[i];
//				d.b == $('#'+aId[0]).val() && oCity.append('<option value="'+d.a+'">'+d.c+'</option>');		
//			}
//			oCity.val(city);
//		}
//		
//		function initDistrict(district){
//			oDistrict.empty(); 
//			for(var i=0;i<aDistrict.length;i++){
//				var d = aDistrict[i];
//				d.b==oCity.val() && oDistrict.append('<option value="'+d.a+'">'+d.c+'</option>');			
//			}
//			oDistrict.val(district);
//		}
//
//		//绑定选择方法
//		oProvice.change(function(){
//			initCity();		
//			initDistrict();
//		});
//		oCity.change(function(){
//			initDistrict();					   
//		})	
//	}	
//});
