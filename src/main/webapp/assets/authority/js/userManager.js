/**
 * Created by LFSenior on 2018/1/31.
 */


/*加载content表格数据*/
$(function () {
    InitMainTable();
});

//初始化bootstrap-table的内容
function InitMainTable () {
	debugger;
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/authority/findAllUserInfo.json?r="+Math.random(),
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
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/authority/findAllDept.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadDept(result.data);
			}else{
			}
		}
	});
	
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/authority/findAllRole.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadRoole(result.data);
			}else{
			}
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
	var option="<button class='btn btn-primary btn-xs fa fa-pencil'  onclick='editUser("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o'  onclick='delUser("+row.id+","+row.contentId+")'></button>";
    return option;
}

/**
 * 删除用户数据
 * @param id
 * @param contentId
 * @returns
 */
function delUser(id,contentId){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id,"contentId":contentId},
		url : V_PATH + "/authority/deleteUser.json?r="+Math.random(),
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
function editUser(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/authority/findAllUserInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				openCalender("修改任务")
				$("#eventId").val(result.data[0].id);
				$("#contentId").val(result.data[0].contentId);
				$("#username").val(result.data[0].username);
				$("#usercode").val(result.data[0].usercode);
				$("#tel").val(result.data[0].phone);
				$("#age").val(result.data[0].age);
				$("#depts").val(result.data[0].dept);
				$("#role").val(result.data[0].role);
				$("#roleId").val(result.data[0].roleId);
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


/*加载部门测试*/
function loadDept(data){
    if(data==null||data.length==0){
        return;
    }
    for(var i=0;i<data.length;i++){
        var opt=$("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
        $("#dept").append(opt);
        $("#depts").append(opt);
    }
}

/**
 * 搜索联系人
 * @returns
 */
function searchContent(){
	var dept=$("#dept").val();
	var name=$("#contentName").val();
	var phone=$("#contentNumber").val();
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"depater":dept,"username":name,"phone":phone},
		url : V_PATH + "/authority/searchUser.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				$('#table').bootstrapTable('load', result.data);
			}else{
				toastr.error("未查询到数据，请重试");
			}
		},
		error : function() {
			toastr.error("未查询到数据，请重试");
		}
	});
}


/*添加用户*/
function addUser(){
	//设置添加用户标记
	$("#eventSign").val(0);
	openCalender("增加用户");
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
	var username=$("#username").val();
	var usercode=$("#usercode").val();
	var tel=$("#tel").val();
	var dept=$("#depts").val();
	var role=$("#role").val();
	var age=$("#age").val();
	var id=$("#eventId").val();
	var roleId=$("#roleId").val();
	var contentId=$("#contentId").val();
	//判断数据可靠信
	if(username==null||username.trim()==""){
		toastr.warning("用户名不能为空");
		return;
	}
	
	if(usercode==null||usercode.trim()==""){
		toastr.warning("用户邮箱不能为空");
		return;
	}
	
	if(tel==null||tel.trim()==""){
		toastr.warning("用户电话不能为空");
		return;
	}
	
	if(age==null||age.trim()==""){
		toastr.warning("年龄不能为空且必须为整数");
		return;
	}
	
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"username":username,"usercode":usercode,"tel":tel,"dept":dept,"role":role,"age":age,"id":id,"contentId":contentId,"roleId":roleId},
		url : V_PATH + "/authority/editUser.json?r="+Math.random(),
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
	$("#contentId").val("");
	$("#username").val("");
	$("#usercode").val("");
	$("#tel").val("");
	$("#age").val("");
	$("#eventContent").val("");
	$("#add-warp").hide();
    $("#open-calendar").hide();
}

/**
 * 新增用户
 * @returns
 */
function addUserMethod(){
	var username=$("#username").val();
	var usercode=$("#usercode").val();
	var tel=$("#tel").val();
	var dept=$("#depts").val();
	var role=$("#role").val();
	var age=$("#age").val();
	//判断数据可靠信
	if(username==null||username.trim()==""){
		toastr.warning("用户名不能为空");
		return;
	}
	
	if(usercode==null||usercode.trim()==""){
		toastr.warning("用户邮箱不能为空");
		return;
	}
	
	if(tel==null||tel.trim()==""){
		toastr.warning("用户电话不能为空");
		return;
	}
	
	if(age==null||age.trim()==""){
		toastr.warning("年龄不能为空且必须为整数");
		return;
	}
	
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"username":username,"usercode":usercode,"tel":tel,"dept":dept,"role":role,"age":age},
		url : V_PATH + "/authority/addUser.json?r="+Math.random(),
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
 * 加载角色
 * @param data
 * @returns
 */
function loadRoole(data){
	if(data==null||data.length==0){
        return;
    }
    for(var i=0;i<data.length;i++){
        var opt=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
        $("#role").append(opt);
    }
}




