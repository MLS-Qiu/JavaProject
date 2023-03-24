<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <base href="${ctx}/">
    <meta charset="utf-8">

    <title>商户信息添加界面</title>

    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/customer/css/cusInsert.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script>
        const jCtx = "${ctx}/";
        const sex = "${cus.cusSex}"
        const error = "${error}"
    </script>
    <script src="assets/customer/js/cusInsert.js"></script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
</head>
<body>
<header>

</header>
<div class="ctr">
    <form id="cus-insert" action="customer/cusInsert" method="post">
        <div class="No1 clear">
            <div class="cus-name fl">
                <label class="required">商店名字：</label>
                <input type="text" name="cName" value="${cus.cusName}"
                       placeholder="在此输入商店名字（不可为空）">
            </div>
            <%--<label class="fl">主人Id：</label>
            <input class="fl" type="text" name="oId" id="o_id" value="${pets.ownerId}"
                   placeholder="在此输入宠物主人Id（若不填写则视为无主人）">--%>
        </div>
        <div class="clear">
            <div class="clear No1 fl">
                <label class="fl">商户姓名：</label>
                <input type="text" name="kPic" value="${cus.cusAge}" placeholder="在此输入商户姓名">
            </div>
            <div class="No1 fl">
                <label>商店类型：</label>
                <select id="kType">
                    <option value="1">个人寄养处</option>
                    <option value="2">宠物医院</option>
                    <option value="3">宠物商店</option>
                </select>
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">商户电话：</label>
                <input type="text" name="cPhone" value="${cus.cusPhone}" placeholder="在此输入商户电话（不可为空）">
            </div>
            <div class="No1 fl">
                <label>商户邮箱：</label>
                <input type="text" name="cEmail" value="${cus.cusEmail}" placeholder="在此输入商户邮箱">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">商店创建日期：</label>
                <input type="text" name="cBirth" class="cBirth" value="${cus.cusBirthday}" readonly
                       placeholder="在此选择商店创建日期">
            </div>
            <div class="No1 fl">
                <label class="required">商户地址：</label>
                <input type="text" name="cAddress" class="cAddress" value="${cus.cusAddress}"
                       placeholder="在此输入商户地址（不可为空）">
            </div>
        </div>
        <div class="No2">
            <label>备注：</label>
            <textarea name="note" class="text" placeholder="在此输入商户备注" value="${cus.note}"></textarea>
        </div>
    </form>

    <div class="insert-bnt">
        <div>
            <label></label>
            <button id="sub" type="button">添加</button>
            <button id="res" type="reset">重置</button>
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>
