/**
 * 用法见wiki文档：http://jira.chsi.com.cn/wiki/pages/viewpage.action?pageId=3671974
 *
 * @param id 要显示分享按钮的容器的Id
 * @param uid 分享的注册用户，可以为空
 */
function SnsShare(id,uid) {
    this.cid = id;
    this.userId = uid;
    this.html = '<img class="share_imgl" src="http://t1.chsi.com.cn/common/images/shareto.png" /><span class="share_text">分享到：</span><ul><li><a title="新浪微博" class="sinawb" href="javascript:void(0);">新浪微博</a></li><li><a title="QQ空间" class="qqkj" href="javascript:void(0);">QQ空间</a></li><li><a title="腾讯微博" class="qqwb" href="javascript:void(0);">腾讯微博</a></li><li class="wx_part"><a class="qqwx" href="javascript:void(0);">微信</a><span class="wx_code"><span class="qrcode"></span><span class="wx_text">用微信扫描二维码<br/>分享至好友和朋友圈</span></span></li></ul>';
    this.recordUrl = "http://www.chsi.com.cn/snsshare.jsp";
    this.recordFlag = true;
    this.ewm_img = '<img src="http://www.chsi.com.cn/qrcodeImg.do" alt="二维码" />';
    this.topic="";
}
SnsShare.prototype = {
    shareto : function(t) {
        var f;
        var title = encodeURIComponent(document.title);
        var url = encodeURIComponent(location.href);
        f = this.recordUrl+"?url="+url+"&title="+title+"&target="+t+"&userId="+this.userId;
        if(this.topic!=''){
            f=f+"&topic="+this.topic;
        }
        function a() {
            if (!window.open(f, t, [ 'toolbar=0,status=0,resizable=1,width=620,height=450,left=',
                    (screen.width - 620) / 2, ',top=', (screen.height - 450) / 2 ].join(''))) {
                document.location.href = f;
            }
        }
        ;
        if (/Firefox/.test(navigator.userAgent)) {
            setTimeout(a, 0);
        } else {
            a();
        }
    },
    setStyleUrl : function(url) {
        this.styleUrl = url;
    },
    setRecordUrl : function(url) {
        this.recordUrl = url;
        this.recordFlag = true;
    },
    setRecordFlag : function() {
        this.recordFlag = false;
    },
    setTopic : function(t) {
        this.topic = t;
    },
    defaultShow : function(){
        document.getElementById(this.cid).innerHTML = this.html;
        var obj = this;
        for(var i=0;i<document.getElementById(obj.cid).getElementsByTagName("a").length-1;i++){
            document.getElementById(obj.cid).getElementsByTagName("a").item(i).onclick = function(){
                var target = this.className;
                obj.shareto(target);
            };
        }
        $('.wx_code').prepend('<span class="wx_jiao"></span><span class="wx_jiao2"></span>');
        $(".wx_part").mouseenter(function(){
            if($(".qrcode").children().length<1){
                $(".qrcode").html(obj.ewm_img);
            }
            $(".qqwx").siblings().show();
        }).mouseleave(function(){
            $(".qqwx").siblings().hide();
        });
    },
    show : function() {
        $("#" + this.cid).addClass("sharepanel");
        $("#" + this.cid).html(this.html);
        $("#" + this.cid).children().mouseenter(function(){
            $(this).parent().stop().animate({width: "154px"}, "fast");
        });
        $("#" + this.cid).children().mouseleave(function(){
            $(this).parent().stop().animate({width: "26px"}, "fast");
        });
        var obj = this;
        $("#" + this.cid + " a").each(function(){
            var target = $(this).attr("class")
            if(target=="qqwx"){
                $(" .wx_part").mouseenter(function(){
                    if($(".qrcode").children().length<1){
                        $(".qrcode").html(obj.ewm_img);
                    }
                    $(".sharepanel .wx_part").stop().animate({height: "181px"}, "fast");
                }).mouseleave(function(){
                    $(".wx_part").stop().animate({height: "31px"}, "fast");
                });
            }else{
                $(this).bind("click", function() {
                    obj.shareto(target);
                })
            }
        })
    }
}