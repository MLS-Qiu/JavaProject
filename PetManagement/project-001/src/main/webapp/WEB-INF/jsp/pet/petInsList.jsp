<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>添加宠物信息</title>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/pet/css/petInsertList.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
    <script>
        const jCtx = "${ctx}/";
        const sex = "${pets.petSex}"
        const error = "${error}"
        //const sus = "${sus}"
    </script>
    <script src="assets/pet/js/petInsert.js"></script>

</head>
<body>
<header>

</header>
<div class="ctr">
    <form id="pet-insert" action="pet/petInsert" method="post">
        <div class="No1 clear">
            <div class="pet-name pn fl">
                <label class="required">宠物名字：</label>
                <input type="text" name="pName" id="p_id" value="${pets.petName}"
                       placeholder="在此输入宠物名字（不可为空）">
            </div>
            <div class="pet-name fl">
            <label>主人编号：</label>
            <input type="text" name="oId" id="o_id" value="${pets.ownerId}"
                   placeholder="在此输入宠物主人Id（不可为空）">
            </div>
        </div>
        <div class="clear">
            <div class="clear No1 fl">
                <label class="fl">宠物性别：</label>
                <div class="fl pet-sex">
                    <input type="radio" id="male" name="pSex" value="公" checked>
                    <label>公</label>
                </div>
                <div class="fl pet-sex">
                    <input type="radio" id="female" name="pSex" value="母">
                    <label>母</label>
                </div>
            </div>
            <div class="No1 fl">
                <label>宠物年龄：</label>
                <input type="text" name="pSex" placeholder="在此输入宠物年龄">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">宠物类别：</label>
                <input type="text" name="pType" value="${pets.petType}" placeholder="在此输入宠物类别（不可为空）">
            </div>
            <div class="No1 fl">
                <label>宠物品种：</label>
                <input type="text" name="pBreed" value="${pets.petBreed}" placeholder="在此输入宠物品种">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">开始日期：</label>
                <input type="text" name="pBegTime" class="pBegTime" value="${pets.depositBegTime}" readonly
                       placeholder="在此选择寄养开始日期（不可为空）">
            </div>
            <div class="No1 fl">
                <label class="required">结束日期：</label>
                <input type="text" name="pEndTime" class="pEndTime" value="${pets.depositEndTime}" readonly
                       placeholder="在此选择寄养结束日期（不可为空）">
            </div>
        </div>
        <div class="No2">
            <label>备注：</label>
            <textarea name="note" class="text" placeholder="在此输入宠物备注" value="${pets.note}"></textarea>
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
