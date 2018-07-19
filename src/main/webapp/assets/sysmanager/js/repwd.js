function rePwd(){
	var pwd=$("#password").val();
	var rePwd=$("#repassword").val();
	if(pwd==null||pwd.trim()==""){
		toastr.warning("密码不能为空");
		return;
	}
	if(rePwd==null||rePwd.trim()==""){
		toastr.warning("确认密码不能为空");
		return;
	}
	if(pwd!=rePwd){
		toastr.warning("确认密码不同，请重新确定");
		return;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"pwd":pwd},
		url : V_PATH + "/sysmanager/rePwdManager.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				toastr.success("重置密码成功");
			}else{
				toastr.warning("重置密码失败，请重试");
			}
		},
		error : function() {
			toastr.error("重置密码失败，请联系服务人员");
		}
	});
	
}