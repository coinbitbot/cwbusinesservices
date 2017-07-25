<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit info page #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="edit_forms">
        <form ng-submit="save();">
            <div class="form-group">
                <label>URL</label>
                <input type="text" ng-model="entity.url" class="form-control">
            </div>
            <div class="form-group">
                <label>Header</label>
                <input type="text" ng-model="entity.header" class="form-control">
            </div>
            <div class="form-group">
                <label>Sub header</label>
                <input type="text" ng-model="entity.sub_header" class="form-control">
            </div>
            <div class="form-group">
                <label>Text</label>
                <textarea id="info_page_text" ng-model="entity.text" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Active</label>
                <input type="checkbox" ng-model="entity.active" class="form-control">
            </div>
            <div class="form-group">
                <label>Meta title</label>
                <input type="text" ng-model="entity.meta_title" class="form-control">
            </div>
            <div class="form-group">
                <label>Meta description</label>
                <textarea ng-model="entity.meta_description" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Meta keywords</label>
                <textarea ng-model="entity.meta_keywords" class="form-control"></textarea>
            </div>
            <button class="btn btn-success">Save</button>
        </form>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/info_pages/edit.js?id=${id}" id="loader"></script>
</body>
</html>
