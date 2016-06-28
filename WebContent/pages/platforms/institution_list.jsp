<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<head>
<meta charset="utf-8">
<title>机构监测</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="w1200">
	<div class="waper fr">
		<h3 class="big-title">机构监测</h3>
        <div class="message-list clearfix search">
            <p class="searchIpt">
            	<a href="javaScript:" style="text-decoration:none;">搜索</a>
                <span class="deng-text1" id="phone-name">机构名称或电话号码</span>
                <input type="text" class="ipts" name="searchAdmin" value="${key }"/>
            </p>
        </div>
        
        <h3 class="title">机构列表</h3>
		<div class="message-list clearfix">
        <ul class="gg-list clearfix">
        	<c:forEach items="${list}" var="institution">
				<li class="teatcher">
					<span class="attr1"></span>
						<c:if test="${!empty institution.logo }">
							<img src="${institution.logo }" class="fl" width="90" height="80" alt="">
						</c:if>
						<c:if test="${empty institution.logo }">
							<img src="../image/logo.png" class="fl" width="88" height="88" alt="">
						</c:if>
					<p>
                    <span class="name">${institution.name }</span>
					<span class="sex">课程类别：${institution.courseType }</span>
					<span class="call">${institution.address }</span>
                    </p>
                    <a name="deleteInstitution" key="${institution.id }" href="javaScript:" class="blue">删除机构</a>
				</li>
			</c:forEach>
          </ul>
        </div>
        
        <c:if test="${page.totleCount != 0 }">
			<jsp:include page="/pages/page.jsp" flush="true"></jsp:include>
		</c:if>
	</div>
</div>
<script type="text/javascript" src="../js/jquery.min.js" ></script>
<script type="text/javascript">
	$.fn.setCursorPosition = function(position){
    if(this.lengh == 0) return this;
    return $(this).setSelection(position, position);
	};

	$.fn.setSelection = function(selectionStart, selectionEnd) {
    if(this.lengh == 0) return this;
    input = this[0];

    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd);
    }

    return this;
	};

	$.fn.focusEnd = function(){
	    this.setCursorPosition(this.val().length);
	};
	$(function(){
		var searchAdmin = $("input[name='searchAdmin']");
		if(searchAdmin.val() != ''){
			$('.deng-text1').hide();
			searchAdmin.focusEnd();
		}
		$('.deng-text1').click(function(){
			$(this).hide();
			$(this).next('input').focus();
		});

		$('.searchIpt input').focusout(function(){
			if ($(this).val() == '') {
				$(this).prev().show();
			};
		});
		$("input[name='searchAdmin']").change(function(){
			var searchAdmin = $(this);
			var key = searchAdmin.val();
	       $.ajax({ 
			type : "GET",
			url : "/admin/institution/list.do?key="+key,
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
				}
			});
    	});
    	$("a[name='deleteInstitution']").click(function(){
    		var deleteInstitution = $(this);
			var institutionId = deleteInstitution.attr("key");
	       $.ajax({
			type : "GET",
			url : "/admin/institution/delete.do?institutionId="+institutionId,
			dataType : "json",
			success : function(data) {
				if(data.success){
					deleteInstitution.parent("li").remove();
				}else{
					alert(data.result);
				}
			   }
			});
    	});
	});
</script>
</body>
</html>