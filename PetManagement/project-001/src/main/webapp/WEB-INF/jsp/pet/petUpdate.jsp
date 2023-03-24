<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--定义一个变量获取上下文路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <%--让一下全部基于上下文路径--%>
    <base href="${ctx}/">
    <meta charset="utf-8">
    <title>宠物信息修改界面</title>
    <link rel="stylesheet" href="assets/reset/reset.css">
    <link rel="stylesheet" href="assets/pet/css/petInsertList.css">
    <script src="assets/lib/jQuery/jquery-3.6.3.min.js"></script>
    <script src="assets/pet/js/petUpdate.js"></script>
    <script src="assets/lib/layer/layer.js"></script>
    <script src="assets/lib/laydate/laydate.js"></script>
        <script>
            const jCtx = "${ctx}/";
            const sex = "${pets.petSex}"
            const error = "${error}"
        </script>
</head>
<body>
<header>

</header>
<div class="ctr">
    <form id="pet-insert" action="pet/petUpdate" method="post">
        <div class="No1 clear">
            <input type="text"name="pId" value="${petById.petId}" style="display: none">
            <div class="pet-name pn fl">
                <label class="required">宠物名字：</label>
                <input type="text" name="pName" id="p_id" value="${petById.petName}"
                       placeholder="在此输入宠物名字（不可为空）">
            </div>
            <div class="pet-name fl">
                <label>主人编号：</label>
                <input type="text" name="oId" id="o_id" value="${petById.ownerId}"
                       placeholder="在此输入宠物主人Id（若不填写则视为无主人）">
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
                <input type="text" name="pAge" value="${petById.petAge}" placeholder="在此输入宠物年龄">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">宠物类别：</label>
                <input type="text" name="pType" value="${petById.petType}" placeholder="在此输入宠物类别（不可为空）">
            </div>
            <div class="No1 fl">
                <label>宠物品种：</label>
                <input type="text" name="pBreed" value="${petById.petBreed}" placeholder="在此输入宠物品种">
            </div>
        </div>
        <div class="clear">
            <div class="No1 fl">
                <label class="required">开始日期：</label>
                <input type="text" name="pBegTime" class="pBegTime" value="${petById.depositBegTime}" readonly
                       placeholder="在此选择寄养开始日期（不可为空）">
            </div>
            <div class="No1 fl">
                <label class="required">结束日期：</label>
                <input type="text" name="pEndTime" class="pEndTime" value="${petById.depositEndTime}" readonly
                       placeholder="在此选择寄养结束日期（不可为空）">
            </div>
        </div>
        <div class="No2">
            <label>备注：</label>
            <textarea name="note" class="text" placeholder="在此输入宠物备注" >${petById.note}</textarea>
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
