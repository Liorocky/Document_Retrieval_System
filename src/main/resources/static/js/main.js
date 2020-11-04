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

var form = layui.form,
    layer = layui.layer;

// 进行登录操作
form.on('submit(login)', function (data) {
    data = data.field;
    if (data.username === '') {
        layer.msg('用户名不能为空');
        return false;
    }
    if (data.password === '') {
        layer.msg('密码不能为空');
        return false;
    }
});
