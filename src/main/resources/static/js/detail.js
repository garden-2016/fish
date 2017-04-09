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
    data: submitData.data
}).done(function(res) {
    console.log(res);
}).fail(function(res) {
});