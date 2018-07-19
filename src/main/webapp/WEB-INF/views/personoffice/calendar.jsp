<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/commit/main.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${V_PATH}/assets/img/favicon.png">
    <title>工作日历</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link href="${V_PATH}/assets/fullcalendar/fullcalendar.min.css" rel="stylesheet"/>
    <link href="${V_PATH}/assets/personoffice/css/calendar.css">
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <!--系列js文件-->
    <script src='${V_PATH}/assets/js/moment.min.js'></script>
    <script src="${V_PATH}/assets/fullcalendar/fullcalendar.min.js"></script>
    <script src="${V_PATH}/assets/js/WdatePicker.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
    <script src="${V_PATH}/assets/personoffice/js/calendar.js" type="text/javascript"></script>
    
    <style>
        #add-warp {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            /*background-color: #0f0f0f;*/
            /*opacity: 0.5;*/
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1;
        }
    </style>
</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> 工作日历</h3>
    <!-- page start-->
    <div class="row mt mb">
        <div class="col-md-12">
            <section class="panel">
                <div class="panel-body">
                    <div id="calendar" class="has-toolbar"></div>
                </div>
            </section>
        </div>
    </div>
    <!-- page end-->
</section>
<section id="add-warp">
</section>
<!--弹出框-->
<div id="open-calendar" style="display:none;width: 40%;position: absolute;z-index: 100;top: 20%;left: 30%;">
	<!-- 用来判断是编辑还是新增 -->
	<input type="hidden" id="eventSign">
	<input type="hidden" id="eventId">
    <div class="form-panel">
        <h4 class="mb"><i class="fa fa-angle-right"></i> 编辑事件</h4>
        <form class="form-horizontal style-form" method="get">
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">事件内容</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="eventContent">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">开始时间</label>
                <div class="col-sm-10">
                    <input id="start" type="text" class="form-control" readonly
                           onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">结束时间</label>
                <div class="col-sm-10">
                    <input id="end" type="text" class="form-control" readonly
                           onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                </div>
            </div>
            <button class="btn btn-danger" type="button" style="display: none;" id="delButton" onclick="delCalendar()">删除</button>
            <button class="btn btn-success" type="button" onclick="selRun()">确定</button>
            <div style="display: inline;float: right">
                <button class="btn btn-info" type="button" onclick="closeCalender()">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>