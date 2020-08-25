// 页面跳转
$(document).ready(function(){
    $("ul > li").click(function (e) {
        e.preventDefault();
        $("#iframeMain").attr("src", $(this).attr("href"));
    });
});

// 获取URL参数
function getUrlVariable(variable) {
    var reg = new RegExp('(^|&)' + variable + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}