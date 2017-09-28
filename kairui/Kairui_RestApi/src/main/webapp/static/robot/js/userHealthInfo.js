(function(){
	function getStatus(val,i){
		var KBIData = ["偏高","偏低","正常"];
		var type = 2;
		if(i==1){
			if(val<18.5){
				type=1;
			}else if(val>24.9){
				type=0;
			}
		}else if(i==3){
			if(val<49.5){
				type=1;
			}else if(val>57){
				type=0;
			}
		}else if(i==4){
			if(val<25){
				type=1;
			}else if(val>34){
				type=0;
			}
		}else if(i==5){
			if(val<1.7){
				type=1;
			}
		}else if(i==6){
			if(val>5.71){
				type=0;
			}
		}else if(i==8){
			if(val<0.56){
				type=1;
			}else if(val>1.7){
				type=0;
			}
		}else if(i==9){
			if(val<2.8){
				type=1;
			}else if(val>5.17){
				type=0;
			}
		}else if(i==10){
			if(val<0.95){
				type=1;
			}else if(val>1.54){
				type=0;
			}
		}else if(i==11){
			if(val<1.34){
				type=1;
			}else if(val>3.1){
				type=0;
			}
		}
		return KBIData[type];
	}
	
	
	var userHealthInfo = function(data){
		var newInfo =  data.newHealthInfo;
		var weightKbi = newInfo.weightKbi.match(/^[\D]+/);
		weightKbi = weightKbi?weightKbi[0]:"正常";
		var shopName = data.machine?data.machine.name:"暂未统计该店铺";
		var shopCity = data.machine?data.machine.city:"店铺城市未录入";
			shopName = shopName?shopName:"暂未统计该店铺";
			shopCity = shopCity?shopCity:"店铺城市未录入";
		//添加诸葛统计总测量数 -----开始
            zhugeGroup = $.extend(zhugeGroup, {
                "总测量数":data.total,
                "店铺":shopName,
                "店铺城市":shopCity,
                "身高":newInfo.height,
        		"体重":newInfo.weight,
        		"体重指标":weightKbi,
        		"脂肪":newInfo.bodyFat,
        		"脂肪指标":newInfo.bodyFatKbi,
        		"BMI":newInfo.bmi,
        		"代谢":newInfo.calorie,
        		"水分率":newInfo.water,
        		"肌肉率":newInfo.muscle,
        		"骨含量":newInfo.bone,
        		"总胆固醇":newInfo.tchol,
        		"甘油三酯":newInfo.trig,
        		"胆固醇酯":newInfo.chol,
        		"高密度蛋白":newInfo.hdl,
        		"低密度蛋白":newInfo.ldl,
        		"BMI指标":getStatus(newInfo.bmi),
        		"水分率指标":getStatus(newInfo.water),
        		"肌肉率指标":getStatus(newInfo.muscle),
        		"骨含量指标":getStatus(newInfo.bone),
        		"总胆固醇指标":getStatus(newInfo.tchol),
        		"血脂指标":newInfo.bloodKBI,
        		"甘油三酯指标":getStatus(newInfo.trig),
        		"胆固醇酯指标":getStatus(newInfo.chol),
        		"高密度蛋白指标":getStatus(newInfo.hdl),
        		"低密度蛋白指标":getStatus(newInfo.ldl),
                "最近一次测量时间":newInfo.updateTime
            });
    	//添加诸葛统计总测量数 -----结束
	};
	$.ajax({
	    url: 'getNewHealthInfo?newInfoId='+newInfoId,
	    /*merge代码时请注意修改此处的url*/
	    dataType: 'json',
	    async: false,
	    success: function (data) {
	    	if(data.total>0 && data.newHealthInfo.openId == openId){
	    		localStorage.setItem("newInfoId",data.newHealthInfo.id);
	    		userHealthInfo(data);
	    	}
	    },
	    error: function (xhr, errorType, error) {
	        /*请求失败*/
	        console.log(error);
	    }
	});
})();