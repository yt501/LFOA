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
    <title>详细信息</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="${V_PATH}/assets/css/toastr.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link href="${V_PATH}/assets/instation/css/halflings.css" rel="stylesheet">
    <link href="${V_PATH}/assets/instation/css/emailinfo.css" rel="stylesheet">
    <script src="${V_PATH}/assets/instation/js/emailinfo.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> Email详细信息</h3>
    <input type="hidden" id="subject" value="${data.title}" >
    <input type="hidden" id="content" value="${data.userid}" >
    
    <br>
    <div class="row mt mb">
        <div class="col-md-12">
            <div class="message dark">
                <div class="emailInfo">
                    <h1>${data.title}</h1>
                    <div class="from"><i class="halflings-icon user"></i> <b>${data.createname}</b> / ${data.email }</div>
                    <div class="date"><i class="halflings-icon time"></i> 收件时间： <b>${data.createdate}</b></div>
                    <div class="menu"></div>
                </div>
                <div class="content">
                    <p>
                        ${data.content}
                    </p>
                </div>
                <br>
                <br>
                <form class="replyForm" method="post" action="">
                    <fieldset>
                        <textarea tabindex="3" class="input-xlarge span12" id="message" name="body" rows="12"
                                  placeholder="Click here to reply"></textarea>
                        <div class="actions">
                            <button tabindex="3" type="button" class="btn btn-success" onclick="sendEmail()">Send message</button>
                        </div>
                    </fieldset>
                </form>
            </div>

        </div>
    </div>

</section>
</body>
</html>