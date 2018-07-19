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
    <title>会议申请</title>
    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="${V_PATH}/assets/dashgumfree/css/to-do.css">
    <link rel="stylesheet" href="${V_PATH}/assets/management/css/roomApplication.css">
    <link rel="stylesheet" href="${V_PATH}/assets/css/toastr.min.css">
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${V_PATH}/assets/management/js/roomApplication.js"></script>
    <script src="${V_PATH}/assets/js/WdatePicker.js"></script>
    <script src="${V_PATH}/assets/js/toastr.min.js"></script>
    <!--script for this page-->
</head>
<body>
<section class="wrapper">
    <h3><i class="fa fa-angle-right"></i> 会议室申请</h3>
    <div class="row mt mb">
        <div class="col-md-12">
            <div class="col-md-8">
                <!--会议室-->
                <c:forEach items="${boardrooms}" var="room">
                	<div class="col-md-3">
	                    <div class="thumbnail">
	                        <img src="${V_PATH}/assets/management/img/imgModel.svg" alt="会议室">
	                        <div class="caption">
	                            <h5>${room.NAME}</h5>
	                            <p>
	                               	 位置：${room.address}<br>
	                                                                                                 是否使用：
									<c:choose>
										<c:when test="${room.state==0 }">未使用</c:when>
										<c:otherwise>
											<label style="color: red;">已使用</label>
										</c:otherwise>
									</c:choose>
	                            </p>
	                            <p>
	                            	<c:choose>
										<c:when test="${room.state==0 }">
			                                <!--可以使用-->
											<a href="#" class="btn-sm btn-primary" role="button" onclick="showRoomInfo('${room.id}','${room.NAME}','${room.address}')">查看</a>
										</c:when>
										<c:otherwise>
			                                <!--不可以使用-->
											<a href="#" disabled="true"  class="btn-sm btn-default" role="button">查看</a>
										</c:otherwise>
									</c:choose>
	                            </p>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
            </div>
            
            <div class="col-md-4" id="roomInfo">
                <form class="form-horizontal style-form" method="get">
                	<input type="hidden" name="id" value="" class="roomid">
                    <!--显示会议信息-->
                    <div class="form-group">
				                        会议室名称：<label class="inputroomName"></label><br>
				                        会议室地址：<label class="inputroomAddress"></label><br>
				                        是否使用：未使用<br>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">会议内容</label>
                        <div class="col-sm-10">
                            <input type="text" id="content" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input id="start" type="text" class="form-control" readonly
                                   onclick="WdatePicker({el:this,dateFmt:'yyyyMMdd'})">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input id="end" type="text" class="form-control" readonly
                                   onclick="WdatePicker({el:this,dateFmt:'yyyyMMdd'})">
                        </div>
                    </div>
                    <div style="display: inline;float: right">
                        <button  type="button" class="btn btn-success" onclick="submitApply()">确定</button>
                    </div>
                </form>
            </div>
        </div><!--/col-md-12 -->
    </div><!-- /row -->
</section>
</body>
</html>