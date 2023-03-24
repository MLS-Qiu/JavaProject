$(function () {
    if (updateUser){
        layer.alert("修改成功");
    }
    if (error) {
        layer.alert(error);
    }

    let timer;//定时器
    $(":input[name = username]").keyup(function () {
        let username = $(this).val();
        //节流
        if (timer) {
            clearTimeout(timer);//清理定时器
        }
        timer = setTimeout(function () {
            checkUsername(username, function (b) {
                if (b) {
                    layer.msg("用户名已存在");
                }
            });
        }, 500);
    });

    $(":input[name = firPassword]").blur(function () {
        let password_first = $(this).val();
        let result = validatePassword(password_first);
        if (result !== true) {
            layer.msg(result);
        }
    });

    $(":input[name = secPassword]").blur(function () {
        let password_first = $(":input[name = firPassword]").val();
        console.log(password_first);
        let password_second = $(this).val();
        if (password_second!=password_first){
            layer.msg("两次密码不一致");
        }
    });
    $("#update-bnt").click(function () {
        $("#update").submit();
    });
});

function checkUsername(username,callback){
    let url = ctx + "user/checkUsername";
    $.ajax(url, {
        method: "post",
        data: {
            username
        },
        dataType: "json",
        success: function (resp) {
            callback(resp.exist);
        }
    });
}
function validatePassword(password) {
    let regex = /^\w{6,12}$/;
    if (!password.match(regex)) {
        return "密码必须介于6~12位之间";
    }

    regex = /[a-z]+/;
    if (!password.match(regex)) {
        return "密码必须包含至少一个小写字母";
    }

    regex = /[A-Z]+/;
    if (!password.match(regex)) {
        return "密码必须包含至少一个大写字母";
    }

    regex = /[0-9]+/;
    if (!password.match(regex)) {
        return "密码必须包含至少一个数字";
    }

    return true;
}