!(function($) {
	$.fn.TCdata = function(options){
		var setting = $.extend({
			today: Date.parse(new Date()) / 1000,
			howday: 1,
			howlong: 7
		},options)

		return this.each(function(){
			var swiperNum = Math.ceil(setting.howlong / 7); //swiper的个数
			var dataArr = [];
			for(var i = 0; i < setting.howlong; i++){
				var dataobj = new Object();
				dataobj.data = (setting.today + 86400 * (i - setting.howday + 1)) * 1000;
				dataobj.timestr = new Date(dataobj.data).toLocaleString();
				dataobj.year = new Date(dataobj.data).getFullYear();
				dataobj.month = new Date(dataobj.data).getMonth() + 1;
				dataobj.day = new Date(dataobj.data).getDate();
				dataArr.push(dataobj);
			}
			var monthArr = [
				'一月',
				'二月',
				'三月',
				'四月',
				'五月',
				'六月',
				'七月',
				'八月',
				'九月',
				'十月',
				'十一月',
				'十二月',
			]
			var str = '';
			for (var i = 0; i < swiperNum; i++) {
				var swiperstr = '';
				for (var j = 0 , n = setting.howlong - 7 * (i + 1) > 0 ? 7 : setting.howlong - 7 * i; j < n; j++) {
					var index = i * 7 + j;
					var thisdayname = dataArr[index];
					if (thisdayname.day == 1) { //显示月份
						swiperstr += '<div class="tcday month"><span>'
							   + thisdayname.day 
							   +'</span><span class="small">'+ monthArr[thisdayname.month - 1] +'</span></div>';
					}else{
						swiperstr += '<div class="tcday"><span>'
								   + thisdayname.day 
								   +'</span></div>';
					}
					
				}
				str += '<div class="swiper-slide" id=swiper_why'+ i +'>'
					 + 	swiperstr 
					 + '</div>';
			}
			$(str).appendTo($(this));
			if ($('.tcday').eq(setting.howday - 1).hasClass('month')) {
				$('.tcday').eq(setting.howday - 1).removeClass('month');
			}
			$('.tcday').eq(setting.howday - 1).addClass('on').addClass('thisday').html('<span>今天</span>');
			$('.tcday').on('click',function(){
				$('.tcday').removeClass('on');
				$(this).addClass('on')
			})
			
		})

	}


}(jQuery));