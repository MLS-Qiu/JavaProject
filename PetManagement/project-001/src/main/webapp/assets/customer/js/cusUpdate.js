$(function () {
    if (error){
        layer.alert(error);
    }
    if (sex) {
        $(":radio[name=sex][value=" + sex + "]").prop("checked", true);
    }
    //日历插件事件
    laydate.render({
        elem:".cBirth"
    });

    //修改事件
    $("#sub").click(function () {
        let url = jCtx + "customer/cusUpdate";
        let data ={};
        //校验用户输入信息
        let id = $(":input[name=cId]").val();
        if (id.trim() === "") {
            layer.msg("必须指定要修改客户的编号");
            return false;
        }
        data.id = id;

        let cName = $(":input[name=cName]").val();
        if (cName.trim() === "") {
            layer.msg("名字不可为空");
            return false;
        }
        data.cName = cName;

        let cSex = $(":input[name=cSex]:checked").val();
        data.cSex = cSex;

        let cAge = $(":input[name=cAge]").val();
        data.cAge = cAge;

        let cPhone = $(":input[name=cPhone]").val();
        if (cPhone.trim() === "") {
            layer.msg("电话不可为空");
            return false;
        }
        data.cPhone = cPhone;

        let cEmail = $(":input[name=cEmail]").val();
        data.cEmail = cEmail;

        let cBirth = $(":input[name=cBirth]").val();
        let regex = /\d{4}-\d{2}-\d{2}/;
        if (!cBirth.match(regex)) {
            layer.msg("生日格式不正确");
            return false;
        }
        data.cBirth = cBirth;

        let cAddress = $(":input[name=cAddress]").val();
        if (cAddress.trim() === "") {
            layer.msg("地址不可为空");
            return false;
        }
        data.cAddress = cAddress;


        let cNote = $(".text").val();
        data.cNote = cNote;


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