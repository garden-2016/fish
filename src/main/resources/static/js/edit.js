function loadEndToDo () {
    //时间控件
    $( ".date-picker" ).datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange:'c-50:c+1'
    });
    //上传框样式
    $(".js-upload-img").on("click",function () {
        var baseName=$(this).attr("data-img");
        $('[name='+baseName+']').trigger("click");
    });
    $(".js-upload").on("change",function () {
        //解决上传路径问题
        // var node = this;
        // var imgURL="";
        // var reader = new FileReader();
        // reader.onload = function (e) {
        //     imgURL = e.target.result;
        //     $(".js-"+this.name+"-name").attr({src:imgURL,alt:imgURL});
        // };
        // reader.readAsDataURL(node.files[0]);
        if(this.name){
            upload_img(this.name);
        }
    });
    //上传
    function upload_img(name) {
        var formData = new FormData();
        formData.append('file', $('[name='+name+']')[0].files[0]);
        $.ajax({
            url: '/resume/upload',
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function(res) {
            if(res){
                $(".js-"+this.name+"-name").attr("src",res);
            }else{
                alert("呵呵,图片上传失败")
            }
        })
    }

    //获取表单数据
    var dataLines=$("#resultTable").find(".js-submit-info");
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
            if($("[name=data-id]").val()){
                dataList["id"]=$("[name=data-id]").val();
            }
            return {success:true, data:dataList};
        }
    }
    //提交操作
    $(".submit-btn").on("click",function () {
        var submitData=getData(dataLines);
        var url='/resume/add';
        if(submitData.success){
            if(submitData.data.id){
                url="/resume/update"
            }
            $.ajax({
                url: url,
                type: 'POST',
                data: submitData.data
            }).done(function(res) {
                alert("提交成功");
            }).fail(function(res) {
                alert("提交失败");
            });
        }else{
            alert("请检查表单是否完整");
        }
        console.log(submitData);
    });
}
loadEndToDo();