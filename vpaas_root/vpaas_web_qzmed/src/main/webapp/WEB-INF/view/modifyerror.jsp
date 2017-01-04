<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>主页</title>

    <link href="<%=basePath %>css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="<%=basePath %>css/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="<%=basePath %>css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="<%=basePath %>js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="<%=basePath %>css/animate.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css?v=2.2.0" rel="stylesheet">
    
    <!-- Mainly scripts -->
    <script src="<%=basePath %>js/jquery-2.1.1.min.js"></script>
    <script src="<%=basePath %>js/bootstrap.min.js?v=3.4.0"></script>
    <script src="<%=basePath %>js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=basePath %>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="<%=basePath %>js/plugins/jeditable/jquery.jeditable.js"></script>


    <!-- Custom and plugin javascript -->
    <script src="<%=basePath %>js/hplus.js?v=2.2.0"></script>
    <script src="<%=basePath %>js/plugins/pace/pace.min.js"></script>
    <style type="text/css">
	label{
	width:80px;
	}
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
	    $("#singninButt").click(function() {       
	       //$("form").submit();
	       //alert($("#bodyXml").val());
	    	$.ajax({
	            type: "POST",
	            url: '<%=request.getContextPath() %>/save',
	            data: {"serviceType":$("#serviceType").val(),"recordId":$("#recordId").val(),"bodyXml":$("#bodyXml").val()},
	            success: function (result) {
	                
	                //alert(strresult);
	                //加载最大可退金额
	            	window.location.href = "${pageContext.request.contextPath}/error";
	            },
	            error: function(data) {
	                alert("error:"+data.responseText);
	             }
	        });
	    });
	});
	</script>
</head>

<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="<%=basePath %>img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">admin</strong>
                             </span> <span class="text-muted text-xs block">超级管理员 </span> </span>
                            </a>
                        </div>
                        <div class="logo-element">
                            H+
                        </div>

                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/home"><i class="fa"></i> <span class="nav-label">主页</span></a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/error"><i class="fa"></i> <span class="nav-label">错误修改</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/convert"><i class="fa"></i> <span class="nav-label">转换历史</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/hand"><i class="fa"></i> <span class="nav-label">手动记录</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/syetem"><i class="fa"></i> <span class="nav-label">系统配置</span></a>
                    </li>
                </ul>

            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message">青州人民医院HIS数据转换</span>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/login">
                                <i class="fa"></i> 退出
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
		    <div class="wrapper wrapper-content animated fadeInRight">
		    	<div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
							<form id="mainForm" action="<%=request.getContextPath() %>/save" method="post" enctype="multipart/form-data">
								<div>
									<label>服务类型</label><input type="text" id="serviceType" name="serviceType" value="${eor.getServiceType()}" >
								</div>
								<div>
									<label>主键</label><input type="text" id="recordId" name="recordId" value="${eor.getRecordId()}" >
								</div>
								<div>
									<label>错误信息</label><label> ${eor.getErrorInfo()} </label>
								</div>
								<div>
									<label>XML报文</label><textarea rows="20" cols="100" id="bodyXml" name="bodyXml" form="mainForm">
										${bodyxml}
									</textarea>
								</div>
								<div>
									<input type="button" value="提交" id="singninButt" class="btn btn-primary"/>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
            <div class="footer">
                <div class="pull-right">
                    By：<a href="http://www.zi-han.net" target="_blank">zihan's blog</a>
                </div>
                <div>
                    <strong>Copyright</strong> H+ &copy; 2014
                </div>
            </div>

        </div>
    </div>

</body>

</html>