var id = getUrlVariable("id");

var table = layui.table;

// 请求文档集内的所有文件
$.ajax({
    url: '/file/fileBox/' + id,    //请求的URL地址
    type: 'GET', //请求方法，GET、POST、PUT、DELETE在这里设置
    timeout: 5000,    //超时时间
    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    success: function (data, textStatus, jqXHR) {    //成功的回调函数
        console.log(data);

        // 填充表格
        table.render({
            elem: '#file-box-list'
            ,initSort: {field: 'numberOrder', type: 'asc'}
            ,cols: [[ //表头
                {field: 'numberOrder', title: '序号', align: 'center', width: 60}
                ,{field: 'fileName', title: '文件名', align: 'center', width: 700}
                ,{field: 'type', title: '类型', align: 'center', width: 60}
                ,{field: 'tools', title: '操作', align: 'center'}
            ]]
            ,data: data.data
        });
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