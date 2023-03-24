$(function () {
    if (error) {
        layer.alert(error);
    }
    /*if (sus) {
        layer.alert(sus);
    }*/
    if (sex) {
        $(":radio[name=sex][value=" + sex + "]").prop("checked", true);
    }
    //日历插件事件
    laydate.render({
        elem: ".pBegTime"
    });
    laydate.render({
        elem: ".pEndTime"
    });

    //插入事件
    $("#sub").click(function () {
        //获取主人Id
        let ownerId = $(":input[name=oId]").val();
        let url = jCtx + "pet/petOwner";
        if (ownerId.trim() === "") {
            layer.msg("宠物主人编号不可为空");
            return false;
        }
        if (ownerId.trim()!=""){
            $.ajax(url, {
                method: "get",
                data: {
                    ownerId
                },
                dataType: "json",
                success: function (resp) {
                    if (resp.success) {
                        insert();
                    } else {
                        layer.alert("输入的主人Id不存在");
                        return;
                    }
                }
            });
        }


    });
});

/*插入函数*/
function insert() {
    //校验用户输入信息
    let pName = $(":input[name=pName]").val();

    if (pName.trim() === "") {
        layer.msg("名字不可为空");
        return false;
    }

    let pType = $(":input[name=pType]").val();
    if (pType.trim() === "") {
        layer.msg("宠物类别不可为空");
        return false;
    }

    let ownerId = $(":input[name=oId]").val();
    if (ownerId.trim() === "") {
        layer.msg("宠物主人编号不可为空");
        return false;
    }

    let pBegTime = $(":input[name=pBegTime]").val();
    let regex = /\d{4}-\d{2}-\d{2}/;
    if (!pBegTime.match(regex)) {
        layer.msg("开始寄养日期不可为空");
        return false;
    }

    let pEndTime = $(":input[name=pEndTime]").val();
    if (!pEndTime.match(regex)) {
        layer.msg("结束寄养日期不可为空");
        return false;
    }

    //输入信息都正确
    $("#pet-insert").submit();
}