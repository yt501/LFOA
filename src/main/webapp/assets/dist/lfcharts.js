/**
 * Created by LFSenior on 2018/5/3.
 */

function loadAllInfo(){
	loadClickNumber();
    loadTaskNumber();
    loadUserOption();
    loadUserState();
    loadOaSource();
    /* 用户成员统计 */
    loadUserNumber();
    loadNotice();
    loadInfo();
}

/**
 * 加载第一个图像（用户登录总量）最近七天的
 */
function loadClickNumber(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadClickNumber.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadClickNumberImp(result.xAxisData,result.xContentData);
			}else{
				toastr.error("用户登录量图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户登录量图表显示错误，请联系管理员");
		}
	});
}

function loadClickNumberImp(xAxisData,xContentData){
	var myChart = echarts.init(document.getElementById('clickNumber'));
    var option = {
        title: {
            text: "登录量图表",
            x: "center"
        },
        tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c}"
        },
        legend: {
            x: 'left',
            data: ["登录量"]
        },
        xAxis: [
            {
                type: "category",
                name: "x",
                splitLine: {show: false},
                data: xAxisData
            }
        ],
        yAxis: [
            {
                type: "log",
                name: "y"
            }
        ],
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: true
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        series: [
            {
                name: "登录量",
                type: "line",
                data: xContentData

            }
        ]
    };
    myChart.setOption(option);
}


/**
 * 加载任务
 */
function loadTaskNumber(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadTaskNumber.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadTaskNumberImp(result.data);
			}else{
				toastr.error("用户任务图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户任务图表显示错误，请联系管理员");
		}
	});
    
}

function loadTaskNumberImp(data){
	var myChart = echarts.init(document.getElementById('taskNumber'));
    var legendData=['已完成任务','未完成任务'];
    var taskValue=[data[0]['yes_value'],data[0]['no_value']];
    var seriesData=[
        {value:taskValue[0], name:'已完成任务'},
        {value:taskValue[1], name:'未完成任务'}
    ];
    var option = {
        title : {
            text: '任务完成量',
            subtext: '个人任务统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:legendData
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'任务完成情况',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesData
            }
        ]
    };
    myChart.setOption(option);
}

/**
 * 用户操作分布,相关的功能使用分布
 * 当前用户和总的用户使用
 */
function loadUserOption(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadUserOption.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadUserOptionImp(result.userOption,result.allOption);
			}else{
				toastr.error("用户操作图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户操作图表显示错误，请联系管理员");
		}
	});
}

function loadUserOptionImp(userOption,allOption){
	var myChart = echarts.init(document.getElementById('userOption'));
    var OPTION_MAX=500;
    var DATA_NAME=['当前用户（Current User）','所有用户（All User）'];
    var option = {
            title : {
                text: '用户操作分布',
                subtext: 'LFOA统计'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                y : 'bottom',
                data:DATA_NAME
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            polar : [
                {
                    indicator : [
                        { text: '个人办公（Personoffice）', max: OPTION_MAX},
                        { text: '公共消息（Publicinfo）', max: OPTION_MAX},
                        { text: '权限管理（Authority）', max: OPTION_MAX},
                        { text: '站内通信（Instation）', max: OPTION_MAX},
                        { text: '系统管理（Sysmanager）', max: OPTION_MAX},
                        { text: '行政管理（Management）', max: OPTION_MAX}
                    ]
                }
            ],
            calculable : true,
            series : [
                {
                    name: '当前用户 vs 所有用户（Current vs All）',
                    type: 'radar',
                    data : [
                        {
                            value : userOption,
                            name : DATA_NAME[0]
                        },
                        {
                            value : allOption,
                            name : DATA_NAME[1]
                        }
                    ]
                }
            ]
        };
    myChart.setOption(option);
}

/**
 * 加载用户满意度报告
 */
function loadUserState(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadUserState.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadUserStateImp(result.data);
			}else{
				toastr.error("用户满意度图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户满意度图表显示错误，请联系管理员");
		}
	});
}

function loadUserStateImp(stateData){
    var myChart = echarts.init(document.getElementById('userStateOption'));
    var option = {
        title : {
            text: '系统满意度报告',
            x:'center',
            textStyle : {
	           fontSize : '15',
	           fontWeight : 'bold'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'满意度',
                type:'pie',
                radius : ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '15',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:stateData
            }
        ]
    };
    myChart.setOption(option);
}


/**
 * 加载系统访问来源
 */
function loadOaSource(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadOaSource.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadOaSourceImp(result.data);
			}else{
				toastr.error("用户访问来源图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户访问来源图表显示错误，请联系管理员");
		}
	});
}

function loadOaSourceImp(sourceData){
	var myChart = echarts.init(document.getElementById('userOASource'));
    var option = {
    	    title : {
    	        text: '访问来源统计',
    	        x:'center',
    	        textStyle : {
		           fontSize : '15',
		           fontWeight : 'bold'
	            }
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    calculable : true,
    	    series : [
    	        {
    	            name:'访问来源',
    	            type:'pie',
    	            radius : '55%',
    	            center: ['50%', '60%'],
    	            data:sourceData
    	        }
    	    ]
    	};
    myChart.setOption(option);
}


/**
 * 加载用户统计相关
 * @returns
 */
function loadUserNumber(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadUserNumber.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				loadUserNumberImp(result.xData,result.yData);
			}else{
				toastr.error("用户统计图表显示错误");
			}
		},
		error : function() {
			toastr.error("用户统计图表显示错误，请联系管理员");
		}
	});
}


function loadUserNumberImp(xData,yData){
	var myChart = echarts.init(document.getElementById('userNumber'));
	var option = {
	    title: {
	        x: 'center',
	        text: '系统用户类别统计',
	        textStyle : {
	           fontSize : '15',
	           fontWeight : 'bold'
            }
	    },
	    tooltip: {
	        trigger: 'item'
	    },
	    calculable: true,
	    grid: {
	        borderWidth: 0,
	        y: 80,
	        y2: 60
	    },
	    xAxis: [
	        {
	            type: 'category',
	            show: false,
	            data: xData
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            show: false
	        }
	    ],
	    series: [
	        {
	            name: '用户数数统计',
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: function(params) {
	                        // build a color map as your need.
	                        var colorList = [
	                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
	                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
	                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
	                        ];
	                        return colorList[params.dataIndex]
	                    },
	                    label: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{b}\n{c}'
	                    }
	                }
	            },
	            data: yData
	        }
	    ]
	};
    myChart.setOption(option);
}








/**********===========通知与消息的加载============************/

/**
 * 加载通知相关
 */
function loadNotice(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadNewNotice.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				for(var i=0;i<result.data.length;i++){
					$("#noticeations").append(getNoticeDataOption(result.data[i]));
				}
			}else{
				toastr.error("获取通知错误");
			}
		},
		error : function() {
			toastr.error("获取通知错误，请联系管理员");
		}
	});
}

/**
 * 根据data获取页面数据
 */
function getNoticeDataOption(data){
	var option="";
	option+="<div class='desc'>";
	option+="<div class='thumb'>";
	option+="<span class='badge bg-theme'><i class='fa fa-clock-o'></i></span>";
	option+="</div>";
	option+="<div class='details'>";
	option+="<p><muted>"+data.createdate+"</muted><br/>";
	option+="<a href='javascript:;' onclick='viewNotice("+JSON.stringify(data)+")'>"+data.createname+"</a>"+data.descripter+"<br/>";
	option+="</p>";
	option+="</div>";
	option+="</div>";
	return option;
}

/*查看通知*/
function viewNotice(data){
	$("#conContent").html(data.content);
	$("#conCreateDate").html(data.createdate);
	$("#conDescripter").html(data.descripter);
	$("#connCreatename").html(data.createname);
	$("#conTitle").html(data.title);
	openCalender();
}



/**
 * 加载消息相关
 */
function loadInfo(){
	$.ajax({
		async : true,
		cache: false,	
		type : "post",
		dataType : "json",   
		url : V_PATH + "/index/loadNewInfo.json?r="+Math.random(),
		success : function(result, textStatus) {
			if(result.state=="T"){
				//成功重新加载页面
				for(var i=0;i<result.data.length;i++){
					$("#messageinfo").append(getInfoDataOption(result.data[i]));
				}
			}else{
				toastr.error("获取信息错误");
			}
		},
		error : function() {
			toastr.error("获取信息错误，请联系管理员");
		}
	});
}

/**
 * 根据data获取页面数据
 */
function getInfoDataOption(data){
	var option="";
	option+="<div class='desc'>";
	option+="<div class='thumb'>";
	option+="<img class='img-circle' src='"+V_PATH+"/assets/dashgumfree/img/ui-divya.jpg' width='35px' height='35px' align=''>";
	option+="</div>";
	option+="<div class='details'>";
	option+="<p><muted>"+data.createdate+"</muted><br/><a href='javascript:;' onclick='viewInfo("+JSON.stringify(data)+")'>"+data.title+"</a>"+data.descripter+"<br/>";
	option+="</p>";
	option+="</div>";
	option+="</div>";
	return option;
}

/*查看通知*/
function viewInfo(data){
//	console.log(data);
	$("#conContent").html(data.content);
	$("#conCreateDate").html(data.createdate);
	$("#conDescripter").html(data.descripter);
	$("#conTitle").html(data.title);
	openCalender();
}

/*****************================弹窗相关js==================****************/
/**
 * 打开弹窗
 * @param text
 * @param start
 * @param end
 * @returns
 */
function openCalender(){
    $("#add-warp").css({ "width": $(document).width(), "height": $(document).height() });
    $("#add-warp").show();
    $("#open-calendar").show();
    window.scrollTo(0,0);
}

/**
 * 关闭弹窗
 * @returns
 */
function closeCalender(){
	//将一系列信息清空$("#conContent").html(data.content);
	$("#conCreateDate").html("");
	$("#conDescripter").html("");
	$("#connCreatename").html("");
	$("#conTitle").html("");
	$("#connCreatename").html("");
	$("#add-warp").hide();
    $("#open-calendar").hide();
}





