(function () {
    /*此处的totalNum是模拟数据，merge代码时应从服务器取得*/
    var laodNum = 0,
    	totalNum = 0,
        perLoadNum = 10;

    _.templateSettings = {
        interpolate: /\{\{(.+?)\}\}/g
    };
    appendHtml();
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

    function appendHtml() {
        $('#bus_ul').append(createHtml());
        totalNum -= laodNum;
        getLeftNum();
    }

    function createHtml() {
        var fansT = $('#busT').html(),
            $div = $('<div></div>');

        $.ajax({
            url: 'getRank?loadNum='+laodNum+'&perLoadNum='+perLoadNum,
            /*merge代码时请注意修改此处的url*/
            dataType: 'json',
            async: false,
            success: function (data) {
            	totalNum=data.total;
            	laodNum+=perLoadNum;
                $.each(data.list, function (i, v) {
                    var fansHtml = _.template(fansT)(v),
                        fansObj = $(fansHtml);
                    $div.append(fansObj);
                    
                });
                if(totalNum == 0){
                    $('.more').html('暂无数据');
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
        if (totalNum <= 0) {
            $('.more').html('全部加载完了');
            totalNum = 0;
        }else {
            $('#leftNum').html(totalNum);
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