function load() {
    var pageNo=$("#pageNo").val();
    var pageSize=$("#pageSize").val();
    $.ajax({
        url: "/resume/paging/"+pageNo+"/"+pageSize ,
        type: 'get'
    }).done(function(res) {
        var total=res.count;
        var pageNoLen=Math.ceil(total/pageSize);
        var pageNoOption="";
        var data=res.data;
        var tbodyContent="";
        $.each(data,function (i, v) {
            tbodyContent+="<td>"+v.id+"</td><td>"+v.name+"</td><td>"+v.gender+"</td><td>"+v.school+"</td><td>"+v.major+"</td><td>"+v.learnType+"</td><td><a href='"+v.verifyImg+"'>下载二维码</a></td><td><a class='del-btn' href='javascript:;' data-id="+v.id+">删除</a><a href='/static/view/list.html?id='"+v.id+">详情</a></td>"
        });
        $(".total").html(total);
        if(pageNoLen>0){
            for(var j=1;j<pageNoLen;i++){
                pageNoOption+="<option value='"+j+"'>"+j+"</option>";
            }
            $("#pageNo").html(pageNoOption);
        }
        $("#list-table tbody").html(tbodyContent);
    }).fail(function(res) {

    });
}
load();

$(".jump").on("click",function () {
    load();
});

$("#list-table").on("click",".del-btn",function () {
    $.ajax({
        url: "/resume/delete/"+$(this).attr("data-id") ,
        type: 'post'
    }).done(function(res) {
        if(res.success==true){
            alert("删除成功");
        }else{
            alert("删除失败");
        }
    }).fail(function(res) {

    });
});
