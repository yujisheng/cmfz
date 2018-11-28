<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <%--<script type="text/javascript" src="script/jquery.js"></script>--%>
    <%--<script type="text/javascript" src="script/common.js"></script>--%>

    <script type="text/javascript">

        $(function () {
            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                //alert("kaptcha");
                $(this).prop('src', "${pageContext.request.contextPath}/getKaptcha.do?=" + new Date().getTime());
            });

            //  form 表单提交
            $("#loginForm").bind("submit", function () {
                //alert("form");
                /* var name = $("#adminName").val();
                 var pwd = $("#adminPassword").val();
                 var kaptcha = $("#enCode").val();
                 //alert(name+"======"+pwd+"======"+kaptcha);
                 $.ajax({
                     url: "/cmfz/admin/adminLogin.do",
                     type: "post",
                     data: {"name": name, "pwd": pwd, "kaptcha": kaptcha},
                     dataType: "json",
                     async: false,
                     success: function (data) {
                         console.log(data);
                         if (data) {
                             location = "/main/main.jsp";
                         } else {
                             // 页面右下角弹出提示框
                             alert("登录失败！");
                             location = "/login.jsp";
                         }
                     }
                 });
                 return false;*/


                $("#loginForm").form("submit", {
                    // 当submit的返回值为false时，阻止表单提交
                    url: "/cmfz/admin/adminLogin.do",
                    // 回调函数，后台响应结果data是json串
                    success: function (data) {
                        console.log(data);
                        // 将json串转换成js对象
                        data = JSON.parse(data);
                        if (data) {
                            location = "${pageContext.request.contextPath}/main/main.jsp";
                        } else {
                            // 页面右下角弹出提示框
                            $.messager.show({
                                title: '提示信息',
                                msg: '登录失败!',   // 提示内容
                                timeout: 3000,      //弹框停留页面的时间
                                showType: 'slide'   //弹框弹出方式
                            });
                            // 刷新验证码
                            $("#captchaImage").prop('src', "${pageContext.request.contextPath}/getKaptcha.do?=" + new Date().getTime());
                        }
                    }
                });
                return false;
            });
        });

    </script>
</head>
<body>

<div class="login">
    <%--<form id="loginForm" action="../back/index.html" method="post">--%>
    <form id="loginForm" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input type="text" name="name" class="text" value="admin"
                           maxlength="20" id="adminName"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input type="password" name="pwd" class="text" value="123456" maxlength="20"
                           autocomplete="off" id="adminPassword"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="kaptcha" class="text captcha" maxlength="4"
                           autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/getKaptcha.do"
                         title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit"
                                                                                                        class="loginButton"
                                                                                                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>