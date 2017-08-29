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
    <div class="wrapper">
        <section class="request-chat">
            <div class="container">
                <div class="col-sm-10 col-sm-offset-1">
            <h1 class="col-xs-12">Request chat</h1>
            </div>
                    <div class="col-sm-10 col-sm-offset-1">
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
                                <strong>Business plan:</strong>&nbsp;<a href="/api/file/${request.id}?type=REQUEST" target="_blank" title="Download"><i class="fa fa-file-text" aria-hidden="true"></i></a>
                            </p>
                        </c:if>
                        <p><strong>Status:</strong> <spring:message code="request.status.${request.status}" /></p>
                    </div>
                    </div>
            </div>
            <div class="clearfix"></div>
        </section>
        <div class="container">
            <div class="col-sm-10 col-sm-offset-1">
            <form id="add_comment_form" class="chat-info">
                <div class="col-xs-12">
                    <h4 class="subheader">Your comment</h4>
                    <textarea placeholder="Your comment here.." class="col-xs-12" id="comment_text"></textarea>
                </div>
                <div>
                    <div class="col-xs-12 col-sm-8">
                        <input type="text" id="filename" class="filename col-xs-12" placeholder="Add file to you comment if need" disabled />
                    </div>
                    <div class="col-xs-12 text-center col-sm-4">
                        <input type="file" name="file" id="comment_file" class="inputfile">
                        <label for="comment_file" class="col-xs-10 col-xs-offset-1 col-sm-12 col-sm-offset-0 btn-theme btn-1 btn-choose-file">Choose file</label>
                    </div>
                </div>
                <div class="text-center"><button class="btn-theme btn-2">Add comment</button></div>
            </form>
            <div class="clearfix"></div>
            <section>
                <div id="comments">

                </div>
                <div class="text-center"><button class="btn-theme btn-1" id="more_comments">More comments</button></div><br/>
                <a href="/profile/requests"><span class="post-nav nav-left"><<</span> Back to All requests</a>
            </section>
            </div>
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