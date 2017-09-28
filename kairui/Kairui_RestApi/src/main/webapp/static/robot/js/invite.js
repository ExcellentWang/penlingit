(function () {
    /*此处的totalNum是模拟数据，merge代码时应从服务器取得*/
    var page = 1;
    var container = $('.invite_table table tbody');
    
    appendHtml();
    $(window).on('scroll', loadMore);

    //加载更多
    function loadMore() {
		if (page > 0 && (getViewportSize().h + $(window).scrollTop()) >= $(document).height()) {
            appendHtml();
        }
    }

    function appendHtml() {
    	container.append(createHtml());
        getLeftNum();
    }

    function createHtml() {
        var fansT = $('#inviteTpl').html(), html = "";
        
        $.ajax({
            url: 'getSharings?page='+(page++),
            /*merge代码时请注意修改此处的url*/
            dataType: 'json',
            async: false,
            success: function (data) {
                $.each(data.list, function (i, v) {      
                    var fansHtml = fansT.replace(/\{(.+?)\}/g,function(a,b){
                    	if(b == "no"){
                    		return i+1;
                    	}else if(b == "taskTime"){
                    		if(v['taskTime']){
                    			return "green";
                    		}
                    	}else if(b == "taskTimeDesc"){
                    		if(v['taskTime']){
                    			return "邀请成功";
                    		}else{
                    			return "邀请中";
                    		}
                    	}else{
                    		return v[b];
                    	}
                    })
                    html= html+fansHtml;
                    
                });
                if(data.total < 20){
                	page = 0;
                    $('.look_more').html('暂无数据');
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
    function getLeftNum() {
        if (page == 0) {
            $('.loading_box').html('全部加载完了');
        }
    }

    //查询窗口的视口尺寸
    function getViewportSize(w){
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