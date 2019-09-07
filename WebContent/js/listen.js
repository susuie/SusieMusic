var song = null;
var hotsong = null;
var king = "";
var kindsong = null;
$(document).ready(function(){
	hotsingerSong();
	familar();
});
function hotsingerSong(){	
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
	$.ajax({
		type:"post",
		url:"../song/hotsong",
		async:false,
		dataType:"json",
		success:function(data){
			hotsong = data.hotSong;
			$("#singer")[0].innerText = hotsong[0].artist;
			kind = hotsong[0].s_kind;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});		
	var i;
	  for(i=0;i<song.length;i++){
		$("#hotsinger").append('<li class="list-group-item">'+
			     '<div class="pull-right m-l">'+
			       '<a href="javascript:;" class="m-r-sm"><i class="icon-cloud-download" onclick="downloadSong('+song[i].s_id+')"></i></a>'+                      
			     '</div>'+
			     '<a href="javascript:;" class="jp-play-me m-r-sm pull-left" onclick="playMusic('+song[i].s_id+')">'+
			      ' <i class="icon-control-play text"></i>'+
			       '<i class="icon-control-pause text-active"></i>'+
			     '</a>'+
			     '<div class="clear text-ellipsis">'+
			      '<span>'+song[i].title+'</span>'+
			       '<span class="text-muted"> -- '+song[i].artist+'</span>'+
			     '</div>'+
			   '</li>');
	  }	 
}
//相关推荐
function familar(){
	$.ajax({
		type:"get",
		url:"../song/kind",
		async:false,
		data:{"kind":kind},
		dataType:"json",
		success:function(data){
			kindsong = data.kindSong;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	var i;
	for(i=0;i<kindsong.length;i++){
		$("#familar").append(
				 '<li class="list-group-item clearfix">'+
                 '<a href="javascript:;" class="jp-play-me pull-right m-t-sm m-l text-md"onclick="playMusic('+kindsong[i].s_id+')">'+
                   '<i class="icon-control-play text"></i>'+
                   '<i class="icon-control-pause text-active"></i>'+
                 '</a>'+
                 '<a href="#" class="pull-left thumb-sm m-r">'+
                   '<img src="../image/m'+i+'.jpg" alt="...">'+
                 '</a>'+
                 '<a class="clear" href="javascript:;">'+
                   '<span class="block text-ellipsis">'+kindsong[i].title+'</span>'+
                   '<small class="text-muted">by '+kindsong[i].artist+'</small>'+
                 '</a>'+
               '</li>'
		);
	}
}