/**
 * Created by wings on 2018/2/11.
 */
/*初始化日历组建*/
$(function () {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        dayNamesShort: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        today: ["今天"],
        firstDay: 1,
        buttonText: {
            today: '本月',
            month: '月',
            week: '周',
            day: '日',
            prev: '上一月',
            next: '下一月'
        },
//        defaultDate: '2016-09-12',//不写默认是当前月份
        lang: 'zh-cn',
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectHelper: true,
        select: function (start, end) {
        	//设置标识"eventSign"0表示新增，1表示修改
        	$("#eventSign").val(0);
            openCalender("新增数据"
            		,$.fullCalendar.formatDate(start,'YYYY-MM-DD hh:mm:ss')
            		,$.fullCalendar.formatDate(end,'YYYY-MM-DD hh:mm:ss'));
            /*if (true) {
                eventData = {
                    title: title,
                    start: start,
                    end: end
                };
                $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
            }
            $('#calendar').fullCalendar('unselect');*/


        },
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        eventClick: function (event, jsEvent, view) {

            // event.source.events[0].title = '222223333'
            // 修改数据
            // 标题
            $("#eventContent").val(event.title);
          //设置标识"eventSign"0表示新增，1表示修改
        	$("#eventSign").val(1);
        	//设置编辑的id
        	$("#eventId").val(event.id);
            openCalender("修改数据"
            		,$.fullCalendar.formatDate(event.start,'YYYY-MM-DD hh:mm:ss')
            		,$.fullCalendar.formatDate(event.end,'YYYY-MM-DD hh:mm:ss'));
            $("#delButton").show();
            //  弹出框
        },
        events: function(start,end,timezone, callback){
        	$.ajax({
        		async : true,
        		cache: false,	
        		type : "post",
        		dataType : "json",   
        		data:{"start":$.fullCalendar.formatDate(start,'YYYY-MM-DD'),"end":$.fullCalendar.formatDate(end,'YYYY-MM-DD')},
        		url : V_PATH + "/personoffice/findCalendar.json?r="+Math.random(),
        		success : function(result, textStatus) {
        			if(result.state=="T"){
        				//成功重新加载页面
        				callback(result.data);
        			}else{
        				toastr.warning("查询无数据");
        			}
        		},
        		error : function() {
        			toastr.error("查询错误");
        		}
        	});
        }
    });
});



/**
 * 打开弹窗
 * @param text
 * @param start
 * @param end
 * @returns
 */
function openCalender(text,start,end){
    if(text!=null){
        $("#open-calendar .mb").text(text);
    }else{
        return;
    }
    if(start!=null){
        $("#start").val(start);
    }
    if(end!=null){
        $("#end").val(end);
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
	//清楚日期和内容
	$("#end").val("");
	$("#start").val("");
	$("#eventContent").val("");
	$("#add-warp").hide();
    $("#open-calendar").hide();
    $("#delButton").hide();
}

/*
 * 根据不同的选择进行不同的操作
 * */
function selRun(){
	var sign=$("#eventSign").val();
	if(sign==0){
		addCalendarData();
	}else{
		editCalendarData();
	}
}

/**
 * 新增数据
 * @returns
 */
function addCalendarData(){
	var end=$("#end").val();
	var start=$("#start").val();
	var content=$("#eventContent").val();
	//判断数据可靠信
	if(content==null||content.trim()==""){
		toastr.warning("内容不能为空");
		return;
	}
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"content":content,"start":start,"end":end},
		url : V_PATH + "/personoffice/addCalendar.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#calendar').fullCalendar( 'refetchEvents');
			}else{
				toastr.warning("添加失败，请重试");
			}
		},
		error : function() {
			toastr.error("添加失败，请联系服务人员");
		}
	});
	//关闭窗口
	closeCalender();
}


/**
 * 修改日期数据
 */
function editCalendarData(event){
	var end=$("#end").val();
	var start=$("#start").val();
	var content=$("#eventContent").val();
	//判断数据可靠信
	if(content==null||content.trim()==""){
		toastr.warning("内容不能为空");
		return;
	}
	//表示可以执行添加了
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"content":content,"start":start,"end":end,"id":$("#eventId").val()},
		url : V_PATH + "/personoffice/updateCalendar.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#calendar').fullCalendar( 'refetchEvents');
			}else{
				toastr.warning("修改失败，请重试");
			}
		},
		error : function() {
			toastr.error("修改失败，请联系服务人员");
		}
	});
	//关闭窗口
	closeCalender();
}

/**
 * 刷新页面数据
 * @returns
 */
function flushCalendar() {
	$('#calendar').fullCalendar( 'refetchEvents');
}

/**
 * 删除calendar的信息
 * @returns
 */
function delCalendar(){
	//执行添加
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		data:{"id":$("#eventId").val()},
		url : V_PATH + "/personoffice/deleteCalendar.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				$('#calendar').fullCalendar( 'refetchEvents');
			}else{
				toastr.warning("删除失败，请重试");
			}
		},
		error : function() {
			toastr.error("删除失败，请联系服务人员");
		}
	});
	//关闭窗口
	closeCalender();
}