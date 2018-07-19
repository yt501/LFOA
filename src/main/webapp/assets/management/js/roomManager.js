/**
 * Created by LFSenior on 2018/1/31.
 */


/*加载content表格数据*/
$(function () {
    InitMainTable();
});

//初始化bootstrap-table的内容
function InitMainTable () {
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/management/findAllRoom.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
			}else{
				toastr.error("查询无数据");
			}
		},
		error : function() {
			toastr.error("查询错误");
		}
	});
}


/**
 * 设置表格添加的方法
 * @param value
 * @param row
 * @returns
 */
function addOption(value,row){
	var option="<button class='btn btn-success btn-xs fa fa-check' onclick='updataApply("+row.id+")'></button>&nbsp;&nbsp;"
	option+="<button class='btn btn-primary btn-xs fa fa-pencil'  onclick='editRoom("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o'  onclick='delRoom("+row.id+")'></button>";
    return option;
}

function updataApply(id){
	debugger;
	/**
	 * 恢复会议室
	 */
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/management/updateRoomApply.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				$('#table').bootstrapTable('load', result.data);
				toastr.success("恢复会议室成功");
			}else{
				toastr.error("恢复失败，请重试");
			}
		},
		error : function() {
			toastr.error("恢复失败，请重试");
		}
	});
}


/**
 * 删除会议室
 * @param id
 * @param contentId
 * @returns
 */
function delRoom(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/management/deleteRoom.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				$('#table').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("删除完成")
			}else{
				toastr.error("删除失败，请重试");
			}
		},
		error : function() {
			toastr.error("删除失败，请重试");
		}
	});
}


/**
 * 编辑用户
 * @param id
 * @param contentId
 * @returns
 */
function editRoom(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/management/findRoomById.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				openCalender("修改会议室")
				$("#eventId").val(result.data[0].id);
				$("#NAME").val(result.data[0].NAME);
				$("#address").val(result.data[0].address);
				$("#eventSign").val(1);
			}else{
				toastr.error("修改失败，请重试");
			}
		},
		error : function() {
			toastr.error("修改失败，请重试");
		}
	});
}





/*添加用户*/
function addUser(){
	//设置添加用户标记
	$("#eventSign").val(0);
	openCalender("增加会议室");
}


/*
 * 根据不同的选择进行不同的操作
 * */
function selRun(){
	var sign=$("#eventSign").val();
	if(sign==0){
		addUserMethod();
	}else{
		editUserMethod();
	}
}


/**
 * 修改用户
 * @returns
 */
function editUserMethod(){
	var name=$("#NAME").val();
	var address=$("#address").val();
	var id=$("#eventId").val();
	//判断数据可靠信
	if(name==null||name.trim()==""){
		toastr.warning("会议室名称不能为空");
		return;
	}
	
	if(address==null||address.trim()==""){
		toastr.warning("会议室地址不能为空");
		return;
	}
	
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"name":name,"address":address,"id":id},
		url : V_PATH + "/management/updataRoom.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
				//关闭窗口
				closeCalender();
			}else{
				toastr.warning("添加失败，请重试");
			}
		},
		error : function() {
			toastr.error("添加失败，请联系服务人员");
		}
	});
}

/**
 * 打开弹窗
 * @param text
 * @param start
 * @param end
 * @returns
 */
function openCalender(text){
    if(text!=null){
        $("#open-calendar .mb").text(text);
    }else{
        return;
    }
    $("#add-warp").css({ "width": $(document).width(), "height": $(document).height() });
    $("#add-warp").show();
    $("#open-calendar").show();
}

/**
 * 关闭弹窗
 * @returns
 */
function closeCalender(){
	//将一系列信息清空
	$("#eventId").val("");
	$("#NAME").val("");
	$("#address").val("");
	$("#add-warp").hide();
    $("#open-calendar").hide();
}

/**
 * 新增用户
 * @returns
 */
function addUserMethod(){
	var NAME=$("#NAME").val();
	var address=$("#address").val();
	//判断数据可靠信
	if(NAME==null||NAME.trim()==""){
		toastr.warning("会议室名称不能为空");
		return;
	}
	
	if(address==null||address.trim()==""){
		toastr.warning("会议室地址不能为空");
		return;
	}
	
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"name":NAME,"address":address},
		url : V_PATH + "/management/createRoom.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
				//关闭窗口
				closeCalender();
			}else{
				toastr.warning("添加失败，请重试");
			}
		},
		error : function() {
			toastr.error("添加失败，请联系服务人员");
		}
	});
}





