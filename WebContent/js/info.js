var info = null;
var username = "";
$(document).ready(function(){
	getAll();
});
function getAll(){
	$.ajax({
		type:"get",
		url:"../info/list",
		async:false,
		dataType:"json",
		success:function(data){
			info = data.infos;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	var i;
	$("#masonry")[0].innerHTML="";
	  for(i=0;i<info.length;i++){
		  $("#masonry").append(				  				 
				  '<div class="item">'+
				  		'<div class="item-overlay opacity animated fadeInDown wrapper bg-info">'+
                  		'<p class="text-white">By '+info[i].nickName+'</p>'+                   
                  		'</div>'+	  	               
	                   '<div class="bottom gd bg-info wrapper">'+
	                       '<div class="m-t m-b"><a href="JavaScript:;" class="b-b b-danger h4 text-u-c text-lt">'+info[i].i_time+'</a></div>'+
	                        '<p class="hidden-xs">'+info[i].info+'</p>'+
	                        '<div data-toggle="class" class="pull-right" onclick="answer('+info[i].i_id+')">'+
	                   			'<i class="fa fa-comment-o"></i>'+
	                   		'</div>'+
	                   '</div>'+	                   
	                    '<a href="javascript:;"><img src='+info[i].img_url+' alt="" class="img-full"></a>'+	                                               
	              '</div>'
		  );
	  }
}
function putNode(){
	$("#new_content").val("");
	$('#addTopic').modal("show");			
	$('#newbuild').unbind('click').bind('click',function(){
		var id = null;
		var img = null;		
		var t = $("#new_content").val();
		$.get("getsession",function(data){
			id = data.userid;
			if(id>22){
				img=id%22;
			}else{
				img=id;
			}
			$.ajax({				
		         type: "get",
		         url: "../info/addInfo",
		         async: false,
		         data: {"u_id":id,"info":$("#new_content").val(),"img_url":"../image/m"+img+".jpg"},
		         //dataType: "json",
		         success: function (result) {        	
		        	 $("#addTopic").modal("hide"); 
		        	 alert("新建成功！");	
		        	 document.location.reload();
		         },
		         error: function (result) {
		        	 $("#new_content").val("新建失败，请重试！");
		         }
		     });    
	});
			
	});	
}
var replay = null;
function answer(id){
	$("#m_replies").val("");//清空回复输入
	$("#MyReplies").modal("show"); //展示回复的模态框
    //var username = null;
    var uid = null;
    $.get("getsession",function(data){
    	//username=data.name;
    	uid = data.userid;
    });
    $.ajax({
    	type: "get",
        url: "../info/getReplay",
        async: false,
        data: {"i_id": id},
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
        		    date = new Date(replay[j].r_time); // 获取时间戳        		    
        		    formatDateTime = date.toLocaleString();
        			r += "发表评论人："+replay[j].nickName+"时间："+formatDateTime+"<br/>"+
    				"内容："+replay[j].r_detailes+"<br/>";               			
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
         url: "../info/addReplay",
         async: false,
         data: {"i_id": id,"r_detailes":$("#m_replies").val(),"u_id":uid},       
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