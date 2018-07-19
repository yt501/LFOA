function showRoomInfo(id,name,address){
    $(".roomid").val(id);
    $(".inputroomName").text(name);
    $(".inputroomAddress").text(address);
    $("#roomInfo").show();
}

function submitApply(){
	var id=$(".roomid").val();
	var statetime=$("#start").val();
	var endtime=$("#end").val();
	var content=$("#content").val();
	if(content==null||content==""){
		toastr.warning("会议内容不能为空");
		return false;
	}
	if(statetime==null||statetime==""||endtime==null||endtime==""){
		toastr.warning("开始和结束时间不能为空");
		return false;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/management/updateBoardroomApply.json?r="+Math.random(),
		data : {"id": id,"statetime": statetime,"endtime": endtime,"content":content},
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadPage(V_PATH+"/management/roomApplication");
			}else{
				toastr.error("申请异常请查看是否已被申请");
				loadPage(V_PATH+"/management/roomApplication");
			}
		},
		error : function() {
			toastr.error("申请错误");
			loadPage(V_PATH+"/management/roomApplication");
		}
	});
	return false;
}