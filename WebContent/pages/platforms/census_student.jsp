<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>学生数量</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">学生数量（${page.totleCount }）</h3>
		<div class="message-list clearfix">
			<ul class="a-tab">
            	<li name="student" key="/census/student/list.do"><span class="current" >学生列表</span></li>
                <li><span>按分机构统计</span></li>
                <li><span>按课程统计</span></li>
            </ul>
		</div>
		<ul class="tj-list clearfix">
			<c:forEach items="${list}" var="user">
			<li>
				<c:if test="${!empty user.photo }">
					<img src="${user.photo}" class="fl" width="120" height="90" alt="" />
				</c:if>
				<c:if test="${empty user.photo }">
						<img src="../image/default_photo.png" class="fl" width="64" height="64" alt="">
					</c:if>
				<div class="contents fl">
					<span class="titles">${user.name}</span>
					<p>电话：${user.phone}</p>
				</div>
				<div class="operation fr">查看详情</div>
			</li>
			</c:forEach>
		</ul>
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("li[name='student']").click(function(){
			var url = $(this).attr("key");
			$.ajax({
				type : "GET",
				url : "/admin"+url,
				dataType : "html",
				success : function(data) {
					$("#ajaxJsp").html(data);
				}
			});
		});
	});
</script>
</html>