
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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

    <form id="regForm" class="form-horizontal">
      <div class="control-group">
        <label class="control-label">账号</label>
        <div class="controls">
          <input type="text" name="username">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">密码</label>
        <div class="controls">
          <input type="text" name="password" id="password">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">重复密码</label>
        <div class="controls">
          <input type="text"  name="repassword">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">电子邮件</label>
        <div class="controls">
          <input type="text" name="email">
        </div>
      </div>

      <div class="form-actions">
        <button type="button" id="regBtn" class="btn btn-primary">注册</button>
        <span id="regMsg" class="hide">注册成功,<span class="sec">3</span>秒后自动跳转到登录页面</span>
        <a class="pull-right" href="/login.do">登录</a>
      </div>
    </form>

  </div>
  <!--box end-->
</div>
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script>
  $(function(){

    $("#regBtn").click(function(){

      $("#regForm").submit();
    });
    $("#regForm").validate({
      errorClass:'text-error',
      errorElement:'span',
      rules:{
        username:{
          required:true,
          rangelength:[3,18],
          remote:"/validate/username.do"
        },
        password:{
          required:true,
          rangelength:[6,18]
        },
        repassword:{
          required:true,
          rangelength:[6,18],
          equalTo:'#password'
        },
        email:{
          required:true,
          email:true,
          remote:"/validate/email.do"
        }
      },
      messages:{
        username:{
          required:"账号不能为空",
          rangelength:"账号长度为6到18位",
          remote:"该账号已被占用"
        },
        password:{
          required:"密码不能为空",
          rangelength:"密码长度为6到18位"
        },
        repassword:{
          required:"确认密码不能为空",
          rangelength:"密码长度为6到18位",
          equalTo:"两次密码不一致"
        },
        email:{
          required:"请输入电子邮件",
          email:"电子邮件格式错误",
          remote:"该电子邮件已被注册"
        }
      },


      submitHandler:function(form){
        $.post("/register.do",$(form).serialize())
                .done(function(result){
                  if(result.state == "error") {
                    alert(result.message);
                  } else {
                    $("#regMsg").removeClass("hide");
                    var sec = 3;
                    setInterval(function(){
                      sec--;
                      if(sec == 0) {
                        window.location.href = "/login.do";
                        return;
                      }
                      $("#regMsg .sec").text(sec);
                    },1000);
                  }
                }).fail(function(){
                  alert("服务器异常，请稍后再试");
                });
      }


    });






  });
</script>
</body>
</html>
