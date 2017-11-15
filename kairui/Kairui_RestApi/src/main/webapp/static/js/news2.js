(function () {
	
    /*此处的totalNum是模拟数据，merge代码时应从服务器取得*/
    var laodNum = 0,
    	totalNum = 0,
        perLoadNum = 4;
	var tab=0;
	var laodNum1 = 0,
		totalNum1 = 0,
		perLoadNum1 = 4;
	
	$(".news_group_box .news_group").hide().eq(0).show();
	  /* $(".news_nav span:first").addClass("on");  */
	  $('.news_nav span').each(function(i,n){
	    $(n).click(function(){
	      $(this).addClass("on").siblings().removeClass("on");
	      $(".news_group_box .news_group").eq(i).show().siblings().hide();
	      if ($("#noon").hasClass("on")) {
	    	  tab=0
	    	  if (totalNum>laodNum) {
	    		  $(window).on('scroll', loadMore);
	    		  var lf;
	    		  if (totalNum-laodNum>perLoadNum) {
	    			  lf=perLoadNum;
				}else {
					lf=totalNum-laodNum;
				}
	    		  $('.load-more').html('<div class="load-more">加载中...</div>');
			}
	      }else if ($("#night").hasClass("on")) {
	    	  tab=1
	    	  if (totalNum1==0) {
	    		  appendHtml();
	    	  }
	    	  if (totalNum1>0) {
	    		  $(window).on('scroll', loadMore);
	    		  $('.load-more').html('<div class="load-more">加载中...</div>');
			}
	      }
	    })
	  });
	
    _.templateSettings = {
        interpolate: /\{\{(.+?)\}\}/g
    };
    loadHead();
    appendHtml();
    $(window).on('scroll', loadMore);

    //加载更多
    function loadMore() {
    	if (tab==0) {
    		if (totalNum == 0) {
                $(window).off('scroll');
                return;
            }
		}else if (tab==1) {
			if (totalNum1 == 0) {
                $(window).off('scroll');
                return;
            }
		}
    	
        if ((getViewportSize().h + $(window).scrollTop()) >= $(document).height()) {
            appendHtml();
        }
    }

    function appendHtml() {
    	if (tab==0) {
    		$('#bus_ul').append(createHtml(2));
            totalNum -= laodNum;
            getLeftNum();
		}else if (tab==1) {
			$('#bus_ul1').append(createHtml(3));
			/*alert(totalNum1+":"+laodNum1);*/
            totalNum1 -= laodNum1;
            getLeftNum();
		}
    }

    function createHtml(type) {
        var fansT = $('#busT').html(),
            $div = $('<div></div>');
        var url;
        if (tab==0) {
   		 url='getNewsList?loadNum='+laodNum+'&perLoadNum='+perLoadNum+'&type='+type;
		}else if (tab==1) {
			 url='getNewsList?loadNum='+laodNum1+'&perLoadNum='+perLoadNum1+'&type='+type;
		}
        $.ajax({
        	url:url,
            /*merge代码时请注意修改此处的url*/
            dataType: 'json',
            async: false,
            success: function (data) {
            	if (tab==0) {
            		/*alert("total="+data.total+"totalN"+totalNum+"load="+laodNum);*/
            		totalNum=data.total;
                	laodNum+=perLoadNum;
				}else if (tab==1) {
//					alert("total="+data.total+"totalN"+totalNum1+"load="+laodNum1);
					totalNum1=data.total;
	            	laodNum1+=perLoadNum1;
				}
            	
                $.each(data.list, function (i, v) {
                    var fansHtml = _.template(fansT)(v),
                        fansObj = $(fansHtml);
                    $div.append(fansObj);
                });
                if (tab==0) {
                	if(totalNum == 0){
                        $('.load-more').html('暂无数据');
                    }
				}else if (tab==1) {
					if(totalNum1 == 0){
	                    $('.load-more').html('暂无数据');
	                }
				}
            },
            error: function (xhr, errorType, error) {
                /*请求失败*/
                console.log(error);
            }
        });
        return $div.html();
    }
    
    /*更新剩下要加载的条数*/
    function getLeftNum() {
    	if (tab==0) {
    		if (totalNum <= 0) {
                $('.load-more').html('全部加载完了');
                totalNum = 0;
            }
		}else if (tab==1) {
//			alert(totalNum1);
			if (totalNum1 <= 0) {
                $('.load-more').html('全部加载完了');
                totalNum1 = 0;
            }
		}
    	
    }

    //查询窗口的视口尺寸
    function getViewportSize(w) {
        w = w || window;
        if (w.innerWidth) { //除IE8及更早的版本，其他browser都支持
            return {
                w: w.innerWidth,
                h: w.innerHeight
            };
        }
        var d = w.document;
        if (d.compatMode = 'CSS1Compat') { //标准模式下的browser
            return {
                w: d.documentElement.clientWidth,
                h: d.documentElement.clientHeight
            };
        }
        return {
            w: d.body.clientWidth,
            h: d.body.clientHeight
        }; //怪异模式下的browser
    }
    
    function loadHead() {
        $.ajax({
            url: 'getNewsHead?loadNum='+0+'&perLoadNum='+5,
            /*merge代码时请注意修改此处的url*/
            dataType: 'json',
            async: false,
            success: function (data) {
            	$.each(data, function (i, v) {
                    $("#newsHead").append('<div class="swiper-slide"><a href="authToNews?id='+v.id+'"><img alt="" src="'+v.image+'" width="100%"><p>'+v.title+'</p></a></div>');
                });
            },
            error: function (xhr, errorType, error) {
                /*请求失败*/
                console.log(error);
            }
        });
    }
})()