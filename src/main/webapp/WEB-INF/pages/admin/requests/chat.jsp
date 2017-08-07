<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html ng-app="chat">
<head>
    <title>Request chat #${id}</title>
</head>
<body>
<div class="container-fluid" ng-controller="chat">
    <jsp:include page="../header.jsp" />
    <div class="main_row">
        <p>
            <strong>user</strong>: {{entity.user_full}}
        </p>
        <p>
            <strong>company:</strong> {{entity.company_name}}
        </p>
        <p>
            <strong>interests:</strong>
            <span ng-if="entity.interests_name" ng-repeat="interest in entity.interests_name">
                {{interest}},&nbsp;
            </span>
            <span ng-if="entity.interest_alter">
                {{entity.interest_alter}}
            </span>
        </p>
        <p>
            <strong>industry:</strong> {{entity.industry_name}}
        </p>
        <p ng-if="entity.has_file">
            <strong>business plan:</strong> <a href="/api/file/{{entity.id}}?type=REQUEST" target="_blank">download</a>
        </p>
        <p>
            <strong>status:</strong> {{entity.status.toLowerCase()}}
        </p>
        <security:authorize access="hasPermission(1,'STATUS_CHANGE_REQUEST')">
            <p>
                <div class="form-group">
                    <label>new status</label>
                    <select ng-model="entity.new_status" class="form-control">
                        <option value="NEW">new</option>
                        <option value="IN_WORK">in work</option>
                        <option value="FINISHED">finished</option>
                    </select>
                </div>
                <button class="btn btn-info" ng-click="setStatus()">set status</button>
            </p>
        </security:authorize>
        <hr />
        <div>
            <form ng-submit="addComment()">
                <div class="form-group">
                    <textarea placeholder="Your comment here.." class="form-control" ng-model="new_comment_text"></textarea>
                </div>
                <div class="form-group">
                    <label>add file to you comment if need</label>
                    <input type="file" name="file" id="comment_file">
                </div>
                <button class="btn btn-success">add comment</button>
            </form>
            <hr />
            <div ng-repeat="comment in comments.slice().reverse()" style="border-bottom: 1px solid #ccc; margin-bottom: 5px;">
                <p>
                    <strong>user:</strong> {{comment.user_full}}
                </p>
                <p>{{comment.text}}</p>
                <p ng-show="comment.has_file">
                    <strong>attached file:</strong> <a href="/api/file/{{comment.id}}?type=REQUEST_COMMENT" target="_blank">download</a>
                </p>
            </div>
            <hr />
            <button class="btn btn-success" ng-click="moreComments()">more comments</button>
        </div>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/requests/chat.js?id=${id}&current_user_id=${current_user_id}" id="loader"></script>
</body>
</html>
