<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>学生数量</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">学生数量（${page.totleCount }）</h3>
		<div class="message-list clearfix">
			<ul class="a-tab">
            	<li name="student" key="/census/student/list.do"><span>学生列表</span></li>
                <li name="student" key="/census/student/list.do"><span class="current" >按分机构统计</span></li>
                <li name="student" key="/census/student/list.do"><span>按课程统计</span></li>
            </ul>
            <div class="a-tab-1">
            	<table border="0" cellpadding="0" cellspacing="0" class="tableL">
                	<tr>
                    	<td class="blue">北平画室1</td>
                        <td>28</td>
                    </tr>
                    <tr>
                    	<td class="blue">北平画室2</td>
                        <td>30</td>
                    </tr>
                </table>
            </div>
		</div>
		<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("li[name='student']").click(function(){
			var url = $(this).attr("key");
			$.ajax({
				type : "GET",
				url : "/admin"+url,
				dataType : "html",
				success : function(data) {
					$("#ajaxJsp").html(data);
				}
			});
		});
	});
</script>
</html>