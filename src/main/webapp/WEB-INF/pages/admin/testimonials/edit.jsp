<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit testimonial #${id}</title>
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
                <label>text</label>
                <textarea id="info_page_text" ng-model="entity.text" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Active</label>
                <input type="checkbox" ng-model="entity.active" class="form-control">
            </div>
            <button class="btn btn-success">Save</button>
        </form>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/testimonials/edit.js?id=${id}" id="loader"></script>
</body>
</html>
