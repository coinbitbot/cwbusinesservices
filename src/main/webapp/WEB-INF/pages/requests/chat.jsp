<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Request chat #${request.id}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>
    <div class="col-md-10 col-md-offset-1">
        <h1>Request chat</h1>
        <div class="col-sm-6">
            <p><strong>Company:</strong> ${request.company_name}</p>
            <p><strong>Interests:</strong>
            <c:choose>
                <c:when test="${request.interests_name ne null}">
                    <c:forEach items="${request.interests_name}" var="name" varStatus="i">
                        ${name}<c:if test="${!i.last}">,&nbsp;</c:if>
                    </c:forEach>
                    <c:if test="${request.interest_alter ne null}">
                        ,&nbsp;${request.interest_alter}
                    </c:if>
                </c:when>
                <c:otherwise>
                    ${request.interest_alter}
                </c:otherwise>
            </c:choose>
            </p>
        </div>
        <div class="col-sm-6">
            <p><strong>Industry:</strong> ${request.industry_name}</p>
            <c:if test="${request.has_file}">
                <p>
                    <strong>Business plan:</strong>&nbsp;<a href="/api/file/${request.id}?type=REQUEST" target="_blank">Download</a>
                </p>
            </c:if>
            <p><strong>Status:</strong> <spring:message code="request.status.${request.status}" /></p>
        </div>
        <div class="clearfix"></div>
        <div class="hr-separator"></div>
        <div>
            <form id="add_comment_form">
                <div class="form-group">
                    <label>Your comment</label>
                    <textarea placeholder="Your comment here.." class="form-control" id="comment_text"></textarea>
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
            <div id="comments">

            </div>
            <div class="text-center"><button class="btn btn-theme-ok" id="more_comments">More comments</button></div>
        </div>
    </div>
    <div class="clearfix"></div>
    <jsp:include page="../common/footer.jsp" />
    <script src="/resources/js/utils/parse_url.js"></script>
    <script src="/resources/js/utils/date.format.min.js"></script>
    <script src="/resources/js/requests/chat.js?id=${request.id}&current_user_id=${current_user.id}" id="loader"></script>
    </body>
    </html>
</compress:html>