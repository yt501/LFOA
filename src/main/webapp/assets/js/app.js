/**
 * Created by Administrator on 2018/1/9.
 */
/*侧边栏隐藏*/
$(function() {
    $('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
        classExpand: 'dcjq-current-parent'
    });
});
//    sidebar toggle

$(function() {
    function responsiveView() {
        var wSize = $(window).width();
        if (wSize <= 768) {
            $('#container').addClass('sidebar-close');
            $('#sidebar > ul').hide();
        }

        if (wSize > 768) {
            $('#container').removeClass('sidebar-close');
            $('#sidebar > ul').show();
        }
    }
    $(window).on('load', responsiveView);
    $(window).on('resize', responsiveView);
    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });
    
    initMethod();
});



/*出事话设计*/
function initMethod(){
	/**
	 * 初始化界面任务
	 */
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/personoffice/queryAllTaskInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				if(result.data.length>5){
					loadTask(result.data,result.length,5);
				}else{
					loadTask(result.data,result.data.length,result.data.length);
				}
			}else{
				toastr.error("查询无数据");
			}
		},
		error : function() {
			toastr.error("查询错误");
		}
	});
	
	/**
	 * 初始化界面任务
	 */
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
				if(result.data.length>5){
					loadEmail(result.data,result.data.length,5);
				}else{
					loadEmail(result.data,result.data.length,result.data.length);
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


function loadEmail(data,total,len){
	$("#emailCount1").html(total);
	$("#emailCount2").html(total);
	$().html();
	if(data==null||data.length==0){
        return;
    }
	for(var i=0;i<len;i++){
		$("#emailIndexList").append(getOptionEmail(data[i].createdate,data[i].title,data[i].createname));
	}
	
}

function loadTask(data,total,len){
	$("#taskCount1").html(total);
	$("#taskCount2").html(total);
	if(data==null||data.length==0){
        return;
    }
	for(var i=0;i<len;i++){
		$("#taskIndexList").append(getOptionTask(data[i].content,data[i].state));
	}
}

function getOptionTask(title,state){
	var percent="";
	if(state==1){
		percent="40%";
	}else if(state==2){
		percent="60%";
	}else if(state==3){
		percent="70%";
	}else{
		percent="72%";
	}
	title=title.substr(0,6);
	var option="<li>";
	var url=V_PATH+"/personoffice/task";
	url='"'+url+'"';
	option+="<a href='#' onclick='loadPage("+url+")'>";
	option+="<div class='task-info'>";
	option+="<div class='desc'>"+title+"</div>";
	option+="<div class='percent'>"+percent+"</div>";
	option+="</div>";
	option+="<div class='progress progress-striped'>";
	option+="<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: "+percent+"'>";
	option+="<span class='sr-only'>"+percent+" Complete (success)</span>";
	option+="</div>";
	option+="</div>";
	option+="</a>";
	option+="</li>";
	return option;
}


/**
 * 获得邮件模板
 * @param data
 * @param message
 * @returns
 */
function getOptionEmail(date,message,createname){
	message=message.substr(0,6);
	var option="<li>";
	var url=V_PATH+"/instation/inbox";
	url='"'+url+'"';
	option+="<a href='#'  onclick='loadPage("+url+")'>";
	option+="<span class='photo'><img alt='avatar' src='"+V_PATH+"/assets/dashgumfree/img/ui-danro.jpg'></span>";
	option+="<span class='subject'>";
	option+="<span class='from'>"+createname+"</span>";
	option+="<span class='time'>"+date+"</span>";
	option+="</span>";
	option+="<span class='message'>"+message;
	option+="</span>";
	option+="</a>";
	option+="</li>";
	return option;
}

