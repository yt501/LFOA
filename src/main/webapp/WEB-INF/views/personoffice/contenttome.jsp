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
    <title>我的通讯录</title>
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
    <script src="${V_PATH}/assets/personoffice/js/contenttome.js" type="text/javascript"></script>
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap-table.min.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>

</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> Content</h3>
    <div class="row mt mb">
        <div class="col-md-12">
            <section class="task-panel tasks-widget">
                <div class="panel-heading">
                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Content List</h5></div>
                    <br>
                </div>
                <div class="panel-body">
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
                                <th data-field="depater">部门</th>
                                <th data-field="NAME">用户名</th>
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
                        <a class="btn btn-default btn-sm pull-right" href="javascript:;" onclick="gotoContent()">跳转到通讯录</a>
                    </div>
                </div>
            </section>
        </div><!--/col-md-12 -->
    </div><!-- /row -->
</section>
</body>
</html>