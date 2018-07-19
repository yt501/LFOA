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
    <title>用户管理</title>
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
    <script src="${V_PATH}/assets/authority/js/userManager.js" type="text/javascript"></script>
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap-table.min.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
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
    <h3><i class="fa fa-angle-right"></i> 用户管理</h3>
    <div class="row mt mb">
        <div class="col-md-12">
            <section class="task-panel tasks-widget">
                <div class="panel-heading">
                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> User List</h5></div>
                    <br>
                </div>
                <div class="panel-body">
                    <div class="task-conten">
                        <!--搜索条件-->
                        <div class="form-inline" role="form">
                            <div class="form-group">
                                部门：
                                <select id="dept" class="form-control">
                                    <option value="-1">全部</option>
                                </select>
                            </div>
                            <div class="form-group">
                                姓名：
                                <input type="text" class="form-control" id="contentName">
                            </div>
                            <div class="form-group">
                                电话：
                                <input type="text" class="form-control" id="contentNumber">
                            </div>
                            <button type="button" class="btn btn-theme" onclick="searchContent()">搜索</button>
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
                                <th data-field="contentId" data-visible="false">ContentID</th>
                                <th data-field="dept">部门</th>
                                <th data-field="username">用户名</th>
                                <th data-field="age">年龄</th>
                                <th data-field="phone">电话</th>
                                <th data-field="email">邮箱</th>
                                <th data-field="createdate">创建时间</th>
                                <th data-formatter="addOption">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <br>
                    <div class=" add-task-row">
                        <a class="btn btn-success btn-sm pull-right" href="javascript:;" onclick="addUser()">添加用户</a>
                    </div>
                </div>
            </section>
        </div><!--/col-md-12 -->
    </div><!-- /row -->
</section>
<section id="add-warp">
</section>
<!--弹出框-->
<div id="open-calendar" style="display:none;width: 40%;position: absolute;z-index: 100;top: 20%;left: 30%;">
	<!-- 用来判断是编辑还是新增 -->
	<input type="hidden" id="eventSign">
	<input type="hidden" id="eventId">
	<input type="hidden" id="contentId">
	<input type="hidden" id="roleId">
    <div class="form-panel">
        <h4 class="mb"><i class="fa fa-angle-right"></i> 添加用户</h4>
        <form class="form-horizontal style-form" method="get">
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">账户邮箱</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="usercode">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">联系电话</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="tel">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">年龄</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="age">
                </div>
            </div>
            <div class="form-group">
				<label class="col-sm-2 col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
                  <select id="depts" class="form-control">
                  </select>
                </div>
            </div>
            <div class="form-group">
				<label class="col-sm-2 col-sm-2 control-label">角色</label>
				<div class="col-sm-10">
                  <select id="role" class="form-control">
                  </select>
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