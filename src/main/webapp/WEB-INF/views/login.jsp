<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="V_PATH" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta name="description" content="登录">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${V_PATH}/assets/img/favicon.png">
    <!--bootstrap css-->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div id="login-page">
    <div class="container">
        <form class="form-login" action="${V_PATH}/login" method="post">
            <h2 class="form-login-heading">登录LFOA</h2>
            <div class="login-wrap">
                <input type="text" class="form-control" placeholder="账号：" name="username" id="username" autofocus>
                <br><br>
                <input type="password" class="form-control" placeholder="密码：" name="password" id="password"><br>
                <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="#"> 忘记密码?</a>
		                </span><br>
                </label><br>
                <button class="btn btn-theme btn-block" href="javascript:;" type="button" onclick="loginOA()"><i class="fa fa-lock"></i> 登录</button><br>
            </div>
        </form>
    </div>
</div>
<script src="${V_PATH}/assets/js/jquery.js"></script>
<script src="${V_PATH}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${V_PATH}/assets/js/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("${V_PATH}/assets/dashgumfree/img/login-bg.jpg", {speed: 500});
</script>
<script src="${V_PATH}/assets/js/toastr.min.js"></script>
<script>
	var V_PATH='${V_PATH}';
</script>
<script src="${V_PATH}/assets/js/login.js"></script>
</body>
</html>