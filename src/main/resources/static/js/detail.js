// 截取键值
function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return decodeURL(r[2]); return null;
}
var id_= getUrlParam("id");
$.ajax({
    url: "/resume/get/"+id_ ,
    type: 'POST',
}).done(function(res) {
    var formList=res.data;
    $.each($(".fill-data"),function (i, v) {
        var that=$(v);
        if(v.tagName=="IMG"){
            that.attr("src",formList[that.attr("data-fill")]);
        }else{
            that.html(formList[that.attr("data-fill")]);
        }
    })
}).fail(function(res) {

});