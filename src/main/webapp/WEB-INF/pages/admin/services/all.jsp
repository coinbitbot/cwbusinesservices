<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Services</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Services</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">
        <a href="/admin/services/create" class="btn btn-theme-ok">Add new service</a>
    </div>
    <div class="clearfix"></div>
    <div class="col-sm-12">
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Active</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.name}}</td>
                    <td>
                        <img ng-src="/api/file/{{entity.id}}?type=SERVICE">
                    </td>
                    <td>
                        <input type="checkbox" ng-model="entity.active" class="form-control checkbox" disabled>
                    </td>
                    <td>
                        <a href="/admin/services/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
                        <div class="up-down">
                            <button class="btn btn-theme-dark btn-small" ng-click="up(entity);" ng-show="!min(entity)"><i class="fa fa-chevron-up" aria-hidden="true"></i>&nbsp;Up</button>
                            <button class="btn btn-theme-dark btn-small" ng-click="down(entity);" ng-show="!max(entity)"><i class="fa fa-chevron-down" aria-hidden="true"></i>&nbsp;Down</button>
                        </div>
                        <button class="btn btn-theme-dark btn-small" ng-click="delete(entity)">Delete</button>
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
<script src="/resources/js/admin/services/all.js"></script>
</body>
</html>
