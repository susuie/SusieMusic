/*
 * 实现后台页面间跳转的js
 */
var username = null;
var role = null;
$(document).ready(function(){	
	$.get("getsession",function(data){ 			
	username=data.name;
	user = data.userid;
	role = data.role;
	authority();
	});		
});
function authority(){
	var manage = document.getElementById("uumanage");
	var event = document.getElementById("event");
	if(role=="vip用户"){
		manage.style.display = "none";
	}
	else if(role=="用户"){
		manage.style.display = "none";
		event.style.display = "none";
	}
}
function turnlogin(){
	window.location.href="./login?username="+username;
}
//点击设置弹出模态框可修改基本信息
function setting(){
	$("#myModalLabel").text("修改");
	$.get("getsession",function(data){
		var name = data.name;
		var role = data.role;
    $("#username").val(data.name);
    $("#nickname").val(data.nickName);
    $("#sex").val(data.usex);
    $("#password").val(data.password);   
	$('#changeInfo').modal("show");
	$('#save').unbind('click').bind('click',function(){
		$.ajax({
	        url:"../user/update",
	        data: {
	            "name": name,
	            "nickName": $("#nickname").val(),
	            "usex":$("sex").val(),
	            "password": $("#password").val(),	                   
	        }, success: function (result) {
	        	alert("修改信息成功！");
	            $("#changeInfo").modal("hide"); 
	            window.location.href = "../signin.html";
	        	}      
	    	});
		});
	});
}