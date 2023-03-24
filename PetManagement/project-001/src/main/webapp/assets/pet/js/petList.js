$(function () {
    //分页事件
    $(".paginate>ul").on("click", "li", function (e) {
        //阻止超链接默认行为
        e.preventDefault();
        let pageNo = $("#pageNo").text().trim();//获取当前页码
        let pageSize = $("#pageSize").val();//获得页面大小
        let pageAllCount = $("#allPageCount").text().trim();//获得总页面数
        //获取a中的文字，并去掉文字两边的空格
        let aText = $(this).text().trim();
        //判断是不是首页
        if ($(this).is(".firPage")) {
            search(1, pageSize);
        } else if ($(this).is(".prevPage")) {
            pageNo--;
            if (pageNo < 1) {
                pageNo = 1;
            }
            search(pageNo, pageSize);
        } else if ($(this).is(".nextPage")) {
            pageNo++;
            if (pageNo > pageAllCount) {
                pageNo = pageAllCount;
            }
            search(pageNo, pageSize);
        } else if ($(this).is(".lastPage")) {
            search(pageAllCount, pageSize);
        } else {
            search(parseInt(aText), pageSize);
        }
    });
    $("#pageSize").change(function () {
        let pageNo = $("#pageNo").text().trim();//获取当前页码
        let pageSize = $("#pageSize").val();//获得页面大小
        search(pageNo, pageSize);
    });

    /*查询按钮*/
    $(".ser-bnt").click(function () {
        let pageSize = $("#pageSize").val();//获得页面大小
        search(1, pageSize);
    });
    $(".ser-bnt").trigger("click");

 /*   /!*添加按钮事件*!/
    $(".insert-bnt").click(function () {
        location.href = jCtx + "pet/petInsert";
    });

    /!*修改按钮事件*!/
    $(".upd-bnt").click(function () {
        let $chs = $("#tbl>tbody>tr>td:first-child>:checked");
        if ($chs.length === 0) {
            layer.msg("请选择您要删除的记录");
        } else if ($chs.length > 1) {
            layer.msg("您一次只能删除一条记录");
        } else {
            //若用户选中条数正确，则获得用户所选中记录的宠物id
            let id = $($chs).closest("tr").children().eq(1).text();
            location.href = jCtx + "pet/petUpdate?id=" + id;
        }
    });*/

/*    /!*删除按钮事件*!/
    $(".del-bnt").click(function () {
        //获得选择器的状态
        let $chs = $("#tbl>tbody>tr>td:first-child>:checked");
        if ($chs.length === 0) {
            layer.msg("请选中您要删除的记录");
            return;
        } else {
            let ids = [];
            //将选中的数据的id取出来放进ids数组中
            $chs.each(function (idx, args) {
                let id = $(this).closest("tr").children().eq(1).text();
                ids.push(id);
            });

            if (ids.length > 0) {
                layer.confirm("是否确认删除", function () {
                    deleteById(ids);
                });
            }
        }
    });
    //重置事件
    $(".reset-bnt").click(function () {
        $(".search>form")[0].reset();
    });*/
/*
    //时期插件
    //1.寄存开始时间始
    laydate.render({
        elem: "#p_bTimeFrom"
    });
    //2.寄存开始时间终
    laydate.render({
        elem: "#p_bTimeTo"
    });
    //3.寄存结束时间始
    laydate.render({
        elem: "#p_eTimeFrom"
    });
    //4.寄存结束时间终
    laydate.render({
        elem: "#p_eTimeTo"
    });*/

    /*//全选与取消全选事件
    $("#checkPapa").click(function () {
        //获得全选键的状态，命名为checkBox
        let checked = $(this).prop("checked");
        //再将所有的选中状态设置得和全选键的一样
        $(".checkChild").prop("checked", checked);
        /!*let $tr = $(".checkChild").parent();
        if ($(this).prop("checked")){
            $tr.addClass(".act-check");
        }else{
            $tr.removeClass(".act-check");
        }*!/
    });

    //点击行选择事件
    $("#tbl>tbody").on("click", "tr>td:not(:first-child)", function () {
        let $tr = $(this).parent();
        //获得checkbox
        let $check = $tr.children().eq(0).children();
        //把选中按钮的状态设置成和现在相反
        $check.prop("checked", !$check.prop("checked"));
    });*/
});

//查找函数
function search(pageNum, pageSize) {
    let petId = $(".search #p_id").val();
    let petName = $(".search #p_name").val();
    let petSex = $(".search #p_sex").val();
    let petAge = $(".search #p_age").val();
    let petOid = $(".search #p_oId").val();
    let petType = $(".search #p_type").val();
    let petBreed = $(".search #p_breed").val();
    let petBegTimeTo = $(".search #p_bTimeTo").val();
    let petBegTimeFrom = $(".search #p_bTimeFrom").val();
    let petEndTimeTo = $(".search #p_eTimeTo").val();
    let petEndTimeFrom = $(".search #p_eTimeFrom").val();
    let petNote = $(".search #p_note").val();

    let data = {
        pageNum,
        pageSize
    };

    //校验数据
    if (petId.trim() !== " ") {
        data.petId = petId;
    }
    if (petName.trim() !== " ") {
        data.petName = petName;
    }
    if (petSex.trim() !== " ") {
        data.petSex = petSex;
    }
    if (petAge.trim() !== " ") {
        data.petAge = petAge;
    }
    if (petOid.trim() !== " ") {
        data.petOid = petOid;
    }
    if (petType.trim() !== " ") {
        data.petType = petType;
    }
    if (petBreed.trim() !== " ") {
        data.petBreed = petBreed;
    }
    if (petBegTimeFrom.trim() !== " ") {
        data.petBegTimeFrom = petBegTimeFrom;
    }
    if (petBegTimeTo.trim() !== " ") {
        data.petBegTimeTo = petBegTimeTo;
    }
    if (petEndTimeFrom.trim() !== " ") {
        data.petEndTimeFrom = petEndTimeFrom;
    }
    if (petEndTimeTo.trim() !== " ") {
        data.petEndTimeTo = petEndTimeTo;
    }
    if (petNote.trim() !== " ") {
        data.petNote = petNote;
    }

    let url = jCtx + "pet/petList";

    //发送一个ajax请求
    $.post(url, data, function (resp) {
        let pets = resp.petList;
        let pg = resp.pi;

        //总记录数和总页面数
        $("#allCount").text(pg.allCount);
        $("#allPageCount").text(pg.pageAllCount);
        $("#pageNo").text(pg.pageNo);

        $("#tbl>tbody").empty();

        pets.forEach(function (i) {
            let $tr = $("<tr>");
            /*$tr.append($("<td><input class='checkChild' type='checkbox'></td>"));*/
            $tr.append($("<td>" + i.petId + "</td>"));
            $tr.append($("<td>" + i.petName + "</td>"));
            $tr.append($("<td>" + i.petSex + "</td>"));
            $tr.append($("<td>" + i.petAge + "</td>"));
            $tr.append($("<td>" + i.ownerId + "</td>"));
            $tr.append($("<td>" + i.petType + "</td>"));
            $tr.append($("<td>" + i.petBreed + "</td>"));
            $tr.append($("<td>" + i.depositBegTime + "</td>"));
            $tr.append($("<td>" + i.depositEndTime + "</td>"));
            $tr.append($("<td>" + i.note + "</td>"));

            $("#tbl").append($tr);
            //更新页码条
            $(".paginate>ul>li:not(.firPage):not(.lastPage):not(.prevPage):not(.nextPage)").remove();
            let pFirst = pg.pageBeg;
            let pLast = pg.pageEnd;
            for (let i = pFirst; i <= pLast; i++) {
                let $li = $("<li><a href='#'>" + i + "</a></li>");
                if (i === pg.pageNo) {
                    $li.addClass("act");
                }
                $(".paginate li.nextPage").before($li);
            }


        });
    }, "json");
}

/*
function deleteById(ids) {
    console.log(ids);
    let url = jCtx + "pet/petDelete";

    $.ajax(url, {
        method: "post",
        data: {
            ids
        },
        dataType: "json",
        traditional: true,//可以向后台提交数组参数
        success: function (resp) {//成功调用此函数
            if (resp.success) {
                layer.msg(resp.suc || "删除成功");
                location.reload();//刷新页面
            } else {
                layer.alert(resp.ero || "删除失败");
            }
        },
        error: function () {//失败调用此函数
            layer.alert(resp.ero || "删除失败");
        }
    });
}*/
