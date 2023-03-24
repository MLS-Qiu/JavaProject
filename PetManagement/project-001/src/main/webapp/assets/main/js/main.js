$(function () {

   //点击首页，二级菜单收回
   $(".hp").click(function(){
      var $l2 = $(this).siblings().children(".level2");
      $l2.slideUp(600);
      $(this).siblings().children(".dimg").removeClass("current");
   });
   //菜单下拉事件
   $(".level1").click(function(){

      var $l2 = $(this).children(".level2");
      $l2.slideDown(600);
      var $otherl2 = $(this).siblings().children(".level2");
      $otherl2.slideUp(600);

      $(this).children(".dimg").addClass("current");

      $(this).siblings().children(".dimg").removeClass("current");
   });

   /*事件委托 点击菜单在content部分显示*/
   $(".nav").on("click","li>a",function (e) {
      /*阻止超链接的默认行为*/
      e.preventDefault();
      /*取出菜单中a中的属性值*/
      let menuVal = $(this).attr("href");
      /* 将获得的值赋给iframe*/
      $(".content>iframe").attr("src",menuVal);
   });

   $(".logout>a").click(function (e) {
      e.preventDefault();
      location.href = ctx + "user/logout";
   });

});