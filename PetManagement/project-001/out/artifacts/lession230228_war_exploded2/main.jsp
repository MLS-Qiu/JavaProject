<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <%--让一下全部基于上下文路径--%>
    <base href="${ctx}/">

    <meta charset="utf-8">
    <title></title>

    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/main/css/main.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script src="assets/main/js/main.js"></script>

</head>
<body>
<div class="ctr clear">
    <!-- 左侧导航栏 -->
    <div class="left fl">
        <!-- 名字部分 -->
        <div class="title clear">
            <div>
                <img src="assets/main/img/title.png" alt="error">
            </div>
            <div>Pet.</div>
        </div>
        <!-- 菜单部分 -->
        <ul class="nav">
            <li>
                <a href="pet/petList">宠物列表</a>
            </li>
            <li>
                <a href="pet/petList">宠物管理</a>
            </li>
            <li>
                <a href="#">用户列表</a>
            </li>
            <li>
                <a href="#">用户管理</a>
            </li>
        </ul>
    </div>

    <!-- 中间显示信息部分 -->
    <div class="content fl">
        <!-- 一个框，可以在框内显示新的页面 -->
        <iframe src="" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>