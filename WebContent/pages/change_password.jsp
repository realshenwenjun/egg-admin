<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="../css/common.css" rel="stylesheet" type="text/css">
 <body style="background:#e8e8e8;">
<div class="topCon">
  <!--banner-->
  <div class="banner">
	<div class="registerCon getpasswordCon">
		<h3 class="getpassword">
			重设密码
		</h3>
		<ul>
		     <li class="deng" style="height: 139px;">
		     	<div class="dengT" style="line-height: 36px;">原始密码</div>
		     	<div class="dengC" style="line-height: 14px;">
			     	<input class="ipt" type="password" name="oldPassword" value=""/>
		     	</div>
				 <div class="dengT" style="line-height: 38px;">新密码</div>
				 <div class="dengC" style="line-height: 51px;">
					 <input class="ipt" type="password" name="newPassword" value=""/>
				 </div>
				 <div class="dengT" style="line-height: 41px;">确认密码</div>
				 <div class="dengC" style="line-height: 49px;">
					 <input class="ipt" type="password" name="conformPassword" value=""/>
				 </div>
		     </li>
		     <li>
		     	<a href="javaScript:" name="changePassword"  class="btnLogin">重设密码</a>
		     </li>
		</ul>
	</div>
  </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery.min.js" ></script>
<script type="text/javascript">
	$(function(){
		$("a[name='changePassword']").click(function(){
			var oldPassword = $("input[name='oldPassword']").val();
			var newPassword = $("input[name='newPassword']").val();
			var conformPassword = $("input[name='conformPassword']").val();
			$.ajax({
				type: "POST",
				url: "/admin/user/changePassword.do",
				data:{oldPassword:oldPassword,newPassword:newPassword,conformPassword:conformPassword},
				dataType: "json",
				success: function(data){
					if(data.code == 000){
						alert("修改成功");
						window.location.href="/admin/pages/index.jsp";
						return;
					}else{
						alert(data.message);
						return;
					}
				}
			});
		});
	});
</script>
</html>
