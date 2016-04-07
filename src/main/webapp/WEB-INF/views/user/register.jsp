<%--
  Created by IntelliJ IDEA.
  User: qiyawei
  Date: 2016/4/7
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>注册用户</title>
  <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
  <div class="box">
    <div class="box-header">
      <span class="title"><i class="fa fa-sign-in"></i> 注册账号</span>
    </div>

    <form action="" class="form-horizontal">
      <div class="control-group">
        <label class="control-label">账号</label>
        <div class="controls">
          <input type="text">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">密码</label>
        <div class="controls">
          <input type="text">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">重复密码</label>
        <div class="controls">
          <input type="text">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">电子邮件</label>
        <div class="controls">
          <input type="text">
        </div>
      </div>
      <div class="form-actions">
        <button class="btn btn-primary">注册</button>
        <a href="login.html">登录</a>
      </div>

    </form>

  </div>
  <!--box end-->
</div>
</body>
</html>
