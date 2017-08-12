<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Subscriptions</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Subscriptions</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">
        <button class="btn btn-theme-ok" ng-click="addSubscription()">Add email</button>
    </div>
    <div class="clearfix"></div>
    <div class="col-sm-3">
        <div class="block-content bg-pattern">
            <div class="page-subtitle">Subscription filter</div>
            <form ng-submit="filterForm()">
                <div class="form-group">
                    <label>Query</label>
                    <input ng-model="filters.query" class="form-control">
                </div>
                <div class="text-center"><button class="btn btn-theme-ok">Filter</button></div>
            </form>
        </div>
    </div>
    <div class="col-sm-9">
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.email}}</td>
                    <td>
                        <button class="btn btn-danger" ng-click="deleteSubscription(entity)">Delete</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <form ng-submit="moveToPage()">
            <div class="form-group text-right page-nav">
                <label>Page</label>
                <select id="pages" class="form-control">
                    <option value="0">1</option>
                </select>
                <button class="btn btn-theme-ok">Move to page</button>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/subscription/all.js"></script>
</body>
</html>
