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
		url : V_PATH + "/authority/findAllRole.json?r="+Math.random(),
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
	var option="<button class='btn btn-primary btn-xs fa fa-pencil'  onclick='editResource("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o'  onclick='delResource("+row.id+")'></button>";
    return option;
}

/**
 * 删除资源数据
 * @param id
 * @returns
 */
function delResource(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/authority/deleteRole.json?r="+Math.random(),
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
 * 编辑资源
 * @param id
 * @param contentId
 * @returns
 */
function editResource(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",  
		data : {"id": id},
		url : V_PATH + "/authority/findRoleById.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功
				$("#eventId").val(result.data.id);
				$("#username").val(result.data.name);
				$("#eventSign").val(1);
				/*设置选中的的资源*/
				var data=result.resources;
				openCalender("修改角色",data);
			}else{
				toastr.error("修改失败，请重试");
				closeCalender();
			}
		},
		error : function() {
			toastr.error("修改失败，请重试");
		}
	});
}



/*添加用户*/
function addResource(){
	//设置添加用户标记
	$("#eventSign").val(0);
	openCalender("增加角色");
}


/*
 * 根据不同的选择进行不同的操作
 * */
function selRun(){
	var sign=$("#eventSign").val();
	if(sign==0){
		addResourceMethod();
	}else{
		editResourceMethod();
	}
}


/**
 * 修改角色
 * @returns
 */
function editResourceMethod(){
	var username=$("#username").val();
	//判断数据可靠信
	if(username==null||username.trim()==""){
		toastr.warning("角色名称 不能为空");
		return;
	}
	var id=$("#eventId").val();
	//获取资源列表ID
	var resources=new Array();
	var temp=$("#resources input[type=checkbox]:checked");
	for(var i=0;i<temp.length;i++){
		resources.push(temp[i].value);
	}
	resources=JSON.stringify(resources);
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"name":username,"resources":resources,"id":id},
		url : V_PATH + "/authority/updateRole.json?r="+Math.random(),
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
function openCalender(text,resources){
	loadResourceu(resources);
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
	$("#username").val("");
	$("#add-warp").hide();
    $("#open-calendar").hide();
}

/**
 * 新增用户
 * @returns
 */
function addResourceMethod(){
	var username=$("#username").val();
	//判断数据可靠信
	if(username==null||username.trim()==""){
		toastr.warning("角色名称 不能为空");
		return;
	}
	//获取资源列表ID
	var resources=new Array();
	var temp=$("#resources input[type=checkbox]:checked");
	for(var i=0;i<temp.length;i++){
		resources.push(temp[i].value);
	}
	resources=JSON.stringify(resources);
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"name":username,"resources":resources},
		url : V_PATH + "/authority/addRole.json?r="+Math.random(),
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
 * 增加类型
 * @param value
 * @param row
 * @returns
 */
function addType(value,row){
	return '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>';
}


/**
 * 加载资源数据
 * @returns
 */
function loadResourceu(resources){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/authority/findAllResourceInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#resources').bootstrapTable('load', result.data);
				for(var i=0;i<resources.length;i++){
					selectCheckBox(resources[i].id);
				}
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
 * 添加资源图标
 * @returns
 */
function addResourceType(value,row){
	if(value=='menu'){
		//目录
		return '<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>';
	}else{
		//资源
		return '<span class="glyphicon glyphicon-file" aria-hidden="true"></span>';
	}
}

/**
 * 添加多选按钮
 */
function addCheckBox(value,row){
	return '<input type="checkbox" value="'+value+'" name="resources">';
}

/**
 * 选中指定value的值
 * @param value
 * @returns
 */
function selectCheckBox(value){
	$("#resources input[value='"+value+"']").attr('checked','true');
}

