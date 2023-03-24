<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>修改个人信息</title>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/user/css/userUpdate.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script src="assets/user/js/userUpdate.js"></script>
    <script>
        const ctx = "${ctx}/";
        const error = "${error}"
        const updateUser = "${updateOK}";
    </script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<form id="update" action="user/userUpdate" method="post">
    <input type="text" class="text" name="username" value="${user.username}" placeholder="请输入新的用户名" required=""
           autofocus>
    <input type="password" class="password" name="firPassword" <%--value="${user.password}"--%> placeholder="请输入新的密码" required=""
           autofocus>
    <input type="password" class="password" name="secPassword" <%--value="${user.password}"--%> placeholder="请再次输入新的密码" required=""
           autofocus>
    <input type="text" class="text" name="nickname" value="${user.nickname}" placeholder="请输入新的昵称" required="" autofocus>
    <input type="text" class="text" name="phone" value="${user.phone}" placeholder="请输入新的电话" required=""
           autofocus>
    <input type="text" class="text" name="address" value="${user.address}" placeholder="请输入新的地址" required=""
           autofocus>
    <%--<input type="file" class="text" name="avatar" value="${user.address}" placeholder="请输入新的地址" required=""
           autofocus>--%>
    <button id="update-bnt" class="btn" type="button">提交</button>
    <button id="reset-bnt" class="btn" type="reset">重置</button>
</form>
</body>
</html>
