<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <base href="${ctx}/">
    <meta charset="utf-8">

    <title>客户信息修改界面</title>

    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/customer/css/cusInsert.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script>
        const jCtx = "${ctx}/";
        const sex = "${cus.cusSex}"
        const error = "${error}"
    </script>
    <script src="assets/customer/js/cusUpdate.js"></script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<header>

</header>
<div class="ctr">
    <form id="cus-insert" action="customer/cusUpdate" method="post">
        <div class="No1 clear">
            <input type="text"name="cId" value="${cusById.cusId}" style="display: none">
            <div class="cus-name fl">
                <label class="required">客户名字：</label>
                <input type="text" name="cName" value="${cusById.cusName}"
                       placeholder="在此输入客户名字（不可为空）">
            </div>
        </div>
        <div class="clear">
            <div class="clear No1 fl">
                <label class="fl">客户性别：</label>
                <div class="fl cus-sex">
                    <input type="radio" id="male" name="cSex" value="男" checked>
                    <label>男</label>
                </div>
                <div class="fl cus-sex">
                    <input type="radio" id="female" name="cSex" value="女">
                    <label>女</label>
                </div>
            </div>
            <div class="No1 fl">
                <label>客户年龄：</label>
                <input type="text" name="cAge" value="${cusById.cusAge}" placeholder="在此输入客户年龄">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">客户电话：</label>
                <input type="text" name="cPhone" value="${cusById.cusPhone}" placeholder="在此输入客户电话（不可为空）">
            </div>
            <div class="No1 fl">
                <label>客户邮箱：</label>
                <input type="text" name="cEmail" value="${cusById.cusEmail}" placeholder="在此输入客户邮箱">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">客户生日：</label>
                <input type="text" name="cBirth" class="cBirth" value="${cusById.cusBirthday}" readonly
                       placeholder="在此选择客户出生日期">
            </div>
            <div class="No1 fl">
                <label class="required">客户住址：</label>
                <input type="text" name="cAddress" class="cAddress" value="${cusById.cusAddress}"
                       placeholder="在此输入客户住址（不可为空）">
            </div>
        </div>
        <div class="No2">
            <label>备注：</label>
            <textarea name="note" class="text" placeholder="在此输入客户备注" value="${cusById.note}"></textarea>
        </div>
    </form>

    <div class="insert-bnt">
        <div>
            <label></label>
            <button id="sub" type="button">修改</button>
            <button id="res" type="reset">重置</button>
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>
