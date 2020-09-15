const tag_display_number = 7; // 默认显示几个标签

var tags_data; // 标签数据
var tags_selected = new Set(); // 已选择的标签

// 文档检索表格
var table = layui.table;

// 初始化加载表格
table.render({
    elem: '#file-box-table'
    , url: '/fileBox/'
    , cellMinWidth: 80
    , cols: [[
        {field: 'id', title: '序号', sort: true}
        , {field: 'title', title: '标题'}
        , {field: 'desc', title: '描述', sort: true}
        , {field: 'count', title: '文件数量'}
        , {field: 'addTime', title: '添加时间', sort: true}
        , {field: 'lastTime', title: '修改时间', sort: true}
    ]]
    , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
        return {
            "code": 0, //解析接口状态
            "msg": res.message, //解析提示文本
            // "count": 1, //解析数据长度
            "data": res.data //解析数据列表
        };
    }
});

// 搜索框回车事件
$('#title-search').on('keydown', function (event) {
    if (event.keyCode === 13) {
        $('#search-btn').trigger("click");
    }
});

// 点击搜索按钮
function searchFileBox() {
    var title = $("#title-search")[0].value;

    // 根据条件筛选返回的数据重载表格
    table.reload('file-box-table', {
        url: '/fileBox/title/tags'
        ,method: "POST"
        ,contentType: 'application/json'
        ,where: {
                    "title": title,
                    "tags": Array.from(tags_selected)
                } //设定异步数据接口的额外参数
        ,done: function(res, curr, count){
            this.where = {};
        }
    });
}

// 弹出fileBox
var layer = layui.layer;

//监听表格行单击事件
table.on('row(file-box-table)', function (obj) {
    // console.log(obj.tr) //得到当前行元素对象
    // console.log(obj.data) //得到当前行数据

    layer.open({
        type: 2,
        content: '/pages/show-file-box.html?id=' + obj.data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    });

    //obj.del(); //删除当前行
    //obj.update(fields) //修改当前行数据
});

// 左侧显示标签
var form = layui.form;
form.on('select(tag-search)', function (data) {
    // 将选中的tag放到左边待选标签中
    if (data.value === "") return; // 忽略空值
    var tag_id = data.value; // 得到被选中标签的id
    var tag_name = tags_data.get(tag_id); // 得到被选中的标签名
    var input = document.createElement("input");
    input.type = 'checkbox';
    input.title = tag_name;
    $('#tag-block').append(input);

    // 从select列表中移除已选中的tag
    $('option').remove("[value=" + tag_id + "]");

    form.render();
});

// 加载标签数据
$.ajax({
    url: '/tag/', // 请求的URL地址
    type: 'GET', // 请求方法，GET、POST、PUT、DELETE在这里设置
    timeout: 5000,    // 超时时间
    dataType: 'json',    // 返回的数据格式：json/xml/html/script/jsonp/text
    success: function (data, textStatus, jqXHR) {    // 成功的回调函数
        tags_data = new Map();

        // 规定待选标签的数量，优化布局
        var block_num = data.data.length < tag_display_number ? data.data.length : tag_display_number;

        for (var o of data.data) {
            if (block_num > 0) {
                var $input = $("<input type='checkbox' lay-filter='tag_selected' value=" + o.id + " title=" + o.name + ">");
                $('#tag-block').append($input);
                tags_data.set(o.id, o.name);
            }

            if (block_num <= 0) {
                // 将剩下的tag添加到下拉列表中
                var $option = $("<option value=" + o.id + ">" + o.name + "</option>");
                $('#tag-search').append($option);
                tags_data.set(o.id, o.name);
            }
            block_num--;
        }

        // 更新select列表和block
        form.render();
    },
    error: function (xhr, textStatus) { // 失败的回调函数
        console.log(textStatus);
    }
});


// 将选中的tag放到左边待选标签中
form.on('select(tag-search)', function (data) {
    if (data.value === "") return; // 忽略空值
    var tag_id = data.value; // 得到被选中标签的id
    var tag_name = tags_data.get(parseInt(tag_id)); // 得到被选中的标签名
    var $input = $("<input type='checkbox' checked lay-filter='tag_selected' value=" + tag_id + " title=" + tag_name + ">");
    $('#tag-block').append($input);

    // 从select列表中移除已选中的tag
    $('option').remove("[value=" + tag_id + "]");

    // 将id添加到set集合中
    tags_selected.add(tag_id);

    form.render();
});

// 监听标签复选框点击
form.on('checkbox(tag_selected)', function(data){
    if (data.elem.checked) {
        tags_selected.add(data.value);
    } else {
        tags_selected.delete(data.value);
    }
    console.log(tags_selected);
});