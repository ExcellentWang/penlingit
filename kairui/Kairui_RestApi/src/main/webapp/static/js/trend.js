$(function(){
	function ajaxdata(indexN,callback){
		var typeNo = $(".swiper-slide").eq(indexN).attr("val");
		//datastory按钮统计
		DS.ready(function(){
	        DS.sendBtnName($(".swiper-slide").eq(indexN).text());
	    });
		zhuge.track("查看"+$(".swiper-slide").eq(indexN).text());
		$.ajax({
			url:'toresult?type='+typeNo,
			type:'get',
			dataType:"json",
			success:function(data){
				callback(indexN,data);
			}
		})
	}  
	var tempcallback = function(indexN,data ){
		//动态刷新值
		data["indexN"] = indexN;
		var htmlStr = $("#swiper_main .swiper-slide").eq(indexN).html();
		if(!htmlStr){
			var html =	$("#tmp").html().replace(/{([^}]+)}/g,
					function($1,$2){
						if($2=="newtime"){
							return data[$2].match(/^[^\s]+/);
						}
						if($2=="unit"){
							if(data.unit){
								return "/"+data[$2]
							}else{
								return"";
							}
						}
						return data[$2];
					})
			$("#swiper_main .swiper-slide").eq(indexN).html(html);
		}
		//圆形设置
		$('#swiper_main .swiper-slide').eq(indexN).find('.chart_box').html("").radialIndicator({
			barColor : data.newval<data.minRange?'#f9f740':(data.newval<data.maxRange?'#00ff72':'#f00812'),
		    barWidth : 4,
		    radius: 90,
		    initValue : 0,       //初始值
		    roundCorner : true,		//是否圆角
		    percentage: false,		//是否显示百分号 
		    displayNumber: false,   //是否显示数字
		    showPercentage : false,
		    minValue: 0,
		    maxValue: Math.floor(data.maxRange*1.5)
		}).data('radialIndicator').animate(data.newval);
		
		
		var trendData  = new Array();
		trendData[0] = new Array();
		trendData[1] = new Array();
		trendData[2] = new Array();
		
		for(var i=0, tmpData = data.trendData.length; i<tmpData;i++){
			trendData[0][i] = data.trendData[tmpData-1-i].time.match(/^\d+-([^\s]+)/)[1];
			trendData[1][i] = data.trendData[tmpData-1-i].val;
			trendData[2][i] = data.minRange;
		}
		
		var myChart = echarts.init(document.getElementById('chart_data'+indexN));
		var option1 = {
		    color: ['#fc7a40','#4ED67F'],
		    tooltip: {},
		    legend: {
		    	itemWidth: 15,
		    	itemHeight: 15,
		        data:[{
		        	name: '个人数据',
		        	icon: 'rect'
		        },
		        {
		        	name: '最小范围',
		        	icon: 'rect'
		        }	
		        ],
		        top: 18,
		    },
		    xAxis: {
		        data: trendData[0],
		        axisLine: {
		        	lineStyle: {
		        		color: '#e7e7e7',
		        	}
		        },
		        axisTick:{
		        	inside : true,
		        },
		        axisLabel: {
		        	show: true,
		        	textStyle: {
		        		color: '#8f8f8f',
		        		fontSize: 10,
		        	}
		        },
		    },
		    yAxis: {
		    	splitLine: {
		            show: true,
		            lineStyle: {
		            	color: '#e7e7e7',
		            }
		        },
		        splitNumber: 5,
		        minInterval: 15, 
		        min: (data.minval>data.minRange?Math.floor(data.minRange*0.9):Math.floor(data.minval*0.9)),  
		        max: Math.ceil(data.maxval*1.2), 
		        triggerEvent: true,  
		        axisLine: {
		        	lineStyle: {
		        		color: '#e7e7e7',
		        	}
		        },
		        axisTick:{
		        	inside : true,
		        },
		        axisLabel: {
		        	show: true,
		        	textStyle: {
		        		color: '#8f8f8f',
		        		fontSize: 10,
		        	}
		        },
		        
		    },
		    
		    series: [{
		        name: '个人数据',
		        type: 'line',
		        data: trendData[1],
		        
		        coordinateSystem : 'cartesian2d',
		        lineStyle: {
		        	normal: {
		        		color: '#fc7a40',
		        		width: '1'
		        	}
		        },
		    },
		    {
		        name: '最小范围',
		        type: 'line',
		        data: trendData[2],
		        coordinateSystem : 'cartesian2d',
		        lineStyle: {
		        	normal: {
		        		color: '#4ED67F',
		        		width: '1'
		        	}
		        },
		    }],
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option1);
	}
	/***********************************************************************************************/
	/***********************************************************************************************/
	/***********************************************************************************************/
	/***********************************************************************************************/
	
	//swiper设置
	var mySwiperTop = new Swiper('#swiper_top',{
		watchSlidesProgress : true,
		watchSlidesVisibility : true,
		slidesPerView : 4.6,
		onTap: function(swiper){
			mySwipermain.slideTo( swiper.clickedIndex);
		}
	})
	var mySwipermain = new Swiper('#swiper_main',{
		initialSlide :2,
		onSlideChangeStart: function(swiper){
			updateNavPosition(swiper);
		}
	})
	function updateNavPosition(swiper){
		ajaxdata(swiper.activeIndex,tempcallback);
		$('#swiper_top .active-nav').removeClass('active-nav')
		var activeNav = $('#swiper_top .swiper-slide').eq(swiper.activeIndex).addClass('active-nav');


		if (!activeNav.hasClass('swiper-slide-visible')) {
				
			if (activeNav.index()>mySwiperTop.activeIndex) {
				
				var thumbsPerNav = Math.floor(mySwiperTop.width/activeNav.width())-1
				mySwiperTop.slideTo(activeNav.index()-thumbsPerNav)
			}
			else {
				
				mySwiperTop.slideTo(activeNav.index())
			}
			
		}
	}
});