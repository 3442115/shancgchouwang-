<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/21
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">

    $(function () {
        initPagination();
    });

    //生成分页导航条函数
    function initPagination() {
        //获取总记录数
        var totalRecord = ${requestScope.pageInfo.total};
        var properties = {
            num_edge_entries: 3, //边缘页数
            num_display_entries: 6, //主体页数
            callback: pageselectCallback,
            items_per_page:${requestScope.pageInfo.pageSize}, //每页显示1项
            current_page: ${requestScope.pageInfo.pageNum - 1},//Pagination内部使用pageIndex来管理页面
            prev_text:"Prev",
            next_text:"Next"
        }
        // 生成页码导航条
        $(".pagination").pagination(totalRecord,properties);

    }
    //pageIndex 0-
    function pageselectCallback(pageIndex) {
        //根据pageIndex计算pageNum
        var pageNum = pageIndex+1;
        //跳转页面
        window.location.href="admin/get/page.html?pageNum="+pageNum+"&keyword=${param.keyword}";
        //由于每个按钮都是超链接，所以这里取消超链接的默认行为
        return false

    }

</script>
<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-siderbar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">

                <form class="form-inline" action="admin/get/page.html" method="post" role="form" style="float:left;">
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <div class="input-group-addon">查询条件</div>
                            <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                </form>

                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>

                    <a style="float: right" href="admin/to/add/page.html" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>新增</a>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td align="center" colspan="6">查询数据为空！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                               <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="Mystatus" >
                                   <tr>
                                       <td>${Mystatus.count}</td>
                                       <td><input type="checkbox"></td>
                                       <td>${admin.getLoginAcct()}</td>
                                       <td>${admin.getUserName()}</td>
                                       <td>${admin.getEmail()}</td>
                                       <td>

                                           <a href="assign/to/assign/role/page.html?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></a>
                                           <a href="admin/to/edit/page.html?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-pencil"></i> </a>
                                           <a href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i> </a>
                                       </td>
                                   </tr>

                               </c:forEach>
                            </c:if>

                            </tbody

                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
