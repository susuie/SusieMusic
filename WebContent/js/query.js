//根据歌名模糊搜索
function titleQuery(){
	var val = $("#titleQuery").val();
	var querySong = null;
	if(val!=""){
	$.ajax({
		type:"post",
		url:"../song/title",
		async:false,		
		data:{"title":val},
		dataType:"json",
		success:function(result){
			querySong = result.querySong;
			if(querySong.length!=0){
				//var is = document.getElementById("indexsong");
				//is.style.display="none";
				$("#indexsong")[0].innerHTML="";
			}
			else{
				alert("没有要查询的歌曲！");
			}
		},
		error:function(result){
			alert("获取数据失败！");
		}
	});	
	var i;
	  for(i=0;i<querySong.length;i++){
		$(".aa").append('<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">'+
	                      '<div class="item">'+
	                        '<div class="pos-rlt">'+
	                          
	                          '<div class="item-overlay opacity r r-2x bg-black">'+
	                            '<div class="text-info padder m-t-sm text-sm">'+
	                             ' <i class="fa fa-star"></i>'+
	                              '<i class="fa fa-star"></i>'+
	                              '<i class="fa fa-star"></i>'+
	                              '<i class="fa fa-star"></i>'+
	                              '<i class="fa fa-star-o text-muted"></i>'+
	                            '</div>'+
	                            '<div class="center text-center m-t-n">'+	                            
	                            '<a href="javascript:;" class="jp-play-me" onclick="playMusic('+querySong[i].s_id+')">'+
                                '<i class="icon-control-play i-2x text"></i>'+
                                '<i class="icon-control-pause i-2x text-active"></i>'+
                                '</a>'+	                            	                            
	                            '</div>'+	                            	                            
	                            '<div class="bottom padder m-b-sm">'+
	                              '<a href="#" class="pull-right" data-toggle="class">'+
	                                '<i class="fa fa-heart-o text"></i>'+
	                                '<i class="fa fa-heart text-active text-danger"></i>'+
	                              '</a>'+
	                              '<a href="#" data-toggle="class">'+
	                                '<i class="fa fa-plus-circle text"></i>'+
	                                '<i class="fa fa-check-circle text-active text-info"></i>'+
	                              '</a>'+
	                            '</div>'+	                            	                            
	                          '</div>'+
	                          '<a href="#"><img src='+querySong[i].poster+' alt="" class="r r-2x img-full"></a>'+
	                        '</div>'+
	                        '<div class="padder-v">'+
	                          '<a href="#" class="text-ellipsis">'+querySong[i].title+'</a>'+
	                          '<a href="#" class="text-ellipsis text-xs text-muted">'+querySong[i].artist+'</a>'+
	                        '</div>'+
	                      '</div>'+
	                    '</div>');
	  }
	}
	else{
		document.location.reload();
	}
}//显示歌词
//解析歌词
function parseLyric(text) {
	//将文本分隔成一行一行，存入数组
	var lines = text.split('\n'),
		//用于匹配时间的正则表达式，匹配的结果类似[xx:xx.xx]
		pattern = /\[\d{2}:\d{2}.\d{2}\]/g,
		//保存最终结果的数组
		result = [];
	//去掉不含时间的行
	while (!pattern.test(lines[0])) {
		lines = lines.slice(1);
	};
	//上面用'\n'生成生成数组时，结果中最后一个为空元素，这里将去掉
	lines[lines.length - 1].length === 0 && lines.pop();
	lines.forEach(function(v /*数组元素值*/ , i /*元素索引*/ , a /*数组本身*/ ) {
		//提取出时间[xx:xx.xx]
		var time = v.match(pattern),
			//提取歌词
			value = v.replace(pattern, '');
		//因为一行里面可能有多个时间，所以time有可能是[xx:xx.xx][xx:xx.xx][xx:xx.xx]的形式，需要进一步分隔
		time.forEach(function(v1, i1, a1) {
			//去掉时间里的中括号得到xx:xx.xx
			var t = v1.slice(1, -1).split(':');
			//将结果压入最终数组
			result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]);
		});
	});
	//最后将结果数组中的元素按时间大小排序，以便保存之后正常显示歌词
	result.sort(function(a, b) {
		return a[0] - b[0];
	});
	return result;
	}
var text=null;	
function showWords(){
	var str = "";
	var querySong;
	var title = $(".jp-title")[0].innerText;
	$.ajax({
		type:"post",
		url:"../song/title",
		async:false,		
		data:{"title":title},
		dataType:"json",
		success:function(result){
			querySong = result.querySong;
		}
	});
	var url = querySong[0].lrc;		
	$.ajax({ url: url,				
			type: "get",
			async:false,
			dataType:"text",								
			success: function(data) { text = data; 
					
	var lyric = parseLyric(text);
	//获取页面上的audio标签
	//var audio = document.getElementById('audio');
	//显示歌词的元素
	var lyricContainer = document.getElementById('lyricContainer');
	$("#lyricContainer").css("background-image","url("+querySong[0].poster+")")
	lyricContainer.innerHTML="";
	for (var i = 0, l = lyric.length; i < l; i++) {
		//if (this.currentTime > lyric[i][0]) {
			//显示到页面
		lyricContainer.innerHTML += '<li style="list-style:none;margin-left:30px;">'+lyric[i][1]+'</li>';
		//}
	}},
	error:function(data){
		alert("当前歌曲暂未搜索到歌词！");
	}
	});
	layer.open({
		type: 1, 
		title:"歌词",
		shade:false,
		offset: ['60px', '100px'],//top和left
		area:['500px', '300px'],
		content: $('#lyricContainer') //这里content是一个dom
		});
}
//歌曲点赞数
function tup(id){
	var song =null;
	var name = $("#co"+id+" a")[0].className;
	$.ajax({
		url: "../song/addTup",				
		type: "post",
		async:false,
		data:{"s_id":id,"className":name},
		success: function(data) {
			if(name=="pull-right"){
				alert("点赞成功！");
			}
			else{
				alert("成功取消点赞！");
			}			
		}		
	});
}

function downloadSong(id){
	var filename = "";
	$.ajax({
		type : "POST",			
		url : "../song/songid",
		data : {"s_id":id},
		async : false,
		success : function(data) {
			filename = data.title+".mp3";
		},
		error : function(data) {
			alert("error");
		}
	});
	$.ajax({
		type : "POST",			
		url : "../song/download",
		data : {"filename":filename},
		async : false,
		success : function(data) {
			alert("下载成功！");
		},
		error : function(data) {
			alert("error");
		}
	});
}
var replay =null;
function comment(id){
	$("#m_replies").val("");//清空回复输入
	$("#MyReplies").modal("show"); //展示回复的模态框
    var username = null;
    var uid = null;
    $.get("getsession",function(data){
    	username=data.name;
    	uid = data.userid;
    });
    $.ajax({
    	type: "get",
        url: "../song/getComment",
        async: false,
        data: {"s_id": id},
        dataType: "json",
        success: function (result) {
        	replay = result.replies;
        	var rep ="";
        	if(replay.length==0){
        		rep="暂无评论";
        		$('#MyReplies .replies').text("回复："+rep);
        	}
        	else{
        		var r="";   var date = null; var formatDateTime = null;
        		for(var j=0;j<replay.length;j++){       			
        		    date = new Date(replay[j].c_time); // 获取时间戳        		    
        		    formatDateTime = date.toLocaleString();
        			r += "发表评论人："+replay[j].username+"时间："+formatDateTime+"<br/>"+
    				"内容："+replay[j].c_details+"<br/>";               			
        		}                		
        		$('#MyReplies .replies').html(r);
        	}         
        },
        error: function (result) {
       	 $("#MyReplies .replies").text("加载失败！");
        }
    });
    $('#confirm_replies').unbind('click').bind('click',function(){
		var t = $("#m_replies").val();
		$.ajax({				
         type: "get",
         url: "../song/addComment",
         async: false,
         data: {"s_id": id,"c_details":$("#m_replies").val(),"username":username,"u_id":uid},       
         success: function (data) {        	 
        	 $("#MyReplies").modal("hide"); 
        	 alert("评论成功！");
         },
         error: function (data) {
        	 $("#m_replies").val("发布评论失败!");
         }
     });
	});
	$('#clear_replies').click(function(){
		$("#m_replies").val("");
	});	
}
function upSong(){
	$('#upsong').modal("show");
//	$('#save').unbind('click').bind('click',function(){
//		//var formdata = new FormData($("#form1")[0]);
//		$.ajax({
//			type : "POST",			
//			url : "../song/upsong",
//			data : {
//				"title":$("#title").val(),
//				"artist":$("#artist").val(),
//				"s_kind":$("#type").val(),
//				//formdata,
//			},
//			scriptCharset : 'utf-8',
//			async : false,
//			cache : false,
//			contentType : false,
//			processData : false,
//			success : function(data) {
//				alert("上传成功！");
//			},
//			error : function(data) {
//				alert("error");
//			}
//		});

	//});
}
