$(function () {
   let url = jCtx+"pet/petList";
   console.log(url);
   $(".ser").click(function () {
      console.log("bbbbbbb");
      $.post(url,{
         pageNum:20,
         pageSize:20
      },function (resp){
         console.log("shjkdahskj");
         /*$("#tbl").empty();
         resp.forEach(function (i) {
            let $tr = $("<tr>");
            $tr.append($("<td>"+i.petId+"</td>"));
            $tr.append($("<td>"+i.petName+"</td>"));
            $tr.append($("<td>"+i.petSex+"</td>"));
            $tr.append($("<td>"+i.petAge+"</td>"));
            $tr.append($("<td>"+i.petType+"</td>"));
            $tr.append($("<td>"+i.petBreed+"</td>"));
            $tr.append($("<td>"+i.depositBegTime+"</td>"));
            $tr.append($("<td>"+i.depositBegTime+"</td>"));
            $tr.append($("<td>"+i.note+"</td>"));

            $("#tbl").append($tr);
         });*/
      },"json");
   });
});