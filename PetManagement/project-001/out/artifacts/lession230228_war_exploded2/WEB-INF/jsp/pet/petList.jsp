<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%--<!DOCTYPE html>--%>
<html>
<head>
    <%--让一下全部基于上下文路径--%>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>宠物信息列表</title>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/pet/css/petList.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script>
        const jCtx = "${ctx}/";
    </script>
    <script src="assets/pet/js/petList.js"></script>
</head>
<body>
<!-- 搜索区 -->
<div class="search">
    <!-- 表单，用于将用户输入的数据发送到web服务端进行处理
     action:提交路径
     method:方式-->
    <form action="" method="post">
        <div>
            <label>宠物编号:</label>
            <input type="text">
        </div>
    </form>
</div>
<!-- 操作区 -->
<div class="action clear">
    <div>
        <button>添加</button>
    </div>
    <div>
        <button>删除</button>
    </div>
    <div>
        <button>修改</button>
    </div>
    <div>
        <button class="ser">查询</button>
    </div>
</div>
<!-- 信息显示区 -->
<div class="display">
    <table id="tbl">
        <thead>
        <tr>
            <th>宠物编号</th>
            <th>宠物姓名</th>
            <th>宠物性别</th>
            <th>宠物年龄</th>
            <th>宠物种类</th>
            <th>宠物品种</th>
            <th>开始寄存时间</th>
            <th>结束寄存时间</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pets">
        <tr>
            <td>${pets.petId}</td>
            <td>${pets.petName}</td>
            <td>${pets.petSex}</td>
            <td>${pets.petAge}</td>
            <td>${pets.petType}</td>
            <td>${pets.petBreed}</td>
            <td>${pets.depositBegTime}</td>
            <td>${pets.depositEndTime}</td>
            <td>${pets.note}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- 分页区 -->
<div class="paginate">
    <ul class="clear">
        <li>
            <a href="#">首页</a>
        </li>
        <li>
            <a href="#">上一页</a>
        </li>
        <li>
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">1</a>
        </li>
        <li>
            <a href="#">下一页</a>
        </li>
        <li>
            <a href="#">尾页</a>
        </li>
    </ul>
</div>

</body>
</html>