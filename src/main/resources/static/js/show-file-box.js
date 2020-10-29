var id = getUrlVariable("id");

var table = layui.table;
var form = layui.form;
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

// 请求文档集内的所有文件
/**
 * @param data.data.fileDOList 文件列表
 * @param data.data.tagDOList 标签列表
 */
$.ajax({
    url: '/fileBox/' + id,    //请求的URL地址
    type: 'GET', //请求方法，GET、POST、PUT、DELETE在这里设置
    timeout: 5000,    //超时时间
    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    success: function (data, textStatus, jqXHR) {    //成功的回调函数
        console.log(data);
        // 填充文件表格
        table.render({
            elem: '#file-box-list'
            , initSort: {field: 'numberOrder', type: 'asc'}
            , cols: [[ //表头
                {field: 'numberOrder', title: '序号', align: 'center', width: 60}
                ,{field: 'fileName', title: '文件名', align: 'center', width: 700}
                ,{field: 'type', title: '类型', align: 'center', width: 60}
                ,{field: 'tools', title: '操作', align: 'center'}
            ]]
            , data: data.data.fileDOList
        });

        console.log("填充标签");
        // 填充标签
        var tagDOList = data.data.tagDOList;
        if (tagDOList.length !== 0) {
            for (var i = 0; i < tagDOList.length; i++) {
                var tagId = tagDOList[i].id;
                var tagName = tagDOList[i].name;
                var $input = $("<input type='checkbox' checked lay-filter='tag-show-block' value=" + tagId + " title=" + tagName + ">");
                $('#tag-block-show').append($input);
            }
        } else {
            var $label = $("<label class='layui-form-label'>无标签</label>");
            $('#tag-block-show').append($label);
        }
        form.render();
    },
    error: function (xhr, textStatus) { //失败的回调函数
        // $("#result").html(textStatus)
    }
});

// 设置文档集的标题、描述
var title = $("#selectedTitle", window.parent.document)[0].innerHTML;
var desc = $("#selectedDesc", window.parent.document)[0].innerHTML;
$("#title-input").val(title);
$("#desc-input").val(desc);

// 打包下载文件
function downloadFileBox() {
    //获取当前网址，
    var curPath = window.document.location.href;
    //获取主机地址之后的目录，
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht = curPath.substring(0, pos);
    var downloadUrl = "/file/download/FileBox/";

    window.location.href= localhostPaht + downloadUrl + id;
}

// 设置标签点击无效
form.on('checkbox(tag-show-block)', function(data){
    $("input[type=checkbox]").prop('checked', 'checked');
    form.render('checkbox');
});

// 删除文档集
function confirmDelete() {
    layer.msg('确定删除？', {
        time: 0 //不自动关闭
        , btn: ['确定', '取消']
        , yes: function(index){
            $.ajax({
                url: '/fileBox/' + id,
                type:'PUT',
                success:function(data){ // http code 200
                    if (data.code === 0) {
                        layer.msg('删除成功', {icon: 1});
                    } else {
                        layer.msg('删除失败', {icon: 2});
                    }
                    setTimeout(function() {
                        parent.location.reload();
                        parent.layer.close(index);
                    }, 1500);
                }
            });
        }
    });
}