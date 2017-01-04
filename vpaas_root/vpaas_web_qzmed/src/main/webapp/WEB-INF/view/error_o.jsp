<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.1.min.js"></script>
<style type="text/css">
.table {
    margin: 0 auto;
    padding: 0;
    position: relative;
    width: 1280px;
}
.table thead tr th {
    background: #044599 none no-repeat scroll 0 0;
    border-color: #02397f;
    border-style: solid;
    border-width: 1px;
    color: #fff;
    font-size: 12px;
    height: 51px;
    letter-spacing: 2px;
    text-align: center;
}
.table tbody tr td {
    border-bottom: 1px solid #ececec;
    border-left: 1px solid #ececec;
    border-right: 1px solid #ececec;
    color: #909090;
    font-size: 15px;
    height: 60px;
    text-align: center;
}

.table .mytd{
	width:140px;
	overflow:hidden;
	display:block;
	text-overflow:ellipsis;
	white-space:nowrap;
	line-height:40px;
}
</style>
<title>错误处理</title>
</head>
<body>
<script type="text/javascript">
var count = ${eorList.size()};
/**
* 选中一行事件
*/
function doSelectRow(row){
	//alert($(row).find("td:eq(1)").text());
	var record = $(row).find("td:eq(1)").text();
	window.location.href = "${pageContext.request.contextPath}/modify?record=" + record;
}

</script>
<h1 >hello,${eorList.size()} + ${eorList.get(0).getServiceType()}</h1>
	<div style="width:1280px;margin: auto;">
		<table class="table table-hover table-striped" cellspacing="0" cellpadding="0" border="0" align=center>
			<thead>
				<tr>
					<th>服务类型</th>
					<th>主键</th>
					<th>身份证号</th>
					<th>业务编码</th>
					<th>XML报文</th>
					<th>License信息</th>
					<th>行政区划代码</th>
					<th>医疗机构代码</th>
					<th>医疗机构名称</th>
					<th>域代码</th>
					<th>域名称</th>
					<th>新建时间</th>
					<th>更新时间</th>
					<th>错误信息</th>
					<th>修改标识</th>
				</tr>
			</thead>
			<tbody id="mybody">
				<c:forEach items="${eorList}" varStatus="i" var="item" > 
					<tr onclick="doSelectRow(this)">   
			            <td>${item.getServiceType()}</td> 
			            <td>${item.getRecordId()}</td> 
			            <td>${item.getIDCard()}</td> 
			            <td>${item.getBusinessTypeNo()}</td> 
			            <td class="mytd">${item.getBodyXml()}</td> 
			            <td>${item.getLicense()}</td> 
			            <td>${item.getAreaCode()}</td> 
			            <td>${item.getOrgCode()}</td> 
			            <td>${item.getOrgName()}</td> 
			            <td>${item.getDomainCode()}</td> 
			            <td>${item.getDomainName()}</td> 
			            <td>${item.getCreateDate()}</td> 
			            <td>${item.getUpdateDate()}</td> 
			            <td>${item.getErrorInfo()}</td>  
			            <td>${item.getModifyMark()}</td>  
		        	</tr>  
        		</c:forEach> 
			</tbody>
		</table>
	</div>
</body>
</html>