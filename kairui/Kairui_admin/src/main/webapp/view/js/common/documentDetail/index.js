var args, base64,cureent_dir, freeObj, getDocumentList, getSelectImage, loadTree,treeObj, targetDir, treeTarget, zTreeOnClick, allImg, imgIds = {};
var dataid;
jQuery.browser = {};
var m = {}, args = args || comn.getArgs();
m.loanApplyId = args['id'] || args['loanApplyId'];
var zTreeObj;
var current;
(function() {
  jQuery.browser.msie = false;
  jQuery.browser.version = 0;
  if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
    jQuery.browser.msie = true;
    return jQuery.browser.version = RegExp.$1;
  }
freeObj = null;

treeTarget = null;

cureent_dir = null;

getDocumentList = null;

targetDir = null;

imgType = null;

var pictures = document.querySelector('#documentList');
var picturesbank= document.querySelector('#bankdocumentList');
var viewer;
var options = {
	url: 'data-original',
	title: true,
	transition: false,
	build: function (e) {},
	built: function(e){},
	show:  function (e) {
		imgIds = {};
		window.parent.toggleTopNav();
	},
	view:  function (e) {
		var _index = e.detail.index, item = $(viewer.images[_index]).parents(".file").data("file");
		
		dataid = $(viewer.images[_index]).parents(".file").data("id");
		if(item.hasRead == 1){ 
			if(!imgIds[item.dirId]){
				imgIds[item.dirId] = [];
			}
			if(imgIds[item.dirId].indexOf(item.id) == -1){
				imgIds[item.dirId].push(item.id); 
			}
		}
	},
	viewed: function(e){},
	hide: function(e){ 
		window.parent.toggleTopNav();
	},
	hidden: function(e){
		comn.ajax({
				url: interUrl.gr.readPhoto,
				type: "get",
				data: {
					fileNamespace: args['space'] || "",
					releventFlow: args['releventFlow'] || "",
					releventFlowNode: args['releventFlowNode'] || "",
					docIds: dataid
				},
				success: function(res){
					if(res.data==0){
						var zTree = $.fn.zTree.getZTreeObj("tree");
						var nodes = zTree.getSelectedNodes();
						zTree.setting.view.fontCss["color"] = "";
					  	zTree.updateNode(nodes[0]);
					}
				}
			});
		for(item in imgIds){
			$.each(imgIds[item], function(index, item){
				  $($("#documentList")).find(".file[data-id='"+ item +"']").find(".glyphicon").css("color", "#1ab394"); 
			});
			comn.ajax({
				url: interUrl.gr.recordDocQueryHistory,
				type: "post",
				data: {
					loanApplyId: m.loanApplyId,
					dirId: item,
					fileNamespace: args['space'] || "",
					releventFlow: args['releventFlow'] || "",
					releventFlowNode: args['releventFlowNode'] || "",
					docIds: imgIds[item].join(",")
				},
				success: function(res){	}
			});
		}
	}
};
imgListHtmlde = function(o, list){
	results = [];
	for (j = 0, len = list.length; j < len; j++) {
	  item = list[j];
	  console.log(item);
	  results.push([
			  item.fileType== "1" ? "<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>" : "<div class='col-xs-8 col-sm-8 col-md-8 col-lg-8'>",
			  "<div class='file' data-file='" + (JSON.stringify(item)) + "' data-id='"+ item.id +"'>",
				  "<div class='image text-center' data-id='" + item.id + "' style='position: relative; height: auto;'>",
					  item.fileType == "1" ?
					  "<img data-original='"+ item.filePath +"' alt='"+ item.fileName +"' src='" + item.filePath + "@100h' height='100' />" : ( item.fileType == "2" ? [
						  "<video id='video"+j+"' class='video-js vjs-default-skin' controls preload='none' data-setup='{}'>",
						  "<source src='" + item.filePath + "' type='video/mp4' />",
						  "</video>"
					  ].join("") : ""),
					  imgType == "deleted" ?
						  ["<div style='position: absolute; top:0; left:0; right: 0; bottom: 0; background-color: rgba(0,0,0,0.3); color: red;'> ",
							  "<h5 class='text-center'>【" + item.modifyRealname + "】</h5>",
							  "<h5 class='text-center'>" + item.modifyTime + "</h5>",
							  "<h5 class='text-center'>已删除</h5>",
						  "</div>"].join("") : "",
				  "</div>",
				  "<div class='file-name' style='text-overflow: ellipsis; overflow: hidden;'>",
					  "<p style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap;' title='"+ item.fileName +"'>" + item.fileName + "</p>",
					  "<div>",                                                         //a<b?a:c<d?c:d
					  "<i class='glyphicon glyphicon-eye-open' style='color: " + (item.hasRead === 1 ?"#CCD5D3": item.objectType == "loan_patch"? "red": "#1ab394") + ";'></i>",
							  "<input type='checkbox' name='pic' class='pull-right' value='" + item.id + "' style='margin: 0;' />",
					  "</div>",
				  "</div>",
			  "</div>",
		  "</div>"
	  ].join(""));
	}
	return [
		"<h4 class='section-title'>"+ o.title +"</h4>",
		"<div class='row'>",
		(results.join("") || "<div class='col-xs-24 col-sm-24 col-md-24 col-lg-24 text-center'><h2>暂无图片!</h2></div>"),
		"</div>"
	].join("");
};
base64 = function(file, index, callback) {
  return lrz(file).then(function(rst) {
    var imgRst;
    imgRst = rst.base64;
    return typeof callback === "function" ? callback(file, imgRst, index) : void 0;
  });
};
imgListHtml = function(o, list){
	results = [];
	for (j = 0, len = list.length; j < len; j++) {
	  item = list[j];
	  results.push([
			  item.fileType== "1" ? "<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>" : "<div class='col-xs-8 col-sm-8 col-md-8 col-lg-8'>",
			  "<div class='file' data-file='" + (JSON.stringify(item)) + "' data-id='"+ item.id +"'>",
				  "<div class='image text-center' data-id='" + item.id + "' style='position: relative; height: auto;'>",
					  item.fileType == "1" ?
					  "<img data-original='"+ item.filePath +"' alt='"+ item.fileName +"' src='" + item.filePath + "@100h' height='100' />" : ( item.fileType == "2" ? [
						  "<video id='video"+j+"' class='video-js vjs-default-skin' controls preload='none' data-setup='{}'>",
						  "<source src='" + item.filePath + "' type='video/mp4' />",
						  "</video>"
					  ].join("") : ""),
					  imgType == "deleted" ?
						  ["<div style='position: absolute; top:0; left:0; right: 0; bottom: 0; background-color: rgba(0,0,0,0.3); color: red;'> ",
							  "<h5 class='text-center'>【" + item.modifyRealname + "】</h5>",
							  "<h5 class='text-center'>" + item.modifyTime + "</h5>",
							  "<h5 class='text-center'>已删除</h5>",
						  "</div>"].join("") : "",
				  "</div>",
				  "<div class='file-name' style='text-overflow: ellipsis; overflow: hidden;'>",
					  "<p style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap;' title='"+ item.fileName +"'>" + item.fileName + "</p>",
					  "<div>",                                                         //a<b?a:c<d?c:d
					  "<i class='glyphicon glyphicon-eye-open' style='color: " + (item.hasRead === 1 ?"#CCD5D3": item.objectType == "loan_patch"? "red": "#1ab394") + ";'></i>",
					  cureent_dir.canDelete === 1 || cureent_dir.canMove === 1 ? "<input type='checkbox' name='pic' class='pull-right' value='" + item.id + "' style='margin: 0;' />" : "<input type='checkbox' name='pic' class='pull-right' value='" + item.id + "' style='margin: 0;' />",
					  "</div>",
				  "</div>",
			  "</div>",
		  "</div>"
	  ].join(""));
	}
	return [
		"<h4 class='section-title'>"+ o.title +"</h4>",
		"<div class='row'>",
		(results.join("") || "<div class='col-xs-24 col-sm-24 col-md-24 col-lg-24 text-center'><h2>暂无图片!</h2></div>"),
		"</div>"
	].join("");
}

zTreeOnClick = function(event, treeId, treeNod) {
  var page;
  $("#fileList tbody").html("");
  //if (!treeNod.isParent) {
  if (true) {
    if (treeNod.docType === "1") {
      $("#upMovie").addClass("hide");
    } else if (treeNod.docType === "2") {
      $("#upMovie").removeClass("hide");
    }
    $("#upImage, #removed").removeClass("disabled");
    $("#moveImage, #delImage").addClass("disabled");
    $("#removed").html("<span>查看已删除影像</span>");
    cureent_dir = treeNod;
    page = 0;
    imgType = null;
    $("#documentList").html("");
    getDocumentList = function(o) {
      page = (o && o.curPage) || ++page;
      if (page === 1) {
        $("#documentList").html("");
      }
      return comn.ajax({
        url: (o && o.url) || interUrl.gr.documentList,
        data: {
          loanApplyId: m.loanApplyId,
          dirId: cureent_dir['id'],
          fileNamespace: args['space'] || "",
          releventFlow: args['releventFlow'] || "",
          releventFlowNode: args['releventFlowNode'] || "",
          type: imgType,
          page: page || 1,
          pageSize: 1000000
        },
        success: function(res) {
          var item;
          if (cureent_dir.canUpload === 2) {
            $("#upImage, #upload, #upMovie").addClass("hide");
          } else {
			if(cureent_dir.docType == "1"){
				$("#upImage, #upload").removeClass("hide"); 
			}else if(cureent_dir.docType == "2"){
				$("#upImage, #upload").addClass("hide"); 
				$("#upMovie").removeClass("hide"); 
			}
          }
          //如果不是专项卡流程，不能上传专项卡图片
          if((cureent_dir.id=="70303"||cureent_dir.id=="70304"||cureent_dir.id=="70305")&&args['releventFlow']!="CARDAPPLY_FLOW"){
        	  $("#upImage, #upload").addClass("hide");
          }
          if(args["pageStatus"]==3){//查看页面不能上传图片
      		$("#upImage").addClass('hide')
      		$("#upload").addClass('hide')
      	}
          if (cureent_dir.canDelete === 2) {
            $("#delImage, #removed").addClass("hidden");
          } else {
            $("#delImage, #removed").removeClass("hidden");
          }
          if (cureent_dir.canMove === 2) {
            $("#moveImage").hide();
          } else {
            $("#moveImage").show();
          }
          if (page === 1) {
            $("#moveImage, #delImage, #btnPrint, #sendContract").addClass("disabled");
          }
		  if([301, 30, 105, 40101].indexOf(cureent_dir.id) != -1){
			  $("#sendBanck, #sendContract, #signDatum").addClass("hidden"); 
			  $("#delImage").html("<span>取消标识文件</span>");
			  $("#delDocument").find(".modal-body h4").text("确定取消文件标识?");
			  if(imgType != "deleted"){
				  if(($.inArray(args['releventFlowNode'], ["LOAN_CAR_FINANCE"]) != -1) && args['releventFlow'] == "LOAN_APPLY_FLOW"){
					  $("#removed").html("<span>查看已取消资产方可见文件</span>"); 
					  $("#delImage").html("<span>取消资产分发可见</span>")
					  $("#delDocument").find(".modal-body h4").text("确定取消资产分发可见?");
				  }else{
					  $("#removed").html("<span>查看已取消标识文件</span>"); 
				  }
			  }
		  }else{
			  if((args['releventFlowNode'] == "DOCUMENT_VERIFY" || args['releventFlowNode'] == "COPY_CONTRACT") && args['releventFlow'] == "DOCUMENT_TRANSMIT_FLOW"){
				  $("#sendBanck").removeClass("hidden"); 
			  }else if(args['releventFlow'] == "INSURANCE_DISPATCHN_FLOW" && args['releventFlowNode'] == "INSURANCE_DISPATCHN_LAUNCH"){ //保险分发流程第一个节点(保险发起) 
				  $("#signDatum").removeClass("hidden"); 
			  }else if(($.inArray(args['releventFlowNode'], ["LOAN_CAR_FINANCE"]) != -1) && args['releventFlow'] == "LOAN_APPLY_FLOW"){
				  $("#sendContract").removeClass("hidden"); 
			  }

			  $("#delImage").html("<span>删除文件</span>");
			  $("#delDocument").find(".modal-body h4").text("确定删除当前文件？");
		  }


		  allImg = res.data;
          $("#documentList").html(function(){
			  var html = "", list = res.data; 
			  if (!treeNod.isParent) {
				  html += imgListHtml({title: cureent_dir.title}, list); 
			  }else{
				  for (var i = 0, len = list.length; i < len; i++) {
					  html += imgListHtml({title: list[i].directoryPath }, list[i].loanDocumentVoList); 
				  } 
			  }
			  return html;
		  });
          
          //点击视频播放时，获取视频的信息
          for (var i = 0; i < res.data.length; i++) {
        	  (function(i){
        	  var player = $("#video"+i+"");
        		  $("#video"+i+"").on("play",function(e) { 
        			   item = player.parents(".file").data("file");
        				dataid = player.parents(".file").data("id");
        				if(item.hasRead == 1){ 
        					if(!imgIds[item.dirId]){
        						imgIds[item.dirId] = [];
        					}
        					if(imgIds[item.dirId].indexOf(item.id) == -1){
        						imgIds[item.dirId].push(item.id); 
        					}
        				} 
           		    });
        	  })(i);
          }
          //看过视频后，调用方法，眼睛变色
          for (var i = 0; i < res.data.length; i++) {
        	  (function(i){
        	  var player = $("#video"+i+"");
        		  $("#video"+i+"").on("pause",function(e) { 
        			  comn.ajax({
        					url: interUrl.gr.readPhoto,
        					type: "get",
        					data: {
        						fileNamespace: args['space'] || "",
        						releventFlow: args['releventFlow'] || "",
        						releventFlowNode: args['releventFlowNode'] || "",
        						docIds: dataid
        					},
        					success: function(res){
        						if(res.data==0){
        							var zTree = $.fn.zTree.getZTreeObj("tree");
        							var nodes = zTree.getSelectedNodes();
        							zTree.setting.view.fontCss["color"] = "";
        						  	zTree.updateNode(nodes[0]);
        						}
        					}
        				});
        			for(item in imgIds){
        				$.each(imgIds[item], function(index, item){
        					  $($("#documentList")).find(".file[data-id='"+ item +"']").find(".glyphicon").css("color", "#1ab394"); 
        				});
        				comn.ajax({
        					url: interUrl.gr.recordDocQueryHistory,
        					type: "post",
        					data: {
        						loanApplyId: m.loanApplyId,
        						dirId: item,
        						fileNamespace: args['space'] || "",
        						releventFlow: args['releventFlow'] || "",
        						releventFlowNode: args['releventFlowNode'] || "",
        						docIds: imgIds[item].join(",")
        					},
        					success: function(res){	}
        				});
        			}
           		    });
        	  })(i);
          }
          
		  if(viewer){
			  viewer.destroy();
		  }
		  viewer = new Viewer(pictures, options);
        }
      });
    };
    return getDocumentList();
  } else {
    return $("#upImage").addClass("disabled");
  }
};
getSelectImage = function() {
  var arr;
  arr = {
    id: [],
    item: []
  };
  $("#documentList .file-name").find("input[name='pic']:checked").each(function() {
    arr.id.push($(this).val());
    return arr.item.push($(this).parents(".col-md-6"));
  });
  return arr;
};
getSelectImageBank = function() {
	  var arr;
	  arr = {
	    id: [],
	    item: []
	  };
	  $("#bankdocumentList").find("input[name='pic']:checked").each(function() {
	    arr.id.push($(this).val());
	    return arr.item.push($(this).parents(".col-md-6"));
	  });
	  return arr;
	};
//当用户点击标示银行可见按钮，保存当前选择的图片到当前选择的银行
$("#bankview").click(function(){
	var a=getSelectImage()['id'].join(",")
	comn.ajax({
		url:"loanDocument/toBankFile",
		data:{
			documentIds:a,
			dirId:current
		},
		success:function(res){
			//让已选图片列表显示图片
			showCurrentPic();
			loadTree();
		}
	});
});
$("#deleteImage").click(function(){
	var a=getSelectImageBank()["id"].join(",");
	//从银行资料里面删除这些图片，并且刷新列表
	comn.ajax({
		url:"loanDocument/delBankFile",
		data:{
			documentIds:a,
			dirId:current
		},
		success:function(res){
			//让已选图片列表显示图片
			showCurrentPic();
		}
	});
})
//让目录当前选择的图片显示
function showCurrentPic(){
	comn.ajax({
		url: interUrl.gr.documentList,
		data: {
			loanApplyId: m.loanApplyId,
			dirId: current,
			fileNamespace: args['space'] || "",
			releventFlow: args['releventFlow'] || "",
			releventFlowNode: args['releventFlowNode'] || "",
			type: imgType,
			page:  1,
			pageSize: 1000000
		},
		success: function(res) {
			console.log(res)
			$("#bankdocumentList").html(function(){
				var html = "", list = res.data; 
				list.sort(function(a,b){
					return a.id>b.id?1:-1
				})
				html += imgListHtmlde({title: ""}, list); 
				return html;
			});
			if(viewer){
				  viewer.destroy();
			  }
			  viewer = new Viewer(picturesbank, options);
		}
	});
}
loadTree = function() {
  var url;
  $.fn.zTree.destroy();
  url = args['isFlow'] === "yes" ? interUrl.gr.documentDir : interUrl.gr.documentAllDir;
  return comn.ajax({
    url: url,
    data: {
      loanApplyId: m.loanApplyId,
      fileNamespace: args['space'] || "",
      releventFlow: args['releventFlow'] || "",
      releventFlowNode: args['releventFlowNode'] || ""
    },
    success: function(res) {
		if(args['loanFlag'] != '2'){
			$("#checkData").addClass("hidden"); 
		}else{
			comn.ajax({
				url: interUrl.gr.documentGetCheckStatus,
				data: {
					projectId: args['projectId'] 
				},
				success: function(res){
					$("#checkData").values({
						documentCheckStatus: res.data.documentCheckStatus 
					})
				} 
			})
		} 
	//银行资料tree
		var zNodes;
		comn.ajax({
			url: "loanDocument/getBankDir",
			data: {
				objectType: "divigeneral",
				 fileNamespace: args['space'] || "",
			      releventFlow: args['releventFlow'] || "",
			      releventFlowNode: args['releventFlowNode'] || "",
			      loanApplyId:m.loanApplyId
			},
			async:false,
			success: function(res){
				 zNodes = res.data.children;
				 zTreeObj = $.fn.zTree.init($("#bankTree"), {
						check: {
							enable: args['loanFlag']=='2',
							chkDisabledInherit: true,
							chkStyle: "checkbox",
						}, 
						data: {
						  key: {
							checked: 'canTick'
						  }, 
						},
				        showLine: true,
				        expand: true,
				        callback: {
				          onClick:function(a,b,c){
							current=c["id"]//将当前选择的文件夹保存到js
							showCurrentPic();//让当前文件夹文件显示
//							if(viewer){
//								  viewer.destroy();
//							  }
//							  viewer = new Viewer(pictures, options);
						}
				        }
						
				      }, zNodes);
			} 
		})
		
	
	
      treeObj = $.fn.zTree.init($("#tree"), {
		check: {
			enable: args['loanFlag']=='2',
			chkDisabledInherit: true,
			chkStyle: "checkbox",
		}, 
		data: {
		  key: {
			checked: 'canTick'
		  }, 
		},
        showLine: true,
        expand: true,
        callback: {
          onClick: zTreeOnClick
        }
      }, res.data);

      treeTarget = $.fn.zTree.init($("#targetTree"), {
        showLine: true,
        expand: true,
        callback: {
          onClick: function(event, treeId, treeNod) {
            if (!treeNod.isParent) {
              targetDir = treeNod;
              console.log(targetDir)
              return $("#targetSure").removeClass("disabled");
            } else {
              return $("#targetSure").addClass("disabled");
            }
          }
        }
      }, res.data);
      treeTarget.expandAll(true);
      
      function changeColor(zTree,treeNodes){
    	  node = zTree.getNodesByParam("name",treeNodes.name, null);
	  		zTree.selectNode(node[0],true);
		  	zTree.setting.view.fontCss["color"] = "red";
		  	zTree.updateNode(treeNodes);
		  	zTree.setting.view.fontCss = {}; 
      }
      if(args['releventFlowNode'] == "LOAN_CAR_FINANCE"||args['releventFlowNode'] == "COPY_CONTRACT"||args['releventFlowNode'] == "DOCUMENT_REVIEW"){
    	  var zTree = $.fn.zTree.getZTreeObj("tree"),node
		  	nodes = zTree.getNodes();
    		for(var i=0;i<nodes.length;i++){
    			var grandpa= nodes[i].children;
    			for(var j=0;j<grandpa.length;j++){
    				var father= grandpa[j].children;
    				for(var k=0;k<father.length;k++){
    					if(father[k].docType==2){
    						if(father[k].identify==0){
    							changeColor(zTree,father[k]);
        					  }else if(father[k].identify==1 && args['releventFlowNode'] == "LOAN_CAR_FINANCE"){
        						  changeColor(zTree,father[k]);
        					  }else if(father[k].identify==2 && (args['releventFlowNode'] == "COPY_CONTRACT"||args['releventFlowNode'] == "DOCUMENT_REVIEW")){
        						  changeColor(zTree,father[k]);
        					  }
    					}
    					var son= father[k].children;
    					for(var p=0;p<son.length;p++){
    						if(son[p].identify==0){
    							changeColor(zTree,son[p]);
        					  }else if(son[p].identify==1 && args['releventFlowNode'] == "LOAN_CAR_FINANCE"){
        						  changeColor(zTree,son[p]);
        					  }else if(son[p].identify==2 && (args['releventFlowNode'] == "COPY_CONTRACT"||args['releventFlowNode'] == "DOCUMENT_REVIEW")){
        						  changeColor(zTree,son[p]);
        					  }
    					}
    					  	}
    					}
    				}
      }
      return treeObj.expandAll(true);
    }
  });
};

handle = function(o) {
  return comn.ajax({
    url: o.url || "",
    data: {
      loanApplyId: m.loanApplyId,
      dirId: o.dirId,
      documentIds: getSelectImage()['id'].join(","),
      fileNamespace: args['space'] || "",
      releventFlow: args['releventFlow'] || "",
      releventFlowNode: args['releventFlowNode'] || ""
    },
    success: function(res) {
      var i, j, len, ref;
      loadTree();
      ref = getSelectImage()['item'];
      for (j = 0, len = ref.length; j < len; j++) {
        i = ref[j];
        $(i).remove();
      }
      $("#moveImage, #delImage").addClass("disabled");
      return typeof o.callback === "function" ? o.callback(res) : void 0;
    }
  });
};
})();
function getNote(){
	comn.ajax({
		url:"cardApply/selectNote",
		data:{
			deliverId: m.loanApplyId
		},
		success:function(res){
			$("[name='currencyNote']").val(res.data.currencyNote)
		}
	})
}
$("#currencySave").click(function(){
	saveNote();
})
function saveNote(){
	comn.ajax({
		url:"cardApply/saveNote",
		data:{
			deliverId: m.loanApplyId,
			currencyNote:$("[name='currencyNote']").val()
		},
		success:function(res){
			tip({
				content:"保存成功"
			})
		}
	})
}
//页面加载完毕后执行
$(function() {
	//如果是资料复核节点，显示银行文件夹
	if(args['releventFlowNode']=="DOCUMENT_REVIEW"){
		$("#bankMain").removeClass("hide");
		$("#bankview").removeClass("hide");
		$("#bankview").attr("disabled",true)
		$("#currencyNote").removeClass('hide');
		getNote();
	}
	
  loadTree();
  $(".checkbox-inline").click(function(){
	$(this).children("input").prop("checked", true); 
  });

  $("#subTree").click(function(){
	  var nodes = treeObj.getCheckedNodes(true), nodeArr = [];
	  for (var i = 0, len = nodes.length; i < len; i++) {
		  if(!nodes[i].isParent){
			  nodeArr.push(nodes[i].id);
		  }
	  }
	  if(nodeArr.length != 0){
		  var documentCheckStatus = $("input[name='documentCheckStatus']:checked").val();
		  comn.ajax({
			  url: interUrl.gr.documentUpdateResult,
			  data: {
				  documentCheckResult: nodeArr.join(","),
				  projectId: args['projectId'],
				  documentCheckStatus: documentCheckStatus 
			  }, 
			  success: function(res){
				  tip({content: '保存成功! '}); 
			  }
		  }) 
	  }else{
		  tip({content: '请选中后再进行提交! '}); 
	  } 
  });

  $("#sendBanck").click(function(){
    if (!$(this).hasClass("disabled")) {
		comn.ajax({
			url: interUrl.gr.documentCopy,
			data: {
				destDirId: '301',
				documentIds: getSelectImage()['id'].join(",")
			},
			success: function(res){
				loadTree(); 
				tip({content: "文件标识成功！"});
			} 
		}); 
    } 
  }); 

  $("#sendContract, #signDatum").click(function(){
	  var _this = this;
    if (!$(this).hasClass("disabled")) {
		comn.ajax({
			url: interUrl.gr.assetDistribution,
			data: {
				loanApplyId: m.loanApplyId,
				fileNamespace: _this.id == "sendContract" ? "ASSET_PACKAGE" : "INSURANCE_DISTRIBUTION",
				documentIds: getSelectImage()['id'].join(","), 
				destDirId: _this.id == "signDatum" ? "40101" : null
			},
			success: function(res){
				loadTree(); 
				tip({content: _this.id == "signDatum" ?  "标识文件成功! " :  "资产分发可见成功！"});
			} 
		});
    } 
  });

  $("#removed").click(function() {
    if (imgType === "deleted") {
      if (!$(this).hasClass("disabled")) {
        return $("#replayFile").modal("show");
      }
    } else {
      imgType = "deleted";
      $(this).addClass("disabled").html("<span>恢复文件</span>");
      return getDocumentList({
        curPage: 1
      });
    }
  });

  $("#delImage").click(function() {
	if (!$(this).hasClass("disabled")) {
	  return $("#delDocument").modal("show");
	}
  });
  $("#moveImage").click(function() {
    if (!$(this).hasClass("disabled")) {
      return $("#targetDir").modal("show");
    }
  });
  $("#upMovie").click(function() {
    return $("#upMovieInput").trigger("click");
  });
  $("#upImage").click(function() {
	  //如果是专项卡申请流程，要先保存基本信息再上传图片
	  if(args['releventFlow']=="CARDAPPLY_FLOW"){
		  if(isSave==0){
			  tip({
				  content:"请先保存基本信息"
			  })
			  return ;
		  }
		  
	  }
    if (!$(this).hasClass("disabled")) {
      return $("#upImageInput").trigger("click");
    }
  });
  $("#replay").click(function() {
    return handle({
      url: interUrl.gr.recoveryFile,
	  dirId: cureent_dir['id'],
      callback: function(res) {
        return $("#replayFile").modal("hide");
      }
    });
  });
  $("#delSure").click(function() {
    if (!$(this).hasClass("disabled")) {
      return handle({
        url: interUrl.gr.delDocument,
		dirId: cureent_dir['id'],
        callback: function(res) {
          return $("#delDocument").modal("hide");
        }
      });
    }
  });
  $("#targetSure").click(function() {
    if (!$(this).hasClass("disabled")) {
      return handle({
        url: interUrl.gr.moveDocument,
		dirId: targetDir['id'],
        callback: function(res) {
          return $("#targetDir").modal("hide");
        }
      });
    }
  });
  $("#btnPrint").click(function() {
    var picArr;
    if (!$(this).hasClass("disabled")) {
      picArr = [];
      $.each(getSelectImage()['item'], function(index, item) {
        var url;
        url = item.find(".file").data("file")['filePath'];
        return picArr.push(url);
      });
      return window.open("../../../Modal/common/documentDetail/imagePrint.html?imgUrl=" + picArr.join(","));
    }
  });
  $("#documentList").on("click", ".file-name", function(e) {
    var $checkbox, $els;
    $els = $("#moveImage, #delImage, #btnPrint, #sendContract, #sendBanck, #signDatum,#bankview");
    if (!$(e.target).is(":input")) {
      $checkbox = $(this).find("input:checkbox")[0];
      $checkbox.checked = !$checkbox.checked;
    }
    if (getSelectImage()['item'].length > 0) {
    	if(current){
    		$("#bankview").attr("disabled",false)
    	}
      if (imgType === "deleted") {
        return $("#removed, #btnPrint,#bankview").removeClass("disabled");
      } else {
        return $els.removeClass("disabled");
      }
    } else {
      if (imgType === "deleted") {
        return $("#moveImage, #delImage, #removed, #btnPrint,#bankview").addClass("disabled");
      } else {
        return $els.addClass("disabled");
      }
    }
  });
  $("#upImageInput").change(function() {
    var fileArr, html, i, j, k, len, results;
    fileArr = this.files;
    results = [];
    for (k = j = 0, len = fileArr.length; j < len; k = ++j) {
      i = fileArr[k];
      html = "";
      results.push(base64(i, k, function(f, o, index) {
		  html = ["<tr>",
			  "<td>",
				  "<img src='" + o + "' width='80' />",
				  "<input name='LoanDocuments[0].fileName' class='hide' value='" + f.name + "' />",
			  "</td>",
			  "<td>" + f.name + "</td>",
			  "<td>" + (((f.size * 0.5) / 1048576).toFixed(2)) + "M</td>",
			  "<td data-name='imgHandle'>",
				  "<button type='button' class='btn btn-danger btn-sm upCancle'><span>取消上传</span></button>",
			  "</td>",
		  "</tr>"].join("");
        return $("#fileList tbody").prepend(html);
      }));
    }
    return results;
  });

  $("#upMovieInput").change(function() {
    var file;
    file = this.files[0];
    return $.ajaxFileUpload({
      url: interUrl.basic + interUrl.gr.upFile,
      secureuri: false,
      fileElementId: 'upMovieInput',
      data: {
        loanApplyId: m.loanApplyId,
        dirId: cureent_dir['id'],
        fileName: file.name,
        fileNamespace: args['space'] || "",
        releventFlow: args['releventFlow'] || "",
        releventFlowNode: args['releventFlowNode'] || ""
      },
      dataType: "json",
      success: function(data, status) {
		if(data.code == "10000") {
			tip({content: data.data});
			loadTree();
	  	} else if(data.code == "20000") {
	  		tip({content: data.message});
			loadTree();
	  	}
      },
      complete: function() {
    	  
      },
      error: function(data, status, e) {
    	  tip({content: data.message});
		  loadTree();
      }
    });
  });

  $("#downLoad").click(function() {
    return window.open(interUrl.basic + interUrl.gr.downLoad + ("?loanApplyId=" + m.loanApplyId + "&fileNamespace=" + (args['space'] || '') + "&releventFlow=" + (args['releventFlow'] || '') + "&releventFlowNode=" + (args['releventFlowNode'] || '')));
  });
  $("#fileList tbody").on("click", ".upCancle", function() {
    return $(this).parents("tr").remove();
  });
  return $("#upload").click(function() {
    var $tr, $trAll, maxImg, num, upImg;
    $tr = $("#fileList tbody").find("tr:not('.loaded')");
    $trAll = $("#fileList tbody").find("tr");
    num = $tr.index();
    if (num === -1) {
      return;
    }
    //如果专项卡流程从主页进来的话，保存过，影像管理上传图片传专项卡的主键
    if(args['releventFlow']=="CARDAPPLY_FLOW"&&isSave!=0){
    	m.loanApplyId=isSave
    }
    maxImg = $tr.length;
    return upImg = setInterval((function(_this) {
      return function() {
        var cur_tr;
        cur_tr = $trAll.get(num);
        if (!$(cur_tr).hasClass("loaded")) {
          comn.ajax({
            url: interUrl.gr.uploadImage,
            data: $.extend($(cur_tr).values(), {
              loanApplyId: m.loanApplyId,
              dirId: cureent_dir['id'],
              fileNamespace: args['space'] || "",
              releventFlow: args['releventFlow'] || "",
              releventFlowNode: args['releventFlowNode'] || "",
			  "LoanDocuments[0].filePath": $(cur_tr).find("img").eq(0).attr("src")
            }),
            success: function(res) {
              $(cur_tr).addClass("loaded").nameValues({
                imgHandle: "<span class='text-success'>上传成功！</span>"
              });
				if (maxImg === 0) {
                getDocumentList({ curPage: 1 });
                loadTree();
              }
				
	              
            }
          });
        }
        num++;
        if (!(--maxImg)) {
          return clearInterval(upImg);
        }
      };
    })(this), 100);
  });
});
//诚易融添加房产及社保公积金校验逻辑
if(args['currentNodeKey'] == "CHENGYIRONG_LOAN_CAR_FINANCE"){//如果是审批节点则需要显示提额校验模块
	// && args['hasFund'] = 1 || args['hasHouse'] ==2 upLoanAmountDiva;
	if(args['hasHouse'] == 1){//如果是1则需要显示房产的校验此时校验默认为通过且不可改
		$('#upLoanAmountDiv').removeClass('hide');
		$('#checkHouse').removeClass('hide');
		loadUpLoanAmountCheckInfo();
		$("#houseCheckResult").val(1);//设置为通过
		$("#checkHouse").attr("disabled",true);
	}
	if(args['hasHouse'] == 2){//如果是2则需要显示房产的校验
		$('#upLoanAmountDiv').removeClass('hide');
		$('#checkHouse').removeClass('hide');
		loadUpLoanAmountCheckInfo();
		
	}
	if(args['hasFund'] == 1){//如果是1则需要显示社保公积金的校验此时校验默认为通过且不可改
		$('#upLoanAmountDiv').removeClass('hide');
		$('#checkFund').removeClass('hide');
		loadUpLoanAmountCheckInfo();
		$("#fundCheckResult").val(1);//设置为通过
		$("#checkFund").attr("disabled",true);
		
	}
	if(args['hasFund'] == 2){//如果是2则需要显示社保公积金的校验
		$('#upLoanAmountDiv').removeClass('hide');
		$('#checkFund').removeClass('hide');
		loadUpLoanAmountCheckInfo();
	}
}
$("#check-btn-save").click(function(){
	var ulr = interUrl.gr.saveCheckResult;
	if(args['upLoanAmountCheckId']){
		ulr = interUrl.gr.updateCheckResult;
	}
	var data = $("#upLoanAmountForm").values();
	comn.ajax({
		url:ulr,
		data:$.extend(data,
				{applyId:args['loanApplyId']}
		),
		success:function(res){
			if(!args['upLoanAmountCheckId']){
				args['upLoanAmountCheckId'] = res.data;
			}
			
			args["houseCheckResult"]=$("#houseCheckResult").val();
			args["fundCheckResult"]=$("#fundCheckResult").val();
			tip({
				content:"保存成功"
			})
		}
	})
});
//获取提额校验信息
function loadUpLoanAmountCheckInfo(){
	comn.ajax({
		url: interUrl.gr.getCheckResultByApplyId,
		data: {
			applyId : args['loanApplyId']
		},
		success:function(res){
			if(res.data){
				args['upLoanAmountCheckId'] = res.data.id;
				$("#upLoanAmountForm").values(res.data);
				args["houseCheckResult"]=res.data.houseCheckResult;
				args["fundCheckResult"]=res.data.fundCheckResult;
			}
		}
	});
}