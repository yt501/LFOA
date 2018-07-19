/**
 * Created by LFSenior on 2018/1/31.
 */
    /*用户测试数据*/
$(function () {
    $("#console-btn").click(function () {
    	$("#createName").val("");
    	$("#createDate").val("");
    	$("#createContent").val("");
        $("#add-warp").hide();
        $("#open-calendar").hide();
        $("#achiveTask").hide();
        $("#achivePersons").children().remove();
        $("#stateRedio input").removeAttr('checked');
    });
});


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
		url : V_PATH + "/personoffice/queryAllTaskInfo.json?r="+Math.random(),
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
 * 未任务状态添加效果
 * @param value
 * @param row
 * @returns
 */
function taskStateOption(value,row){
	var option1="<span class='badge bg-theme'>完成</span>";
	var option2="<span class='badge bg-info'>一般</span>";
	var option3="<span class='badge bg-warning'>中等</span>";
	var option4="<span class='badge bg-important'>紧急</span>";
	var option5="<span class='badge bg-success'>其他</span>";
	if(row.state==0){
		return option1;
	}else if(row.state==1){
		return option2;
	}else if(row.state==2){
		return option3;
	}else if(row.state==3){
		return option4;
	}else{
		return option5;
	}
	
	
}

/**
 * 添加指定功能
 * @param value
 * @param row
 * @returns
 */
function addOption(value,row){
    var option="<button class='btn btn-success btn-xs fa fa-check'  onclick='compleTask("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-primary btn-xs fa fa-pencil'  onclick='editTask("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o'  onclick='delTask("+row.id+")'></button>";
    return option;
}

/**
 * 完成任务
 * @param id
 * @returns
 */
function compleTask(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/personoffice/updateTaskComple.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				$('#table').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("任务完成")
			}else{
				toastr.error("完成失败，请重试");
			}
		},
		error : function() {
			toastr.error("完成失败，请重试");
		}
	});
}

/**
 * 编辑任务
 * @param id
 * @returns
 */
function editTask(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/personoffice/queryTaskById.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				openCalender("修改任务")
				$("#createName").val(result.data.createname);
				$("#createDate").val(result.data.createdate);
				$("#createContent").val(result.data.content);
				$("#createId").val(result.data.id);
				$("#stateRedio input[value='"+result.data.state+"']").attr('checked','true')
				$("#createState").val(0);
			}else{
				toastr.error("修改失败，请重试");
			}
		},
		error : function() {
			toastr.error("修改失败，请重试");
		}
	});
}





/**
 * 打开编辑框
 * @param text
 * @returns
 */
function openCalender(text){
    if(text!=null){
        $("#open-calendar .mb").text(text);
    }else{
        return;
    }
    $("#add-warp").show();
    $("#open-calendar").show();
}

/**
 * 删除任务
 * @param id
 * @returns
 */
function delTask(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"taskid": id},
		url : V_PATH + "/personoffice/deleteUserTask.json?r="+Math.random(),
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
 * 添加任务按钮
 * @returns
 */
function addTask(){
    openCalender("添加任务");
    //查询通讯，得到当前用户的通讯录信息
    $.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/personoffice/queryAddContent.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				for(var i=0;i<result.data.length;i++){
					apendPerson(result.data[i]);
				}
			}else{
				toastr.error("查询无数据");
			}
		},
		error : function() {
			toastr.error("查询错误");
		}
	});
    //然后加载到页面
    $("#createState").val(1);
    $("#achiveTask").show();
    $("#createName").removeAttr("readonly");
}

function apendPerson(data){
	var option=$("<label class='checkbox-inline'><input type='checkbox' name='person' value='"+data.userid+"'>"+data.NAME+"</label>");
	$("#achivePersons").append(option);
}

/**
 * 确定按钮
 * @returns
 */
function infoSubmit(){
	//表示添加任务
	if($("#createState").val()==1){
		createTaskSubmit();
	}
	
	//表示更新任务
	if($("#createState").val()==0){
		updateTaskSubmit();
	}
}

/**
 * 更新Task提交方法
 * @returns
 */
function updateTaskSubmit(){
	var createDate=$("#createDate").val();
	var content=$("#createContent").val();
	var id=$("#createId").val();
	var state=$("#stateRedio input:checked").val();
	
	if(createDate==null||createDate==""){
		toastr.warning("创建时间不能为空");
		return false;
	}
	if(content==null||content==""){
		toastr.warning("内容给不能为空");
		return false;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id":id,"content":content,"createdate":createDate,"state":state},
		url : V_PATH + "/personoffice/updateTaskInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
				toastr.success("修改成功");
				$("#console-btn").click();
			}else{
				toastr.error("修改失败");
				$("#console-btn").click();
			}
		},
		error : function() {
			toastr.error("修改失败");
			$("#console-btn").click();
		}
	});
}

/**
 * 添加任务
 * @returns
 */
function createTaskSubmit(){
	var createName=$("#createName").val();
	var createDate=$("#createDate").val();
	var content=$("#createContent").val();
	var state=$("#stateRedio input:checked").val();
	var temp=$("#achivePersons input:checked");
	var userids=new Array();
	for(var i=0;i<temp.length;i++){
		userids.push($(temp[i]).val());
	}
	
	
	if(state==null||state==""){
		state='1';
	}
	if(createName==null||createName==""){
		toastr.warning("发布人不能为空");
		return false;
	}
	if(createDate==null||createDate==""){
		toastr.warning("创建时间不能为空");
		return false;
	}
	if(content==null||content==""){
		toastr.warning("内容给不能为空");
		return false;
	}
	
	if(userids==null||userids.length==0){
		toastr.warning("请选择任务接受人");
		return false;
	}
	//转成json格式
	userids=JSON.stringify(userids);
	
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"createname":createName,"content":content,"createdate":createDate,"state":state,"userids":userids},
		url : V_PATH + "/personoffice/insertTask.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
				toastr.success("添加成功");
				$("#console-btn").click();
			}else{
				toastr.error("添加失败");
			}
		},
		error : function() {
			toastr.error("添加失败");
		}
	});
}

/**
 * 根据用户名模糊搜索
 * @returns
 */
function searchByName(){
	var name=$("#contentName").val();
	if(name==null||name==""){
		toastr.warning("发布人不能为空");
		return;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"createname":name},
		url : V_PATH + "/personoffice/queryTaskByName.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载数据
				$('#table').bootstrapTable('load', result.data);
			}else{
				toastr.error("未查询到数据");
			}
		},
		error : function() {
			toastr.error("未查询到数据");
		}
	});
}

/**
 * 打开完成的任务列表
 * @returns
 */
function openTaskList(){
	loadPage(V_PATH+'/personoffice/completask');
}
