$(function (){

  //图片预览
  var i = 1;
  function imgPreview(file, $input){
    try{
      if(!window.FileReader){
        $input.get(0).select();
        var reallocalpath = document.selection.createRange().text;
        var img = getShowImg();
        $(img).css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")")
        $(img).attr("src",'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==');
       }else{
        var reader = new FileReader();
        reader.readAsDataURL(file[0]);
         reader.onload = function(e){
        	 var img = getShowImg();
        	 $(img).attr("src",this.result);
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

  function getShowImg(){
	  var $img = $('#output').find("img").eq(0);
	  if($img.length == 0){
		  $img = $('<img>'), $div = $('<div>'), $i = $('<i>');
	      $img.width(90).height(90);
	      $div.attr('id', 'file' + i++).addClass('fileItem');
	      $div.append($img).append($i);
	      $('#output').append($div);
	  }
	  return $img;
  }

  $('#addpic').on('change', function(){
      var $input = $(this).find('input');
      var files = $input.get(0).files?$input.get(0).files:$input;
      imgPreview(files, $input);
      
  });

  //删除图片
  $('#output').on('click', 'i', function(){
    $(this).parent().remove();
    if($("#output").find(".fileItem").length<=5){
        $("#addpic").show();
      }
  });
    
});