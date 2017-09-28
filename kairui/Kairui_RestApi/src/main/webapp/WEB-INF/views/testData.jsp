<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="format-detection" content="telephone=no">
  <meta name="format-detection" content="email=no" />
  <title>监测仪实时数据</title>
</head>
<body style="background:#fff;">
<div class="business_box">
  <div class="data_num">
    <span><em>创造的总客流量</em><b>${totalCount }</b><i>${dateTime }</i></span>
    <span><em>今日测量人数</em><b>${dayTotalCount }</b></span>
    <span><em>入驻商家总数</em><b>${businessCount }</b></span>
  </div>
  <h4 class="bus_tit">总客流量排行榜</h4>
  <ul id="dataUl" class="bus_list spe noborder" id="bus_ul">
  </ul>
  <a href="javascript:;" class="bus_all" id="btn">立即入驻</a>
</div>
<div class="window">
  <div class="window_cont" id="errDiv">
    <p id="errMsg">轻轻松松让您的门店客流量和收益翻倍，热线电话：</p>
    <a href="tel:0755-23021470">0755-23021470</a>
  </div>
</div>

<script type="text/javascript">
$(function(){
	$.ajax({
		url:'getRank?loadNum=0&perLoadNum=10',
		dataType:'json',
		async: true,
		success:function(data){
			var htm='';
			var list = data.list;
			for ( var key in list) {
				var obj = list[key];
				htm+='<li><h3><i>TOP</i></h3><h4>'+obj.name+'</h4><b class="b1">'+obj.totalCount+'</b></li>';
				/* htm+='<li><h3><i>TOP</i></h3><h4>'+obj.name+'</h4><b class="b1">今日测量：'+obj.dayCount+'</b><b class="b2">总测量：'+obj.totalCount+'</b></li>'; */
			}
			$("#dataUl").append(htm);
		},
		error:function(req,err,ecp){
		}
	});
	
	// 弹出窗
    $("#btn").click(function(){
    	$(".window_cont").addClass("zoomIn");
    	$(".window").show();   
    })
    $(".window").click(function(){
      $(".window_cont").removeClass("zoomIn");
      setTimeout(function(){
          $(".window").hide();
      }, 200 )
    })
  })
</script>

</body>
</html>