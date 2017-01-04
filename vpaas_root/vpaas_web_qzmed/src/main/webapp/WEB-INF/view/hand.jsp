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

    <!-- Data Tables -->
    <script src="<%=basePath %>js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="<%=basePath %>js/plugins/dataTables/dataTables.bootstrap.js"></script>

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
                    <li  class="active">
                        <a href="${pageContext.request.contextPath}/hand"><i class="fa"></i> <span class="nav-label">手动记录</span></a>
                    </li>
                    <li>
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
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>手动转换历史记录</h5>
                                <div class="ibox-tools" style="margin-top: -10px;">

                                     <button value="提交" id="singninButt" class="btn btn-primary">手动转换</button>

                                </div>
                            </div>
                            <div class="ibox-content">

                                <table class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                        	<th>试图名称</th>
                                            <th>开始时间</th>
											<th>结束时间</th>
											<th>转换条数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- 添加数据 -->
                                        <c:forEach items="${hconList}" varStatus="i" var="item" > 
										<tr>  
											<td>${item.getViewName()}</td>  
								            <td>${item.getStartTime()}</td> 
								            <td>${item.getEndTime()}</td> 
								            <td>${item.getCount()}</td> 
							        	</tr>  
					        		</c:forEach> 
                                    </tbody>
                                    <tfoot>
	                                    <tr>
											<td colspan="4">第${pager.getCurrentPage()}页 共${pager.getTotalPages()}页
												<button onclick="first(${pager.getCurrentPage()})">首页</button>&nbsp;&nbsp;
												<button onclick="previous(${pager.getCurrentPage()})">上一页</button>&nbsp;&nbsp;
												<button onclick="next(${pager.getCurrentPage()},${pager.getTotalPages()})">下一页</button>&nbsp;&nbsp;
												<button	onclick="last(${pager.getCurrentPage()},${pager.getTotalPages()})">尾页</button>
											</td>
										</tr>
									</tfoot>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div class="pull-right">
                    By：<a href="http://www.zi-han.net" target="_blank"></a>
                </div>
                <div>
                    <strong>Copyright</strong> H+ &copy; 2014
                </div>
            </div>

        </div>
    </div>

    <script>
    /*$(document).ready(function () {
        $('.dataTables-example').dataTable();
    });*/
        $(document).ready(function() {
            $("#singninButt").click(function() { 
            	//$("#msg").html("数据转换中，请等待……");
            	if(window.confirm('你确定要进行手动数据转换？')){
	            	$.ajax({
	                    type: "POST",
	                    url: '${pageContext.request.contextPath}/submit',
	                    data: {},
	                    success: function (result) {
	                    	//alert(result);
	                    	if(result == 0){
	                    		$("#msg").html("数据转换结束！！！");
	                    		//$("#msg").css("color","red");
	                    	}else{
	                    		$("#msg").html("数据转换出现错误！！！");
	                    		$("#msg").css("color","red");
	                    	}
	                    },
	                    error: function(data) {
	                        alert("error:"+data.responseText);
	                     }
	                });
            	}else{
            		alert("你取消了手动数据转换的操作！");
            	}
            });
        });
        
        function first(obj){
        	var currentPage = obj;
        	if(1 < currentPage){
        		var url = "${pageContext.request.contextPath}/page?m=first&c="+currentPage;
        		window.location.href = "${pageContext.request.contextPath}/hand?m=first&c="+currentPage;
        	}else{
        	alert("已经是首页了");
        	}
        }
        function previous(obj){
        	var currentPage = obj;
        	if(1 < currentPage){
        		var url = "${pageContext.request.contextPath}/page?m=previous&c="+currentPage;
        		window.location.href = "${pageContext.request.contextPath}/hand?m=previous&c="+currentPage;
        		//toPage(url, turl);
        	}else{
        	alert("已经是最前一页了");
        	}
        }
        function next(obj,totalPages){
        	var currentPage = obj;
        	if(currentPage < totalPages){
        		var url = "${pageContext.request.contextPath}/page?m=next&c="+currentPage.toString();
        		window.location.href = "${pageContext.request.contextPath}/hand?m=next&c="+currentPage.toString();
        		//alert(turl);
        		//toPage(url, turl)
        	}else{
        	alert("已经是最后一页了");
        	}
        }
        function last(obj,totalPages){
        	var currentPage = obj;
        	if(currentPage < totalPages){
        		var url = "${pageContext.request.contextPath}/page?m=last&c="+currentPage;
        		window.location.href = "${pageContext.request.contextPath}/hand?m=last&c="+currentPage;
        		//toPage(url, turl);
        	}else{
        	alert("已经是尾页了");
        	}
        }
        
        function toPage(url, turl){
        	//alert(turl);
			$.ajax({
	            type: "POST",
	            url: url,
	            data: {},
	            async: false,
	            success: function (result) {
	            	window.location.href=turl; 
	            },
	            error: function(data) {
	                alert("error:"+data.responseText);
	             }
	        });
	}
    </script>

</body>

</html>