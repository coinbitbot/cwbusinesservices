<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Profile</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>
    <div class="container">
        <section>
            <h1 class="col-xs-12">Requests</h1>
            <div class="clearfix"></div>
            <div class="col-sm-9 col-sm-push-3">
                <div class="row requests">
                    <c:choose>
                        <c:when test="${requests ne null}">
                            <c:forEach var="request" items="${requests}">
                                <div class="col-sm-6 reguest-wrapper">
                                    <div class="request-item">
                                        <div>
                                            <h4 class="subheader">Request info</h4>
                                            <p><strong>Company:</strong> ${request.company_name}</p>
                                            <p><strong>Industry:</strong> ${request.industry_name}</p>
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
                                            <p><strong>Request status:</strong> <spring:message code="request.status.${request.status}" /></p>
                                        </div>
                                        <div class="text-center action-link">
                                            <a href="/requests/${request.id}/chat" title="Chat"><i class="fa fa-comments" aria-hidden="true"></i></a>
                                            <a href="/api/file/${request.id}?type=REQUEST" title="Download Business Plan"><i class="fa fa-file-text" aria-hidden="true"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="clearfix"></div>
                            <div class="text-center"><a href="/requests/create" class="btn-theme btn-1">Add request</a></div>
                        </c:when>
                        <c:otherwise>
                        <div class="text-center no-info">You still have no requests. You can create it <br/>
                            <a href="/requests/create">Create request</a>
                        </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-sm-3 col-sm-pull-9">
                <jsp:include page="user_menu.jsp" />
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <script>
        $(document).ready(function() {
            // Active menu
            $('#user_requests').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>