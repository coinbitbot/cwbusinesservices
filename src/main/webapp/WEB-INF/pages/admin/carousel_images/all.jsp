<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Carousel images</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All carousel images</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}
    </div>
    <div class="col-sm-6 text-right add-new">
        <a href="/admin/carousel_images/create" class="btn btn-theme-ok">Add new carousel image</a>
    </div>
    <div class="clearfix"></div>
    <div class="col-sm-12">
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>
                        <img ng-src="{{entity.icon}}" class="form-img">
                    </td>
                    <td>
                        {{entity.description}}
                    </td>
                    <td>
                        <a href="/admin/carousel_images/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
                        <br />
                        <button class="btn btn-danger" ng-click="deleteCarouselImage(entity)">Delete</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="clearfix"></div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/carousel_images/all.js"></script>
</body>
</html>
