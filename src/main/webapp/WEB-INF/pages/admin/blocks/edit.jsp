<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit block #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
            <div class="top-nav">
                <a href="/admin/blocks/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all blocks</a>
            </div>
            <div class="page-title">Create / Edit block</div>
            <form ng-submit="save();">
                <div>
                    <label>Code:</label> {{entity.code.toLowerCase()}}
                </div>
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" ng-model="entity.title" class="form-control">
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blocks/edit.js?id=${id}" id="loader"></script>
</body>
</html>
