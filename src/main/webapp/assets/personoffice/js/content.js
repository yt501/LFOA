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
		url : V_PATH + "/personoffice/queryNotAddContent.json?r="+Math.random(),
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
    var option="<a href='#' onclick='addContent("+row.id+")'>添加到我的联系列表</a>";
    return option;
}

function addContent(id){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"achiveuserid":id},
		url : V_PATH + "/personoffice/addContentToMe.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//添加成功删除当前行
				$('#table').bootstrapTable('remove', {
			        field: 'id',
			        values:[""+id+""]
			    });
				toastr.success("添加成功");
			}else{
				toastr.error("添加失败，请重试");
			}
		},
		error : function() {
			toastr.error("添加失败，请重试");
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
		data:{"depater":dept,"NAME":name,"phone":phone},
		url : V_PATH + "/personoffice/queryNotAddContentByParam.json?r="+Math.random(),
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


function queryMyContent(){
	loadPage(V_PATH+'/personoffice/contenttome');
}



