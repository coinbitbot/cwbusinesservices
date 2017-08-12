<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit testimonial #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-md-10 col-md-offset-1">
            <div class="top-nav">
                <a href="/admin/testimonials/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all testimonials</a>
            </div>
            <div class="page-title">Create / Edit testimonial</div>
            <form ng-submit="save();">
                <div class="form-group">
                    <label>Author Name</label>
                    <input type="text" ng-model="entity.name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Text</label>
                    <textarea id="info_page_text" ng-model="entity.text" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <input type="checkbox" ng-model="entity.active" class="form-control checkbox">
                    <label>Active</label>
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/testimonials/edit.js?id=${id}" id="loader"></script>
</body>
</html>
