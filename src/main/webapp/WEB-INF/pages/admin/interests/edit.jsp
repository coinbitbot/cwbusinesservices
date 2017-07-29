<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit industry #${id}</title>
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
            <button class="btn btn-success">Save</button>
        </form>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/interests/edit.js?id=${id}" id="loader"></script>
</body>
</html>
