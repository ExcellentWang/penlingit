(function($){
  Highcharts.setOptions({
    global: {
      useUTC: false
    }
  });
  
  
  var browser = {
		    versions: function () {
		        var u = navigator.userAgent, app = navigator.appVersion;
		        return {         //移动终端浏览器版本信息
		            trident: u.indexOf('Trident') > -1, //IE内核
		            presto: u.indexOf('Presto') > -1, //opera内核
		            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
		            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
		            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
		            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
		            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
		            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
		            iPad: u.indexOf('iPad') > -1, //是否iPad
		            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
		            weixin: u.indexOf('MicroMessenger') > -1,
		            qq: u.match(/\sQQ/i) == " qq"
		        };
		    }(),
		    language: (navigator.browserLanguage || navigator.language).toLowerCase()
	};
  
  var abnormal = {color: '#fe7b40', marker: {states: {
    hover:{fillColor: '#fe7b40',lineWidth: 2, lineColor: '#fe7b40'}}
  }};

  var shareTime = $("#sysDate").val();
  var date = new Date(parseInt(shareTime));
  
  date.setHours(0);
  date.setMinutes(0);
  var time = date.setSeconds(0);
  time = time + 1000*3600*24;

  var openId=$("#openId").val();
  var dataType=$("#dataType").val();
  
  var hData;
  var data;
  var lastData;
  laodData(3);
  
  function laodData(timeType){
	  $.ajax({
		  url: "queryCount",
		  type: "post",
		  dataType : "json",
		  data: {
			  "openId" : openId,
			  "dataType": dataType,
			  "timeType" : timeType
		  },
		  success: function(resultData){
			  if(resultData){
				  var temp = eval("(" + resultData + ")");
		  		  var data = {};
		  		  for(var i=0;i<temp.length;i++){
	  				var x = Number(temp[i].time);
	  				var y = Number(temp[i].value);
	  				var status = Number(temp[i].bmi);
	  				var height = Number(temp[i].height);
	  				data[x] = [y,status,height];
		  		  }
		  		var lastval=temp[temp.length-1];
	  			var y = Number(lastval.value);
	  			var status = lastval.bmi;
	  			var height = lastval.height;
	  			var date = new Date(x);
	  			$('#ear').html(y);
		  		  $('#date').html(parseDate(date));
		  		  if (dataType==1) {
		  			  var start= (height/100*height/100*22*0.9).toFixed(1);
		  			var end = (height/100*height/100*22*1.1).toFixed(1);
		  			var htm = "参考范围："+(height/100*height/100*22*0.9).toFixed(1)+"~"+(height/100*height/100*22*1.1).toFixed(1)+"kg";
		  			$("#rank").html(htm);
		  			if(status<18.5){
				    	 $('.status').html('低于正常体重'+(start-y).toFixed(1)+'kg').addClass('abnormal');
				    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"kg<b id='msg'>低于正常体重"+(start-y).toFixed(1)+"kg</b>");
				    	 $('#context').html('您的体重偏低，应增加膳食的摄入量，增加体重。膳食内容应丰富多样，不挑食，不偏食。在摄入足够蛋白质的情况下，宜多进食一些含脂肪、碳水化合物（即淀粉、糖类等）较丰富的食物。另外，您应保持充足而良好的睡眠，适当运动，对食物的消化和吸收。');
				    }else if(status>=18.5&&status<=24.9){
				    	$('.status').html('正常').removeClass('abnormal');
				    	 $('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"kg<b id='msg'>正常</b>");
				    	 $('#context').html('您的体重在正常范围内，望您坚持健康监测，始终保持健康。');
				    }else if(status>24.9&&status<=28.0){
				    	$('.status').html('超重'+(y-end).toFixed(1)+'kg').addClass('abnormal');
				    	 $('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"kg<b id='msg'>超重"+(y-end).toFixed(1)+"kg</b>");
				    	 $('#context').html('您的体重偏高，建议调整饮食结构，少进高脂肪食物，多进高纤维素食物，适当增加活动，保持健美体型。');
				    }else{
				    	$('.status').html('超重'+(y-end).toFixed(1)+'kg').addClass('abnormal');
				    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"kg<b id='msg'>超重"+(y-end).toFixed(1)+"kg</b>");
				    	 $('#context').html('您的体重偏高，建议调整饮食结构，少进高脂肪食物，多进高纤维素食物，适当增加活动，保持健美体型。');
				    }
				  }else if (dataType==2) {
						if(y<17.1){
					    	 $('.status').html('偏瘦').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏瘦</b>");
					    	 $('#context').html('脂肪率偏低，建议您合理安排饮食，摄入一定脂肪含量的食物，保持正常的健康状态');
					    }else if(y>=17.1&&y<=28){
					    	$('.status').html('正常').removeClass('abnormal');
					    	 $('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>正常</b>");
					    	 $('#context').html('您的脂肪率在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else if(y>28&&y<=35){
					    	$('.status').html('轻度').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>轻度</b>");
					    	 $('#context').html('您的脂肪率偏高，建议您改变生活方式，控制饮食，降低对脂肪含量较高食物的摄入，少吃油炸食品；多运动。');
					    }else{
					    	$('.status').html('肥胖').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>肥胖</b>");
					    	 $('#context').html('您的脂肪率偏高，建议您改变生活方式，控制饮食，降低对脂肪含量较高食物的摄入，少吃油炸食品；多运动。');
					    }
					}else if (dataType==3) {
						if(y<49.5){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏低</b>");
					    	 $('#context').html('低水分率对身体并无益处，较低的水分率可能导致身体体型过胖，危害身体健康。建议您在生活中注意饮食，调整生活作息方式，多锻炼身体。');
					    }else if(y>=49.5&&y<=57){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>正常</b>");
					    	 $('#context').html('您的水分率在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>偏高</b>");
					    	 $('#context').html('高水分率可能导致人体水肿，也可能是肾炎引起的病症。建议您多关注自身水分率比例，多做强身健体的运动。');
					    }
					}else if (dataType==4) {
						if(y<25){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏低</b>");
					    	 $('#context').html('肌肉率偏低的人，基础新陈代谢较慢，容易发心血管疾病，建议您多锻炼身体，多运用');
					    }else if(y>=25&&y<=34){
					    	$('.status').html('标准').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>标准</b>");
					    	 $('#context').html('您的肌肉率在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>偏高</b>");
					    	 $('#context').html('肌肉率偏高的人，新陈代谢快，容易疲惫。建议您降低运动强度，适当调理身体。');
					    }
					}else if (dataType==5) {
						if(y<1.7){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"kg<b id='msg'>偏低</b>");
					    	 $('#context').html('骨量偏低，会出现抽筋以及腰痛等症状。建议您及时补钙以及维生素D');
					    }else{
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"kg<b id='msg'>正常</b>");
					    	 $('#context').html('您的骨含量在正常范围内，望您坚持健康监测，始终保持健康。');
					    }
					}else if (dataType==6) {
						if(y<18.5){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"<b id='msg'>偏低</b>");
					    	 $('#context').html('BMI偏低，说明体型偏瘦，易患营养不良。建议您增加营养的摄入，保证身体的健康状态。');
					    }else if(y>=18.5&&y<=24.9){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"<b id='msg'>正常</b>");
					    	 $('#context').html('您的BMI在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"<b id='msg'>偏高</b>");
					    	 $('#context').html('BMI偏高，说明体型偏胖；BMI愈高，罹患胆结石、第二型糖尿病、高血压、心脏病及高脂血症等疾病的机率也随之增高；建议你合理饮食，调整生活方式，多运动，多吃清谈食物');
					    }
					}else if (dataType==7) {
						if(y>=5.17){
					    	 $('.status').html('偏高').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
					    	 $('#context').html('总胆固醇偏高可能引发人体肝和肺的病变。建议：少吃或不吃动物内脏、蛋黄等胆固醇含量极高的食物，少吃肥肉和荤油，减少饱和脂肪的摄入。多吃蔬菜、水果和菌藻类食物。如果总胆固醇偏高，一定要到医院进行进一步检查，查明总胆固醇偏高的原因，必要时对症治疗。');
					    }else{
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
					    	 $('#context').html('您的总胆固醇含量在正常范围内，望您坚持健康监测，持续保持健康。');
					    }
					}else if (dataType==8) {
						if(y<0.56){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
					    	 $('#context').html('甘油三酯偏低可能会导致甲状腺功能亢进，肾上腺皮质机能减退，肝功能低下，引发脂肪肝等症。建议：如果血脂的其他指标正常，建议加强营养，高蛋白饮食，注意多食些动物脂肪；如果其他各项指标也有不正常，建议去医院进行详细检查，并听取医生意见，以防有疾病隐患。');
					    }else if(y>=0.56&&y<=1.7){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
					    	 $('#context').html('您的甘油三酯含量在正常范围内，望您坚持健康监测，持续保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
					    	 $('#context').html('甘油三酯偏高容易造成“血稠”导致动脉粥样硬化、高血脂。建议：1.多吃粗粮，多吃鱼，肉，瘦肉，豆制品，蔬菜瓜果，少吃精致食品，甜食，奶油，巧克力，肥肉，内脏，鸡皮，鱼子，脑，虾皮等。 2.多吃海带，紫菜，木耳，香菇，大蒜，洋葱，有利于降低血脂，少吃蛋类，少吃油煎炸食品。 3.尽量吃植物油，少食动物油，少吃花生。');
					    }
					}else if (dataType==9) {
						if(y<2.8){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
					    	 $('#context').html('胆固醇酯偏低可能会使细胞膜的稳定性减弱，脆性增加，脑血管易破裂出血。建议： 可以适当增加营养,不要挑食,营养均衡。多吃鸡蛋和肉类。');
					    }else if(y>=2.8&&y<=5.17){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
					    	 $('#context').html('您的胆固醇酯含量在正常范围内，望您坚持健康监测，持续保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
					    	 $('#context').html('胆固醇酯偏高可能引发人体肝和肺的病变。建议：少吃或不吃动物内脏、蛋黄等胆固醇含量极高的食物，少吃肥肉和荤油，减少饱和脂肪的摄入。多吃蔬菜、水果和菌藻类食物。如果总胆固醇偏高，一定要到医院进行进一步检查，查明总胆固醇偏高的原因，必要时对症治疗。');
					    }
					}else if (dataType==10) {
						if(y<0.95){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
					    	 $('#context').html('高密度脂蛋白偏低，可能会导致动脉硬化症、心血管，冠心病等疾病。建议： 要提高高密度脂蛋白胆固醇,降低低密度,要多吃香菇等蘑菇类食物,海藻类食物,豆类,芝麻.要摄入足够的必需脂肪酸.摄入富含EPA,DHA的油脂,可以补充高品质的深海鱼油；平时家里做法要选择高品质的植物油,如橄榄油,亚麻籽油,花生油等.一定避免动物油.饮食要清淡,多吃蔬菜水果,每天可以吃少量的坚果,如大杏仁,核桃等!并且要坚持每天运动。');
					    }else if(y>=0.95&&y<=1.54){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
					    	 $('#context').html('您的高密度脂蛋白含量在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
					    	 $('#context').html('高密度脂蛋白胆固醇升高基本没有害处，通俗的说在标准范围内高密度脂蛋白越高越好，它对人体起好的、保护的作用。');
					    }
					}else if (dataType==11) {
						if(y<1.34){
					    	 $('.status').html('偏低').addClass('abnormal');
					    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
					    	 $('#context').html('低密度脂蛋白偏低并没有害处，在标准范围内，低密度脂蛋白越低越好。');
					    }else if(y>=1.34&&y<=3.1){
					    	$('.status').html('正常').removeClass('abnormal');
					    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
					    	 $('#context').html('您的低密度脂蛋白含量在正常范围内，望您坚持健康监测，始终保持健康。');
					    }else{
					    	$('.status').html('偏高').addClass('abnormal');
					    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
					    	 $('#context').html('低密度脂蛋白偏高，可能会导致过多的胆固醇在血管壁上逐渐沉积，从而会使血管管腔逐渐变狭窄使血流速度不断变慢，发生动脉粥样硬化，可能引起肝病、营养不良、骨髓瘤、急性心肌梗、中风等疾病。    建议：平时饮食要注意少吃含胆固醇高的食物，饮食宜清淡、低盐，适量饮茶，少饮酒或禁酒，多吃粗粮如玉米、高粱、适量精肉、家禽、鱼类、牛奶、鸡蛋蛋白，各种蔬菜、瓜果、适量洋葱、大蒜、香菇、木耳等。纯糖食品要限制。');
					    }
					}else if (dataType==12) {
						$("#rank").html("");
						$("#testVal").hide();
						$("#context").hide();
						$(".status").hide();
					}
				  init(data,timeType);
			  }else {
				  changeX(timeType);
			}
		  }
	  });
  }
  
  
  function init(resultData,timeType){
	//生成并解析数据
	  hData = parseData(resultData);
	  data = hData.lineData;

	  //生成曲线图
	  $('#chart').highcharts({
	    chart: {
	      type: 'spline',
	      height: 300
	    },
	    credits: {
	      enabled: false
	    },
	    legend:{
	      enabled: false
	    },
	    title:{
	      text: null
	    },
	    xAxis:{
	      type: 'datetime',
	      gridLineWidth: 1,
	      gridLineDashStyle: 'dot',
	      gridLineColor: '#f2f2f2',
	      lineColor: '#f2f2f2',
	      tickColor: '#f2f2f2',
	      min: time - 24 * 3600 * 1000 * 30,
	      max: time,
	      //tickPositioner:getPositions(30),
	      tickPositions : getTickPositions(30),
	      labels: {
	        formatter: function(){
	          var date = new Date(this.value);
	          return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
	        }
	      }
	    },
	    yAxis:{
	      title:{
	        text: null
	      },
	      lineWidth: 1,
	      gridLineColor: '#f2f2f2',
	      lineColor: '#f2f2f2',
	      floor: 0,/*,
	      min:36,
	      max:39,
	      tickInterval: 0.5*/
	    },
	    tooltip:{
	      enabled: false
	    },
	    plotOptions: {
	      area: {
	    	  marker:{
	    		  symbol: 'circle'
	    	  }
	      },
	      spline:{
	    	  marker:{
	    		  symbol: 'circle'
	    	  }
	      },
	      series: {
	        marker: {
	          fillColor: null,
	          lineWidth: 2,
	          lineColor: null
	        },
	        point:{
	          events:{
	            mouseOver: function(){
	              showEar(this);
	            }
	          }
	        },
	        states: {
	          hover:{
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

	 //自定义legend
	  var renderer = new Highcharts.Renderer($('#chart')[0], '100%', 50);
	  renderer.circle(40, 10, 6).attr({
	    fill: '#78d623'
	  }).add();
	  renderer.label('正常', 50, 0).css({
	    color: '#545a67'
	  }).add();
	  renderer.circle(100, 10, 6).attr({
	    fill: '#fe7b40'
	  }).add();
	  renderer.label('异常', 110, 0).css({
	    color: '#545a67'
	  }).add();
	  
	  changeX(timeType);
  }

//切换X轴:前7天
  $('#seven').on('click', function(){
	  laodData(2);
  });

//切换x轴:前30天
  $('#thirty').on('click', function(){
	  laodData(3);
  });

//切换x轴:当天
  $('#day').on('click', function(){
	  laodData(1);
  });
  
  function showEar(point){
	    var x = point.x, y = point.y,
	    	status = point.status,height = point.height,
	      date = new Date(x);
	    if (dataType==1) {
	    	var start = (height/100*height/100*22*0.9).toFixed(1);
	    	var end = (height/100*height/100*22*1.1).toFixed(1);
  			var htm = "参考范围："+start+"~"+end+"kg";
  			$("#rank").html(htm);
	    	if(status<18.5){
		    	 $('.status').html('低于正常体重'+(start-y).toFixed(1)+'kg').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"kg<b id='msg'>低于正常体重"+(start-y).toFixed(1)+"kg</b>");
		    	 $('#context').html('您的体重偏低，应增加膳食的摄入量，增加体重。膳食内容应丰富多样，不挑食，不偏食。在摄入足够蛋白质的情况下，宜多进食一些含脂肪、碳水化合物（即淀粉、糖类等）较丰富的食物。另外，您应保持充足而良好的睡眠，适当运动，对食物的消化和吸收。');
		    }else if(status>=18.5&&status<=24.9){
		    	$('.status').html('正常').removeClass('abnormal');
		    	 $('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"kg<b id='msg'>正常</b>");
		    	 $('#context').html('您的体重在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else if(status>24.9&&status<=28.0){
		    	$('.status').html('超重'+(y-end).toFixed(1)+'kg').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"kg<b id='msg'>超重"+(y-end).toFixed(1)+"kg</b>");
		    	 $('#context').html('您的体重偏高，建议调整饮食结构，少进高脂肪食物，多进高纤维素食物，适当增加活动，保持健美体型。');
		    }else{
		    	$('.status').html('超重'+(y-end).toFixed(1)+'kg').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"kg<b id='msg'>超重"+(y-end).toFixed(1)+"kg</b>");
		    	 $('#context').html('您的体重偏高，建议调整饮食结构，少进高脂肪食物，多进高纤维素食物，适当增加活动，保持健美体型。');
		    }
		}else if (dataType==2) {
			if(y<17.1){
		    	 $('.status').html('偏瘦').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏瘦</b>");
		    	 $('#context').html('脂肪率偏低，建议您合理安排饮食，摄入一定脂肪含量的食物，保持正常的健康状态');
		    }else if(y>=17.1&&y<=28){
		    	$('.status').html('正常').removeClass('abnormal');
		    	 $('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>正常</b>");
		    	 $('#context').html('您的脂肪率在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else if(y>28&&y<=35){
		    	$('.status').html('轻度').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>轻度</b>");
		    	 $('#context').html('您的脂肪率偏高，建议您改变生活方式，控制饮食，降低对脂肪含量较高食物的摄入，少吃油炸食品；多运动。');
		    }else{
		    	$('.status').html('肥胖').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>肥胖</b>");
		    	 $('#context').html('您的脂肪率偏高，建议您改变生活方式，控制饮食，降低对脂肪含量较高食物的摄入，少吃油炸食品；多运动。');
		    }
		}else if (dataType==3) {
			if(y<49.5){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏低</b>");
		    	 $('#context').html('低水分率对身体并无益处，较低的水分率可能导致身体体型过胖，危害身体健康。建议您在生活中注意饮食，调整生活作息方式，多锻炼身体。');
		    }else if(y>=49.5&&y<=57){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>正常</b>");
		    	 $('#context').html('您的水分率在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>偏高</b>");
		    	 $('#context').html('高水分率可能导致人体水肿，也可能是肾炎引起的病症。建议您多关注自身水分率比例，多做强身健体的运动。');
		    }
		}else if (dataType==4) {
			if(y<25){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"%<b id='msg'>偏低</b>");
		    	 $('#context').html('肌肉率偏低的人，基础新陈代谢较慢，容易发心血管疾病，建议您多锻炼身体，多运用');
		    }else if(y>=25&&y<=34){
		    	$('.status').html('标准').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"%<b id='msg'>标准</b>");
		    	 $('#context').html('您的肌肉率在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"%<b id='msg'>偏高</b>");
		    	 $('#context').html('肌肉率偏高的人，新陈代谢快，容易疲惫。建议您降低运动强度，适当调理身体。');
		    }
		}else if (dataType==5) {
			if(y<1.7){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"kg<b id='msg'>偏低</b>");
		    	 $('#context').html('骨量偏低，会出现抽筋以及腰痛等症状。建议您及时补钙以及维生素D');
		    }else{
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"kg<b id='msg'>正常</b>");
		    	 $('#context').html('您的骨含量在正常范围内，望您坚持健康监测，始终保持健康。');
		    }
		}else if (dataType==6) {
			if(y<18.5){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"<b id='msg'>偏低</b>");
		    	 $('#context').html('BMI偏低，说明体型偏瘦，易患营养不良。建议您增加营养的摄入，保证身体的健康状态。');
		    }else if(y>=18.5&&y<=24.9){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"<b id='msg'>正常</b>");
		    	 $('#context').html('您的BMI在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"<b id='msg'>偏高</b>");
		    	 $('#context').html('BMI偏高，说明体型偏胖；BMI愈高，罹患胆结石、第二型糖尿病、高血压、心脏病及高脂血症等疾病的机率也随之增高；建议你合理饮食，调整生活方式，多运动，多吃清谈食物');
		    }
		}else if (dataType==7) {
			if(y>=5.17){
		    	 $('.status').html('偏高').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
		    	 $('#context').html('总胆固醇偏高可能引发人体肝和肺的病变。建议：少吃或不吃动物内脏、蛋黄等胆固醇含量极高的食物，少吃肥肉和荤油，减少饱和脂肪的摄入。多吃蔬菜、水果和菌藻类食物。如果总胆固醇偏高，一定要到医院进行进一步检查，查明总胆固醇偏高的原因，必要时对症治疗。');
		    }else{
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
		    	 $('#context').html('您的总胆固醇含量在正常范围内，望您坚持健康监测，持续保持健康。');
		    }
		}else if (dataType==8) {
			if(y<0.56){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
		    	 $('#context').html('甘油三酯偏低可能会导致甲状腺功能亢进，肾上腺皮质机能减退，肝功能低下，引发脂肪肝等症。建议：如果血脂的其他指标正常，建议加强营养，高蛋白饮食，注意多食些动物脂肪；如果其他各项指标也有不正常，建议去医院进行详细检查，并听取医生意见，以防有疾病隐患。');
		    }else if(y>=0.56&&y<=1.7){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
		    	 $('#context').html('您的甘油三酯含量在正常范围内，望您坚持健康监测，持续保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
		    	 $('#context').html('甘油三酯偏高容易造成“血稠”导致动脉粥样硬化、高血脂。建议：1.多吃粗粮，多吃鱼，肉，瘦肉，豆制品，蔬菜瓜果，少吃精致食品，甜食，奶油，巧克力，肥肉，内脏，鸡皮，鱼子，脑，虾皮等。 2.多吃海带，紫菜，木耳，香菇，大蒜，洋葱，有利于降低血脂，少吃蛋类，少吃油煎炸食品。 3.尽量吃植物油，少食动物油，少吃花生。');
		    }
		}else if (dataType==9) {
			if(y<2.8){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
		    	 $('#context').html('胆固醇酯偏低可能会使细胞膜的稳定性减弱，脆性增加，脑血管易破裂出血。建议： 可以适当增加营养,不要挑食,营养均衡。多吃鸡蛋和肉类。');
		    }else if(y>=2.8&&y<=5.17){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
		    	 $('#context').html('您的胆固醇酯含量在正常范围内，望您坚持健康监测，持续保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
		    	 $('#context').html('胆固醇酯偏高可能引发人体肝和肺的病变。建议：少吃或不吃动物内脏、蛋黄等胆固醇含量极高的食物，少吃肥肉和荤油，减少饱和脂肪的摄入。多吃蔬菜、水果和菌藻类食物。如果总胆固醇偏高，一定要到医院进行进一步检查，查明总胆固醇偏高的原因，必要时对症治疗。');
		    }
		}else if (dataType==10) {
			if(y<0.95){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
		    	 $('#context').html('高密度脂蛋白偏低，可能会导致动脉硬化症、心血管，冠心病等疾病。建议： 要提高高密度脂蛋白胆固醇,降低低密度,要多吃香菇等蘑菇类食物,海藻类食物,豆类,芝麻.要摄入足够的必需脂肪酸.摄入富含EPA,DHA的油脂,可以补充高品质的深海鱼油；平时家里做法要选择高品质的植物油,如橄榄油,亚麻籽油,花生油等.一定避免动物油.饮食要清淡,多吃蔬菜水果,每天可以吃少量的坚果,如大杏仁,核桃等!并且要坚持每天运动。');
		    }else if(y>=0.95&&y<=1.54){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
		    	 $('#context').html('您的高密度脂蛋白含量在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
		    	 $('#context').html('高密度脂蛋白胆固醇升高基本没有害处，通俗的说在标准范围内高密度脂蛋白越高越好，它对人体起好的、保护的作用。');
		    }
		}else if (dataType==11) {
			if(y<1.34){
		    	 $('.status').html('偏低').addClass('abnormal');
		    	 $('#testVal').removeClass('s1').removeClass('s2').addClass('s3').html(y+"mmol/L<b id='msg'>偏低</b>");
		    	 $('#context').html('低密度脂蛋白偏低并没有害处，在标准范围内，低密度脂蛋白越低越好。');
		    }else if(y>=1.34&&y<=3.1){
		    	$('.status').html('正常').removeClass('abnormal');
		    	$('#testVal').removeClass('s3').removeClass('s2').addClass('s1').html(y+"mmol/L<b id='msg'>正常</b>");
		    	 $('#context').html('您的低密度脂蛋白含量在正常范围内，望您坚持健康监测，始终保持健康。');
		    }else{
		    	$('.status').html('偏高').addClass('abnormal');
		    	$('#testVal').removeClass('s1').removeClass('s3').addClass('s2').html(y+"mmol/L<b id='msg'>偏高</b>");
		    	 $('#context').html('低密度脂蛋白偏高，可能会导致过多的胆固醇在血管壁上逐渐沉积，从而会使血管管腔逐渐变狭窄使血流速度不断变慢，发生动脉粥样硬化，可能引起肝病、营养不良、骨髓瘤、急性心肌梗、中风等疾病。    建议：平时饮食要注意少吃含胆固醇高的食物，饮食宜清淡、低盐，适量饮茶，少饮酒或禁酒，多吃粗粮如玉米、高粱、适量精肉、家禽、鱼类、牛奶、鸡蛋蛋白，各种蔬菜、瓜果、适量洋葱、大蒜、香菇、木耳等。纯糖食品要限制。');
		    }
		}
	    
	    
  var yStr = y.toFixed(1).toString();
  yStr = remove0(yStr);
  
  $('#ear').html(yStr);
  $('#date').html(parseDate(date));
}
  
  //消除小数点后的0
  function remove0(str){
	  str = str.toString();
		if(str.indexOf(".")!=-1){
			if(str.substr(str.length-1)=='0' || str.substr(str.length-1)=='.'){
				str = str.substring(0,str.length-1);
				str = remove0(str);
			}
		}
		return str;
  }

  //解析数据
  function parseDate(date){
    var date = new Date(date);
    return date.getFullYear() + '-' + formatNum(date.getMonth()+1) + '-' +
     formatNum(date.getDate()) + ' ' + formatNum(date.getHours()) + ':' +
      formatNum(date.getMinutes());
  }

  function formatNum(num){
    return num>=10?num:'0'+num;
  }

  function parseData(resultData){
    var data = resultData;
    var lineData = [], 
      y, status,height;
    for(var x in data){
      y = data[x][0];
      x = parseInt(x);
      status = data[x][1];
      height = data[x][2];
      //生成线性图的数据
      
      /*if(status==2){
    	  lineData.push($.extend({x: x, y: y,status: status}, abnormal));
     }else if(status==3){
    	 lineData.push($.extend({x: x, y: y,status: status}, abnormal));
     }else if(status==4){
    	 lineData.push($.extend({x: x, y: y,status: status}, abnormal));
     }else if(status==5){
    	 lineData.push($.extend({x: x, y: y,status: status}, abnormal));
     }else{
    	 lineData.push({x: x, y: y,status: status});
     }*/
      
      if (dataType==1) {
	    	if(status<18.5){
	    		lineData.push($.extend({x: x, y: y,status: status,height:height}, abnormal));
	    	}else if(status>=18.5&&status<=24.9){
	    		lineData.push({x: x, y: y,status: status,height:height});
		    }else if(status>24.9&&status<=28.0){
		    	lineData.push($.extend({x: x, y: y,status: status,height:height}, abnormal));
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status,height:height}, abnormal));
		    }
		}else if (dataType==2) {
			if(y<17.1){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=17.1&&y<=28){
				lineData.push({x: x, y: y,status: status});
		    }else if(y>28&&y<=35){
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==3) {
			if(y<49.5){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=49.5&&y<=57){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==4) {
			if(y<25){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=25&&y<=34){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==5) {
			if(y<1.7){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else{
				lineData.push({x: x, y: y,status: status});
		    }
		}else if (dataType==6) {
			if(y<18.5){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=18.5&&y<=24.9){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==7) {
			if(y>=5.17){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else{
				lineData.push({x: x, y: y,status: status});
		    }
		}else if (dataType==8) {
			if(y<0.56){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=0.56&&y<=1.7){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==9) {
			if(y<2.8){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=2.8&&y<=5.17){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==10) {
			if(y<0.95){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=0.95&&y<=1.54){
				lineData.push({x: x, y: y,status: status});
		    }else{
		    	lineData.push($.extend({x: x, y: y,status: status}, abnormal));
		    }
		}else if (dataType==11) {
			if(y<1.34){
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}else if(y>=1.34&&y<=3.1){
				lineData.push({x: x, y: y,status: status});
			}else{
				lineData.push($.extend({x: x, y: y,status: status}, abnormal));
			}
		}else if (dataType==12) {
			lineData.push({x: x, y: y,status: status});
		}
    }
    return {
      lineData: lineData
    };
  }
  
  function getTickPositions(type){
	  var positions = [];
	  if(type==1){
		  positions.push(time + 4 * 3600 * 1000);
		  positions.push(time + 8 * 3600 * 1000 );
		  positions.push(time + 12 * 3600 * 1000);
		  positions.push(time + 16 * 3600 * 1000);
		  positions.push(time + 20 * 3600 * 1000);
		  positions.push(time + 24 * 3600 * 1000);
	  }else if(type==7){
		  positions.push(time - 24 * 3600 * 1000 * 6);
	      positions.push(time - 24 * 3600 * 1000 * 5);
	      positions.push(time - 24 * 3600 * 1000 * 4);
	      positions.push(time - 24 * 3600 * 1000 * 3);
	      positions.push(time - 24 * 3600 * 1000 * 2);
	      positions.push(time - 24 * 3600 * 1000);
	      positions.push(time);
	  }else{
		  positions.push(time - 24 * 3600 * 1000 * 30);
	      positions.push(time - 24 * 3600 * 1000 * 25);
	      positions.push(time - 24 * 3600 * 1000 * 20);
	      positions.push(time - 24 * 3600 * 1000 * 15);
	      positions.push(time - 24 * 3600 * 1000 * 10);
	      positions.push(time - 24 * 3600 * 1000 * 5);
	      positions.push(time);
	  }
	  return positions;
  }

  function getPositions(type){
	var po;
	if(type==1){
		po = function(){
			var positions = [];
			positions.push(time);
	        positions.push(time + 4 * 3600 * 1000);
	        positions.push(time + 8 * 3600 * 1000 );
	        positions.push(time + 12 * 3600 * 1000);
	        positions.push(time + 16 * 3600 * 1000);
	        positions.push(time + 20 * 3600 * 1000);
	        positions.push(time + 24 * 3600 * 1000);
	        return positions;
		};
	  }else if(type==7){
		  po = function(){
			  var positions = [];
			  positions.push(time - 24 * 3600 * 1000 * 6);
		      positions.push(time - 24 * 3600 * 1000 * 5);
		      positions.push(time - 24 * 3600 * 1000 * 4);
		      positions.push(time - 24 * 3600 * 1000 * 3);
		      positions.push(time - 24 * 3600 * 1000 * 2);
		      positions.push(time - 24 * 3600 * 1000);
		      positions.push(time);
		      return positions;
		  };
	  }else if(type==30){
		  po = function (){
			  var positions = [];
			  positions.push(time - 24 * 3600 * 1000 * 30);
		      positions.push(time - 24 * 3600 * 1000 * 25);
		      positions.push(time - 24 * 3600 * 1000 * 20);
		      positions.push(time - 24 * 3600 * 1000 * 15);
		      positions.push(time - 24 * 3600 * 1000 * 10);
		      positions.push(time - 24 * 3600 * 1000 * 5);
		      positions.push(time);
		      return positions;
		  };
	  }
	 return po;
  }
  
  //时间戳转日期
  function getLocalTime(nS) {
	  return parseDate(parseInt(nS));
   }
  
  //更改日期，切换坐标系
  function changeX(timeType){
	  var chart = $('#chart').highcharts();
	  
	  var date = new Date(parseInt(shareTime));
	  date.setHours(0);
	  date.setMinutes(0);
	  time = date.setSeconds(0);
	  
	  if(timeType==1){
		    //if($("#day").hasClass('active')){return;}
		    $("#day").addClass('active').siblings().removeClass('active');
		    chart.xAxis[0].update(
		      {
		    	  //tickPositioner: getPositions(1), 
		    	  tickPositions : getTickPositions(1),
		    	  labels: {
		        formatter: function(){
		          var date = new Date(this.value);
		          if(date.getHours() == 0){
		            return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
		          }else{
		            return  formatNum(date.getHours()) + ':00';
		          }
		        }
		      }
		      });
		    chart.xAxis[0].setExtremes(time,time + 24 *3600 * 1000,true, {duration: 1000});
		    
		    
	  }else if(timeType==2){
		  time = time + 1000*3600*24;
		  
		  $("#seven").addClass('active').siblings().removeClass('active');
		    chart.xAxis[0].update({
		    	//tickPositioner: getPositions(7),
		    	tickPositions : getTickPositions(7),
		    	labels: {
		        formatter: function(){
		          var date = new Date(this.value);
		          return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
		        }}
		    });
		    chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 6, time, true, {duration: 1000});
		    
	  }else{
		  time = time + 1000*3600*24;
		  $("#thirty").addClass('active').siblings().removeClass('active');
		    chart.xAxis[0].update({
		    	//tickPositioner: getPositions(30),
		    	tickPositions : getTickPositions(30),
		    	labels: {
		        formatter: function(){
		          var date = new Date(this.value);
		          return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
		        }}
		    });
		    chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 30, time,true, {duration: 1000});
	  }
	  
  }
})($)