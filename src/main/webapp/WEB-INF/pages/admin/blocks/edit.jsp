<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit block #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="edit_forms">
        <form ng-submit="save();">
            <div>
                code: {{entity.code.toLowerCase()}}
            </div>
            <div class="form-group">
                <label>title</label>
                <input type="text" ng-model="entity.title" class="form-control">
            </div>
            <button class="btn btn-success">Save</button>
        </form>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blocks/edit.js?id=${id}" id="loader"></script>
</body>
</html>
