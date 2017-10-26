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
				console.log(res.code)
				if (res.code === 0) {
					location.href = "main.html";
				} else if (res.code === 2) {
					$('#errInfo').html(res['message']);
					$("#loginError").modal("show");
				}
			}
		});
	});

	$("#switch").click(function() { //登录二维码切换
		$(this).toggleClass('code');
		var code = $(this).attr('class');
		if (code == 'code') {
			$(".login_box").addClass('hide').next(".codeBox").removeClass('hide');
		} else {
			$(".login_box").removeClass('hide').next(".codeBox").addClass('hide');
		}
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
