(function () {
    /*此处的totalNum是模拟数据，merge代码时应从服务器取得*/
    var laodNum = 0,
    	totalNum = 0,
        perLoadNum = 16;
    var id;
    
    $(window).on('scroll', loadMore);

    //加载更多
    function loadMore() {
        if (totalNum == 0) {
            $(window).off('scroll');
            return;
        }
       if ((getViewportSize().h + $(window).scrollTop()) >= $(document).height()) {
           appendHtml();
       }
    }

    if($('#activityId').val()!=''){
    	appendHtml();
    }
    
    function appendHtml() {
        $('#users').append(createHtml());
        totalNum -= laodNum;
        getLeftNum();
    }

    function createHtml() {
    	var html ='';
        $.ajax({
            url: 'getUsers?loadNum='+laodNum+'&perLoadNum='+perLoadNum+'&activityId='+$('#activityId').val(),
            /*merge代码时请注意修改此处的url*/
            dataType: 'json',
            async: false,
            success: function (data) {
            	totalNum=data.total;
            	laodNum+=perLoadNum;
            	$("#actinfo").html('本周期'+totalNum+'人参加，完成任务的童鞋分<b>'+totalNum*10+'元</b>奖金');
            	var list = data.list;
            	if (list.length>0) {
            		for ( var key in list) {
                		var obj = list[key];
                		html=html+'<li><img src="'+obj.logo+'" alt=""><p>'+obj.name+'</p></li>';
                    }
				}
            },
            error: function (xhr, errorType, error) {
                /*请求失败*/
                console.log(error);
            }
        });
        return html;
    }
    
    /*更新剩下要加载的条数*/
    function getLeftNum(){
        if (totalNum <= 0) {
            /* $('.load-more').html('全部加载完了');*/
             totalNum = 0;
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
    
})()