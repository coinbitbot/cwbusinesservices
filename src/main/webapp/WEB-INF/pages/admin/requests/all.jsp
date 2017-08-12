<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html ng-app="all">
<head>
    <title>Requests</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Requests</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">

    </div>
    <div class="clearfix"></div>
    <div class="col-sm-3">
        <div class="block-content bg-pattern">
            <div class="page-subtitle">Requests filter</div>
            <form ng-submit="filterForm()">
                <div class="form-group">
                    <label>Query</label>
                    <input ng-model="filters.query" class="form-control">
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <select ng-model="filters.status" class="form-control">
                        <option value="">All</option>
                        <option value="NEW">New</option>
                        <option value="IN_WORK">In work</option>
                        <option value="FINISHED">Finished</option>
                    </select>
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
                    <th>User</th>
                    <th>Request info</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.user_full}}</td>
                    <td>
                        <em>Company:</em> {{entity.company_name}}
                        <Br />
                        <em>Interests:</em>
                        <span ng-if="entity.interests_name" ng-repeat="interest in entity.interests_name">
                            {{interest}},&nbsp;
                        </span>
                        <span ng-if="entity.interest_alter">
                            {{entity.interest_alter}}
                        </span>
                        <Br />
                        <em>Industry:</em> {{entity.industry_name}}
                    </td>
                    <td>
                        <strong>{{entity.status.toLowerCase()}}</strong>
                        <br />
                        <security:authorize access="hasPermission(1,'STATUS_CHANGE_REQUEST')">
                            <div class="form-group">
                                <em>New status</em>
                                <select ng-model="entity.new_status" class="form-control">
                                    <option value="NEW">New</option>
                                    <option value="IN_WORK">In work</option>
                                    <option value="FINISHED">Finished</option>
                                </select>
                            </div>
                            <button class="btn btn-theme-dark" ng-click="setStatus(entity)">Set status</button>
                        </security:authorize>
                    </td>
                    <td>
                        <a href="/admin/requests/{{entity.id}}/chat" class="btn btn-theme-ok">Chat</a>
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
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/requests/all.js"></script>
</body>
</html>
