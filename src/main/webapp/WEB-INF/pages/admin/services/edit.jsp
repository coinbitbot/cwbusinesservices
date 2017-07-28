<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit service #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="edit_forms">
        <form ng-submit="save();">
            <div class="form-group">
                <label>name</label>
                <input type="text" ng-model="entity.name" class="form-control">
            </div>
            <div class="form-group">
                <label>description</label>
                <textarea ng-model="entity.description" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Active</label>
                <input type="checkbox" ng-model="entity.active" class="form-control">
            </div>
            <button class="btn btn-success">Save</button>
        </form>
        <div ng-show="entity.id">
            <hr>
            <img ng-src="{{service_icon}}" style="width: 300px;">
            <form ng-submit="uploadFile();">
                <input type="file" id="image" accept="image/*">
                <button class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/services/edit.js?id=${id}" id="loader"></script>
</body>
</html>
