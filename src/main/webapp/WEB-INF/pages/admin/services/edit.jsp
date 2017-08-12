<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit service #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-md-10 col-md-offset-1">
            <div class="top-nav">
                <a href="/admin/services/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all services</a>
            </div>
            <div class="page-title">Create / Edit service</div>
            <form ng-submit="save();">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" ng-model="entity.name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea ng-model="entity.description" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <input type="checkbox" ng-model="entity.active" class="form-control checkbox">
                    <label>Active</label>
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
            <div ng-show="entity.id">
                <div class="hr-separator"></div>
                <img ng-src="{{service_icon}}" class="form-img">
                <form ng-submit="uploadFile();">
                    <div class="block-fileinput text-center">
                        <input type="file" id="image" accept="image/*" class="inputfile">
                        <input type="text" id="filename" class="filename" disabled />
                        <label for="image" class="btn-theme-dark btn">Choose file</label>
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                        <button class="btn btn-theme-ok">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/services/edit.js?id=${id}" id="loader"></script>
</body>
</html>
