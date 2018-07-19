<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="V_PATH" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>重置密码</title>
    <meta name="description" content="重置面膜">
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
            <h2 class="form-login-heading">重置密码</h2>
            <div class="login-wrap">
                <input type="password" class="form-control" placeholder="新密码：" name="password" id="password" autofocus>
                <br><br>
                <input type="password" class="form-control" placeholder="确定密码：" name="repassword" id="repassword"><br>
                <br>
                <button class="btn btn-theme btn-block" href="javascript:;" type="button" onclick="rePwd()"><i class="fa fa-lock"></i> 重置密码</button><br>
            </div>
        </form>
    </div>
</div>
<script>
    $.backstretch("${V_PATH}/assets/dashgumfree/img/login-bg.jpg", {speed: 500});
</script>
<script src="${V_PATH}/assets/js/toastr.min.js"></script>
<script>
	var V_PATH='${V_PATH}';
</script>
<script src="${V_PATH}/assets/sysmanager/js/repwd.js"></script>
</body>
</html>