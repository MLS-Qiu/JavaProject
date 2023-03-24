<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <%--让一下全部基于上下文路径--%>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/main/css/homePage.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script>
        const ctx = "${ctx}/";
    </script>
    <script src="assets/main/js/hpage.js"></script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<div id="header">
    <div class="clear">
        <h1 class="fl">尊敬的用户</h1>
        <h1 class="fl" id="nickname">${user.nickname}</h1>
        <h1 class="fl">,您好！</h1>
    </div>
    <div><h2>欢迎使用宠物寄养信息管理系统~</h2></div>
</div>
<div id="ctr">
    <div>
        <img id="mmt" src="assets/main/img/ctr.jpeg" alt="">
    </div>
</div>

<div id="footer">
    <a id="edit-btn" href="javascript:void(0)">修改个人信息</a>
</div>
</body>
</html>
