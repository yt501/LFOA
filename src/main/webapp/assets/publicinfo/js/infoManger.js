/**
 * Created by LFSenior on 2018/1/31.
 */
    /*用户测试数据*/
$(function () {
    $("#console-btn").click(function () {
    	$("#createTitle").val("");
    	$("#createDate").val("");
    	$("#createContent").val("");
        $("#add-warp").hide();
        $("#open-calendar").hide();
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
		url : V_PATH + "/publicinfo/queryAllInfo.json?r="+Math.random(),
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
 * 添加指定功能
 * @param value
 * @param row
 * @returns
 */
function addOption(value,row){
    var option="<a href='#' onclick='updateContent("+row.id+")'>修改 </a>";
    option+="<a href='#' onclick='deleteRow("+row.id+")'> 删除</a>";
    return option;
}

/**
 * 更新内容
 * @param id
 * @returns
 */
function updateContent(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/publicinfo/queryInfoById.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				openCalender("修改消息")
				$("#createTitle").val(result.data.title);
				$("#createDate").val(result.data.createdate);
				$("#createContent").val(result.data.content);
				$("#createId").val(result.data.id);
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

function deleteRow(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/publicinfo/deleteInfoById.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("删除成功");
			}else{
				toastr.error("删除失败");
			}
		},
		error : function() {
			toastr.error("删除失败");
		}
	});
}

/*测试用方法*/
function addContent(id){
    $('#table').bootstrapTable('remove', {
        field: 'id',
        values:"'"+id+"'"
    });
    //alert(id);
}

/**
 * 点击添加消息按钮
 * @returns
 */
function addInfo(){
    openCalender("添加消息");
    $("#createState").val(1);
}


function openCalender(text){
    if(text!=null){
        $("#open-calendar .mb").text(text);
    }else{
        return;
    }
    $("#add-warp").show();
    $("#open-calendar").show();
}

function infoSubmit(){
	//表示添加消息
	if($("#createState").val()==1){
		createInfoSubmit();
	}
	
	//表示更新消息
	if($("#createState").val()==0){
		updateInfoSubmit();
	}
}

/**
 * 更新消息
 * @returns
 */
function updateInfoSubmit(){
	var title=$("#createTitle").val();
	var createDate=$("#createDate").val();
	var content=$("#createContent").val();
	var id=$("#createId").val();
	if(title==null||title==""){
		toastr.warning("标题不能为空");
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
	
	var descripter=content.substr(0,20);
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id":id,"title":title,"createdate":createDate,"content":content,"descripter":descripter},
		url : V_PATH + "/publicinfo/updateInfo.json?r="+Math.random(),
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
 * 创建新消息
 * @returns
 */
function createInfoSubmit(){
	var title=$("#createTitle").val();
	var createDate=$("#createDate").val();
	var content=$("#createContent").val();
	if(title==null||title==""){
		toastr.warning("标题不能为空");
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
	
	var descripter=content.substr(0,20);
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"title":title,"createdate":createDate,"content":content,"descripter":descripter},
		url : V_PATH + "/publicinfo/addInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#table').bootstrapTable('load', result.data);
				toastr.success("增加成功");
				$("#console-btn").click();
			}else{
				toastr.error("增加失败");
				$("#console-btn").click();
			}
		},
		error : function() {
			toastr.error("增加失败");
			$("#console-btn").click();
		}
	});
}


/**
 * 搜索相关内容
 * @returns
 */
function searchByName(){
	var name=$("#contentName").val();
	if(name==null||name==""){
		toastr.warning("作者不能为空");
		return;
	}
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"createname":name},
		url : V_PATH + "/publicinfo/queryInfoByAuthor.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
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