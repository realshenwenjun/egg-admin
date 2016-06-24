<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<title>活动反馈</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<link href="../css/sinaFaceAndEffec.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src="../js/sinaFaceAndEffec.js" type="text/javascript"></script>

</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">活动反馈</h3>
		<h3 class="small-title">反馈意见</h3>
		<div class="inpour clearfix">
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[0].name }：${censusList[0].state}</label>
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[1].name }：${censusList[1].state}</label>
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[2].name }：${censusList[2].state}</label>
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[3].name }：${censusList[3].state}</label>
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[4].name }：${censusList[4].state}</label>
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[5].name }：${censusList[5].state}</label>
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[6].name }：${censusList[6].state}</label>
			<label class="w95 fl" style="margin-right: 0;text-align: right;width: 200px;">${censusList[7].name }：${censusList[7].state}</label>
		</div>
		<h3 class="small-title">意见留言</h3>
		<div class="consult-list clearfix">
			<ul class="clearfix">
			<c:forEach items="${list}" var="activityAsk">
				<li class="clearfix">
					<span class="attr"></span>
					<c:if test="${!empty activityAsk.userPhoto }">
						<img src="${activityAsk.userPhoto }" class="fl" width="64" height="64" alt="">
					</c:if>
					<c:if test="${empty activityAsk.userPhoto }">
						<img src="../image/default_photo.png" class="fl" width="64" height="64" alt="">
					</c:if>
					<span class="name">${activityAsk.userName }</span><br>
					<span class="sex"><c:if test="${activityAsk.sex == 1 }">男</c:if><c:if test="${activityAsk.sex == 2 }">女</c:if></span>
					<div class="contents">${activityAsk.context}</div>
					<div class="dates"><fmt:formatDate value="${activityAsk.createTime}"
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
<script type="text/javascript">
$(document).ready(function(){
});
</script>

