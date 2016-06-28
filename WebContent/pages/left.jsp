<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar-default sidebar" role="navigation">
  <div class="sidebar-nav navbar-collapse">
    <ul class="nav" id="side-menu">
     	<li>
          <a href="javaScript:" name="ajaxModel" url="/pages/change_password.jsp"> 修改密码<span class="nav nav-second-level"></span></a>
        </li>
      <c:if test="${user.type == 0}">
        <li>
          <a href="#"> 素材管理<span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="javaScript:" name="ajaxModel" url="/information/list.do"> 新闻资讯</a>
            </li>
            <li>
              <a href="javaScript:" name="ajaxModel" url="/announcement/list.do"> 公告管理</a>
            </li>
            <li>
              <a href="javaScript:" name="ajaxModel" url="/carousel/list.do"> 轮播图管理</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"> 活动维护<span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="javaScript:" name="ajaxModel" url="/activity/list.do"> 活动列表</a>
            </li>
            <li>
              <a href="javaScript:" name="ajaxModel" url="/activity/census.do"> 活动统计</a>
            </li>
            <li>
              <a href="javaScript:" name="ajaxModel" url="/activity/ask/list.do"> 咨询服务</a>
            </li>
            <li>
              <a href="javaScript:" name="ajaxModel" url="/activity/feedback/list.do"> 活动反馈</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"> 机构管理<span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="javaScript:" name="ajaxModel" url="/institution/list.do"> 机构监测</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"> 数据统计<span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="javaScript:" name="ajaxModel" url="/census/student/list.do"> 学生数量</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#"> 团队成员<span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <li>
              <a href="javaScript:" name="ajaxModel" url="/user/supper/admin/list.do"> 账号管理</a>
            </li>
          </ul>
        </li>
      </c:if>
    </ul>
  </div>
  <!-- /.sidebar-collapse -->
</div>
