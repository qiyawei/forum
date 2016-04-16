<%--
  Created by IntelliJ IDEA.
  User: qiyawei
  Date: 2016/4/9
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>用户设置</title>
  <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/static/css/style.css">
  <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
  <div class="box">
    <div class="box-header">
      <span class="title"><i class="fa fa-cog"></i> 基本设置</span>
    </div>

    <form action="" class="form-horizontal" id="emailForm">
      <div class="control-group">
        <label class="control-label">账号</label>
        <div class="controls">
          <input type="text" value="${sessionScope.user.username}" readonly>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">电子邮件</label>
        <div class="controls">
          <input type="text" name="email" value="${sessionScope.user.email}">
        </div>
      </div>
      <div class="form-actions">
        <button class="btn btn-primary" type="button" id="btnSaveEmail">保存</button>
        <span id="emailHelp" class="text-success hide">电子邮件修改成功</span>
      </div>

    </form>

  </div>
  <!--box end-->
  <div class="box">
    <div class="box-header">
      <span class="title"><i class="fa fa-key"></i> 密码设置</span>
      <span class="pull-right muted" style="font-size: 12px">如果你不打算更改密码，请留空以下区域</span>
    </div>

    <form action=""  method="post" class="form-horizontal" id="passwordForm">
      <div class="control-group">
        <label class="control-label">密码</label>
        <div class="controls">
          <input type="password" name="password" id="password">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">重复密码</label>
        <div class="controls">
          <input type="password" name="repassword">

        </div>
      </div>
      <div class="form-actions">
        <button class="btn btn-primary" id="btnSavePassword" type="button">保存</button>
        <span id="passwordHelp" class="text-success hide">密码修改成功,请重新登录</span>
      </div>

    </form>

  </div>
  <!--box end-->

  <div class="box">
    <div class="box-header">
      <span class="title"><i class="fa fa-user"></i> 头像设置</span>
    </div>

    <form action="" method="post" class="form-horizontal">
      <div class="control-group">
        <label class="control-label">当前头像</label>
        <div class="controls">
          <img src="http://7xq3kx.com1.z0.glb.clouddn.com/${sessionScope.user.img}?imageView2/1/w/40/h/40" class="img-circle img1" alt="">
        </div>
      </div>
      <hr>
      <p style="padding-left: 20px">关于头像的规则</p>
      <ul>
        <li>禁止使用任何低俗或者敏感图片作为头像</li>
        <li>如果你是男的，请不要用女人的照片作为头像，这样可能会对其他会员产生误导</li>
      </ul>
      <div class="form-actions">
        <div id="picker" class="webuploader-pick">上传新头像</div>
      </div>


    </form>

  </div>
  <!--box end-->

</div>
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script>
  $(function(){
    $("#btnSaveEmail").click(function(){
      $("#emailForm").submit();
    });

    $("#emailForm").validate({
      errorClass:"text-error",
      errorElement:"span",
      rules:{
        email:{
          required:true,
          email:true,
          remote:"/validate/email.do?from=from"
        }
      },
      messages:{
        email:{
          required:"请输入电子邮件",
          email:"邮箱格式不正确",
          remote:"该邮件已经注册"
        }
      },
      submitHandler:function(form){
        var btn =  $("#btnSaveEmail");
        $.ajax({
          url:"/user/changeEmail.do",
          type:"post",
          data:$(form).serialize(),
          beforeSend:function(){
            btn.text("保存中").attr("disabled","disabled");
          },
          success:function(json){
            if(json.state == "success"){
              $("#emailHelp").show().fadeOut(2000);
            }
          },
          complete:function(){
            btn.text("保存").removeAttr("disabled");
          },
          error:function(){
            alert("服务器出错了")
          }
        })
      }
    })







    $("#btnSavePassword").click(function(){
      $("#passwordForm").submit();
    });

    $("#passwordForm").validate({
      errorElement:"span",
      errorClass:"text-error",
      rules:{
        password:{
          required:true,
          rangelength:[6,18]
        },
        repassword:{
          required:true,
          rangelength:[6,18],
          equalTo:"#password"
        }
      },
      messages:{
        password:{
          required:"请输入密码",
          rangelength:"密码长度为6到18位"
        },
        repassword:{
          required:"请输入密码",
          rangelength:"密码长度为6到18位",
          equalTo:"两次密码不一致"
        }
      },
      submitHandler:function(form){
        var btn =  $("#btnSavePassword");
        $.ajax({
          url:"/user/changePassword.do",
          type:"post",
          data:$(form).serialize(),
          beforeSend:function(){
            btn.text("保存中").attr("disabled","disabled");
          },
          success:function(json){
            if(json.state == "success"){
              $("#passwordHelp").show().fadeOut(2000,function(){
                window.location.href = "/logout.do";
              })
            }else{
                alert("密码修改失败")
            }
          },
          complete:function(){
            btn.text("保存").removeAttr("disabled");
          },

          error:function(){
            alert("服务器出错了")
          }
        })
      }
    })


    var uploader = WebUploader.create({
      swf:'/static/js/webuploder/Uploader.swf',
      server:"http://upload.qiniu.com",
      pick:"#picker",
      accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
      },
      auto:true,
      fileVal:"file",
      formData:{"token":"${token}"}
    });

    uploader.on("uploadProgress",function(file){
      $(".webuploader-pick").text("头像上传中...").attr("disabled","disabled");
    })

    //文件上传失败时调用
    uploader.on("uploadError",function(file){
      alert("上传服务器错误");
    });

    //无论上传成功还是失败都调用
    uploader.on("uploadComplete",function(){
      $(".webuploader-pick").text("上传新头像").removeAttr("disabled");
    });


    uploader.on("uploadSuccess",function(file,result){
      var key = result.key;
      $.post("/user/changeImg.do",{"key":key}).done(function(json){
        if(json.state == "success") {
          $(".img1").attr("src","http://7xq3kx.com1.z0.glb.clouddn.com/"+key+"?imageView2/1/w/40/h/40");
          $(".img2").attr("src","http://7xq3kx.com1.z0.glb.clouddn.com/"+key+"?imageView2/1/w/20/h/20");
        }
      }).fail(function(){
        alert("服务器忙，请稍后再试");
      });

    });















  })












</script>


</body>
</html>