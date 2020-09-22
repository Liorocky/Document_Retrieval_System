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

        // $("#result").html(data) //设置id为result的标签的html内容为返回的data数据
    },
    error: function (xhr, textStatus) { //失败的回调函数
        // $("#result").html(textStatus)
    }
});

// // 请求文档集标题、描述
// $.ajax({
//     url: '/fileBox/' + id,    //请求的URL地址
//     type: 'GET', //请求方法，GET、POST、PUT、DELETE在这里设置
//     timeout: 5000,    //超时时间
//     dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
//     success: function (data, textStatus, jqXHR) {    //成功的回调函数
//         console.log(data);
//         // $("#result").html(data) //设置id为result的标签的html内容为返回的data数据
//     },
//     error: function (xhr, textStatus) { //失败的回调函数
//         // $("#result").html(textStatus)
//     }
// });