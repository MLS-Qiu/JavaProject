$(function () {
    if (error){
        layer.alert(error);
    }
    //自动赋值用户名和密码，实现自动登录
    let rememberState = localStorage.getItem("rememberMe");
    if (rememberState === "true") {
        let username = localStorage.getItem("username");
        if (username) {
            $("#username").val(username);
        }
        let password = localStorage.getItem("password");
        if (password) {
            $("#password").val(password);
        }
    }
   $("#login-bnt").click(function () {
       let state = $("#reMe").prop("checked");
       if (state) {
           let username = $("#username").val();
           let password = $("#password").val();
           //将用户名和密码保存到本地缓存
           localStorage.setItem("rememberMe", "true");//第2个参数是字符串类型
           localStorage.setItem("username", username);
           localStorage.setItem("password", password);
       } else {
           localStorage.removeItem("rememberMe");
           localStorage.removeItem("username");
           localStorage.removeItem("password");
       }
       //数据校验
       let username = $(":input[name=username]").val();
       if (username.trim() === "") {
           layer.msg("请输入用户名");
           return false;
       }
       let password = $(":input[name=password]").val();
       if (password.trim() === "") {
           layer.msg("请输入密码");
           return false;
       }
       //提交数据
       $("#login").submit();
   });
    $("#captcha").click(function () {
        $(this).attr("src", ctx + "user/captcha?k=" + new Date().getTime());
    });
});