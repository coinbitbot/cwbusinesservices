<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit email #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container col-md-10 col-md-offset-1" ng-controller="edit_forms">
        <div class="top-nav">
            <a href="/admin/emails/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all Emails</a>
        </div>
        <div class="page-title">Create / Edit email</div>
        <form ng-submit="save();">
            <div>
                <label>Code:</label> {{entity.code.toLowerCase()}}
            </div>

            <div class="form-group">
                <label>Subject</label>
                <input type="text" ng-model="entity.subject" class="form-control">
            </div>
            <div class="form-group">
                <label>Text</label>
                <textarea id="text" ng-model="entity.text" class="form-control"></textarea>
            </div>
            <div class="text-center">
                <button class="btn btn-theme-ok">Save</button>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/emails/edit.js?id=${id}" id="loader"></script>
</body>
</html>
