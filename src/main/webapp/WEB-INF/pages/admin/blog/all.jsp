<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Blogs</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Blog</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">
        <a href="/admin/blog/create" class="btn btn-theme-ok">Add new blog</a>
    </div>
    <div class="clearfix"></div>
    <div>
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.name}}</td>
                    <td>{{entity.code}}</td>
                    <td>
                        <a href="/admin/blog/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
                        <div class="up-down">
                        <button class="btn btn-theme-dark btn-small" ng-click="up(entity);" ng-show="!min(entity)"><i class="fa fa-chevron-up" aria-hidden="true"></i>&nbsp;Up</button>
                        <button class="btn btn-theme-dark btn-small" ng-click="down(entity);" ng-show="!max(entity)"><i class="fa fa-chevron-down" aria-hidden="true"></i>&nbsp;Down</button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blog/all.js"></script>
</body>
</html>
