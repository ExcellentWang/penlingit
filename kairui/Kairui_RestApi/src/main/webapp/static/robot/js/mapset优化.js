
var map = new BMap.Map("map_content",{minZoom:5,maxZoom:16});   
// map.setMapStyle({
//   styleJson:[]
// });
function posifun(){ //兼容ios10的系统不支持http协议问题
	var geolocation = new BMap.Geolocation();  
	geolocation.getCurrentPosition(function (r) {  
		if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
			var mk = new BMap.Marker(r.point);  
			currentLat = r.point.lat;  
			currentLon = r.point.lng;  
          
			var pt = new BMap.Point(currentLon, currentLat);  
			var geoc = new BMap.Geocoder(); 
			map.centerAndZoom(pt, 15);
			//标记当前坐标
			addMarker1(pt);

			geoc.getLocation(pt, function (rs) {  
				var addComp = rs.addressComponents;  
				var city = addComp.city;  
				var addComp = rs.addressComponents;  
				var texts = addComp.district + "-" + addComp.street + "-" + addComp.streetNumber;  
				//获取地理位置成功，跳转  
				var urlcity = 'http://api.map.baidu.com/geocoder/v2/?output=json&ak=T6PElGOQ58xZikZqfK99tiqwKNza7zHv&location=';
				$.ajax({
					url: urlcity + currentLat+','+currentLon,
					type:'GET',
					dataType:'jsonp',
				}).then(getdata);
			})
		}  
	})
}

if (navigator.geolocation){
	navigator.geolocation.getCurrentPosition(showPosition,showError);
}else{
	alert('Geolocation is not supported by this browser.');
}

//getdata 请求获取数据
var getdata = function(data){
	//alert(data);
	var thiscity1 = data.result.addressComponent.city;
	var thiscity = thiscity1.indexOf('市') < 0 ? thiscity1 : thiscity1.substring(0,thiscity1.length-1);
	$.ajax({
		url:'gps?city=' + thiscity,
		type:'GET',	
		dataType:'json'
	}).then(returnrobotdata);
};

var returnrobotdata = function(data){
	showmarker(data);
};


function showPosition(position){
	var point = new BMap.Point(position.coords.longitude,position.coords.latitude);  // 创建点坐标  
	
	var convertor = new BMap.Convertor();
	var pointArr = [];
	pointArr.push(point);
	convertor.translate(pointArr, 1, 5 ,function(pointA){
		map.centerAndZoom(pointA.points[0], 15);
		addMarker1(pointA.points[0]);
		point.getLocation(pointA.points[0], function (rs) {  
			var addComp = rs.addressComponents;  
			var city = addComp.city;  
			var addComp = rs.addressComponents;  
			var texts = addComp.district + "-" + addComp.street + "-" + addComp.streetNumber;  
			//获取地理位置成功，跳转  
			var urlcity = 'http://api.map.baidu.com/geocoder/v2/?output=json&ak=T6PElGOQ58xZikZqfK99tiqwKNza7zHv&location=';
			$.ajax({
				url: urlcity + position.coords.latitude +','+position.coords.longitude,
				type:'GET',
				dataType:'jsonp',
			}).then(getdata);
		})
	});
}
function showError(error)
{
	switch(error.code) 
	{
	case error.PERMISSION_DENIED:
	  alert("User denied the request for Geolocation.")
	  break;
	case error.POSITION_UNAVAILABLE:
	  posifun();
	  //alert("Location information is unavailable.")
	  //break;
	case error.TIMEOUT:
	  alert("The request to get user location timed out.")
	  break;
	case error.UNKNOWN_ERROR:
	  alert("An unknown error occurred.")
	  break;
	}
}

map.enableScrollWheelZoom(); //设置放大缩小
map.disableDragging();     //禁止拖拽
setTimeout(function(){
   map.enableDragging();   //两秒后开启拖拽
   //map.enableInertialDragging();   //两秒后开启惯性拖拽
}, 2000);
	
var l_icon = new BMap.Icon('/Health_Scale_Portal/static/robot/img/l_icon.png', new BMap.Size(25, 25), {
	offset: new BMap.Size(0, 0),
	imageOffset: new BMap.Size(0, 0), // 设置图片偏移 
    anchor: new BMap.Size(0, 0)
});//定义图标
var a_icon = new BMap.Icon('/Health_Scale_Portal/static/robot/img/a_icon.png', new BMap.Size(25, 25), {
	offset: new BMap.Size(0, 0),
	imageOffset: new BMap.Size(0, 0), // 设置图片偏移 
    anchor: new BMap.Size(0, 0)
});//定义图标


//当前位置图标
var thisicon = new BMap.Icon('/Health_Scale_Portal/static/robot/img/thisicon.png', new BMap.Size(20, 20), {
	offset: new BMap.Size(0, 0),
	imageOffset: new BMap.Size(0, 0), // 设置图片偏移 
    anchor: new BMap.Size(0, 0)
});

window.dqClickDom = {};

// 编写自定义函数,创建标注
function addMarker(point,arrayobj){
	var marker = new BMap.Marker(point,{icon:l_icon});
	map.addOverlay(marker);
	(function() {  
		marker.addEventListener("click", function(){          
			//this.openInfoWindow(infoWindow); map.zoomIn();
			$('#showrobot').show();
			$('#hos').html(arrayobj.shop);
			$('#addr').html(arrayobj.address);
			$('#iphone').html(arrayobj.mobile);
			$('.robot_status').hide();
			tagIcon(this);
		});
	})();
}
function addMarkerF(point,arrayobj){
	var marker = new BMap.Marker(point,{icon:a_icon});
	map.addOverlay(marker);
	(function() { 
		marker.addEventListener("click", function(){          
			$('#showrobot').show();
			$('#hos').html(arrayobj.shop);
			$('#addr').html(arrayobj.address);
			$('#iphone').html(arrayobj.mobile);
			$('.robot_status').show();
			tagIcon(this);
		});
	})();
}

function addMarker1(point){
	var marker = new BMap.Marker(point,{icon:thisicon});
	map.addOverlay(marker);
}

// 向地图添加标注
function showmarker(robotdata){
	for (var i = 0; i < robotdata.length; i++) {
		var x = robotdata[i].lng;
		var y = robotdata[i].lat;
		var pointX = new BMap.Point(x,y);
		var arrayobj = {};
		arrayobj.shop = robotdata[i].shop;
		arrayobj.address = robotdata[i].address;
		arrayobj.mobile = robotdata[i].mobile;
		if (robotdata[i].isBoot == 1) {
			addMarker(pointX,arrayobj);
		}else{
			addMarkerF(pointX,arrayobj);
		}
	}
}

var tagIcon = function(obj){
	var _this = $($(obj).get(0).zc).find('img');

		if(dqClickDom.length){
			var pngImgPrev = dqClickDom.attr('src').indexOf('a_icon') < 0 ? '/Health_Scale_Portal/static/robot/img/l_icon.png' : '/Health_Scale_Portal/static/robot/img/a_icon.png';
			    dqClickDom.parent().width(25);
			    dqClickDom.parent().height(25);
			    dqClickDom.attr('src',pngImgPrev);
			    dqClickDom.parent().parent().css("z-index",9);
		}

	    var pngImg = _this.attr('src').indexOf('a_icon') < 0 ? '/Health_Scale_Portal/static/robot/img/l_icon_b.png' : '/Health_Scale_Portal/static/robot/img/a_icon_b.png';
	    _this.parent().width(25);
	    _this.parent().height(25);
	    _this.attr('src',pngImg);
	    _this.width(25);_this.height(25);
	    _this.parent().parent().css("z-index",999);
	    dqClickDom = _this;

}