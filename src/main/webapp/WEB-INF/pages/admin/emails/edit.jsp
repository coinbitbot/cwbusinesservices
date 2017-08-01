<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit email #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="edit_forms">
        <form ng-submit="save();">
            <p>
                code: {{entity.code.toLowerCase()}}
            </p>
            <div class="form-group">
                <label>subject</label>
                <input type="text" ng-model="entity.subject" class="form-control">
            </div>
            <div class="form-group">
                <label>text</label>
                <textarea id="text" ng-model="entity.text" class="form-control"></textarea>
            </div>
            <button class="btn btn-success">Save</button>
        </form>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/emails/edit.js?id=${id}" id="loader"></script>
</body>
</html>
