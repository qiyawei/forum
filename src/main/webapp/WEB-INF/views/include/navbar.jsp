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
      <c:if test="${not emptysessionScope.user}">
        <span>${sessionScope.user.username}</span>
      </c:if>
    </a>
    <ul class="unstyled inline pull-right">
      <c:choose>
        <c:when test="${not empty sessionScope.user}">
          <li>
            <a href="#">
              <img src="http://7xq3kx.com1.z0.glb.clouddn.com/${sessionScope.user.img}?imageView2/1/w/20/h/20" class="img-circle img1" alt="">
            </a>
          </li>
          <li>
            <a href="/topic/new.do"><i class="fa fa-plus"></i></a>
          </li>
          <li>
            <a  id="bell" href="javascript:;"><i class="fa fa-bell"><span id="informnum"></span></i></a>
          </li>
          <li>
            <a href="/user/setting.do"><i class="fa fa-cog"></i></a>
          </li>
          <li>
            <a href="/logout.do"><i class="fa fa-sign-out"></i></a>
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
<script src="/static/js/jquery-1.12.2.min.js"></script>
<script>
  $(function(){
    <c:if test="${not empty sessionScope.user}">
    setInterval(function(){
      $.get("/user/inform.do",function(json){
        var length = json.length;
        if(length){
          $("#informnum").text(length).css("color","red");
        }

      })
    },10000)
    </c:if>
  })

  $("#bell").click(function(){
    $("#informnum").text("");
    $.post("/user/inform.do");
  });
</script>