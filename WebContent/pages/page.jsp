<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.dskj.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page clearfix" style="width: 950px;">
				<span class="p-num">
					<c:if test="${page.totleCount > 0 }">
					<c:if test="${page.pageNo > 1 }">
						<a name="skipPage" class="btn btn-default" href="javaScript:" value="${page.pageNo - 1}"><i>&lt;</i><em>上一页</em></a>
					</c:if>
					<c:if test="${page.pageNo == 1 }">
						<a class="btn"><i>&lt;</i><em>上一页</em></a>
					</c:if>
					
					
					<c:if test="${page.pageCount <= 10 }">
						<% 
							Page p = (Page) request.getAttribute("page");
							for(int i = 0;i < p.getPageCount();i++){
								if(p.getPageNo() == (i+1)){
						%>
								<a href="javaScript:" class="btn curr"><%=i+1%></a>
						<%
								}else{
						%>
								<a name="skipPage" href="javaScript:" class="btn btn-default" value="<%=i+1%>"><%=i+1%></a>
						<%
								}
							}
						 %>
					</c:if>
					<c:if test="${page.pageCount > 10 }">
						<c:if test="${page.pageNo > 4 }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo - 4 }">${page.pageNo - 4 }</a> 
						</c:if>
						<c:if test="${page.pageNo > 3 }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo - 3 }">${page.pageNo - 3 }</a> 
						</c:if>
						<c:if test="${page.pageNo > 2 }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo - 2 }">${page.pageNo - 2 }</a> 
						</c:if>
						<c:if test="${page.pageNo > 1 }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo - 1 }">${page.pageNo - 1 }</a> 
						</c:if>
						<a href="javaScript:" class="btn curr">${page.pageNo }</a>
						<c:if test="${page.pageNo + 1 <= page.pageCount }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo + 1 }">${page.pageNo + 1 }</a> 
						</c:if>
						<c:if test="${page.pageNo + 2 <= page.pageCount }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo + 2 }">${page.pageNo + 2 }</a> 
						</c:if>
						<c:if test="${page.pageNo + 3 <= page.pageCount }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo + 3 }">${page.pageNo + 3 }</a> 
						</c:if>
						<c:if test="${page.pageNo + 4 <= page.pageCount }">
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageNo + 4 }">${page.pageNo + 4 }</a> 
						</c:if>
						<c:if test="${page.pageNo + 4 < page.pageCount }">
							<b class="pn-break ">…</b>
							<a name="skipPage" href="javaScript:" class="btn btn-default" value="${page.pageCount}">${page.pageCount}</a>
						</c:if> 
					</c:if>
					
					<c:if test="${page.pageNo < page.pageCount }">
						<a name="skipPage" class="btn btn-default" href="javaScript:" value="${page.pageNo + 1}">下一页<i>&gt;</i></a> 
					</c:if>
					<c:if test="${page.pageNo == page.pageCount }">
						<a class="btn">下一页<i>&gt;</i></a> 
					</c:if>
					</c:if>
				</span> 
				<span class="p-skip"> 
					<input type="hidden" name="pageCount" value="${page.pageCount }" /> <em>共<b>${page.pageCount }</b>页&nbsp;&nbsp;去第</em><input class="input-txt" name="go"><em>页</em> 
					<a class="btn btn-default" href="javaScript:" name="submit">确定</a> </span>
			</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='submit']").click(function() {
			var go = $("input[name='go']").val();
			if (go < 0) {
				return;
			}
			var pageCount = $("input[name='pageCount']").val();
			if (parseInt(go) > parseInt(pageCount)) {
				return;
			}
			announcementList(go);
		});
		$("a[name='skipPage']").click(function() {
			var pageNo = $(this).attr("value");
			announcementList(pageNo);
		});
	});
	function announcementList(pageNo) {
		$.ajax({
			type : "GET",
			url : "/admin${page.url}",
			data : {pageNo : pageNo,pageSize : "${page.pageSize}"},
			dataType : "html",
			success : function(data) {
				$("#ajaxJsp").html(data);
			}
		});
	}
</script>