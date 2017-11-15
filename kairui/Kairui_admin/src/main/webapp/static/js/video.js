(function($){
	//显示播放视频
	$.fn.showVideo = function(options){
			var jsPath = ctx+"static/js";
			var flashvars = {
			autoStart        : 'true',
			skin             : jsPath+'/player/flash/skins/vodWhite.swf',
			buffer           : 1,
			title            : '香港卫视',
			type             : "m3u8",
			logo             : jsPath+'/player/images/logo.png',
			lang             : 'zh_CN',
			clarityButton  : 'disable',
			playerName  :'Sewise Player',
			copyright  :'All right reserved the SEWISE inc 2011-2013',
			published        : 0,
			draggable  :"true",
			videoUrl         : "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8",
			startTime        : 0
			};
			flashvars = $.extend(flashvars, options);
			var params = {
			allowfullscreen   : true,
			wmode             : "transparent",
			allowscriptaccess : 'always'
			};
			var attributes = {
			id : 'sewise_x_player',
			name : 'sewise_x_player'
			};
			//显示视频
			return this.each(function() {
				$(this).css("display","block").html("").prepend('<div id="swf-container"></div><div class="menu11" title="关闭视频" onclick="instructions(\'code\',7);"></div>');
				swfobject.embedSWF(jsPath+"/player/flash/SewisePlayer.swf", "swf-container", "100%", "100%", "9.0.115", false, flashvars, params, attributes);
				$(this).drag();
			
			});
		}
})(jQuery);