<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    $(function () {
        // 添加对话框
        $("#addTurnImg").dialog({
            title: "添加对话框",
            width: 580,
            height: 400,
            resizable: true,
            closed: true,
            //maximizable: true,
            modal: true,
            //closable:true
        });
        // 添加对话框=========END

        // 文件上传(文件域)的改变事件
        $(':file').change(function (event) {
            //  HTML5 js 对象的获取
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];
                // 文件的限定类型什么的道理是一样的
                if (file.size > 1024 * 1024 * 10) {
                    alert('图片大小不能超过 2MB!');
                    return false;
                }
                // file对象生成可用的图片
                var URL = window.URL || window.webkitURL;
                // 通过 file 生成目标 url
                var imgURL = URL.createObjectURL(file);
                // 用这个 URL 产生一个 <img> 将其显示出来
                $("#turnImage").prop('src', imgURL);
            }
        });
    });

    function add_submit() {
        //alert(0);
        // 添加表单提交
        $("#addTurnImg_form").form("submit", {
            // 通过form控件的submit方法提交数据
            url: "/cmfz/turnImg/addImg.do",
            success: function (data) {
                // 回调函数，后台响应结果data是json串
                console.log(data);
                data = JSON.parse(data);
                if (data) {
                    // 添加成功后关闭添加对话框并重新加载数据
                    $("#addTurnImg").dialog("close");
                    $("#imgDatagrid").edatagrid("load");
                } else {
                    $.messager.alert("提示消息", "添加失败！", "info");
                }
            }
        });
        // 添加表单提交========END
    }

    // 取消添加操作
    function exit() {
        //alert(0);
        $("#addTurnImg").dialog("close");
        $("#imgDatagrid").edatagrid("load");
    }

    // 取消添加操作====END
    // 定义toolbar 工具栏
    var toolbar = [{
        iconCls: 'icon-add',
        text: "添加",
        handler: function () {
            //alert('添加按钮');
            $("#addTurnImg").dialog("open");
        }
    }, '-', {
        iconCls: 'icon-remove',
        text: "删除",
        handler: function () {
            //alert("删除");
            // 获取要删除的数据的id
            var deleteIds = $("#imgDatagrid").edatagrid("getSelections");
            console.log(deleteIds);
            if (deleteIds.length == 0) {
                $.messager.alert("提示信息", "请选择删除数据！", "info");
            } else {
                // 确认是否删除
                var isConfirm = confirm("确认要删除选中的内容吗？");
                if (isConfirm) {
                    // 获取选中数据的id值
                    var ids = new Array(deleteIds.length);
                    for (var i = 0; i < deleteIds.length; i++) {
                        ids[i] = deleteIds[i].img_id;
                    }
                    //alert(ids);
                    $.ajax({
                        url: "/cmfz/turnImg/deleteMultiImg.do",
                        type: "post",
                        data: "ids=" + ids,
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            //alert(data);
                            if (data) {
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '删除成功',
                                    showType: 'show',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
                                $("#imgDatagrid").datagrid("reload");
                            } else {
                                $.messager.alert("提示信息", "删除失败！", "info");
                            }
                        }
                    });
                }
            }
        }
    }, '-', {
        iconCls: 'icon-edit',
        text: "修改",
        handler: function () {
            //alert('编辑按钮')
            /*获取选中行*/
            var row = $("#imgDatagrid").edatagrid("getSelected");
            console.log(row);
            if (row == null) {
                $.messager.show({
                    title: '警告',
                    msg: '请选中修改行。',
                    showType: 'show',
                    timeout: 3000,
                    showType: 'slide'
                });
            } else {
                /*将当前行变成可编辑模式*/
                var index = $("#imgDatagrid").edatagrid("getRowIndex", row);
                console.log(index);
                $("#imgDatagrid").edatagrid("editRow", index);
            }

        }
    }, '-', {
        iconCls: 'icon-save',
        text: "保存",
        handler: function () {
            // 保存编辑并发送到服务器
            $("#imgDatagrid").edatagrid("saveRow");
            //alert('保存');
        }
    }]
    // 定义toolbar 工具栏===========END

    // 数据展示
    $(function () {
        $('#imgDatagrid').edatagrid({
            url: '/cmfz/turnImg/getTurnImg',
            updateUrl: '/cmfz/turnImg/updateImg.do',
            columns: [[
                {field: 'img_title', title: '主题', width: 100},
                {field: 'img_desc', title: '描述', width: 100},
                {
                    field: 'img_status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'imgPath', title: '路径', width: 100},
                {field: 'img_date', title: '时间', width: 100}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 15, 40, 300],
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                //console.log(rowData);
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:100px;"></td>' +
                    '<td style="border:0">' +
                    '<p style="text-indent: 2em">date:&nbsp; ' + rowData.img_date + '</p>' +
                    '<p style="text-indent: 2em">description:&nbsp; ' + rowData.img_desc + '</p>' +
                    '<p style="text-indent: 2em">path:&nbsp; ' + rowData.imgPath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
    });
    // 数据展示==========END
</script>
<%--数据展示--%>
<table id="imgDatagrid"></table>
<%--数据展示======END--%>

<%--轮番图添加--%>
<div id="addTurnImg" class="easyui-dialog" data-options="closed:true">
    <form id="addTurnImg_form" style="margin:30px" enctype="multipart/form-data" method="post">
        <table width="500px" border="1px solid black">
            <tr>
                <td rowspan="3" width="200px" height="120px"><img src="" width="200px" id="turnImage"/></td>
                <td>主题：<input type="text" name="img_title"/></td>
            </tr>
            <tr>
                <td>状态：<input type="text" name="img_status" value="展示"/></td>
            </tr>
            <tr>
                <td>轮番图上传：<input type="file" name="uploadFile"/></td>
            </tr>
            <tr>
                <td colspan="2">描述：<textarea rows="3" cols="60" name="img_desc"></textarea></td>
            </tr>
        </table>
        <br><br>
        <div width="500px" align="center"><input type="button" value="确定" onclick="add_submit()"/><input type="button"
                                                                                                         value="取消"
                                                                                                         onclick="exit()"/>
        </div>
    </form>
</div>
<%--轮番图添加=======END--%>
