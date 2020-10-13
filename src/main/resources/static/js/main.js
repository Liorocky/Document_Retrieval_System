// 获取URL参数
function getUrlVariable(variable) {
    var reg = new RegExp('(^|&)' + variable + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

// 自定义唯一值map 判断是否有value 类似hasKey
function hasValue(map, value) {

}