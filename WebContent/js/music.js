var kindSong = null;
var kind = null;
$(document).ready(function(){	
	var style = $(".list-group-item");
	kind = $(".list-group-item.active")[0].innerText;
	$.ajax({
		type:"get",
		url:"../song/kind",
		async:false,
		data:{"kind":kind},
		dataType:"json",
		success:function(data){
			kindSong = data.kindSong;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	style.unbind('click').bind('click',function(){
		if(this.className=="list-group-item active"){
				$.ajax({
				type:"get",
				url:"../song/kind",
				async:false,
				data:{"kind":kind},
				dataType:"json",
				success:function(data){
					kindSong = data.kindSong;
				},
				error:function(data){
					alert("获取数据失败！");
				}
			});	
				$("#kind")[0].innerHTML="";
				for(i=0;i<kindSong.length;i++){					
					$("#kind").append(			
						'<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">'+
					    '<div class="item">'+
					      '<div class="pos-rlt">'+
					        '<div class="item-overlay opacity r r-2x bg-black">'+
					          '<div class="center text-center m-t-n" onclick=playMusic('+kindSong[i].s_id+')>'+
					            '<span><i class="fa fa-play-circle i-2x"></i></span>'+
					          '</div>'+
					        '</div>'+
					        '<a href="track-detail"><img src='+kindSong[i].poster+' alt="" class="r r-2x img-full"></a>'+
					      '</div>'+
					      '<div class="padder-v">'+
					       '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis">'+kindSong[i].title+'</a>'+
					        '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">'+kindSong[i].artist+'</a>'+
					      '</div>'+
					    '</div>'+
					  '</div>');		  
				  }
		}
		else{
			$(".list-group-item.active")[0].className="list-group-item";
			this.className = "list-group-item active";			
			kind = this.innerText;
			$("#kt")[0].innerText=kind;
			$.ajax({
				type:"get",
				url:"../song/kind",
				async:false,
				data:{"kind":kind},
				dataType:"json",
				success:function(data){
					kindSong = data.kindSong;
				},
				error:function(data){
					alert("获取数据失败！");
				}
			});	
			$("#kind")[0].innerHTML="";
			for(i=0;i<kindSong.length;i++){				
				$("#kind").append(			
					'<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">'+
				    '<div class="item">'+
				      '<div class="pos-rlt">'+
				        '<div class="item-overlay opacity r r-2x bg-black">'+
				          '<div class="center text-center m-t-n" onclick=playMusic('+kindSong[i].s_id+')>'+
				            '<span><i class="fa fa-play-circle i-2x"></i></span>'+
				          '</div>'+
				        '</div>'+
				        '<a href="track-detail"><img src='+kindSong[i].poster+' alt="" class="r r-2x img-full"></a>'+
				      '</div>'+
				      '<div class="padder-v">'+
				       '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis">'+kindSong[i].title+'</a>'+
				        '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">'+kindSong[i].artist+'</a>'+
				      '</div>'+
				    '</div>'+
				  '</div>');		  
			  }
		}
	});	
	var i;
	  for(i=0;i<kindSong.length;i++){
		$("#kind").append(			
			'<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">'+
		    '<div class="item">'+
		      '<div class="pos-rlt">'+
		        '<div class="item-overlay opacity r r-2x bg-black">'+
		          '<div class="center text-center m-t-n" onclick=playMusic('+kindSong[i].s_id+')>'+
		            '<span><i class="fa fa-play-circle i-2x"></i></span>'+
		          '</div>'+
		        '</div>'+
		        '<a href="track-detail"><img src='+kindSong[i].poster+' alt="" class="r r-2x img-full"></a>'+
		      '</div>'+
		      '<div class="padder-v">'+
		       '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis">'+kindSong[i].title+'</a>'+
		        '<a href="track-detail" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">'+kindSong[i].artist+'</a>'+
		      '</div>'+
		    '</div>'+
		  '</div>');		  
	  }
});