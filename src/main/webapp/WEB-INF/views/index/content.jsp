<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commit/main.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>主页内容</title>
    <link rel="icon" type="image/png" href="${V_PATH}/assets/img/favicon.png">

    <!-- Bootstrap core CSS -->
    <link href="${V_PATH}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${V_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${V_PATH}/assets/dashgumfree/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="${V_PATH}/assets/dashgumfree/css/jquery.gritter.css" />
    <%-- <link rel="stylesheet" type="text/css" href="${V_PATH}/assets/lineicons/style.css"> --%>    
    
    <!-- Custom styles for this template -->
    <link href="${V_PATH}/assets/dashgumfree/css/style.css" rel="stylesheet">
    <link href="${V_PATH}/assets/dashgumfree/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
      <div class="row">
          <div class="col-lg-9 main-chart">
              <div class="row mt">
                  <!-- SERVER STATUS PANELS -->
                  <div class="col-md-4 col-sm-4 mb">
                      <div class="white-panel pn donut-chart" id="userStateOption">
                      </div><! --/grey-panel -->
                  </div><!-- /col-md-4-->


                  <div class="col-md-4 col-sm-4 mb">
                      <div class="white-panel pn " id="userOASource">
                      </div>
                  </div><!-- /col-md-4 -->

                  <div class="col-md-4 mb">
                      <!-- WHITE PANEL - TOP USER -->
                      <div class="white-panel pn" id="userNumber">
                      </div>
                  </div><!-- /col-md-4 -->


              </div><!-- /row -->


              <div class="row mt">
                  <!--CUSTOM CHART START -->
                  <div class="custom-bar-chart" id="userOption">

                  </div>
                  <!--custom chart end-->
              </div><!-- /row -->

              <div class="row mt">
                  <!--CUSTOM CHART START -->
                  <div class="custom-bar-chart" id="taskNumber">

                  </div>
                  <!--custom chart end-->
              </div><!-- /row -->

              <div class="row mt">
                  <!--CUSTOM CHART START -->
                  <div class="custom-bar-chart" id="clickNumber">

                  </div>
                  <!--custom chart end-->
              </div><!-- /row -->
          </div><!-- /col-lg-9 END SECTION MIDDLE -->

          <div class="col-lg-3 ds">
              <!--COMPLETED ACTIONS DONUTS CHART-->
              <h3>NOTIFICATIONS</h3>
			  <div id="noticeations">
			  	
			  </div>

              <!-- USERS ONLINE SECTION -->
              <h3>MESSAGE INFO</h3>
              <!-- First Member -->
              <div id="messageinfo">
              
              </div>
              <!-- CALENDAR-->
              <div id="calendar" class="mb">
                  <div class="panel green-panel no-margin">
                      <div class="panel-body">
                          <div id="date-popover" class="popover top" style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                              <div class="arrow"></div>
                              <h3 class="popover-title" style="disadding: none;"></h3>
                              <div id="date-popover-content" class="popover-content"></div>
                          </div>
                          <div id="my-calendar"></div>
                      </div>
                  </div>
              </div><!-- / calendar -->

          </div><!-- /col-lg-3 -->
      </div><! --/row -->
  </section>
  <section id="add-warp">
  </section>
  <!--弹出框-->
<div id="open-calendar" style="display:none;width: 40%;position: absolute;z-index: 100;top: 20%;left: 30%;">
	<!-- 用来判断是编辑还是新增 -->
	<input type="hidden" id="eventSign">
	<input type="hidden" id="eventId">
    <div class="form-panel">
        <h5 class="text-success"><span id="conTitle"></span><small id="conCreateDate"></small></h5>
        <blockquote>
		  <small id="conDescripter">
		  		
		 </small>
		</blockquote>
		<p class="text-left" id="conContent"></p>
		<blockquote class="pull-right">
		  <small id="connCreatename"></small>
		</blockquote>
		<br><br>
        <form class="form-horizontal style-form" method="get">
            <button class="btn btn-success btn-sm" type="button" onclick="closeCalender()">确定</button>
        </form>
    </div>
</div>
    <!-- js placed at the end of the document so the pages load faster -->
<%--    <script src="${V_PATH}/assets/js/jquery.js"></script>
    <script src="${V_PATH}/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <!--描述：如果开启则在其他页面将无法访问，不开启首页无法使用（邮件信息，和任务信息）-->
    <script src="${V_PATH}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${V_PATH}/assets/js/app.js"></script>
 --%>    
 <script src="${V_PATH}/assets/js/toastr.min.js"></script>

  <!--script for this page-->
  <script src="${V_PATH}/assets/js/zabuto_calendar.js"></script>
  <script src="${V_PATH}/assets/dist/echarts-all.js"></script>
  <script src="${V_PATH}/assets/dist/lfcharts.js"></script>

  <script type="application/javascript">
	  $(document).ready(function () {
	      $("#date-popover").popover({html: true, trigger: "manual"});
	      $("#date-popover").hide();
	      $("#date-popover").click(function (e) {
	          $(this).hide();
	      });
	
	      $("#my-calendar").zabuto_calendar({
	      });
	
	      loadAllInfo();
		  /* 每十分钟加载一次 */	      
	      setInterval(() => {
				loadAllInfo();
			}, 36000000);
		  });
    </script>
  </body>
</html>