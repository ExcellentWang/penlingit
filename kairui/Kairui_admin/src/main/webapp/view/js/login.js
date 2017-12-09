$(function() {
	if ($.cookie('userName') == 'null') {
		var name = '';
	} else {
		var name = $.cookie('userName');
	}
	$("input[name=userName]").val(name);
	var addCookie, delCookie;

	function addCookie(a) {
		$.cookie('userName', a, {
			expires: 1
		});
	};

	function delCookie(b) {
		$.cookie('userName', null);
	};
	$("body").keydown(function(event) {//对页面添加回车登录事件
		  if (event.keyCode == "13") {//keyCode=13是回车键
			  $("#loginBtn").click();
		  }
		});  
	$("#loginBtn").click(function() {
		$.ajax({
			url: interUrl.basic + "user/login",
			type: "POST",
			data: $("#loginForm").values(),
			success: function(res) {
				var code = $('.check i').attr('index');
				var userName = $("input[name=userName]").val();
				if (code == 1) {
					delCookie(userName);
				} else if (code == 0) {
					addCookie(userName);
				};
				if (res.code === 0) {
					location.href = "main.html";
				} else if (res.code === 2) {
					$('#errInfo').html(res['message']);
					$("#loginError").modal("show");
				}
			}
		});
	});
	$(".check i").click(function() {
		var code = $(this).attr('class');
		if (code == null || code == '') {
			$(this).addClass('cancel').attr('index', '1');
		} else if (code == 'cancel') {
			$(this).removeClass('cancel').attr('index', '0');
		}
	});
});
