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
<title>添加机构</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/region.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/**
		 **
		 */
		$("a[name='addInformation']").click(function() {
			var logo = $("input[name='path']").val();
			var name = $("input[name='name']").val();
			var address = $("input[name='address']").val();
			var courseType = $("input[name='courseType']").val();
			var tel = $("input[name='tel']").val();
			var teacherCount = $("input[name='teacherCount']").val();
			var summary = $("textarea[name='summary']").val();
			var regionId = $("select[name='city']").val();
			$.ajax({
				type : "POST",
				url : "/admin/institution/add.do",
				data : {
					logo : logo,
					name : name,
					summary : summary,
					address : address,
					tel : tel,
					teacherCount : teacherCount,
					courseType : courseType,
					regionId : regionId
				},
				dataType : "html",
				success : function(data) {
					var ph = "${phone}";
					if(ph != null && ph != "") {
						alert("添加成功，请牢记管理员手机号：" + ph)
						$("#ajaxJsp").html(data);
					}else{
						alert("添加失败!");
					}
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
			<h3 class="big-title">添加机构</h3>

			<h3 class="small-title">编辑图文消息</h3>
			<div class="inpour clearfix">
				<label class="w95 fl">机构名称：</label>
				<input type="text" class="inputst w565 fl" name="name" value="" placeholder="">
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">课程类别：</label>
				<input type="text" class="inputst w565 fl" name="courseType" value="" placeholder="">
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">联系方式：</label>
				<input type="text" class="inputst w565 fl" name="tel" value="" placeholder="">
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">老师数量：</label>
				<input type="text" class="inputst w565 fl" name="teacherCount" value="" placeholder="">
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">详细地址：</label>
				<input type="text" class="inputst w565 fl" name="address" value="" placeholder="">
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">省：</label>
				<select class="inputst fl" name="province" url="/region/list.do">
				</select>
			</div>
			<div class="inpour clearfix">
				<label class="w95 fl">市：</label>
				<select class="inputst fl" name="city">
				</select>
			</div>
			<h3 class="title">机构logo</h3>

			<div class="img-all">
				<div id="load_img" class="imgs fl" ></div>
				<div class="texts fl">
					<p>图片格式必须为：bmp,jpeg,jpg,gif;图片不可大于5Mb</p>

					<p>图片建议尺寸：900像素*500像素</p>

					<p>图片内容不得涉及政治敏感及色情</p>
					<a href="javaScript:" name="upload" class="btns">上&nbsp;传</a>
					<form id="imgForm" method="post" action="/admin/file/upload.do"
						enctype="multipart/form-data" target="exec_target">
						<input name="file" type="file" style="display:none;">
						<input name="fileDir" type="hidden" value="institution">
					</form>
					<iframe id="exec_target" name="exec_target" style="display:none;"></iframe>
				</div>
				<input type="hidden" name="path" />
			</div>
			
			<h3 class="title">机构简介</h3>
			<div class="kindeditors">
				<textarea name="summary" width="1024"></textarea>
			</div>
			<div class="btns-paper">
				<a href="javaScript:" name="addInformation" class="submit">保存</a>
			</div>
		</div>
	</div>
</body>

