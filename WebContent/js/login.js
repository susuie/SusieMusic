/*判断用户名格式是否正确*/
function checkemail(){
	var uuser=false;
	var regemail=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(;\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*$/;
	var femail=document.getElementById("username");
	if(femail.value==""){
		alert("用户名不能为空，请使用邮箱格式！");
	}
	else{
		if(!(femail.value.match(regemail))){			
			alert("邮箱格式输入错误，请检查！");
		}
		else{
			uuser=true;
		}
	}
	return uuser;
}

/*判断密码格式是否正确*/
function checkpwd(){
	var ppwd=false;
	var fpwd=document.getElementById("pwd");
	if(fpwd.value==""||fpwd.value.length<6||fpwd.value.length>20){
		alert("请输入6-20位用户密码！");
	}
	else{
		ppwd=true;
	}
	return ppwd;
}
/**
 * 登陆前对账号密码进行验证，账号密码格式不对.
 */
$(document).ready(function(){
	
	//enter登录
	$("#pwd").bind("keypress",function(event){
		if(event.keyCode=="13"){
			$("#subform").click();
		}
	});	
	$("#subform").click(function(){	
		var usern=$("#username").val();
		var passw=$("#pwd").val();
		if(checkemail()&&checkpwd()){			
			$.ajax({
				url:"susie/loginverify",				
				type: "post",
				async:false,
				data:{"username":usern,"password":passw},
				success:function(result){
					if(result=="success"){
						$("form").submit();
					}
					else{
						alert("用户名或密码错误！");			
					}
				},
				error:function(result){
					alert("用户名或密码错误！");		
				}
			});				
		}else{
			$("#subform").attr("disabled","ture"); 
		}
	});
	$("input").hover(function(){
			$("#subform").removeAttr("disabled");
	});
});
