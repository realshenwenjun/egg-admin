<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>账号管理</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">账号管理</h3>
        <h3 class="small-title"><span class="fr"><a name="deleteAdmin" href="javaScript:" class="add-btns" style="margin-left:700px;text-decoration:none;">删除管理员</a><a name="addAdmin" href="javaScript:" class="add-btns ml10" style="margin-left:24px;text-decoration:none;">添加管理员</a></span></h3>
		<div class="message-list clearfix numZ">
			<ul class="clearfix">
				<c:forEach items="${list}" var="user">
					<li class="clearfix">
						<span class="attr"></span>
						<c:if test="${!empty user.photo }">
							<img src="${user.photo }" class="fl" width="64" height="64" alt="">
						</c:if>
						<c:if test="${empty user.photo }">
							<img src="../image/default_photo.png" class="fl" width="64" height="64" alt="">
						</c:if>
						<p style="width:450px; margin-left:20px; float:left;">
	                    <span class="name" style="font-size:20px; color:#000;display:block; width:100%; line-height:30px;">${user.name }</span>
						<span class="sex" style="font-size:16px; color:#000;">专业：${user.interest }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：${user.phone }</span>
	                    </p>
					</li>
				</c:forEach>
			</ul>
		</div>
		<c:if test="${page.totleCount != 0 }">
			<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
		</c:if>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='addAdmin']").click(function() {
			$.ajax({
			type : "GET",
			url : "/admin/user/nomal/search.do?key=",
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
			});
		});
		$("a[name='deleteAdmin']").click(function() {
			$.ajax({
			type : "GET",
			url : "/admin/user/supper/admin/delete/list.do",
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
			});
		});
	});
</script>
</html>