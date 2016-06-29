<%--
  Created by IntelliJ IDEA.
  User: chenbo
  Date: 2016/4/28
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>报名成功</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<link href="../css/sinaFaceAndEffec.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">报名成功</h3>
		<h3 class="small-title">已完成订单</h3>
		<div class="consult-list clearfix">
			<ul class="clearfix">
			<c:forEach items="${list}" var="activitySign">
				<li class="clearfix">
					<span class="attr"></span>
					<span class="reply" style="right:500px;">报名人数：${activitySign.reserveCount}人</span>
					<div class="reply" >${activitySign.activityName}</div>
					<span class="reply" style="right:0px;top:50px;"><fmt:formatDate value="${activitySign.createTime}"
								pattern="yyyy-MM-dd HH:mm" /></span>
					<span class="attr"></span>
					<c:if test="${!empty activitySign.userPhoto }">
						<img src="${activitySign.userPhoto }" class="fl" width="64" height="64" alt="">
					</c:if>
					<c:if test="${empty activitySign.userPhoto }">
						<img src="../image/default_photo.png" class="fl" width="64" height="64" alt="">
					</c:if>
					<span class="name">${activitySign.userName }</span><br>
					<span class="sex"><c:if test="${activitySign.userSex == 1 }">男</c:if><c:if test="${activitySign.userSex == 2 }">女</c:if></span><br/>
					<span class="name">${activitySign.userPhone}</span><br>
					
				</li>
				</c:forEach>
			</ul>
		</div>
		
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
</html>

