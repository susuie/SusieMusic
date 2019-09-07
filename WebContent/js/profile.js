var info = null;
var user = null;
var username = null;
$(document).ready(function(){
	$.get("getsession",function(data){ 			
		user = data.userid;
		username = data.name;
		getInfoById(user);
		});		
});
function getInfoById(id){
	$.ajax({
		type:"get",
		url:"../info/getInfo",
		async:false,
		dataType:"json",
		data:{"u_id":id},
		success:function(data){
			info = data.infos;
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});	
	var i;
	$("#active")[0].innerHTML="";
	  for(i=0;i<info.length;i++){
		  $("#active").append(				  				 
				  '<li class="list-group-item">'+
                  '<a href="#" class="thumb-sm pull-left m-r-sm">'+
                    '<img src='+info[i].img_url+' class="img-circle">'+
                  '</a>'+
                  '<a href="#" class="clear">'+
                    '<small class="pull-right">'+info[i].i_time+'</small>'+
                    '<strong class="block">'+username+'</strong>'+
                    '<small>'+info[i].info+'</small>'+
                  '</a>'+
                '</li>'
		  );
	  }
}

//积分兑换
function integral(){
	var rep=null;
	$("#integral").modal("show"); //展示当前用户积分
    $.ajax({
    	type: "get",
        url: "../user/userid",
        async: false,
        data: {"u_id": user},
        dataType: "json",
        success: function (result) {        		
        		rep = result.balance;
        		$('#integral .nowIntegral').text(rep);        	         
        },
        error: function (result) {
       	 $("#integral .nowIntegral").text("加载失败！");
        }
    });
    $('#intecharge').unbind('click').bind('click',function(){
    	if(rep>=300){
			$.ajax({				
	         type: "get",
	         url: "../user/chargebalance",
	         async: false,
	         data:{"uid":user},
	         success: function (data) {        	 
	        	 $("#integral").modal("hide"); 
	        	 alert("兑换成功！");
	         },
	         error: function (data) {
	        	 alert("兑换失败请重试!");
	         }
	     });
    	}
    	else{
    		alert("当前积分不足，每300积分可换取5天vip哦！");
    	}
	});
}
//vip充值
function recharge(){
	$("#recharge").modal("show"); //展示充值的模态框
	var value="";
	  var radio=document.getElementsByName("choose");
	  
    $('#ensure').unbind('click').bind('click',function(){  
    	for(var i=0;i<radio.length;i++){
    		if(radio[i].checked==true){
    		  value=radio[i].value;
    		  break;
    		}
    	  }
			$.ajax({				
	         type: "get",
	         url: "../user/vip",
	         async: false,
	         data:{"value":value,"uid":user},
	         success: function (data) {        	 
	        	 $("#recharge").modal("hide"); 
	        	 alert("充值成功！若未及时显示vip特权，请重新登录！");
	         },
	         error: function (data) {
	        	 alert("充值失败请重试!");
	         }
	     });    	
	});
}