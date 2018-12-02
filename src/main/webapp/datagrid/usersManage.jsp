<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-edit',
            text: "全部导出",
            handler: function () {
                alert('编辑按钮')
                /*$.ajax({
                   url:"/cmfz/exprotAndImport/exportExcel.do"
                });*/
                location = "${pageContext.request.contextPath}/exprotAndImport/exportExcel.do";
            }
        }, '-', {
            iconCls: 'icon-help',
            text: "全部导入",
            handler: function () {
                //alert('帮助按钮')
                //location="${pageContext.request.contextPath}/exprotAndImport/importExcel.do?";
                $("#importExcel_dd").dialog("open");

            }
        }, '-', {
            iconCls: 'icon-help',
            text: "自定义导出",
            handler: function () {
                alert('帮助按钮')
            }
        }]

        // 数据展示
        $('#users_dg').datagrid({
            url: '/cmfz/user/getAllUser.do',
            columns: [[
                {title: '用户名', field: 'username', width: 100},
                {title: '性别', field: 'sex', width: 100},
                {title: '法号', field: 'dharmaName', width: 100},
                {title: '电话号码', field: 'phoneNum', width: 100},
                {title: '密码', field: 'password', width: 100},
                {title: '省份', field: 'province', width: 100},
                {title: '城市', field: 'city', width: 100},
                {title: '个性签名', field: 'sign', width: 100},
                {title: '头像', field: 'headPic', width: 100},
                {
                    title: '用户状态', field: 'user_status', width: 100,
                    formatter: function (value, row, index) {
                        //alert(row.user_status);
                        //console.log(row);
                        if (row.user_status == 1) {
                            return '男';
                        } else {
                            return '女';
                        }
                    }
                },
                {title: '注册时间', field: 'user_date', width: 100, align: 'right'}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 30],
            toolbar: toolbar
        });
        // 数据展示=====END

        // 添加章节对话框
        $('#importExcel_dd').dialog({
            title: '添加章节',
            width: 400,
            height: 200,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    importExcel();
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#importExcel_dd").dialog("close")
                }
            }],
        });
        // 添加章节对话框==========END
    })

    function importExcel() {
        // 添加表单提交
        $("#importExcel_ff").form("submit", {
            // 通过form控件的submit方法提交数据
            url: "/cmfz/exprotAndImport/importExcel.do",
            success: function (data) {
                // 回调函数，后台响应结果data是json串
                console.log(data);
                data = JSON.parse(data);
                if (data) {
                    // 添加成功后关闭添加对话框并重新加载数据
                    $("#importExcel_dd").dialog("close");
                    $("#users_dg").treegrid("load");
                } else {
                    $.messager.alert("提示消息", "导入Excel失败！", "info");
                }
            }
        });
        // 添加表单提交========END
    }
</script>

<table id="users_dg"></table>

<div id="importExcel_dd">

    <form id="importExcel_ff" method="post" enctype="multipart/form-data">
        <input type="text" name="titleRows" value="1" id="titleRows">
        <input type="text" name="headerRows" value="1" id="headerRows">
        <div>
            请选择导入的Excel:<input type="file" name="uploadFile" style="width:300px">
        </div>
    </form>
</div>