<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html ng-app="all">
<head>
    <title>Requests</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all">
    <div class="col-md-12">
        <div class="col-md-3">

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
                <select ng-model="filters.status" class="form-control">
                    <option value="">all</option>
                    <option value="NEW">new</option>
                    <option value="IN_WORK">in work</option>
                    <option value="FINISHED">finished</option>
                </select>
                <button class="btn btn-success">Filter</button>
            </form>
        </div>
        <div class="col-md-9">
            <table class="table">
                <thead>
                <tr>
                    <th>user</th>
                    <th>request info</th>
                    <th>status</th>
                    <th>actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.user_full}}</td>
                    <td>
                        <strong>company:</strong> {{entity.company_name}}
                        <Br />
                        <strong>interests:</strong>
                        <span ng-if="entity.interests_name" ng-repeat="interest in entity.interests_name">
                        {{interest}},&nbsp;
                    </span>
                        <span ng-if="entity.interest_alter">
                        {{entity.interest_alter}}
                    </span>
                        <Br />
                        <strong>industry:</strong> {{entity.industry_name}}
                    </td>
                    <td>
                        <strong>{{entity.status.toLowerCase()}}</strong>
                        <br />
                        <security:authorize access="hasPermission(1,'STATUS_CHANGE_REQUEST')">
                            <div class="form-group">
                                <label>new status</label>
                                <select ng-model="entity.new_status" class="form-control">
                                    <option value="NEW">new</option>
                                    <option value="IN_WORK">in work</option>
                                    <option value="FINISHED">finished</option>
                                </select>
                            </div>
                            <button class="btn btn-info" ng-click="setStatus(entity)">set status</button>
                        </security:authorize>
                    </td>
                    <td>
                        <a href="/admin/requests/{{entity.id}}/chat" target="_blank" class="btn btn-success">Chat</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/requests/all.js"></script>
</body>
</html>
