$(function () {
    if (error){
        layer.alert(error);
    }
    if (sex) {
        $(":radio[name=sex][value=" + sex + "]").prop("checked", true);
    }
    //日历插件事件
    laydate.render({
        elem:".pBegTime"
    });
    laydate.render({
        elem:".pEndTime"
    });

    //修改事件
    $("#sub").click(function () {
        let url = jCtx + "pet/petUpdate";
        let data ={};
        //校验用户输入信息
        let id = $(":input[name=pId]").val();
        if (id.trim() === "") {
            layer.msg("必须指定要修改宠物的编号");
            return false;
        }
        data.id = id;

        let pName = $(":input[name=pName]").val();
        if (pName.trim() === "") {
            layer.msg("名字不可为空");
            return false;
        }
        data.pName = pName;

        let pSex = $(":input[name=pSex]:checked").val();
        data.pSex = pSex;

        let pAge = $(":input[name=pAge]").val();
        data.pAge = pAge;

        let oId = $(":input[name=oId]").val();
        data.pOwnerId = oId;

        let pType = $(":input[name=pType]").val();
        if (pType.trim() === "") {
            layer.msg("宠物类别不可为空");
            return false;
        }
        data.pType = pType;

        let pBreed = $(":input[name=pBreed]").val();
        data.pBreed = pBreed;

        let pBegTime = $(":input[name=pBegTime]").val();
        let regex = /\d{4}-\d{2}-\d{2}/;
        if (!pBegTime.match(regex)) {
            layer.msg("开始寄养日期不可为空");
            return false;
        }
        data.pBegTime = pBegTime;

        let pEndTime = $(":input[name=pEndTime]").val();
        if (!pEndTime.match(regex)) {
            layer.msg("结束寄养日期不可为空");
            return false;
        }
        data.pEndTime = pEndTime;

        let pNote = $(".text").val();
        data.pNote = pNote;


        //输入信息都正确
        $.post(url, data, function (resp) {
            if (resp.suc){
                layer.msg("修改成功");
            }else {
                layer.msg("修改失败");
            }
        },"json");
    });
});