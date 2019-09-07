var song =null;
var hotsong = null;
var newsong = null;
var loveSong = null;
var user = null;
$(document).ready(function(){
	index();
	newSong();
	hotSong();	
	//getsession
	var username = null;
	$.get("getsession",function(data){ 			
	username=data.nickName;
	user = data.userid;
	$("#usersession")[0].innerText = username;
	songlist();
	});
	 window.setInterval("updateTime()", 1000*60*60*24);
});
function index(){
	$.ajax({
		type:"post",
		url:"../song/lists",
		async:false,
		dataType:"json",
		success:function(data){
			song = data.song;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	var i;
	  for(i=0;i<song.length;i++){
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
	                            '<a href="javascript:;" class="jp-play-me" onclick="playMusic('+song[i].s_id+')">'+
                                '<i class="icon-control-play i-2x text"></i>'+
                                '<i class="icon-control-pause i-2x text-active"></i>'+
                                '</a>'+	                            	                            
	                            '</div>'+	                            	                            
	                            '<div class="bottom padder m-b-sm" id="co'+song[i].s_id+'">'+
	                              '<a href="#" class="pull-right" data-toggle="class" onclick="tup('+song[i].s_id+')">'+
	                                '<i class="fa fa-heart-o text"></i>'+
	                                '<i class="fa fa-heart text-active text-danger"></i>'+
	                                '<span>'+song[i].s_tup+'</span>'+
	                              '</a>'+
	                              '<a href="#" data-toggle="class" onclick="collect('+song[i].s_id+')">'+
	                                '<i class="fa fa-plus-circle text"></i>'+
	                                '<i class="fa fa-check-circle text-active text-info"></i>'+
	                              '</a>'+
	                              '<a href="#" data-toggle="class" style="margin-left:10px;" onclick="downloadSong('+song[i].s_id+')">'+
	                              '<i class="icon-cloud-download">'+song[i].s_download+'</i>'+
	                              '</a>'+
	                              '<a href="#" data-toggle="class" class="pull-right" '+
	                              'style="margin-right:6px;" onclick="comment('+song[i].s_id+')">'+
	                              '<i class="fa fa-comment-o"></i>'+ song[i].s_comment +'</a>'+
	                            '</div>'+	                            	                            
	                          '</div>'+
	                          '<a href="#"><img src='+song[i].poster+' alt="" class="r r-2x img-full"></a>'+
	                        '</div>'+
	                        '<div class="padder-v">'+
	                          '<a href="#" class="text-ellipsis">'+song[i].title+'</a>'+
	                          '<a href="#" class="text-ellipsis text-xs text-muted">'+song[i].artist+'</a>'+
	                        '</div>'+
	                      '</div>'+
	                    '</div>');
	  }
	  
	 
	  
	  
}
function newSong(){
	 $.ajax({
			type:"get",
			url:"../song/newsong",
			async:false,
			dataType:"json",
			success:function(data){
				newsong = data.newSong;
			},
			error:function(data){
				alert("获取数据失败！");
			}
		});	
	  for(i=0;i<newsong.length;i++){
		  $("#newsong").append(
				  '<div class="col-xs-6 col-sm-3">'+
               '<div class="item">'+
                 '<div class="pos-rlt">'+
                   '<div class="item-overlay opacity r r-2x bg-black">'+
                     '<div class="center text-center m-t-n">'+
                       '<a href="javascript:;" class="jp-play-me" onclick="playMusic('+newsong[i].s_id+')">'+
                     '<i class="fa fa-play-circle i-2x text"></i>'+
                       '<i class="fa fa-play-circle i-2x text-active"></i>'+
                       '</a>'+
                     '</div>'+
                   '</div>'+
                   '<a href="javascript:;"><img src="../image/new'+i+'.jpg" alt="" class="r r-2x img-full"></a>'+
                 '</div>'+
                 '<div class="padder-v">'+
                   '<a href="javascript:;" class="text-ellipsis">'+newsong[i].title+'</a>'+
                   '<a href="javascript:;" class="text-ellipsis text-xs text-muted">'+newsong[i].artist+'</a>'+
                 '</div>'+
               '</div>'+
             '</div>'
		  );
	  }
}
function hotSong(){
	$.ajax({
		type:"post",
		url:"../song/hotsong",
		async:false,
		dataType:"json",
		success:function(data){
			hotsong = data.hotSong;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
  for(i=0;i<hotsong.length;i++){
	$("#hotsong").append(
			 '<a href="javascript:;" class="list-group-item clearfix" onclick="playMusic('+hotsong[i].s_id+')">'+
             '<span class="pull-right h2 text-muted m-l">'+(i+1)+'</span>'+
             '<span class="pull-left thumb-sm avatar m-r">'+
             '<img src="../image/h'+i+'.jpg" alt="...">'+
             '</span>'+
             '<span class="clear">'+
               '<span>'+hotsong[i].title+'</span>'+
               '<small class="text-muted clear text-ellipsis">by'+ hotsong[i].artist+'</small>'+
             '</span>'+
           '</a>'
	);
  }
}

function songlist(){
	$.ajax({
		type:"get",
		url:"../collection/list",
		async:false,
		dataType:"json",
		data:{"uid":user},
		success:function(data){
			loveSong = data.song;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	$("#lovesong")[0].innerHTML="";
  for(i=0;i<loveSong.length;i++){
	$("#lovesong").append(
			'<li >'+
            '<a href="javascript:;" class="auto">'+                                                       
              '<span onclick="playMusic('+loveSong[i].s_id+')">'+loveSong[i].title+'</span>'+
            '</a>'+
          '</li>'
			);
  }
}
//歌曲是否被当前用户收藏
function collect(id){
	$.ajax({
		 url:"../collection/addCollection",
	     type:"post",
	     async:false,
	     data: {"songid":id,"userid":user},
	    success:function(result){
	    	alert("收藏歌曲成功！");
	    	songlist();
	    },error:function(result){
	    	alert("收藏歌曲失败！")
	    }	  	      
	});
}

/**
 * 更新vip时间
 */
var status = true;
updateTime = function(){	
	$.ajax({
		 url:"../user/updateTime",
	     type:"post",
	     async:false,
	     success:function(result){
	    	 status= false;
	    	 alert("调用成功！");	    	
	     },
	});
}


