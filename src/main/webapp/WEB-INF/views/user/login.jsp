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
  <title>用户登录</title>
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
      <span class="title"><i class="fa fa-sign-in"></i> 登录</span>
    </div>
    <c:choose>
      <c:when test="${param.state == 'logout'}">
        <div class="alert">你已经安全退出</div>
      </c:when>
      <c:when test="${param.state == 'forget'}">
        <div class="alert">密码设置成功，请重新登陆</div>
      </c:when>
    </c:choose>

    <form method="post" class="form-horizontal" id="loginForm">
      <div class="control-group">
        <label class="control-label">账号</label>
        <div class="controls">
          <input type="text" name="username">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">密码</label>
        <div class="controls">
          <input type="password" name="password">
        </div>
      </div>


      <script type="text/template" id="template">
        <div class="control-group">
          <label class="control-label">验证码</label>
          <div class="controls">
            <input type="text" name="patchca"/><br/>
            <a  href="javascript:;" id="patchca">看不清换一张</a> <img src="/patchca.do" id="img" />
          </div>
        </div>
      </script>
      <div id="num">

      </div>


      <div class="control-group">
        <label class="control-label"></label>
        <div class="controls">
          <a href="/forget.do">忘记密码</a>
        </div>
      </div>

      <div class="form-actions">
        <button type="button" id="btn" class="btn btn-primary">登录</button>

        <a class="pull-right" href="/register.do">注册账号</a>
      </div>

    </form>



  </div>
  <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script>
  $(function(){
    $("#btn").click(function(){
      $("#loginForm").submit();
    });

    $("#loginForm").validate({
      errorClass:"text-error",
      errorElement:"span",
      rules:{
        username:{
          required:true
        },
        password:{
          required:true
        }
      },
      messages:{
        username:{
          required:"请输入账号"
        },
        password:{
          required:"请输入密码"
        }
      },
    submitHandler:function(form){
      $.ajax({
        url:"/login.do",
        type:"post",
        data:$(form).serialize(),
        beforeSend:function(){
          $("#btn").text("登陆中");
          $("#btn").attr("disabled","disabled")
        },

        success:function(json){
          if(json.state == 'error') {
            alert(json.message);
            var source = $("#template").html();
            if(json.num > 3){
              $("#num").html(source)
            }

          } else {
           window.location.href = "/index.do";
          }
        },

        error:function(){
          alert("服务器出错了")
        },
        complete:function(){
          $("#btn").text("登陆");
          $("#btn").removeAttr("disabled");

        }
      })
    }

    });


  })

</script>
</body>
</html>
