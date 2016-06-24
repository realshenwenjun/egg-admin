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
<title>咨询服务</title>
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
		<h3 class="big-title">咨询服务</h3>
		<h3 class="small-title">已完成订单</h3>
		<div class="consult-list clearfix">
			<ul class="clearfix">
			<c:forEach items="${list}" var="activityAsk">
				<li class="clearfix">
					<span class="attr"></span>
					<c:if test="${empty activityAsk.answer }">
						<a href="javascript:;" class="reply" key="${activityAsk.id }" name="reply_${activityAsk.id }">回复</a>
					</c:if>
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
					<c:if test="${empty activityAsk.answer }">
					<div class="comment" name="comment_${activityAsk.id }">
						<div class="content">
							<div class="cont-box">
								<div class="consu">
									<span>回复@${activityAsk.userName }:</span><textarea style="text-indent: 63px;" class="text" name="textarea_${activityAsk.id }">${activityAsk.answer.context }</textarea>
								</div>
							</div>
							<!-- <div class="tools-box">
								<div class="operator-box-btn"><span class="face-icon">☺</span></div>
							</div> -->
							<div class="tool-btns"><a href="javaScript:" name="send" key="${activityAsk.id }" keyName="${activityAsk.userName}">发送</a><a href="javaScript:" class="cancel" key="${activityAsk.id }">取消</a></div>
						</div>
					</div>
					</c:if>
					<c:if test="${!empty activityAsk.answer }">
					<div class="contents"><div class="consu">
									回复@${activityAsk.userName } : ${activityAsk.answer.context }
								</div></div>
					</c:if>
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
	$('.reply').click(function() {
		var key = $(this).attr("key");
		$('div[name="comment_'+key+'"]').show();
		return false;
	});
	$('.cancel').click(function() {
		var key = $(this).attr("key");
		$('div[name="comment_'+key+'"]').hide();
		return false;
	});
	$('a[name="send"]').click(function() {
		var key = $(this).attr("key");
		var keyName = $(this).attr("keyName");
		var context = $('textarea[name="textarea_'+key+'"]').val();
		if(context == ""){
			return false;
		}
		$.ajax({
				type : "POST",
				url : "/admin/activity/ask/answer/add.do",
				dataType : "json",
				data : {askId : key,context : context},
				success : function(data) {
					if(data.success){
						$('div[name="comment_'+key+'"]').hide();
						$('div[name="comment_'+key+'"]').after("<div class='contents'><div class='consu'>回复@"+keyName+" : "+context+"</div></div>");
						$('a[name="reply_'+key+'"]').hide();
						return false;
					}else{
						alert("回复失败");
						return false;
					}
				}
			});
	});
	// 绑定表情
	//$('.face-icon').SinaEmotion($('.text'));
	// AnalyticEmotion(inputText) 文字转图像
})
</script>

