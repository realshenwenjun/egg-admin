<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>活动列表</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">活动列表</h3>        
		<h3 class="small-title">管理图文内容</h3>
        <h3 class="small-title"><span class="fr"><a name="addActivity" href="javaScript:" class="add-btns" style="margin-left:795px;text-decoration:none;">添加新的活动</a></span></h3>
		<div class="sportList clearfix">
			<ul>
				<c:forEach items="${list}" var="activity">
            	<li>
                	<div class="ImgCon">
                    	<p class="ImgText">
                        	<span class="Img"><img src="${activity.thumbImg }" /></span>
                        </p>
                        <p class="textCon" style="margin:2px auto;">
                        	<span class="NameImg">${activity.name }</span>
                        </p>
                        <p class="textCon" style="margin:-5px auto;">
                        	<span class="redT">￥${activity.price }</span>
                            <span class="greyT">已售${activity.saleCount }</span>
                        </p>
                    </div>
                    <p class="ImgA" style="border-top:3px solid #8b8b8b">
                    	<a name="updateActivity" key="${activity.id }" href="javaScript:" class="bianji"></a>
						<a name="deleteActivity" key="${activity.id }" href="javaScript:" class="shanchu"></a>
                    </p>
                </li>
                </c:forEach>
            </ul>
		</div>
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='addActivity']").click(function() {
			$.ajax({
			type : "GET",
			url : "/admin/pages/platforms/activity_add.jsp",
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
			});
		});
		$("a[name='updateActivity']").click(function() {
			var id = $(this).attr("key");
			$.ajax({
			type : "GET",
			url : "/admin/activity/get.do?id="+id,
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
			});
		});
		$("a[name='deleteActivity']").click(function() {
			if(confirm("确认删除")) {
				var $a = $(this);
				var id = $(this).attr("key");
				$.ajax({
					type: "GET",
					url: "/admin/activity/delete.do?id=" + id,
					dataType: "json",
					success: function (data) {
						if (data.success) {
							$a.parent("p").parent("li").hide();
						} else {
							alert("删除失败");
						}
					}
				});
			}
		});
	});
</script>
</html>

