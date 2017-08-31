<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit employee #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container col-md-10 col-md-offset-1" ng-controller="edit_forms">
        <div class="top-nav">
            <a href="/admin/employee/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all employees</a>
        </div>
        <div class="page-title">Create / Edit employee</div>
        <form ng-submit="save();">
            <div class="form-group">
                <label>Name</label>
                <input type="text" ng-model="entity.name" class="form-control">
            </div>
            <div class="form-group">
                <label>Position</label>
                <input type="text" ng-model="entity.position" class="form-control">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" ng-model="entity.email" class="form-control">
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" ng-model="entity.phone" class="form-control">
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea id="description_text" ng-model="entity.description" class="form-control"></textarea>
            </div>
            <div class="text-center"><button class="btn btn-theme-ok">Save</button></div>
        </form>
    </div>
    <div class="clearfix"></div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/employee/edit.js?id=${id}" id="loader"></script>
</body>
</html>
