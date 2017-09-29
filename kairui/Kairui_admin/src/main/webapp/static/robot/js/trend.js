(function ($) {
    $.fn.showhighcharts = function(data) {
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });
            var date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            var time = date.setSeconds(0);

            //格式化
            function formatNum(num) {
                return num >= 10 ? num : '0' + num;
            }
            
          //点击点显示
            function showEar(point) {
                var x = point.x,
                    y = point.y,
                    date = new Date(x);
                //点击小圆点暂时空着以后加效果
            }
            return this.each(function () {
            var self = $(this);
            
            //显示最近一次测量数据
            var lastData = data[data.length - 1];

            //生成曲线图
            self.find('#chart').highcharts({
                chart: {
                    type: 'line',
                    height: 200
                },
                credits: {
                    enabled: false
                },
                legend: {
                    enabled: false
                },
                title: {
                    text: null
                },
                xAxis: {
                    type: 'datetime',
                    gridLineWidth: 1,
                    gridLineDashStyle: 'dot',
                    gridLineColor: '#f2f2f2',
                    lineColor: '#f2f2f2',
                    tickColor: '#f2f2f2',
                    min: time - 24 * 3600 * 1000 * 30,
                    max: time,
                    tickPositioner: function () {
                        var positions = [];
                        positions.push(time - 24 * 3600 * 1000 * 30);
                        positions.push(time - 24 * 3600 * 1000 * 25);
                        positions.push(time - 24 * 3600 * 1000 * 20);
                        positions.push(time - 24 * 3600 * 1000 * 15);
                        positions.push(time - 24 * 3600 * 1000 * 10);
                        positions.push(time - 24 * 3600 * 1000 * 5);
                        positions.push(time);
                        return positions;
                    },
                    labels: {
                        formatter: function () {
                            var date = new Date(this.value);
                            return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                        }
                    }
                },
                yAxis: {
                    title: {
                        text: null
                    },
                    lineWidth: 1,
                    gridLineColor: '#f2f2f2',
                    lineColor: '#f2f2f2'
                },
                tooltip: {
                    enabled: false
                },
                plotOptions: {
                    series: {
                        marker: {
                            fillColor: null,
                            lineWidth: 2,
                            lineColor: null
                        },
                        point: {
                            events: {
                                mouseOver: function () {
                                    showEar(this);
                                }
                            }
                        },
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        }
                    }
                },
                series: [
                    {
                        name: '正常',
                        lineWidth: 1,
                        color: '#77d726',
                        data: data
              }
            ]
            });


            //切换X轴:前7天
            self.on('click','#seven', function () {
                var chart = self.find('#chart').highcharts();
                if ($(this).hasClass('active')) {
                    return;
                }
                $(this).addClass('active').siblings().removeClass('active');
                chart.xAxis[0].update({
                    tickPositioner: function () {
                        var positions = [];
                        positions.push(time - 24 * 3600 * 1000 * 6);
                        positions.push(time - 24 * 3600 * 1000 * 5);
                        positions.push(time - 24 * 3600 * 1000 * 4);
                        positions.push(time - 24 * 3600 * 1000 * 3);
                        positions.push(time - 24 * 3600 * 1000 * 2);
                        positions.push(time - 24 * 3600 * 1000 * 1);
                        positions.push(time);
                        return positions;
                    },
                    labels: {
                        formatter: function () {
                            var date = new Date(this.value);
                            return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                        }
                    }
                });
                chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 6, time, true, {
                    duration: 1000
                });
            });

            //切换x轴:前30天
            self.on('click',"#thirty", function () {
                var chart = self.find('#chart').highcharts();
                if ($(this).hasClass('active')) {
                    return;
                }
                $(this).addClass('active').siblings().removeClass('active');
                chart.xAxis[0].update({
                    tickPositioner: function () {
                        var positions = [];
                        positions.push(time - 24 * 3600 * 1000 * 30);
                        positions.push(time - 24 * 3600 * 1000 * 25);
                        positions.push(time - 24 * 3600 * 1000 * 20);
                        positions.push(time - 24 * 3600 * 1000 * 15);
                        positions.push(time - 24 * 3600 * 1000 * 10);
                        positions.push(time - 24 * 3600 * 1000 * 5);
                        positions.push(time);
                        return positions;
                    },
                    labels: {
                        formatter: function () {
                            var date = new Date(this.value);
                            return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                        }
                    }
                });
                chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 30, time, true, {
                    duration: 1000
                });
            });

            //切换x轴:当天
            self.on('click','#day', function () {
                var chart = self.find('#chart').highcharts();
                if ($(this).hasClass('active')) {
                    return;
                }
                $(this).addClass('active').siblings().removeClass('active');
                chart.xAxis[0].update({
                    tickPositioner: function () {
                        var positions = [];
                        positions.push(time);
                        positions.push(time + 4 * 3600 * 1000);
                        positions.push(time + 8 * 3600 * 1000);
                        positions.push(time + 12 * 3600 * 1000);
                        positions.push(time + 16 * 3600 * 1000);
                        positions.push(time + 20 * 3600 * 1000);
                        positions.push(time + 24 * 3600 * 1000);
                        return positions;
                    },
                    labels: {
                        formatter: function () {
                            var date = new Date(this.value);
                            if (date.getHours() == 0) {
                                return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                            } else {
                                return formatNum(date.getHours()) + ':00';
                            }
                        }
                    }
                });
                chart.xAxis[0].setExtremes(time, time + 24 * 3600 * 1000, true, {
                    duration: 1000
                });
            });
        });
    }
})(jQuery);

(function($){
		
		//设置 指标状态：偏高，偏低，正常，相应的文案信息
		var parent =".status";
		var show = ".jy_show";
		var showSpan = "span"
		var desc = ".desc";
		var down = "down";
		var up = "up";
		for(var item in uhtsi){
			var itemClass = $(parent+" ."+item);
			var val = uhtsi[item].value;
			var state = uhtsi[item].state
			if(val<state[0]){
				uhtsi[item].KBIData = 0;
				uhtsi[item].KBIDesc = KBIDesc[item][0];
				itemClass.find(show).addClass(down);
			}else if(state.length==1 || val < state[1]){
				uhtsi[item].KBIData = 2;
				uhtsi[item].KBIDesc = KBIDesc[item][2];
			}else{
				itemClass.find(show).addClass(up);
				uhtsi[item].KBIData = 1;
				uhtsi[item].KBIDesc = KBIDesc[item][1];
			}
			itemClass.find(show+" "+showSpan).html(KBIData[uhtsi[item].KBIData]);
			itemClass.find(desc).html(uhtsi[item].KBIDesc);
		}
		
		//swiper设置
		var mySwiperTop = new Swiper('#swiper_top',{
			watchSlidesProgress : true,
			watchSlidesVisibility : true,
			slidesPerView : 2,
			effect : 'coverflow',
			centeredSlides: true,
			pagination : '.swiper-pagination',
			paginationClickable :true,
			speed:600,
			onTap: function(){
				mySwipermain.slideTo( mySwiperTop.clickedIndex)
			}
		})
		
		var mySwipermain = new Swiper('#swiper_main',{
            autoHeight: true,
            onInit: function(swiper){
                    swiper.container[0].style.height=swiper.slides[swiper.activeIndex].offsetHeight+'px';
                },
            onTouchStart: function(swiper){
              swiper.container[0].style.height=swiper.slides[swiper.activeIndex].offsetHeight+'px';
            },
            onSlideChangeEnd: function(swiper){
                    swiper.container[0].style.height=swiper.slides[swiper.activeIndex].offsetHeight+'px';
                },
            onSlideChangeStart: function(swiper){
            laodData(swiper);
            }
    	})
	    mySwiperTop.params.control = mySwipermain;//需要在Swiper2初始化后，Swiper1控制Swiper2
	    mySwipermain.params.control = mySwiperTop;//需要在Swiper1初始化后，Swiper2控制Swiper1

	    $('.jc_items').on('click','li',function(e){
	      var thisIndex = $(this).index();
	      mySwiperTop.slideTo(thisIndex+2, 500, false);
	      mySwipermain.slideTo(thisIndex+2, 500, true);
	      mySwipermain.container[0].style.height=mySwipermain.slides[mySwipermain.activeIndex].offsetHeight+'px';
	    })

		//获取趋势数据
		var getTrend = function(dataType,obj,context){
			var oldVal = obj.val;							//当前选择时间的  值
			var oldTime = new Date(obj.time.replace(/-/g, '/')).getTime();		//当前选择的时间
			var currTime = new Date(currTimeStr.replace(/-/g, '/')).getTime();			//当前时间
			var changeTime = Math.ceil((currTime - oldTime)/86400000);	//时间差
			changeTime = changeTime == 0 ? 1 : changeTime;
			var currVal = uhtsi[dataType].value;			//最近一次的值
			var maxVal, minVal, changeVal , changeAdded="";					//最大峰值，最小峰值，数据变化的值,附加值变化的值（脂肪率）
			var data = {
					type:uhtsiType[dataType],
					state:uhtsi[dataType].KBIData
			}
			if(oldVal > currVal){
				maxVal = oldVal;
				minVal = currVal;
				changeVal = (oldVal - currVal).toFixed(2);
				data["datachange"] = 1;
			}else if(oldVal < currVal){
				maxVal = currVal;
				minVal = oldVal;
				changeVal = (currVal - oldVal).toFixed(2);
				
				data["datachange"] = 0;
			}else{
				maxVal = currVal;
				minVal = oldVal;
				changeVal = 0;
				data["datachange"] = 2;
			}
			
			if(changeVal>=0){
				if(changeVal < uhtsi[dataType].changeValue[0]){
					data["changerange"] = 0;
				}else if(changeVal < uhtsi[dataType].changeValue[1]){
					data["changerange"] = 1;
				}else{
					data["changerange"] = 2;
				}
			}
			
			if(changeTime<7){
				data["timerange"] = 0;
			}else if(changeTime<=14){
				data["timerange"] = 1;
			}else{
				data["timerange"] = 2;
			}
			
			if(dataType == "weight"){
				var oldAdded = obj.addedval;
				var currAdded = uhtsi["bodyfat"].value;
				changeAdded = oldAdded > currAdded ? oldAdded - currAdded : currAdded - oldAdded;
				changeAdded = changeAdded.toFixed(2);
				if(oldAdded < currAdded){
					data["addedvalue"] = 0
				}else if(oldAdded > currAdded){
					data["addedvalue"] = 1
				}else{
					data["addedvalue"] = 2
				}
			}
			var descDoc = "本阶段,您的初始"+uhtsi[dataType].name+"为<span>"+oldVal+uhtsi[dataType].unit+"</span>,最高峰为<span>"+maxVal+uhtsi[dataType].unit+"</span>,最低峰为<span>"+minVal+uhtsi[dataType].unit+"</span>,"+changeTime+"天内"+uhtsi[dataType].name+change[data["datachange"]];
			$(context).find('#descDoc').html(descDoc);
			$.ajax({
				  url: "getUserHealthTrendInfo",
				  type: "post",
				  dataType : "json",
				  data: data,
				  success: function(resultData){
					  var trendDoc = resultData[0].content;
					  trendDoc = trendDoc.replace("{day}",changeTime+"");
					  trendDoc = trendDoc.replace("{change}",changeVal);
					  trendDoc = trendDoc.replace("{added}",changeAdded);
					  $(context).find('#trendDoc').html(trendDoc);
				  }
			});
		}
		  function laodData(swiper){
			var index = swiper.activeIndex+1;
			var obj = $(".main"+index);
			var type = obj.attr("val");
			var dataType = uhtsiType[type];
			var timeType = 3;
			var historyDate;
			
			if(1<index){
				//统计诸葛事件
				zhuge.track("查看"+uhtsi[type].name);
				if(!obj.attr("resultData")){
				  $.ajax({
					  url: "queryUserHealthInfo",
					  type: "post",
					  dataType : "json",
					  data: {
						  "dataType": dataType,
						  "timeType" : timeType,
						  "end":currTimeStr
					  },
					  success: function(resultData){
						  historyDate = resultData;
						  obj.attr("resultData",1);
						  var data = [];
						  for(var i in resultData){
							  var timeDate = new Date(resultData[i].time.replace(/-/g, '/'));
							  var time = timeDate.getTime();
							  if(i == 1){
								  obj.find("#screen select").append("<option selected='selected' value='"+i+"'>"+resultData[i].time+"</option>"); 
							  }else{
								  obj.find("#screen select").append("<option value='"+i+"'>"+resultData[i].time+"</option>"); 
							  }
							  data.push({x:time,y:resultData[i].val});
						  }
						  data.reverse();
						  obj.showhighcharts(data);
						  if(resultData.length>1){
							  //对比前一条数据
							  getTrend(type,historyDate[1],obj);
						  }else if(resultData.length>0){
							//对比自身
							  getTrend(type,historyDate[0],obj);
						  }
					  }})
				}
			}
			obj.find("#screen select").on("change",function(){
				var option = $(this).val();
				getTrend(type,historyDate[option],obj);
			})
		}
	
})(jQuery);
