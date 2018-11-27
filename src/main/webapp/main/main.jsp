<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            $.ajax({
                url: "/cmfz/menu/getMenus.do",
                type: "post",
                //data:{"auctionId":auctionId},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    for (var i = 0; i < data.length; i++) {
                        $('#aa').accordion('add', {
                            title: "<img src='../themes/icons/" + data[i].menu_iconCls + "'/>" + data[i].menu_title,
                            content: function () {
                                var menus = data[i].menus;
                                console.log(menus);
                                var con = "";
                                for (var ii = 0; ii < menus.length; ii++) {
                                    con = con + "<p style='text-indent: 2em'><a href='#' style='text-decoration: none'><img src='../themes/icons/" + menus[ii].menu_iconCls + "'/>" + menus[ii].menu_title + "</a></p>";
                                }
                                return con;
                            },
                            selected: false,
                            //iconCls:"icon-cut",
                            border: true
                        });
                    }
                }
            });
        });

        // 添加一个页签，并展示对应分类下的图书信息
        function toAddTabsByBookCategory(node) {
            //console.log(node.text);
            //alert(node.id);
            // 判断当前点击标签是否存在
            var isExists = $("#tt").tabs("exists", node.text);
            if (isExists) {
                // 存在
                $("#tt").tabs("select", node.text);
            } else {
                // 不存在
                $('#tt').tabs('add', {
                    title: node.text,
                    closable: true,
                    //iconCls:"icon-save",
                    content: "<iframe src='book.jsp?categoryId=" + node.id + "' width='100%' height='100%'></iframe>"
                });
            }
        }

        // 添加一个页签，并展示对应分类下的图书信息====END
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>

    </div>
</div>
</body>
</html>