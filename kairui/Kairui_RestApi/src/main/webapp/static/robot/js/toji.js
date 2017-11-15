/*诸葛统计事件统计开始*/
$(function(){
	//进入页面
	/*share_title = $("title").text();*/
	zhuge.track("进入-"+share_title+"页面");
	//点击跳转到外站

	$('.zhugeButton').each(function(){
	    $(this).bind('click',function(){
	        zhuge.track("将跳转到-"+$(this).attr("title")+"-页面");
	    });
	});

	/*诸葛统计事件统计结束*/
	/*诸葛统计用户注册开始*/
	if(zhugeGroup){
		zhuge.identify(openId, zhugeGroup);
	}else{
		zhuge.identify(openId);
	}
	/*诸葛统计用户注册结束*/
});


/*数据说--统计微信分享开始*/
 wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: appId, // 必填，公众号的唯一标识
	    timestamp: timestamp, // 必填，生成签名的时间戳
	    nonceStr: nonceStr, // 必填，生成签名的随机串
	    signature: signature,// 必填，签名，见附录1
	    jsApiList: ['checkJsApi',
	                'onMenuShareTimeline',
	                'onMenuShareAppMessage',
	                'onMenuShareQQ',
	                'onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

 
wx.ready(function () {
	        wx.onMenuShareAppMessage({
	            title: share_title,
	            desc: share_desc,
	            link: share_link,
	            imgUrl: share_image,
	            type: '',
	            dataUrl: '',
	            success: function () {
	            },
	            cancel: function () { 
			    }
	        });
	        wx.onMenuShareTimeline({
	            title: share_title,
	            link: share_link,
	            imgUrl: share_image,
	            success: function () {
	            },
	            cancel: function () { 
			    }
	        });
	        wx.onMenuShareQQ({
	            title: share_title,
	            desc: share_desc,
	            link: share_link,
	            imgUrl: share_image,
	            success: function () {
	            },
		        cancel: function () { 
			    }
	        });
	        wx.onMenuShareWeibo({
	            title: share_title,
	            desc: share_desc,
	            link: share_link,
	            imgUrl: share_image,
	            success: function () {
	            },
	            cancel: function () { 
			    }
	        });
});

wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

});
