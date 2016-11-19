<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>机构图片</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">机构图片</h3>        
		<h3 class="small-title">管理图文内容</h3>
		<h3 class="small-title"><span class="fr"><a name="upload" institutionId="${institutionId}" href="javaScript:" class="add-btns" style="margin-left:795px;text-decoration:none;">添加新相册</a></span></h3>
		<div style="display:none;">
			<form id="imgForm" method="post" action="/admin/file/upload.do"
				enctype="multipart/form-data" target="exec_target">
				<input name="file" type="file" style="display:none;">
				<input name="fileDir" type="hidden" value="propa">
			</form>
			<iframe id="exec_target" name="exec_target"></iframe>
		</div>
		<div class="sportList clearfix">
			<ul>
				<c:forEach items="${list}" var="propagate">
            	<li>
                	<div class="ImgCon">
                    	<p class="ImgText">
                        	<img src="${propagate.propagate }" width="100%" height="100%"/>
                        </p>
                    </div>
                    <p class="ImgA" style="border-top:2px solid #8b8b8b;">
<%--                     	<a name="getPropaDetail" key="${propagate.id }" href="${propagate.propagate }" class="detail"></a><!--  -->
 --%>						<a name="deletePropa" key="${propagate.id }" href="javaScript:" class="delete"></a>
                    </p>
                </li>
                </c:forEach>
            </ul>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='deletePropa']").click(function() {
			if(confirm("确认删除")) {
				var $a = $(this);
				var id = $(this).attr("key");
				$.ajax({
					type: "GET",
					url: "/admin/institution/propa/delete.do?institutionId=" + id,
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
		/**
		 **
		 */
		$("a[name='upload']").click(function() {
			$("input[name='file']").click();
		});
		$("input[name='file']").change(function() {
			var fileSize = $(this)[0].files[0].size;
			if	(fileSize > 5*1024*1024){
				alert("图片不可大于5Mb");
			}else{
				$("#imgForm").submit();
			}
		});
		//iframe加载响应，初始页面时也有一次，此时data为null。
		$("#exec_target").load(
				function() {
					var data = $(window.frames['exec_target'].document.body)
							.find("pre").html();
					//若iframe携带返回数据，则显示在feedback中
					if(data != null && data != undefined) {
						var jsonData = $.parseJSON(data);
						if (jsonData.result != null) {
							var propagate = jsonData.result;
							var institutionId = $("a[name='upload']").attr("institutionId");
							$.ajax({
								type: "POST",
								data:{institutionId:institutionId,propagate:propagate},
								url: "/admin/institution/propa/add.do",
								dataType: "json",
								success: function (data) {
									if (data.success) {
										goToPage("/institution/propa/list.do?institutionId=" + institutionId);
									} else {
										alert("删除失败");
									}
								}
							});
						} else {
							alert("上传失败");
						}
					}
				});
	});
</script>
</html>

