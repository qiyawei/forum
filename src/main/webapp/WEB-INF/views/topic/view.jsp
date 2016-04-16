<%--
  Created by IntelliJ IDEA.
  User: qiyawei
  Date: 2016/4/11
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>主题页</title>
  <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="/static/css/style.css">
  <link rel="stylesheet" href="/static/js/simditor-2.3.6/styles/simditor.css">
  <link rel="stylesheet" href="/static/js/code/hemisu-light.css"/>
  <style>
    body{
      background-image: url(/static/img/bg.jpg);
    }
    .simditor .simditor-body {
      min-height: 100px;
    }
  </style>
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
  <div class="box">
    <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
      <li><a href="/index.do">首页</a> <span class="divider">/</span></li>
      <li class="active">${topic.topicType.typename}</li>
    </ul>
    <div class="topic-head">
      <img class="img-rounded avatar" src="http://7xq3kx.com1.z0.glb.clouddn.com/${topic.user.img}?imageView2/1/w/60/h/60" alt="">
      <h3 class="title">${topic.title}</h3>
      <p class="topic-msg muted"><a href="">${topic.user.username}</a> · <span class="timeago" title="${topic.createtime}"></span></p>
    </div>
    <div class="topic-body">
     ${topic.content}
    </div>
    <div class="topic-toolbar">
      <c:if test="${not empty sessionScope.user}">
      <ul class="unstyled inline pull-left">
        <c:choose>
          <c:when test="${action == 'fav'}">
            <li><a href="javascript:;" class="fav">取消收藏</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="javascript:;" class="fav">加入收藏</a></li>
          </c:otherwise>
        </c:choose>
        <li><a href="">感谢</a></li>
        <li><a href=""></a></li>
      </ul>
      </c:if>
      <ul class="unstyled inline pull-right muted">
        <li>${topic.viewnum}次点击</li>
        <li ><span id="favnum">${topic.favnum}</span>人收藏</li>
        <li>${topic.likenum}人感谢</li>
      </ul>
    </div>
  </div>
  <!--box end-->

  <div class="box" style="margin-top:20px;">
    <div class="talk-item muted" style="font-size: 12px">
      <span id="replyNum"></span>个回复 | 直到<span id="replyTime"></span>
    </div>
    <div id="comment-list"></div>





  </div>

<c:choose>
  <c:when test="${ not empty sessionScope.user}">
    <div class="box" style="margin:20px 0px;">
      <a name="new"></a>
      <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
      <form action="" style="padding: 15px;margin-bottom:0px;">
        <textarea name="text" id="editor"></textarea>
      </form>
      <div class="talk-item muted" style="text-align: right;font-size: 12px">
        <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
        <button type="submit" id="sendComment" class="btn btn-primary">发布</button>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="box" style="margin:20px 0px;">
      <div style="padding: 20px">
        请 <a href="/login.do?redirecturl=/topic/view.do?id=${topic.id}">登录</a> 后在发表回复
      </div>
    </div>
  </c:otherwise>
</c:choose>


</div>
<!--container end-->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="/static/js/simditor-2.3.6/scripts/module.min.js"></script>
<script src="/static/js/simditor-2.3.6/scripts/hotkeys.min.js"></script>
<script src="/static/js/simditor-2.3.6/scripts/uploader.min.js"></script>
<script src="/static/js/simditor-2.3.6/scripts/simditor.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/code/prettify.js"></script>
<script src="/static/js/timeago.js"></script>
<script src="/static/js/handlebars-v4.0.5.js"></script>
<script src="/static/js/moment.min.js"></script>
<script type="text/x-handlerbars-template"id="template">
  {{#each data}}
  <div class="talk-item" >
    <a id="reply{{count @index}}"></a>
    <table class="talk-table">
      <tr>
        <td width="50">
          <img class="avatar" src="http://7xq3kx.com1.z0.glb.clouddn.com/{{user.img}}?imageView2/1/w/40/h/40" alt="">
        </td>
        <td width="auto">
          <a href="" style="font-size: 12px">{{user.username}}</a> <span style="font-size: 12px"  class="reply timeago" title="{{createtime}}">{{createtime}}</span>
          <br>
          {{{text}}}
        </td>
        <td width="70" align="right" style="font-size: 12px">
          <a  href="javascript:;" class="replyLink" data-count="{{count @index}}"title="回复"><i class="fa fa-reply"></i></a>&nbsp;
          <span class="badge">{{count @index}}</span>
        </td>
      </tr>
    </table>
  </div>
  {{/each}}
</script>

<script>
  $(function(){
    <c:if test="${not empty sessionScope.user}">
    var editor = new Simditor({
      textarea: $('#editor'),
      toolbar:false
    });

    $("#sendComment").click(function(){
      var value = editor.getValue();
      if(value) {
        sendComment();
      } else {
        editor.focus();
      }
    });


    function sendComment() {
      var $btn = $("#sendComment");
      $.ajax({
        url:"/topic/comment/new.do",
        type:"post",
        data:{"text": editor.getValue(),"topicid":"${topic.id}"},
        beforeSend:function(){
          $btn.text("发布中...").attr("disabled","disabled");
        },
        success:function(json){
          if(json.state == "error") {
            alert(json.message);
          } else {

            initComment();
          }
        },
        error:function(){
          alert("服务器忙，请稍后再试");
        },
        complete:function(){
          $btn.text("发布").removeAttr("disabled");
        }
      });
    }

    </c:if>

    $(document).on("click",".replyLink",function(){
      var count = $(this).attr("data-count");
      var msg = "<a href='#reply"+count+"'>#" + count + "楼</a>&nbsp;&nbsp;";
      editor.setValue(msg);
      window.location.href="#new";
      editor.focus();
    });

    function initComment() {
      $.ajax({
        url:"/topic/comment/load.do",
        type:"get",
        data:{"topicId":"${topic.id}"},

        success:function(json){
          if(json.state == "error") {
            alert(json.message);
          } else {
            $("#comment-list").html("");
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            var html = template(json);
            $("#comment-list").append(html);

            $("#replyNum").text(json.data.length);
            if(json.data.length != 0) {
              $("#replyTime").text(json.data[json.data.length - 1].createtime);
            } else {
              $("#replyTime").text(moment().format("YYYY-MM-DD HH:mm:ss"));
            }
            $(".timeago").timeago();
          }
        },
        error:function(){
          alert("服务器错误，请稍后再试");
        }

      });
    }

    initComment();


    $(".fav").click(function(){
      var $this =  $(this);
      var action =$this.text() == "加入收藏"?"fav":"unfav";
      $.post("/topic/fav.do",{'topicid':${topic.id},'action':action}).done(function(json){
        if(json.state == "error"){
          alert(json.message)
        }else{
          if(action=="fav"){
            $this.text("取消收藏");
            $("#favnum").text(${topic.favnum + 1});
          }else{
            $this.text("加入收藏");
            $("#favnum").text(${topic.favnum});
          }
        }
      }).fail(function(){
        alert("服务器忙请稍后")
      });

    })










    Handlebars.registerHelper("count",function(index){
      return index+1;
    })


    $(".timeago").timeago();

    $("pre").addClass("prettyprint");
    prettyPrint();




  });
</script>

</body>
</html>