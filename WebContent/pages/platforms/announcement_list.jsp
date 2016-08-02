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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta charset="utf-8">
<title>公告管理</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="../image/logo.ico" />
</head>
<body>
	<div class="w1200">
		<div class="waper fr">
			<h3 class="big-title">公告管理</h3>
			<h3 class="small-title">
				<span class="fl">管理图文消息</span><span class="fr"><a name="addAnnouncement" href="javaScript:" class="add-btns" style="margin-left:780px;text-decoration:none;">添加新公告</a>
				</span>
			</h3>
			<ul class="gg-list clearfix">
				<c:forEach items="${list}" var="announcement">
					<li><img src="${announcement.annoImage }" class="fl"
						width="90" height="80" alt="" />
						<div class="contents fl">
							<p>${announcement.title }</p>
							<p><c:if test="${fn:length(announcement.content) < 50 }">${announcement.content}</c:if>
							<c:if test="${fn:length(announcement.content) >= 50 }">${fn:substring(announcement.content,0,50)}...</c:if></p>
						</div>
						<div class="dates fl">
							<fmt:formatDate value="${announcement.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
						<div class="fr">
							<a href="javaScript:" name="updateAnnouncement" url="/announcement/get.do?id=${announcement.id }">编辑</a>/<a href="javaScript:" name="deleteAnnouncement" url="/announcement/delete.do?id=${announcement.id }">删除</a>
						</div></li>
				</c:forEach>
			</ul>
			<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='addAnnouncement']").click(function() {
			$.ajax({
			type : "GET",
			url : "/admin/pages/platforms/announcement_add.jsp",
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
			});
		});
		$("a[name='deleteAnnouncement']").click(function() {
			if(confirm("确认删除")) {
				var $a = $(this);
				var url = $a.attr("url");
				$.ajax({
					type: "GET",
					url: "/admin" + url,
					dataType: "json",
					success: function (data) {
						if (data.success) {
							$a.parent("div").parent("li").hide();
						} else {
							alert("删除失败");
						}
					}
				});
			}
		});
		$("a[name='updateAnnouncement']").click(function() {
			var $a = $(this);
			var url = $a.attr("url");
			$.ajax({
				type : "GET",
				url : "/admin" + url,
				dataType : "html",
				success : function(data) {
					$("#ajaxJsp").html(data);
				}
			});
		});
	});
</script>

