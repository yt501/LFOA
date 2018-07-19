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
		url : V_PATH + "/personoffice/queryAllCompleTaskInfo.json?r="+Math.random(),
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
    var option="<button class='btn btn-primary btn-xs fa fa-pencil'  onclick='editTask("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o'  onclick='delTask("+row.id+")'></button>";
    return option;
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
	if(typeof(state)=="undefined"||state==null||state==""){
		toastr.warning("必须指定任务状态");
		return false;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id":id,"content":content,"createdate":createDate,"state":state},
		url : V_PATH + "/personoffice/updateCompleTaskInfo.json?r="+Math.random(),
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

function openTask(){
	loadPage(V_PATH+'/personoffice/task');
}
