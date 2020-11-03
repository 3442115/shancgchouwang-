<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="Chrome">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/login.css" charset="UTF-8">

    <script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                window.history.back()
            })
        })
    </script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 管理员登录</h2>
     <br>
    <h3>尚筹网消息</h3>
    <h3>${requestScope.exception.message}</h3>
    <h3>${message }</h3>
    <button style="width: 150px;margin: 50px auto 0px auto" class="btn btn-group-lg btn-success btn-block">点我返回</button>
</div>

</body>
</html>