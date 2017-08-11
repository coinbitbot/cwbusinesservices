<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Emails</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Emails</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">

    </div>
    <div class="clearfix"></div>
    <div class="col-sm-3">
        <div class="block-content bg-pattern">
            <div class="page-subtitle">Emails filter</div>
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
                    <th>Subject</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.subject}}</td>
                    <td>
                        <a href="/admin/emails/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
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
<script src="/resources/js/admin/emails/all.js"></script>
</body>
</html>
