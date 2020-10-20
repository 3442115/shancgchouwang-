<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <%--引入jquery--%>
<%--    老师的出现问题 --%>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>


    <script type="text/javascript">
     //发送数组 方案一
        $(function() {
            var array = [5, 8, 12];
            var reques = JSON.stringify(array);
        $("#btn1").click(function () {
            $.ajax({
                "url": "send/array/one.html",
                "type": "post",
                "data": {
                   "array": [5,8,12]
                },
                "dataType": "text",
                "success": function (response) {
                 alert(response);
                },
                 "erroe": function (response) {
                  alert(response);
                 }

            })
        })

     //发送数组方案二
        $("#btn2").click(function () {
            $.ajax({
                "url": "send/array/two.html",
                "type": "post",
                "data": {
                    "array[0]": 5,
                    "array[1]": 8,
                    "array[2]": 12
                },
                "dataType": "text",
                "success": function (response) {
                    alert(response);
                },
                "erroe": function (response) {
                    alert(response);
                }

            })
        })


     //发送数组方案三
            $("#btn3").click(function () {
                $.ajax({
                    "url": "send/array/three.html",
                    "type": "post",
                    "data":reques,
                    "contentType":"application/json;charset=UTF-8",
                    "dataType": "text",
                    "success": function (response) {
                        alert(response);
                    },
                    "erroe": function (response) {
                        alert(response);
                    }

                })
            })

            var student = {
                "stuId": 5,
                "stuName": "tom",
                "address": {
                    "province": "广东",
                    "city": "深圳",
                    "street": "后瑞"
                },
                "subjectList": [
                    {
                        "subjectName": "JavaSE",
                        "subjectScore": 100
                    }, {
                        "subjectName": "SSM",
                        "subjectScore": 99
                    }
                ],
                "map": {
                    "k1": "v1",
                    "k2": "v2"
                }
            };
            // 将JSON对象转换为JSON字符串
            var requestBody = JSON.stringify(student);

            // 发送Ajax请求
           $("#btn4").click(function () {
               $.ajax({
                   "url": "test/send/object.json",
                   "type": "post",
                   "data": requestBody,
                   "contentType": "application/json;charset=UTF-8",
                   "dataType": "json",
                   "success": function (response) {
                       console.log(response);
                   },
                   "error": function (response) {
                       console.log(response);
                   }
               });
           })
        })
    </script>
</head>
<body>
<%--<a href="test/ssm.html">测试ssm</a>--%>
<a id="ssm" href="test/ssm.html">Ssm</a>
<button id="btn1">Send [5,8,12] One</button>
<br>
<br>
<button id="btn2">Send [5,8,12] two</button>
<br>
<br>
<button id="btn3">Send [5,8,12] three</button>
<br>
<br>
<button id="btn4">send Student</button>
</body>
</html>