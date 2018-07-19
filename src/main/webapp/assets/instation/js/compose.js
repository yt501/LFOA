/*createnaem：LFSenior date:2018-03-03*/

/*加载content表格数据*/
$(function () {
    InitMainTable();
});

/**
 * 初始化函数吗，初始化联系人列表
 * @returns
 */
function InitMainTable () {
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/personoffice/queryAddContent.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadContent(result.data);
			}else{
				toastr.error("未查询到联系人数据");
			}
		},
		error : function() {
			toastr.error("查询错误");
		}
	});
	
	/*查询未读邮件数目*/
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/instation/queryEmailInfoTotal.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$("#countEmail").html(result.total);
			}else{
				$("#countEmail").html(0);
			}
		},
		error : function() {
			$("#countEmail").html(0);
		}
	});
}

/**
 * 加载联系人列表
 * @returns
 */
function loadContent(data){
	//选择用户通讯录中欧冠的联系人
	//加载到对应的地方
	if(data==null||data.length==0){
        return;
    }
    for(var i=0;i<data.length;i++){
        var opt=$("<option value='"+data[i].id+"'>"+data[i].NAME+"</option>");
        $("#content").append(opt);
    }
}


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

