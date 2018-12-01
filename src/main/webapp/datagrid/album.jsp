<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-tip',
            text: "专辑详情",
            handler: function () {
                //alert('专辑详情')
                var row = $("#album_tt").treegrid("getSelected");
                //console.log(row.id);
                if (row == null) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选中专辑',
                        showType: 'show',
                        timeout: 3000,
                        showType: 'slide'
                    });
                } else {
                    if (row.score != null) {
                        $("#album_title").val(row.title);
                        $("#album_author").val(row.author);
                        $("#album_score").val(row.score);
                        $("#album_broadCast").val(row.broadCast);
                        $("#album_count").val(row.album_count);
                        $("#coverImg").prop('src', "${pageContext.request.contextPath}" + row.coverImg);
                        $("#publishDate").val(row.publishDate);
                        $("#album_brief").val(row.brief);
                        $("#album_dd").dialog("open");

                    } else {
                        $.messager.show({
                            title: '提示信息',
                            msg: '请选中专辑',
                            showType: 'show',
                            timeout: 3000,
                            showType: 'slide'
                        });
                    }
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加专辑",
            handler: function () {
                //alert('添加专辑')
                var row = $("#album_tt").treegrid("getSelected");
                //console.log(row.id);
                if (row == null) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选中专辑',
                        showType: 'show',
                        timeout: 3000,
                        showType: 'slide'
                    });
                } else {
                    if (row.score != null) {
                        $("#addAlbum_dd").dialog("open");
                    } else {
                        $.messager.show({
                            title: '提示信息',
                            msg: '请选中专辑',
                            showType: 'show',
                            timeout: 3000,
                            showType: 'slide'
                        });
                    }
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加章节",
            handler: function () {
                //alert('添加章节');
                var row = $("#album_tt").treegrid("getSelected");
                //console.log(row.id);
                if (row == null) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选中专辑',
                        showType: 'show',
                        timeout: 3000,
                        showType: 'slide'
                    });
                } else {
                    if (row.score != null) {
                        $("#album_id").val(row.id);
                        $("#addChapter_dd").dialog("open");
                    } else {
                        $.messager.show({
                            title: '提示信息',
                            msg: '请选中专辑',
                            showType: 'show',
                            timeout: 3000,
                            showType: 'slide'
                        });
                    }
                }
            }
        }, '-', {
            iconCls: 'icon-undo',
            text: "下载音频",
            handler: function () {
                //alert('下载音频');
                var row = $("#album_tt").treegrid("getSelected");
                console.log(row);
                if (row == null) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选中章节',
                        showType: 'show',
                        timeout: 3000,
                        showType: 'slide'
                    });
                } else {
                    if (row.score == null) {
                        if (row.chapter_size != null) {
                            location.href = "${pageContext.request.contextPath}/chapter/download?url=" + row.downPath + "&title=" + row.title;
                        }
                    } else {
                        $.messager.show({
                            title: '提示信息',
                            msg: '请选中章节',
                            showType: 'show',
                            timeout: 3000,
                            showType: 'slide'
                        });
                    }
                }
            }
        }]

        $('#album_tt').treegrid({
            url: '/cmfz/album/getAllAlbumAdnChapter.do',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {title: '名称', field: 'title', width: 180},
                {title: "下载路径", field: "downPath", width: 60, align: 'right'},
                {title: "章节大小", field: 'chapter_size', width: 80},
                {title: '章节时长', field: 'duration', width: 80}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 15, 40, 300],
            toolbar: toolbar,

            // 双击播放
            onDblClickRow: function (row) {
                console.log(row);
                $("#audio_id").prop("src", "${pageContext.request.contextPath}" + row.downPath)
                $("#audio").dialog("open")
            }
        });

        // 文件上传(文件域)的改变事件
        $(':file').change(function (event) {
            //  HTML5 js 对象的获取
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];
                // 文件的限定类型什么的道理是一样的
                if (file.size > 1024 * 1024 * 10) {
                    alert('图片大小不能超过 10MB!');
                    return false;
                }
                // file对象生成可用的图片
                var URL = window.URL || window.webkitURL;
                // 通过 file 生成目标 url
                var imgURL = URL.createObjectURL(file);
                // 用这个 URL 产生一个 <img> 将其显示出来
                $("#AlbumImage1").prop('src', imgURL);
            }
        });

        // 添加专辑详情对话框
        $('#addAlbum_dd').dialog({
            title: '添加章节',
            width: 640,
            height: 400,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    addAlbum();
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addAlbum_dd").dialog("close");
                }
            }],
        });
        // 添加专辑详情对话框=======END

        // 添加章节对话框
        $('#addChapter_dd').dialog({
            title: '添加章节',
            width: 400,
            height: 200,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    addChapter();
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addChapter_dd").dialog("close")
                }
            }],
        });
        // 添加章节对话框==========END

        // 播放
        $('#audio').dialog({
            title: '播放',
            width: 400,
            height: 200,
            closed: true,
            onClose: function () {
                //alert(0);
                $("#album_tt").treegrid("load");
            }
        });
        // 播放====END
    })

    function addAlbum() {
        // 添加表单提交
        $("#addAlbum_ff").form("submit", {
            // 通过form控件的submit方法提交数据
            url: "/cmfz/album/addAlbum.do",
            success: function (data) {
                // 回调函数，后台响应结果data是json串
                console.log(data);
                data = JSON.parse(data);
                if (data) {
                    // 添加成功后关闭添加对话框并重新加载数据
                    $("#addAlbum_dd").dialog("close");
                    $("#album_tt").treegrid("load");
                } else {
                    $.messager.alert("提示消息", "添加专辑失败！", "info");
                }
            }
        });
        // 添加表单提交========END
    }

    function addChapter() {
        // 添加表单提交
        $("#addChapter_ff").form("submit", {
            // 通过form控件的submit方法提交数据
            url: "/cmfz/chapter/addChapter.do",
            success: function (data) {
                // 回调函数，后台响应结果data是json串
                console.log(data);
                data = JSON.parse(data);
                if (data) {
                    // 添加成功后关闭添加对话框并重新加载数据
                    $("#addChapter_dd").dialog("close");
                    $("#album_tt").treegrid("load");
                } else {
                    $.messager.alert("提示消息", "添加章节失败！", "info");
                }
            }
        });
        // 添加表单提交========END
    }
</script>
<%--数据展示--%>
<table id="album_tt"></table>
<%--数据展示====END--%>

<div id="album_dd" class="easyui-dialog" title="专辑详情" style="width:640px;height:320px;"
     data-options="resizable:true,modal:true,closed: true">

    <table width="625px" border="1px solid black">
        <tr>
            <td rowspan="6" width="200px" height="120px"><img src="" width="350px" height="180px" id="coverImg"/></td>
            <td>主题：<input type="text" id="album_title"/></td>
        </tr>
        <tr>
            <td>评分：<input type="text" id="album_score"/></td>
        </tr>
        <tr>
            <td>作者：<input type="text" id="album_author"/></td>
        </tr>
        <tr>
            <td>播音：<input type="text" id="album_broadCast"/></td>
        </tr>
        <tr>
            <td>发布时间：<input type="text" id="publishDate"/></td>
        </tr>
        <tr>
            <td>章节集数：<input type="text" id="album_count"/></td>
        </tr>
        <tr>
            <td colspan="2">详情简介：<textarea rows="3" cols="89" id="album_brief"></textarea></td>
        </tr>
    </table>

</div>

<div id="addAlbum_dd">

    <form id="addAlbum_ff" method="post" enctype="multipart/form-data">

        <table width="625px" border="1px solid black">
            <tr>

                <td rowspan="6" width="200px" height="120px"><img src="" width="350px" height="180px" id="AlbumImage1"/>
                </td>
                <td>主题：<input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>评分：<input type="text" name="score"/></td>
            </tr>
            <tr>
                <td>作者：<input type="text" name="author"/></td>
            </tr>
            <tr>
                <td>播音：<input type="text" name="broadCast"/></td>
            </tr>
            <tr>
                <td>章节集数：<input type="text" name="album_count"/></td>
            </tr>
            <tr>
                <td>上传专辑封面：<input type="file" name="uploadFile"/></td>
            </tr>
            <tr>
                <td colspan="2">详情简介：<textarea rows="3" cols="89" name="brief"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="addChapter_dd">

    <form id="addChapter_ff" method="post" enctype="multipart/form-data">
        <input type="hidden" name="album_id" value="" id="album_id">
        <div>
            标题:<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            请选择:<input type="file" name="uploadFile" style="width:300px">
        </div>
    </form>
</div>

<div id="audio">
    <audio id="audio_id" src="" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>


