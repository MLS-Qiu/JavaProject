$(function () {
   /*事件委托 点击菜单在content部分显示*/
   $(".nav").on("click","li>a",function (e) {
      /*阻止超链接的默认行为*/
      e.preventDefault();
      console.log("111111");
      /*取出菜单中a中的属性值*/
      let menuVal = $(this).attr("href");
      /* 将获得的值赋给iframe*/
      $(".content>iframe").attr("src",menuVal);
   });
});