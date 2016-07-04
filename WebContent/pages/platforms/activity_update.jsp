<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<title>编辑活动</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<script charset="utf-8" src="../kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
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
		window.editor = K.create('textarea[name="detail"]',options);
		/**
		 **
		 */
		$("a[name='updateActivity']").click(function() {
			window.editor.sync();
			var name = $("input[name='name']").val();
			var title = $("input[name='title']").val();
			var contact = $("input[name='contact']").val();
			var url = $("input[name='url']").val();
			var oldPrice = $("input[name='oldPrice']").val();
			var price = $("input[name='price']").val();
			var shortDetail = $("textarea[name='shortDetail']").val();
			var thumbImg = $("input[name='thumbImg']").val();
			var detail = $("textarea[name='detail']").val();
			var beginDate = $("input[name='beginDate']").val();
			var endDate = $("input[name='endDate']").val();
			var reserveBeginDate = $("input[name='reserveBeginDate']").val();
			var reserveEndDate = $("input[name='reserveEndDate']").val();
			$.ajax({
				type : "POST",
				url : "/admin/activity/update.do",
				data : {
					id : "${activity.id}",
					name : name,
					title : title,
					contact : contact,
					url : url,
					oldPrice : oldPrice,
					price : price,
					shortDetail : shortDetail,
					thumbImg : thumbImg,
					detail : detail,
					beginDate : beginDate,
					endDate : endDate,
					reserveBeginDate : reserveBeginDate,
					reserveEndDate : reserveEndDate
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
							$("#load_img").html("<img src='" + jsonData.result + "' width='200' height='150'/>");
							$("input[name='thumbImg']").val(jsonData.result);
						} else {
							alert("上传失败");
							$("#load_img").html("");
							$("#input[name='thumbImg']").val("");
						}
					}
				});
	});
</script>
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">编辑活动</h3>
		<h3 class="small-title">管理图文信息</h3>
		<h3 class="title">活动信息</h3>
		<div class="inpour clearfix">
			<label class="w95 fl">活动名称：</label>
			<input type="text" class="inputst w565 fl" name="name" value="${activity.name }" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">活动标题：</label>
			<input type="text" class="inputst w565 fl" name="title" value="${activity.title }" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">客服电话：</label>
			<input type="text" class="inputst w565 fl" name="contact" value="${activity.contact }" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">活动外部链接：</label>
			<input type="text" class="inputst w565 fl" name="url" value="${activity.url }" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">活动价格：</label>
			<input type="text" class="inputst w240 fl" name="oldPrice" value="${activity.oldPrice }" placeholder="">
			<label class="w95 fl" style="margin-left: 0;text-align: right;width: 83px;">优惠价格：</label>
			<input type="text" class="inputst w240 fl" name="price" value="${activity.price }" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">开始时间：</label>
			<input type="text" class="inputst w240 fl" name="beginDate" value="<fmt:formatDate value="${activity.beginDate}"
								pattern="yyyy-MM-dd" />" placeholder="">
			<label class="w95 fl" style="margin-left: 0;text-align: right;width: 83px;">结束时间：</label>
			<input type="text" class="inputst w240 fl" name="endDate" value="<fmt:formatDate value="${activity.endDate}"
								pattern="yyyy-MM-dd" />" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">预约开始：</label>
			<input type="text" class="inputst w240 fl" name="reserveBeginDate" value="<fmt:formatDate value="${activity.reserveBeginDate}"
								pattern="yyyy-MM-dd" />" placeholder="">
			<label class="w95 fl" style="margin-left: 0;text-align: right;width: 83px;">预约结束：</label>
			<input type="text" class="inputst w240 fl" name="reserveEndDate" value="<fmt:formatDate value="${activity.reserveEndDate}"
								pattern="yyyy-MM-dd" />" placeholder="">
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">活动简介：</label>
			<textarea name="shortDetail" value="${activity.shortDetail}">${activity.shortDetail}</textarea>
		</div>
		<div class="inpour clearfix">
			<label class="w95 fl">封面图片：</label>
			<div class="imgs fl" id="load_img">
				<img src="${activity.thumbImg}" width="200" height="150"/>
			</div>
			<div class="texts fl">
				<p>图片格式必须为：bmp,jpeg,jpg,gif;图片不可大于5Mb</p>
				<p>图片内容不得涉及政治敏感及色情</p>
				<a href="javaScript:" name="upload" class="btns">上&nbsp;传</a>
				<input type="hidden" name="thumbImg" value="${activity.thumbImg}"/>
				<form id="imgForm" method="post" action="/admin/file/upload.do"
				enctype="multipart/form-data" target="exec_target">
				<input name="file" type="file" style="display:none;">
				<input name="fileDir" type="hidden" value="activity">
				</form>
				<iframe id="exec_target" name="exec_target" style="display:none;"></iframe>
			</div>
		</div>
		<h3 class="title">活动详细</h3>
		<div class="kindeditors">
			<textarea name="detail" style="width:1000px;height:300px;">${activity.detail}</textarea>
		</div>
		<h3 class="title"></h3>
		<div class="btns-paper">
			<a href="javaScript:" name="updateActivity" class="submit">保存</a>
		</div>
	</div>
</div>
</body>
</html>