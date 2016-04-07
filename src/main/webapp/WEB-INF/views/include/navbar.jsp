<%--
  Created by IntelliJ IDEA.
  User: qiyawei
  Date: 2016/4/7
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-bar">
  <div class="container">
    <a href="/index.do" class="brand">
      <i class="fa fa-reddit-alien"></i>
    </a>
    <ul class="unstyled inline pull-right">
      <c:choose>
        <c:when test="${not empty sessionScope.user}">
          <li>
            <a href="#">
              <img src="http://7xp5t4.com1.z0.glb.clouddn.com/Fqb8f9uDknAt2ilBoY-ipSZRMes-?imageView2/1/w/20/h/20" class="img-circle" alt="">
            </a>
          </li>
          <li>
            <a href=""><i class="fa fa-plus"></i></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-bell"></i></a>
          </li>
          <li>
            <a href="setting.html"><i class="fa fa-cog"></i></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-sign-out"></i></a>
          </li>
        </c:when>
        <c:otherwise>
          <li>
            <a href="/login.do"><i class="fa fa-sign-in"></i></a>
          </li>
        </c:otherwise>
      </c:choose>

    </ul>
  </div>
</div>