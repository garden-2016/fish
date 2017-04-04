(function () {
    //时间控件
    $( ".date-picker" ).datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange:'c-30:c+10'
    });
    //上传框样式
    $(".js-upload-img").on("click",function () {
        var baseName=$(this).attr("data-img");
        $('[name='+baseName+']').trigger("click");
    });
    $(".js-upload").on("change",function () {
        $(".js-"+this.name+"-name").html(this.value);
    });
    //上传
    function upload_img(name) {
        var formData = new FormData();
        formData.append('file', $('[input='+name+']')[0].files[0]);
        $.ajax({
            url: '/upload',
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function(res) {

        }).fail(function(res) {

        });
    }

    //获取表单数据
    var dataLines=$("#resultTable").find("input,select");
    function getData(data) {
        var index=0;
        var errorList=[];
        var dataList={};
        $.each(data,function (i, v) {
            if(v.value){
                dataList[v.name]=v.value;
                index++;
            }else {
                errorList.push(v.name);
            }
        });
        if(index!=data.length){
            return {success:false, data:errorList};
        }else{
            return {success:true, data:dataList};
        }
    }
    $(".btn").on("click",function () {
        console.log(getData(dataLines));
    });
}())