<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<title>用户反馈</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">信息反馈</h3>
		<h3 class="title">意见留言</h3>
		<div class="message-list clearfix">
			<ul class="clearfix">
				<c:forEach items="${list}" var="userInfoBack">
				<li class="clearfix">
					<span class="attr"></span>
					<c:if test="${!empty userInfoBack.photo }">
						<img src="${userInfoBack.photo }" class="fl" width="64" height="64" alt="">
					</c:if>
					<c:if test="${empty userInfoBack.photo }">
						<img src="../image/default_photo.png" class="fl" width="64" height="64" alt="">
					</c:if>
					<span class="name">${userInfoBack.userName }</span><br>
					<span class="sex"><c:if test="${userInfoBack.sex == 1 }">男</c:if><c:if test="${userInfoBack.sex == 2 }">女</c:if></span>
					<div class="contents">${userInfoBack.info }</div>
					<div class="dates"><fmt:formatDate value="${userInfoBack.createTime}"
								pattern="yyyy-MM-dd HH:mm" /></div>
				</li>
				</c:forEach>
			</ul>
		</div>
		
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
</html>

