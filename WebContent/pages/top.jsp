<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="topLogo">
    <div class="topLogoCon">
  		<span>
  			<a target="_self" href="./index.jsp"><img src="../images/logo.png"></a>
	  	</span>
        <ul>
            <li>
                <a target="_self" href="../index.html">首页</a>
            </li>
            <li>
                <a target="_self" href="../aboutUs.html">关于我们</a>
            </li>
            <li>
                <a target="_self" href="../contact.html">联系我们</a>
            </li>
            <li>
                <a target="_self" href="./index.jsp">进入管理</a>
            </li>
            <c:if test="${empty user}">
                <li>
                    <a target="_self" href="register.html">注册</a>/
                    <a target="_self" href="login.html">登录</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="./index.jsp">
        <c:if test="${user.type == 0}">
            蛋生云-平台维护中心
        </c:if>
    </a>
</div>
<ul class="nav navbar-top-links navbar-right">
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
            <li><a href="#"><i class="fa fa-user fa-fw"></i><c:if test="${user.name == null}"> ${user.phone}</c:if><c:if
                    test="${user.name != null}"> ${user.name}</c:if></a>
            </li>
            <!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a> -->
            </li>
            <li class="divider"></li>
            <li><a href="/admin/user/login_out.do"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>
    <!-- /.dropdown -->
</ul>