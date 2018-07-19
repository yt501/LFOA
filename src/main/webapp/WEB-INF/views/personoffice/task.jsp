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
    <title>消息管理</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/to-do.css" rel="stylesheet">
    <!--引入bootstrap table css-->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap-table.min.css" rel="stylesheet">
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap-table.min.js"></script>
    <script src="${V_PATH}/assets/personoffice/js/task.js" type="text/javascript"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
    <script src="${V_PATH}/assets/js/WdatePicker.js"></script>
    <style>
        #add-warp {
            display: none;
            position: absolute;
            height: 100%;
            width: 100%;
            bottom: 0;
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
    <h3><i class="fa fa-angle-right"></i> 我的任务</h3>
    <div class="row mt mb">
        <div class="col-md-12">
            <section class="task-panel tasks-widget">
                <div class="panel-heading">
                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Task List</h5></div>
                    <br>
                </div>
                <div class="panel-body">
                    <div class="task-conten">
                        <!--搜索条件-->
                        <div class="form-inline" role="form">
                            <div class="form-group">
                     			 发布人：
                                <input type="text" class="form-control" id="contentName">
                            </div>
                            <button type="button" class="btn btn-theme" onclick="searchByName()">搜索</button>
                        </div>
                    </div>
                    <div class="content" id="content">
                        <table class="table" id="table"
                               data-toggle="table"
                               data-toolbar="#toolbar"
                               data-page-size="5"
                               data-pagination="true"
                        >
                            <thead>
                            <tr>
                                <th data-field="id" data-visible="false">ID</th>
                                <th data-field="state" data-visible="false">状态</th>
                                <th data-field="content" data-width="20%">任务内容</th>
                                <th data-field="createname">发布人</th>
                                <th data-field="createdate">发布时间</th>
                                <th data-formatter="taskStateOption">任务状态</th>
                                <th data-formatter="addOption">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <br>
                    <div class=" add-task-row">
                        <a class="btn btn-success btn-sm pull-left" href="javascript:;" onclick="addTask()">添加任务</a>
                        <a class="btn btn-default btn-sm pull-right" href="javascript:;" onclick="openTaskList()">已完成任务</a>
                    </div>
                </div>
            </section>
        </div><!--/col-md-12 -->
    </div><!-- /row -->
</section>
<section id="add-warp">
</section>
<!--弹出框-->
<div id="open-calendar" style="display:none;width: 40%;position: absolute;z-index: 100;top: 20%;left: 30%;height: 70%;">
    <div class="form-panel">
        <h4 class="mb"><i class="fa fa-angle-right"></i> </h4>
        <form class="form-horizontal style-form" method="get">
        	<input type="hidden" id="createId" value="">
        	<input type="hidden" id="createState" value="">
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">发布人</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly id="createName">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">创建时间</label>
                <div class="col-sm-10">
                    <input id="createDate" type="text" class="form-control" readonly
                                   onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">内容</label>
                <div class="col-sm-10">
                    <textarea id="createContent" class="form-control" rows="3"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">任务状态</label>
                <div class="col-sm-10" id="stateRedio">
                    <label class="checkbox-inline">
                    	<input type="radio" name="state" value="3">
                    	<span class="badge bg-important">紧急</span>
                    </label>
                    <label class="checkbox-inline">
                    	<input type="radio" name="state" value="2">
                    	<span class="badge bg-warning">中等</span>
                    </label>
                    <label class="checkbox-inline">
                    	<input type="radio" name="state" value="1">
                    	<span class="badge bg-info">一般</span>
                    </label>
                    <label class="checkbox-inline">
                    	<input type="radio" name="state" value="4">
                    	<span class="badge bg-success">其他</span>
                    </label>
                </div>
            </div>
            <div class="form-group" style="display: none;" id="achiveTask">
                <label class="col-sm-2 col-sm-2 control-label">接受任务者</label>
                <div class="col-sm-10" id="achivePersons">
                </div>
            </div>
            <button id="console-btn" class="btn btn-info" type="button">取消</button>
            <div style="display: inline;float: right">
	            <button class="btn btn-success" onclick="infoSubmit()" type="button">确定</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>