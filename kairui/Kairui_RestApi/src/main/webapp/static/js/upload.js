$(function (){

  //图片预览
  var i = 1;
  function imgPreview(file, $input){
    try{
      if(!window.FileReader){
        $input.get(0).select();
        var reallocalpath = document.selection.createRange().text;
        var obj = createEle();
        obj.img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
        obj.img[0].src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
        appEle(obj, $input);
       }else{
        var reader = new FileReader();
        reader.readAsDataURL(file[0]);
         reader.onload = function(e){
          var obj = createEle();
          obj.img[0].src = this.result;
          appEle(obj, $input);
         }
       }
    }catch(ex){
      var fileSrc = $input.val(),
        fileName = fileSrc.substring(fileSrc.lastIndexOf('\\')+1);
        if($('#errMsg').html() == ''){$('#errMsg').html('当前浏览器不支持图片预览');}
      $('<div>').addClass('filePItem').append($input)
      .append($('<span>').html(fileName)).append($('<i>'))
      .appendTo('#output');
    }
  }

  function createEle(){
    var $img = $('<img>'), $div = $('<div>');
      $img.width(94).height(64);
      $div.attr('id', 'file').addClass('fileItem');
    return {wrap: $div, img: $img};
  }

  function appEle(obj, $input){
    var $div = obj.wrap, $img = obj.img, $i = $('<i>');
    $div.append($img).append($input).append($i);
    $('#output').append($div);
   if($("#output").find(".fileItem").length>=1){
        $("#addpic").hide();
      }
  }

  $('#addpic').on('change', function(){
      var $input = $(this).find('input');
      var files = $input.get(0).files?$input.get(0).files:$input;
      imgPreview(files, $input);
      $(this).append($('<input type="file" name="file" accept="image/*">'));
      
  });

  //删除图片
  $('#output').on('click', 'i', function(){
    $(this).parent().remove();
    if($("#output").find(".fileItem").length<=1){
        $("#addpic").show();
      }
  });

  var ts = true;
    $(".upload_btn").click(function () {
        if (ts) {
            $(".succ").show().addClass("animated");
            $(this).css('background','#ccc');
            ts = false;
        };
        setTimeout(function () {
            ts = true;
            $(".succ").hide().removeClass("animated");
        }, 2000);
    })
    
});