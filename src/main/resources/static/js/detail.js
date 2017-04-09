// 截取键值
function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return decodeURI(r[2]); return null;
}
var id_= getUrlParam("id");
$.ajax({
    url: "/resume/get/"+id_ ,
    type: 'POST'
}).done(function(res) {
    $(".main").removeClass("hide");
    var formList=res.data;
    $.each($(".fill-data"),function (i, v) {
        var that=$(v);
        if(v.tagName=="IMG"){
            that.attr("src",formList[that.attr("data-fill")]);
        }else if(that.hasClass("fordate")){
            that.html(fortime(formList[that.attr("data-fill")]));
        }else{
            that.html(formList[that.attr("data-fill")]);
        }
    })
}).fail(function(res) {

});
function fortime(data) {
    if(data){
        var dataList=data.split("-");
        return dataList[0]+"年"+Number(dataList[1])+"月"+Number(dataList[2])+"日";
    }
    return "";
}