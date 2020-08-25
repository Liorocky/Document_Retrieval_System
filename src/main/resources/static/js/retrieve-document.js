// 文档检索表格
var table = layui.table;
table.render({
    elem: '#file-box-table'
    ,url:'/fileBox/'
    // ,even: true // 隔行背景
    ,cellMinWidth: 80
    ,cols: [[
        {field:'id', title: '序号', sort: true}
        ,{field:'title', title: '标题'}
        ,{field:'desc', title: '描述', sort: true}
        ,{field:'count', title: '文件数量'}
        ,{field:'addTime', title: '添加时间', sort: true}
        ,{field:'lastTime', title: '修改时间', sort: true}
    ]]
    ,response: {
        statusCode: 0 //重新规定成功的状态码为 200，table 组件默认为 0
    }
    ,parseData: function(res) { //将原始数据解析成 table 组件所规定的数据
        return {
            "code": 0, //解析接口状态
            "msg": res.message, //解析提示文本
            // "count": 1, //解析数据长度
            "data": res.data //解析数据列表
        };
    }
});

// 弹出fileBox
var layer = layui.layer;

//监听行单击事件
table.on('row(file-box-table)', function(obj){
    // console.log(obj.tr) //得到当前行元素对象
    // console.log(obj.data) //得到当前行数据

    layer.open({
        type: 2,
        content: '/pages/show-file-box.html?id=' + obj.data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    });

    //obj.del(); //删除当前行
    //obj.update(fields) //修改当前行数据
});

// 标签
var form = layui.form;
form.on('select(tag-search)', function(data){
    var tag_id = data.value; // 得到被选中标签的id
    var tag_name = data.elem[data.value].label; // 得到被选中的标签名
    var input = document.createElement("input");
    input.type = 'checkbox';
    input.title = tag_name;
    $('#tag-block').append(input);
    form.render("checkbox");
});