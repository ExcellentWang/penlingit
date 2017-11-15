document.write('<div class="tc" id="modal_weixin" style="display: none; ">\
                <div class="tc-bg"></div>\
                <div class="tc-con">\
                    <p>请选择右上角"在浏览器中打开"</p>\
                </div>\
            </div>');
function is_weixin() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == "micromessenger" ||
        ua == 'qq') {
        return true;
    } else {
        return false;
    }
}
var weixinc = document.getElementById('modal_weixin');

//if(/ipad|iphone|mac/i.test(navigator.userAgent)) ios.style.display='';
if (is_weixin()) {
    weixinc.style.display = '';
    android.onclick = function () {
        weixinc.style.display = '';
    }
}
weixinc.onclick = function () {
    weixinc.style.display = 'none';
}