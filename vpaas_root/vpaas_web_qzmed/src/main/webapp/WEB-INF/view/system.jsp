<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <!-- Custom and plugin javascript -->
    <script src="<%=basePath %>js/hplus.js?v=2.2.0"></script>
    <script src="<%=basePath %>js/plugins/pace/pace.min.js"></script>

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
                    <li>
                        <a href="${pageContext.request.contextPath}/error"><i class="fa"></i> <span class="nav-label">错误修改</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/convert"><i class="fa"></i> <span class="nav-label">转换历史</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/hand"><i class="fa"></i> <span class="nav-label">手动记录</span></a>
                    </li>
                    <li  class="active">
                        <a href="${pageContext.request.contextPath}/system"><i class="fa"></i> <span class="nav-label">系统配置</span></a>
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
            <div class="wrapper wrapper-content">               
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>系统配置</h5>
                            </div>
                            <div class="ibox-content">
                                <div class="feed-activity-list">
                                    <div class="feed-element">
                                        <div>
	                                		<select id="t" onchange="change(this)">
	                                		  <option value="">选择定时周期</option>
	                                		  <option value ="小时">小时间隔</option>
	                                		  <option value ="每天">每天</option>
											</select>
										</div>
									</div>
									<div class="feed-element">
										<div id="c_h" style="display:none">
											每间隔几个小时执行一次<br>
											请输入间隔的时间：<input type="text" placeholder="请输入1-12的数字" id="h_h">
											<button class="btn btn-primary" onclick="save()">保存设置</button>
										</div>
										<div id="c_d" style="display:none">
											每天的固定时间执行任务<br>
											请输入小时：<input type="text" placeholder="请输入1-23的数字" id="d_h"><br>
											请输入分钟：<input type="text" placeholder="请输入0-59的数字" id="d_m">
											<button class="btn btn-primary" onclick="save()">保存设置</button>
										</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
            <div>
                <div class="footer">
                    <div>
                        <strong>Copyright</strong>  &copy; 2014
                    </div>
                </div>
            </div>

        </div>
    </div>
	<script type="text/javascript">
		function change(s){
			var tmp = $("#t").val();
			if(tmp == "小时"){
				$("#c_h").css("display","block");
				$("#c_d").css("display","none");
			}else if(tmp == "每天"){
				$("#c_h").css("display","none");
				$("#c_d").css("display","block");
			}else if(tmp == ""){
				$("#c_h").css("display","none");
				$("#c_d").css("display","none");
			}
		}
		function save(){
			var hour = '', min = '', inte = '';
			var tmp = $("#t").val();
			if(tmp == "小时"){
				inte = $("#h_h").val();
				if(inte < '1' || inte > '23'){
					alert("请输入正确的数值！");
				}
			}else if(tmp == "每天"){
				hour = $("#d_h").val();
				min = $("#d_m").val();
			}
			$.ajax({
	            type: "POST",
	            url: '<%=request.getContextPath() %>/systemSave',
	            data: {"hour":hour,"min":min,"inte":inte},
	            success: function (result) {
	                
	                alert(result);
	                //加载最大可退金额
	            	//window.location.href = "${pageContext.request.contextPath}/error";
	            },
	            error: function(data) {
	                alert("error:"+data.responseText);
	             }
	        });
		}
	</script>
    

</body>

</html>