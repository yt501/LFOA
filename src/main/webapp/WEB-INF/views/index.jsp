<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<c:set var="V_PATH" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <meta name="description" content="首页">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${V_PATH}/assets/img/favicon.png">
    <!--bootstrap css-->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <script src="${V_PATH}/assets/js/jquery.js"></script>
    <script src="${V_PATH}/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <!--描述：如果开启则在其他页面将无法访问，不开启首页无法使用（邮件信息，和任务信息）-->
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${V_PATH}/assets/js/app.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
</head>
<body>
<section id="container">
    <!--header-->
    <header class="header black-bg">
        <!--侧边栏缩进效果-->
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>
        <!--logo start-->
        <a href="index.html" class="logo"><b>LFSENIOR OA</b></a>
        <!--logo end-->
        <div class="nav notify-row" id="top_menu">
            <!--  notification start -->
            <ul class="nav top-menu">
                <!--任务说明-->
                <!-- settings start -->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                        <i class="fa fa-tasks"></i>
                        <!--显示未完成任务-->
                        <span class="badge bg-theme" id="taskCount1"></span>
                    </a>
                    <ul class="dropdown-menu extended tasks-bar" id="taskIndexList">
                        <div class="notify-arrow notify-arrow-green"></div>
                        <li>
                            <p class="green">你有<span id="taskCount2"></span>个任务未结束</p>
                        </li>
                    </ul>
                </li>
                <!-- settings end -->

                <!--邮箱说明-->
                <!-- inbox dropdown start-->
                <li id="header_inbox_bar" class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                        <i class="fa fa-envelope-o"></i>
                        <!--未读邮件数目-->
                        <span class="badge bg-theme" id="emailCount1"></span>
                    </a>
                    <ul class="dropdown-menu extended inbox" id="emailIndexList">
                        <div class="notify-arrow notify-arrow-green"></div>
                        <li>
                            <p class="green">你有<span id="emailCount2"></span>个邮件</p>
                        </li>
                        
                    </ul>
                </li>
                <!-- inbox dropdown end -->
            </ul>
            <!--  notification end -->
        </div>
        <div class="top-menu">
            <!--登出-->
            <ul class="nav pull-right top-menu">
                <li><a class="logout" href="${V_PATH}/logout">Logout</a></li>
            </ul>
        </div>
    </header>
</section>
<!--sidebar start-->
<aside>
    <div id="sidebar"  class="nav-collapse "  style="z-index: 10">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <p class="centered"><a href="javascript:;"><img src="${V_PATH}/assets/dashgumfree/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
            <h5 class="centered" id="loginUserName"></h5>
            <!--功能列表-->
            <li class="mt">
                <a class="active" href="javascript:;" onclick="loadPage('${V_PATH}/index/indexContent')">
                    <i class="fa fa-dashboard"></i>
                    <span>我的办公桌</span>
                </a>
            </li>
            <shiro:hasPermission name="/personoffice">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class="fa fa-desktop"></i>
	                    <span>个人办公</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/personoffice/task">
	                    	<li><a href="#" onclick="loadPage('${V_PATH}/personoffice/task')">我的任务</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/personoffice/calendar">
	                    	<li><a href="#" onclick="loadPage('${V_PATH}/personoffice/calendar')">我的日历</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/personoffice/content">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/personoffice/content')">通讯录</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="/publicinfo">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class="fa fa-cogs"></i>
	                    <span>公共消息</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/publicinfo/infoManger">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/publicinfo/infoManger')">消息管理</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/publicinfo/noticeManger">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/publicinfo/noticeManger')">公告管理</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="/instation">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class="fa fa-book"></i>
	                    <span>站内通信</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/instation/inbox">
	                    	<li><a  href="#"  onclick="loadPage('${V_PATH}/instation/inbox')">收件箱</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/instation/compose">
	                    	<li><a  href="#"  onclick="loadPage('${V_PATH}/instation/compose')">发邮件</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/instation/rubbsh">
	                    	<li><a  href="#"  onclick="loadPage('${V_PATH}/instation/rubbsh')">回收站</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="/management">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class="fa fa-tasks"></i>
	                    <span>行政管理</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/management/roomApplication">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/management/roomApplication')">会议室申请</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/management/roomManager">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/management/roomManager')">会议室管理</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="/authority">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class="fa fa-th"></i>
	                    <span>权限管理</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/authority/userManager">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/authority/userManager')">用户管理</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/authority/roleManager">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/authority/roleManager')">角色管理</a></li>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/authority/resourceManager">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/authority/resourceManager')">资源管理</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="/sysmanager">
	            <li class="sub-menu">
	                <a href="javascript:;" >
	                    <i class=" fa fa-bar-chart-o"></i>
	                    <span>系统管理</span>
	                </a>
	                <ul class="sub">
	                	<shiro:hasPermission name="/sysmanager/userManager">
	                    	<li><a  href="#" onclick="loadPage('${V_PATH}/sysmanager/rePwdManager')">修改密码</a></li>
	                    </shiro:hasPermission>
	                </ul>
	            </li>
            </shiro:hasPermission>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<!--main content start-->
<section id="main-content">
</section>

<script>
	var V_PATH='${V_PATH}';
    function loadPage(url){
        $("#main-content").load(url)
    }
    
    $(".sub li").click(function () {
        $(".sub li a").css({"background-color":"rgba(255, 255, 255, 0)"});
    });
    $(function() {
    	loadPage(V_PATH+"/index/indexContent");
		/* 加载用户名 */    	
    	$.ajax({
    		async : true,
    		cache: false,	
    		type : "post",
    		dataType : "json",   
    		url : V_PATH + "/sysmanager/getUserName.json?r="+Math.random(),
    		success : function(result, textStatus) {
    			if(result.state=="T"){
    				//成功重新加载页面
    				$("#loginUserName").html(result.userName);
    			}else{
    				toastr.warning("获取用户名失败");
    			}
    		},
    		error : function() {
    			toastr.error("获取用户名失败，请联系服务人员");
    		}
    	});
	})
</script>
</body>
</html>