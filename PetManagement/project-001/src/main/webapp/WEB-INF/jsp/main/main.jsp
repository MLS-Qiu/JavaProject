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
    <script>
        const ctx = "${ctx}/";
        const error = "${error}";
    </script>
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
            <li class="hp">
                <a href="pet/homePage">首页</a>
            </li>
            <li class="level1">
                <div class="dimg">
                    <p>宠物信息管理</p>
                    <img src="assets/main/img/arrowhead.png" alt="">
                </div>
                <ul class="level2">
                    <li><a href="pet/petList">宠物信息列表</a></li>
                    <li><a href="pet/petSearch">管理宠物信息</a></li>
                    <%--<li><a href="pet/petInsert">添加宠物信息</a></li>
                    <li><a href="pet/petSearch">删除宠物信息</a></li>
                    <li><a href="pet/petUpdate">修改宠物信息</a></li>--%>
                </ul>
            </li>
            <li class="level1">
                <div class="dimg">
                    <p>用户信息管理</p>
                    <img src="assets/main/img/arrowhead.png" alt="">
                </div>
                <ul class="level2">
                    <li><a href="customer/cueList">用户信息列表</a></li>
                    <li><a href="customer/cusSearch">管理用户信息</a></li>
                    <%--<li><a href="customer/cusInsert">添加用户信息</a></li>
                    <li><a href="customer/cusSearch">删除用户信息</a></li>
                    <li><a href="customer/cusUpdate">修改用户信息</a></li>--%>
                </ul>
            </li>
            <%--<li class="level1">
                <div class="dimg">
                    <p>商品信息管理</p>
                    <img src="assets/main/img/arrowhead.png" alt="">
                </div>
                <ul class="level2">
                    <li><a href="kennels/kenList">商品信息列表</a></li>
                    <li><a href="kennels/kenSearch">查询商品信息</a></li>
                    <li><a href="kennels/kenInsert">添加商品信息</a></li>
                    <li><a href="kennels/kenSearch">删除商品信息</a></li>
                    <li><a href="kennels/kenUpdate">修改商品信息</a></li>
                </ul>
            </li>--%>
        </ul>
        <div class="logout">
            <a href="javascript:void(0)">退出登录</a>
        </div>
    </div>

    <!-- 中间显示信息部分 -->
    <div class="content fl">
        <!-- 一个框，可以在框内显示新的页面 -->
        <iframe src="pet/homePage" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>