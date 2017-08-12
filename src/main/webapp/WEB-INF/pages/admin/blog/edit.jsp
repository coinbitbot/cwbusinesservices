<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit blog #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
            <div class="top-nav">
                <a href="/admin/blog/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all blog</a>
            </div>
            <div class="page-title">Create / Edit blog</div>
            <form ng-submit="save();">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" ng-model="entity.name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Code</label>
                    <input type="text" ng-model="entity.code" class="form-control">
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blog/edit.js?id=${id}" id="loader"></script>
</body>
</html>
