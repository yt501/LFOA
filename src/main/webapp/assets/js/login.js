/*登录相关js文件*/
function loginOA(){
	var username=$("#username").val();
	var password=$("#password").val();
	if(username==null||username.trim()==""){
		toastr.warning("账号不能为空");
		return false;
	}
	if(password==null||password.trim()==""){
		toastr.warning("密码不能为空");
		return false;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/login?r="+Math.random(),
		data : {"username": username,"password": password},
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				window.location.reload();
			}else{
				toastr.error(result.message);
			}
		},
		error : function() {
			toastr.error("系统错误，请联系管理员");
		}
	});
}