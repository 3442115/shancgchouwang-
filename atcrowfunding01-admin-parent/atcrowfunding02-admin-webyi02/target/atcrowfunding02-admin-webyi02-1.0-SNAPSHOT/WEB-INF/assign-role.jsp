<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/21
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<script type="text/javascript">
    $(function () {

        $("#toRightBtn").click(function () {
            // 把左边的第一个未分配追加到右边的第二个
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        })
        $("#toleftBtn").click(function () {
            // 把左边的第一个未分配追加到右边的第二个
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        })
        $("#submitBtn").click(function () {
            $("select:eq(1)>option").prop("selected","selected");
        })

    })
</script>
<body>


<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-siderbar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/do/login/page.html">首页</a></li>
                <li><a href="admin/get/page.html">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
                       <input type="hidden" name="adminId" value="${param.adminId}" />
<%--                        回到原来的界面     --%>
                       <input type="hidden" name="pageNum" value="${param.pageNum}" />
                       <input type="hidden" name="keyword" value="${param.keyword}" />

                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                              <c:forEach items="${requestScope.unAssignList}" var="role">
                                  <option value="${role.id}">${role.name}</option>
                              </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li id="toleftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                style="margin-top:20px;"></li>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList"  class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" id="submitBtn" style="width: 150px "  class="btn btn-sm btn-success btn-block">保存</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
