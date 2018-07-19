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
		data:{"select":0},
		url : V_PATH + "/instation/queryEmailInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#todyList').bootstrapTable('load', result.data);
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
		data:{"select":1},
		url : V_PATH + "/instation/queryEmailInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#olderList').bootstrapTable('load', result.data);
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
    var option="<button class='btn btn-success btn-xs fa fa-check' onclick='findContent("+row.id+")'></button>&nbsp;&nbsp;";
    option+="<button class='btn btn-danger btn-xs fa fa-trash-o' onclick='deleteRow("+row.id+")'></button>";
    return option;
}


/**
 * 增加邮件状态
 * @param value
 * @param row
 * @returns
 */
function addState(value,row){
	var option1="<span class='badge bg-theme'>已读</span>";
	var option2="<span class='badge bg-important'>未读</span>";
	if(row.state==0){
		return option2;
	}else{
		return option1;
	}
}

/**
 * 查看邮件详细信息
 * @param id
 * @returns
 */
function findContent(id){
	loadPage(V_PATH+'/instation/emailinfo?id='+id);
}

/**
 * 删除邮件
 * @param id
 * @returns
 */
function deleteRow(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"id":id},
		url : V_PATH + "/instation/deleteAchiveEmail.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#todyList').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				$('#olderList').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("删除成功");
			}else{
				toastr.error("删除失败，请重试");
			}
		},
		error : function() {
			toastr.error("删除失败，请重试");
		}
	});
}

/*跳转到详情页面*/
function addToInfo(value,row){
	var url=V_PATH+"/instation/emailinfo?id="+row.id;
	url='"'+url+'"';
	var opt="<a href='#' onclick='loadPage("+url+")'>"+row.title+"</a>";
	return opt;
}