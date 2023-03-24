$(function () {
    if (error) {
        layer.alert(error);
    }
 /*   if (sus) {
        layer.alert(sus);
    }*/
    if (sex) {
        $(":radio[name=cSex][value=" + sex + "]").prop("checked", true);
    }
    //日历插件事件
    laydate.render({
        elem: ".cBirth"
    });
    //插入事件
    $("#sub").click(function () {
        insert();
    });

});

/*插入函数*/
function insert() {
    //校验用户输入信息
    let cName = $(":input[name=cName]").val();
    if (cName.trim() === "") {
        layer.msg("名字不可为空");
        return false;
    }

    let cPhone = $(":input[name=cPhone]").val();
    if (cPhone.trim() === "") {
        layer.msg("电话不可为空");
        return false;
    }

    let cAddress = $(":input[name=cAddress]").val();
    if (cAddress.trim() === "") {
        layer.msg("地址不可为空");
        return false;
    }
    //输入信息都正确
    $("#cus-insert").submit();

}