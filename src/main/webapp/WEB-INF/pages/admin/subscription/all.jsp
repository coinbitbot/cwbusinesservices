<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Subscriptions</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all">
    <div class="col-md-12">
        <div class="col-md-3">
            <button class="btn btn-success" ng-click="addSubscription()">add email</button>
        </div>
        <div class="col-md-3">
            total count: {{total_count}}, on page: {{number_on_page}}
        </div>
    </div>
    <div class="col-md-12">
        <div class="col-md-3">
            <form ng-submit="moveToPage()">
                <div class="form-group">
                    <label>page</label>
                    <select id="pages" class="form-control">
                        <option value="0">1</option>
                    </select>
                </div>
                <button class="btn btn-success">Move to page</button>
            </form>
            <hr />
            <form ng-submit="filterForm()">
                <div class="form-group">
                    <label>query</label>
                    <input ng-model="filters.query" class="form-control">
                </div>
                <button class="btn btn-success">Filter</button>
            </form>
        </div>
        <div class="col-md-9">
            <table class="table">
                <thead>
                <tr>
                    <th>email</th>
                    <th>actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.email}}</td>
                    <td>
                        <button class="btn btn-danger" ng-click="deleteSubscription(entity)">delete</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/subscription/all.js"></script>
</body>
</html>
