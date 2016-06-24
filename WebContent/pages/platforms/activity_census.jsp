<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>活动统计</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">活动统计</h3>
		<h3 class="small-title">已完成订单</h3>
		<ul class="tj-list clearfix">
			<c:forEach items="${list}" var="activity">
			<li>
				<img src="${activity.thumbImg}" class="fl" width="120" height="90" alt="" />
				<div class="contents fl">
					<span class="titles">${activity.name}</span>
					<p><c:if test="${fn:length(activity.title) < 100 }">${activity.title}</c:if>
							<c:if test="${fn:length(activity.title) >= 100 }">${fn:substring(activity.title,0,100)}...</c:if></p>
					<span class="prices">¥${activity.price}　<em>¥${activity.oldPrice}</em></span>
				</div>
				<div class="operation fr">已售出：${activity.saleCount}</div>
			</li>
			</c:forEach>
		</ul>
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</html>