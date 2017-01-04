<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#submit").click(function() { 
    	$("#msg").html("数据转换中，请等待……");
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
    });
});
</script>
</head>
<body>
<h2>test ${xxx}</h2>
<h1 >hello,${user.getName()},your password is ${user.getPwd()}</h1>
<a href="${pageContext.request.contextPath}/error">error</a>
<a href="javascript:void()" id="submit">手动提交</a>
<div id="msg"></div>
</body>
</html>
