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
    <title>收件箱</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="${V_PATH}/assets/dashgumfree/css/to-do.css">
    <link rel="stylesheet" href="${V_PATH}/assets/personoffice/css/tasks.css">
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap-table.min.css" rel="stylesheet">
    <!-- js placed at the end of the document so the pages load faster -->
    <!--script for this page-->
    <script src="${V_PATH}/assets/instation/js/inbox.js" type="text/javascript"></script>
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap-table.min.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> Email Lists</h3>
    <div class="row mt mb">
        <div class="col-md-12">
            <section class="task-panel tasks-widget">
                <div class="panel-heading">
                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Email List - ToDay</h5></div>
                    <br>
                </div>
                <div class="panel-body">
                    <div class="task-content">
                    	<table class="table" id="todyList"
                               data-toggle="table"
                               data-toolbar="#toolbar"
                               data-page-size="5"
                               data-pagination="true"
                        >
                            <thead>
                            <tr>
                                <th data-field="id" data-visible="false">ID</th>
                                <th data-field="state" data-visible="false">状态</th>
                                <th data-field="createdate">收件时间</th>
                                <th data-field="title" data-formatter="addToInfo">标题</th>
                                <th data-field="createname">发件人</th>
                                <th data-field="descripter">内容描述</th>
                                <th data-formatter="addState">状态</th>
                                <th data-formatter="addOption">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </section>
        </div><!--/col-md-12 -->
        <div class="col-md-12">
            <section class="task-panel tasks-widget">
                <div class="panel-heading">
                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Email List - Older Messages</h5></div>
                    <br>
                </div>
                <div class="panel-body">
                    <div class="task-content">
						<table class="table" id="olderList"
                               data-toggle="table"
                               data-toolbar="#toolbar"
                               data-page-size="5"
                               data-pagination="true"
                        >
                            <thead>
                            <tr>
                                <th data-field="id" data-visible="false">ID</th>
                                <th data-field="state" data-visible="false">状态</th>
                                <th data-field="createdate">收件时间</th>
                                <th data-field="title"  data-formatter="addToInfo">标题</th>
                                <th data-field="createname">发件人</th>
                                <th data-field="descripter">内容描述</th>
                                <th data-formatter="addState">状态</th>
                                <th data-formatter="addOption">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </section>
        </div><!--/col-md-12 -->
    </div><!-- /row -->
</section>
</body>
</html>