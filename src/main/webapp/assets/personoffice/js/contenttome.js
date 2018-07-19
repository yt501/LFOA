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
		url : V_PATH + "/personoffice/queryAddContent.json?r="+Math.random(),
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
    var option="<a href='#' onclick='delContent("+row.id+")'>从我的通讯录删除</a>";
    return option;
}

function delContent(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"achiveuserid":id},
		url : V_PATH + "/personoffice/deleteContentToMe.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//添加成功删除当前行
				$('#table').bootstrapTable('remove', {
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

/**
 * 跳转到通讯录
 * @returns
 */
function gotoContent(){
	loadPage(V_PATH+'/personoffice/content');
}
