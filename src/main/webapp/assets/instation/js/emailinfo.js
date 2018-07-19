/**
 * 发送邮件
 * @returns
 */
function sendEmail(){
	//判断相关内容是否符合要求
	var content=$("#content").val();
	var subject=$("#subject").val();
	var message=$("#message").val();
	if(content==null||content.trim==""||content=="-1"){
		toastr.error("请选择收件人");
		return;
	}
	if(subject==null||subject.trim()==""){
		toastr.error("邮件主题不能为空");
		return;
	}
	if(message==null||message.trim()==""){
		toastr.error("邮件内容不能为空");
		return;
	}
	var descripter=message.substr(0,20);
	subject="回复邮件："+subject;
	/*发送邮件*/
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"title":subject,"descripter":descripter,"content":message,"achiveUserId":content},
		url : V_PATH + "/instation/addNewEmailInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadPage(V_PATH+'/instation/inbox');
			}else{
				toastr.error("发送错误请重试");
			}
		},
		error : function() {
			toastr.error("发送错误请重试");
		}
	});
}