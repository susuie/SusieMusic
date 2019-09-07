/**
 * 点击注册，若注册成功则跳转到登录页面
 */	
var signup=false;
function adduser(){
	var email = $("#email").val();
	var pwd = $("#pwd").val();
	var nickName = $("#nickname").val();
	 if(email!=""&&pwd!=""){
		$.ajax({
			type:"post",
			url:"user/addUser",
			async:false,
			data:{
				"name":email,
				"password":pwd,
				"nickName":nickName,
				"balance":"0",
				"time":5,
				"role":"用户",
				"type":"大众",
				"usex":"男",
				"umg":"../image/logo2.png",
				"userIsOnline":0,
			},
			success:function(result){
				alert("恭喜您，注册成功！"); 
				signup=true;
				return true;
			},error:function(result){
				alert("注册失败，请重试！");
			}
			});
	}
	 else{
		 alert("用户名和密码不能为空！");
	 }
	if(signup){
		window.location.href="signin.html";
	}	
}

