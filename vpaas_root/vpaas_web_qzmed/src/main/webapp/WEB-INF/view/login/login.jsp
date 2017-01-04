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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>登录</title>
<link href="<%=basePath %>css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="<%=basePath %>css/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="<%=basePath %>css/animate.css" rel="stylesheet">
<link href="<%=basePath %>css/style.css?v=2.2.0" rel="stylesheet">

<script src="<%=basePath %>js/jquery-2.1.1.min.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js?v=3.4.0"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#denlu").click(function() { 
    	var username = $("#username").val();
    	var password = $("#password").val();
    	var res = $.ajax({
            type: "POST",
            url: '<%=request.getContextPath() %>/cin',
            data: {"username":username,"password":password},
            async: false,
            success: function (result) {
            	if(result == '0'){
            		//alert(0);
            		window.location.href = "${pageContext.request.contextPath}/home";
            	}else if(result == '1'){
            		//alert(1);
            		//document.getElementById("msg") .innerHTML = "用户名或密码错误，请重新输入！";
            		$("#msg").html("用户名或密码错误，请重新输入！");
            		$("#msg").css("color","red");
            	}
            },
            error: function(data) {
                alert("error:"+data.responseText);
             }
        });
    	
    });
});
</script>
</head>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用管理系统</h3>

            <form class="m-t" enctype="multipart/form-data" method="post" onsubmit="return false">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required="" id="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" id="password">
                </div>
                <button id="denlu" class="btn btn-primary block full-width m-b">登 录</button>

            </form>
            <div id="msg" style="width:200px;height:40px;"></div>
        </div>
    </div>

    

</body>

</html>