<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html ng-app="chat">
<head>
    <title>Request chat #${id}</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container" ng-controller="chat">
    <div class="col-md-10 col-md-offset-1">
        <div class="top-nav">
            <a href="/admin/requests/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all requests</a>
        </div>
        <div class="page-title">Request chat</div>
        <div class="col-sm-6">
            <p><strong>User:</strong> {{entity.user_full}}</p>
            <p><strong>Company:</strong> {{entity.company_name}}</p>
            <p><strong>Interests:</strong>
            <span ng-if="entity.interests_name" ng-repeat="interest in entity.interests_name">
                {{interest}},&nbsp;
            </span>
            <span ng-if="entity.interest_alter">
                {{entity.interest_alter}}
            </span></p>
        </div>
        <div class="col-sm-6">
            <p><strong>Industry:</strong> {{entity.industry_name}}</p>
            <p ng-if="entity.has_file">
                <strong>Business plan:</strong> <a href="/api/file/{{entity.id}}?type=REQUEST" target="_blank">Download</a>
            </p>
            <p><strong>Status:</strong> {{entity.status.toLowerCase()}}</p>
            <security:authorize access="hasPermission(1,'STATUS_CHANGE_REQUEST')">
                <div class="form-group">
                    <div class="form-inline">
                        <strong>New status&nbsp</strong>
                        <select ng-model="entity.new_status" class="form-control">
                            <option value="NEW">new</option>
                            <option value="IN_WORK">in progress</option>
                            <option value="FINISHED">finished</option>
                        </select>
                    </div><br/>
                    <div class="set-status"><button class="btn btn-theme-ok" ng-click="setStatus()">Set status</button></div>
                </div>
            </security:authorize>
        </div>
        <div class="clearfix"></div>
        <div class="hr-separator"></div>
        <div>
            <form ng-submit="addComment()">
                <div class="form-group">
                    <label>Your comment</label>
                    <textarea placeholder="Your comment here.." class="form-control" ng-model="new_comment_text"></textarea>
                </div>
                <div class="form-group">
                    <div class="block-fileinput text-center">
                        <label>Add file to you comment if need</label>
                        <input type="file" name="file" id="comment_file" class="inputfile">
                        <input type="text" id="filename" class="filename" disabled />
                        <label for="comment_file" class="btn-theme-dark btn">Choose file</label>
                    </div>
                </div>
                <div class="text-center"><button class="btn btn-theme-ok">Add comment</button></div>
            </form>
            <div class="hr-separator"></div>
            <div ng-repeat="comment in comments.slice().reverse()" class="item-comment">
                <p>
                    <strong>User:</strong> {{comment.user_full}}
                </p>
                <p>{{comment.text}}</p>
                <p ng-show="comment.has_file">
                    <strong>Attached file:</strong> <a href="/api/file/{{comment.id}}?type=REQUEST_COMMENT" target="_blank">Download</a>
                </p>
            </div>
            <div class="text-center"><button class="btn btn-theme-ok" ng-click="moreComments()">More comments</button></div>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/requests/chat.js?id=${id}&current_user_id=${current_user_id}" id="loader"></script>
</body>
</html>
