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
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<!-- 搜索区 -->
<div class="search">
    <!-- 表单，用于将用户输入的数据发送到web服务端进行处理
     action:提交路径
     method:方式-->
    <form style="display: none" action="" method="post">
        <div>
            <label>宠物编号:</label>
            <input type="text" id="p_id" placeholder="请输入宠物编号">
        </div>
        <div>
            <label>宠物姓名:</label>
            <input type="text" id="p_name" placeholder="请输入宠物姓名">
        </div>
        <div>
            <label>宠物性别:</label>
            <select id="p_sex">
                <option>不限</option>
                <option>公</option>
                <option>母</option>
            </select>
        </div>
        <div>
            <label>宠物年龄:</label>
            <input type="text" id="p_age" placeholder="请输入宠物年龄">
        </div>
        <div>
            <label>主人Id:</label>
            <input type="text" id="p_oId" placeholder="请输入宠物主人Id">
        </div>
        <div>
            <label>宠物种类:</label>
            <input type="text" id="p_type" placeholder="请输入宠物种类">
        </div>
        <div>
            <label>宠物品种:</label>
            <input type="text" id="p_breed" placeholder="请输入宠物品种">
        </div>
        <div>
            <label>开始寄存时间:</label>
            <input type="text" id="p_bTimeFrom" readonly>
            to
            <input type="text" id="p_bTimeTo" readonly>
        </div>
        <div>
            <label>结束寄存时间:</label>
            <input type="text" id="p_eTimeFrom" readonly>
            to
            <input type="text" id="p_eTimeTo" readonly>
        </div>
        <div>
            <label>备注:</label>
            <input type="text" id="p_note" placeholder="请输入备注">
        </div>
    </form>
</div>
<!-- 操作区 -->
<div style="display: none" class="action clear">
    <div>
        <button class="insert-bnt">添加</button>
    </div>
    <div>
        <button class="del-bnt">删除</button>
    </div>
    <div>
        <button class="upd-bnt">修改</button>
    </div>
    <div>
        <button class="ser-bnt">查询</button>
    </div>
    <div>
        <button class="reset-bnt">重置</button>
    </div>
</div>
<!-- 信息显示区 -->
<div class="display">
    <table id="tbl">
        <thead>
        <tr>
            <%--<th><input type="checkbox" id="checkPapa"></th>--%>
                <th><div>宠物编号</div></th>
                <th><div>宠物姓名</div></th>
                <th><div>宠物性别</div></th>
                <th><div>宠物年龄</div></th>
                <th><div>主人编号</div></th>
                <th><div>宠物种类</div></th>
                <th><div>宠物品种</div></th>
                <th><div>开始寄存时间</div></th>
                <th><div>结束寄存时间</div></th>
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
    <div class="pCount">
        <div>
            <span>总共有</span>
            <span id="allCount"></span>
            <span>条记录</span>
        </div>
        <div>
            <span>总共</span>
            <span id="allPageCount"></span>
            <span>页</span>
            <span id="pageNo" style="display: none"></span>
        </div>
    </div>
    <ul class="clear">
        <li class="firPage">
            <a href="#">首页</a>
        </li>
        <li class="prevPage">
            <a href="#">上一页</a>
        </li>
        <li class="pageNum"><a>1</a></li>
        <li class="pageNum"><a>2</a></li>
        <li class="pageNum"><a>3</a></li>
        <li class="pageNum"><a>4</a></li>
        <li class="pageNum"><a>5</a></li>
        <li class="pageNum"><a>6</a></li>
        <li class="pageNum"><a>7</a></li>
        <li class="nextPage">
            <a href="#">下一页</a>
        </li>
        <li class="lastPage">
            <a href="#">尾页</a>
        </li>
    </ul>
    <div class="pSize">
        <span>设置显示记录条数为：</span>
        <select id="pageSize">
            <option value="15">15条</option>
            <option value="20">20条</option>
            <option value="35">35条</option>
            <option value="50">50条</option>
        </select>
    </div>
</div>

</body>
</html>