<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>登录界面</title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8"/>
   <%-- <meta name="keywords"
          content="Report Login Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <!-- //Meta tag Keywords -->
    <link href="//fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;500;700;900&display=swap" rel="stylesheet">
    <!--/Style-CSS -->
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <!--//Style-CSS -->
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" media="all">--%>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/log/css/login.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script src="assets/log/js/login.js"></script>
    <script>
        const ctx = "${ctx}/";
        const error = "${error}";
    </script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<!-- form section start -->
<section class="w3l-hotair-form">
    <h1>Pet. Login Interface</h1>
    <div class="container">
        <!-- /form -->
        <div class="workinghny-form-grid">
            <div class="main-hotair">
                <div class="content-wthree">
                    <h2>Log In</h2>
                    <form id="login" action="user/login" method="post">
                        <input type="text" id="username" class="text" name="username" placeholder="用户名" required="" autofocus>
                        <input type="password" id="password" class="password" name="password" placeholder="用户密码" required=""
                               autofocus>
                        <input type="text" name="captcha" class="captcha" placeholder="验证码" required=""
                               autofocus>
                        <img id="captcha" src="user/captcha">
                        <input type="checkbox" id="reMe">
                        <label class="reText">记住我</label>
                        <button id="login-bnt" class="btn" type="button">Log In</button>
                    </form>

                    <p class="account">Don't have an account? <a href="#">Register</a></p>
                </div>
                <div class="w3l_form align-self">
                    <div class="left_grid_info">
                        <img id="tuPian" src="assets/log/img/2.png" alt="" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <!-- //form -->
    </div>
    <!-- copyright-->
    <div class="copyright text-center">
        <p class="copy-footer-29">© 2023 Pet. Login Interface. All rights reserved | Design by <a
                href="https://w3layouts.com">MLS</a></p>
    </div>
    <!-- //copyright-->
</section>
<!-- //form section start -->
</body>
</html>
