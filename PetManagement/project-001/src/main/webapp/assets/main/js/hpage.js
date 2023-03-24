$(function () {
    //点击修改个人信息，layer弹窗
    $("#edit-btn").click(function () {
        layer.open({
            type: 2,
            title: '修改个人信息',
            shadeClose: true,
            shade: false,
            area: ['600px', '500px'],
            content: ctx + 'user/userUpdate'
        });
    });
});