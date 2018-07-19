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
    <title>发送邮件</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link href="${V_PATH}/assets/instation/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${V_PATH}/assets/instation/js/compose.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> Send Email</h3>
    <br>
    <div id="page-wrapper">
        <div class="main-page">
            <div class="progressbar-heading grids-heading">
            </div>
            <!--grids-->
            <div class="grids">
                <div class="compose-grids">
                    <div class="col-md-4">
                        <div class="panel panel-widget">
                            <div class="compose-left">
                                <div class="folder widget-shadow">
                                    <ul>
                                        <li class="head">操作</li>
                                        <li><a href="#" onclick="loadPage('${V_PATH}/instation/inbox')"><i class="fa fa-inbox"></i>收件箱 <span id="countEmail">0</span></a></li>
                                        <li><a href="#" onclick="loadPage('${V_PATH}/instation/rubbsh')"><i class="fa fa-trash-o"></i>回收站</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="panel panel-widget">
                            <div class="compose-right widget-shadow">
                                <div class="panel-default">
                                    <div class="panel-heading">
                                        发送一封信邮件
                                    </div>
                                    <div class="panel-body">
                                        <div class="alert alert-info">
                                            请填写邮件信息
                                        </div>
                                        <form class="com-mail">
	                                        <select id=content class="form-control control3">
			                                    <option value="-1">请选择收件人</option>
			                                </select>
                                            <input type="text" id="subject" class="form-control1 control3" placeholder="Subject :">
                                            <textarea rows="6" id="message" class="form-control1 control2"
                                                      placeholder="Message :"></textarea>
                                            <input type="button" onclick="sendEmail()" value="Send Message">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
        <!--//grids-->
    </div>
    </div>
</section>
</body>
</html>