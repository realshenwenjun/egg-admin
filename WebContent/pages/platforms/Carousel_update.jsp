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
<title>编辑轮播图</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<script charset="utf-8" src="../kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript">
	var options = {
		filterMode : false,
		uploadJson : "/admin/image/upload.do",
		extraFileUploadParams : {
			fileDir : "imageRoom"
		},
		fillDescAfterUploadImage : true,
		filePostName : "file"
	};
	KindEditor.ready(function(K) {
		window.editor = K.create('textarea[name="context"]',options);
		/**
		 **
		 */
		$("a[name='updateCarousel']").click(function() {
			window.editor.sync();
			var id = $("input[name='id']").val();
			var val = $("input[name='val']").val();
			var path = $("input[name='path']").val();
			var title = $("input[name='title']").val();
			 if (path == null || path == "") {
				alert("请先上传轮播图");
				return;
			}
			var context = $("textarea[name='context']").val();
			$.ajax({
				type : "POST",
				url : "/admin/carousel/update.do",
				data : {
					id : id,
					val : val,
					title : title,
					path : path,
					context : context
				},
				dataType : "html",
				success : function(data) {
					$("#ajaxJsp").html(data);
				}
			});
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
							$("#load_img").html("<img src='" + jsonData.result + "'  width='200' height='150'/>");
							$("input[name='path']").val(jsonData.result);
						} else {
							alert("上传失败");
							$("#load_img").html("");
							$("#input[name='path']").val("");
						}
					}
				});
	});
</script>
</head>
<body>
	<div class="w1200">
		<div class="waper fr">
			<h3 class="big-title">编辑轮播图</h3>
			<input type="hidden" name="id" value="${carousel.id }">
			<h3 class="small-title">提示: 可以连接发布地址或者平台发布自己轮播图内容</h3>
			<h3 class="title">标题</h3>

			<div class="kindeditors">
				<input type="text" name="title" style="width:500;" value="${carousel.title}">
			</div>
			<h3 class="title">轮播连接</h3>

			<div class="kindeditors">
				<input type="text" name="val" style="width:500;" value="${carousel.val}">
			</div>
			<h3 class="title">轮播图片</h3>

			<div class="img-all">
				<div id="load_img" class="imgs fl" >
					<img alt="" src="${carousel.path}" width="200" height="150">
				</div>
				<div class="texts fl">
					<p>图片格式必须为：bmp,jpeg,jpg,gif;图片不可大于5Mb</p>

					<p>图片建议尺寸：900像素*500像素</p>

					<p>图片内容不得涉及政治敏感及色情</p>
					<a href="javaScript:" name="upload" class="btns">上&nbsp;传</a>
					<form id="imgForm" method="post" action="/admin/file/upload.do"
						enctype="multipart/form-data" target="exec_target">
						<input name="file" type="file" style="display:none;">
						<input name="fileDir" type="hidden" value="carousel">
					</form>
					<iframe id="exec_target" name="exec_target" style="display:none;"></iframe>
				</div>
				<input type="hidden" name="path" value="${carousel.path}"/>
			</div>
			<h3 class="title">正文</h3>

			<div class="kindeditors">
				<textarea name="context" style="width:1000px;height:300px;">${carousel.context}</textarea>
			</div>
			<h3 class="title"></h3>

			<div class="btns-paper">
				<a href="javaScript:" name="updateCarousel" class="submit">保存</a>
			</div>
		</div>
	</div>
</body>

