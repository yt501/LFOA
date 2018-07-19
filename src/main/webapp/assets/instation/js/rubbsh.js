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
		url : V_PATH + "/instation/queryRubbshEmail.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#emailList').bootstrapTable('load', result.data);
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
 * 从回收站恢复邮件
 * @param id
 * @returns
 */
function findContent(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"id":id},
		url : V_PATH + "/instation/updateRubbshEmailRecover.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#emailList').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("恢复成功");
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
		data:{"emailid":id},
		url : V_PATH + "/instation/deleteEmalForRubbsh.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#emailList').bootstrapTable('remove', {
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