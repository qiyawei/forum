<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kaishengit.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: qiyawei
  Date: 2016/4/7
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>首页</title>
  <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
  <div class="box">
    <div class="talk-item">
      <ul class="topic-type unstyled inline" style="margin-bottom:0px;">
        <li class="${empty param.typeid?"active":""}"><a href="/index.do">全部</a></li>

        <c:forEach items="${typeList}" var="type">
          <li class="${param.typeid == type.id ? "active":""}"><a href="/index.do?typeid=${type.id}">${type.typename}</a></li>
        </c:forEach>

      </ul>
    </div>


<C:forEach items="${page.items}" var="topic">
  <div class="talk-item">
    <table class="talk-table">
      <tr>
        <td width="50">
          <img class="avatar" src="http://7xq3kx.com1.z0.glb.clouddn.com/${topic.user.img}?imageView2/1/w/40/h/40" alt="">
        </td>
        <td width="80">
          <a href="">${topic.user.username}</a>
        </td>
        <td width="auto">
          <a href="/topic/view.do?id=${topic.id}">${topic.title}</a>
        </td>

        <C:if test="${topic.replynum > 0}">
          <td width="50" align="center">
            <span class="badge">12</span>
          </td>
        </C:if>

      </tr>
    </table>
  </div>
</C:forEach>

    <c:if test="${page.totalPages > 1}">
      <div class="pagination pagination-right" id="pages" style="margin:0px 20px 20px 0px"></div>
    </c:if>


  </div>
  <!--box end-->
</div>
<!--container end-->
<div class="footer">
  <div class="container">
    Copyright © 2015 kaishengit
  </div>
</div>
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
  $(function(){
    $("#pages").twbsPagination({
      totalPages:${page.totalPages},
      visiblePages:5,
      first:'首页',
      last:'末页',
      prev:'上一页',
      next:'下一页',
      href:"?typeid=${param.typeid}&p={{number}}"
    });
  });
</script>
</body>
</html>
