// 标签
var form = layui.form;
form.on('select(tag-search)', function (data) {
    var tag_id = data.value; // 得到被选中标签的id
    var tag_name = data.elem[data.value].label; // 得到被选中的标签名
    var input = document.createElement("input");
    input.type = 'checkbox';
    input.title = tag_name;
    $('#tag-block').append(input);
    form.render("checkbox");
});

// 文档上传表格
var table = layui.table;

// 初始化加载表格
var tableIns = table.render({
    elem: '#file-upload-table'
    // ,width: 900
    , cols: [[
        {field: 'id', title: '序号', sort: true}
        , {field: 'fileName', title: '文件名'}
        , {field: 'type', title: '文件类型', sort: true}
        , {fixed: 'right', title: '操作', toolbar: '#bar'}
    ]]
    , data: [{
        'id': 1
        , 'fileName': '文档1'
        , 'type': 'pdf'
    }]
});


//上传
//多文件列表示例
var files=[];
var $ = layui.jquery
    ,upload = layui.upload;
var demoListView = $('#demoList')
    , uploadListIns = upload.render({
    elem: '#choose-btn' // 选择文件按钮
    , url: '/file/upload' //改成您自己的上传接口
    , accept: 'file'
    , exts: 'txt|rar|zip|doc|docx|pdf|xls|xlsx' //允许上传的文件类型
    , multiple: true
    , auto: false
    , bindAction: '#upload-btn' // 确认录入按钮
    , choose: function (obj) {
        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
        //读取本地文件
        obj.preview(function (index, file, result) {
            var tr = $(['<tr id="upload-' + index + '">'
                , '<td>' + file.name + '</td>'
                , '<td>' + (file.size / 1024).toFixed(1) + 'kb</td>'
                , '<td>等待上传</td>'
                , '<td>'
                , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                , '</td>'
                , '</tr>'].join(''));

            //单个重传
            tr.find('.demo-reload').on('click', function () {
                obj.upload(index, file);
            });

            //删除
            tr.find('.demo-delete').on('click', function () {
                delete files[index]; //删除对应的文件
                tr.remove();
                uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
            });

            demoListView.append(tr);
        });
    }
    , done: function (res, index, upload) {
        if (res.code === 0) {
            files.push({"fileName": res.filename, "fileUrl": res.fileUrl, "fileSize": res.fileSize});//,"fileUrl":res.fileUrl
            var json = JSON.stringify(files);
            //将上传的文件信息加入到集合中并转换成json字符串
            $("#fileJson").attr("value", json);

            var fileUrl = res.fileUrl;
            var tr = demoListView.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
            tds.eq(3).html('<span>' + fileUrl + '</span>');
            tds.eq(4).html(''); //清空操作
            return delete this.files[index]; //删除文件队列已经上传成功的文件
        }
        this.error(index, upload);
    }
    , error: function (index, upload) {
        var tr = demoListView.find('tr#upload-' + index)
            , tds = tr.children();
        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
});
