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
<head>
<meta charset="utf-8">
<title>轮播图管理</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="../image/logo.ico" />
</head>
<body>
	<div class="w1200">
		<div class="waper fr">
			<h3 class="big-title">轮播图管理</h3>
			<h3 class="small-title">管理轮播图</h3>
			<h3 class="small-title">
				<span class="fr"><a href="javascript:" name="addCarousel"
					url="/carousel/add/page.do" class="add-btns" style="margin-left:795px;text-decoration:none;">添加轮播图</a>
				</span>
			</h3>
			<div class="silder-list clearfix">
				<ul class="clearfix">
					<c:forEach items="${list}" var="carousel">
						<li><img src="${carousel.path}" class="fl" width="460"
							height="153" alt="" />
							<div class="operation">
								<span class="fl"><fmt:formatDate
										value="${carousel.createTime}" pattern="yyyy-MM-dd" /> </span> <a
									name="delete" url="/carousel/delete.do?id=${carousel.id }"
									href="javaScript:">删除</a><a href="javaScript:" name="update" url="/carousel/get.do?id=${carousel.id }">编辑</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>

			<!-- <div class="page clearfix">
			<span class="p-num">
				<a class="pn-prev" href=""><i>&lt;</i><em>上一页</em></a>
				<a href="" class="curr">1</a>
				<a href="" class=" ">2</a>
				<a href="" class=" ">3</a>
				<a href="" class=" ">4</a>
				<a href="" class=" ">5</a>
				<b class="pn-break ">…</b>
				<a href="" class="">8</a>
				<a class="pn-next" href="">下一页<i>&gt;</i></a>
			</span>
			<span class="p-skip">
				<em>共<b>8</b>页&nbsp;&nbsp;去第</em><input class="input-txt" ><em>页</em>
				<a class="btn btn-default">确定</a>
			</span>
		</div> -->
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='addCarousel']").click(function(){
			var url = $(this).attr("url");
	            $.ajax({
	                type: "GET",
	                url: "/admin"+url,
	                dataType: "html",
	                success: function(data){
                        $("#ajaxJsp").html(data);
	                }
	            });
	        });
	        $("a[name='update']").click(function(){
			var url = $(this).attr("url");
	            $.ajax({
	                type: "GET",
	                url: "/admin"+url,
	                dataType: "html",
	                success: function(data){
                        $("#ajaxJsp").html(data);
	                }
	            });
	        });
		$("a[name='delete']").click(function() {
			var $a = $(this);
			var url = $a.attr("url");
			$.ajax({
				type : "GET",
				url : "/admin" + url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$a.parent("div").parent("li").hide();
					} else {
						alert("删除失败");
					}
				}
			});
		});
	});
</script>

