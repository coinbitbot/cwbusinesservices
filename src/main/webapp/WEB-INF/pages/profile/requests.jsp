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
    <jsp:include page="user_menu.jsp" />
    <div>
        <c:choose>
            <c:when test="${requests ne null}">
                <table>
                    <thead>
                    <tr>
                        <th>Request info</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="request" items="${requests}">
                        <tr>
                            <td>
                                Company: ${request.company_name}
                                <br />
                                Industry: ${request.industry_name}
                                <br />
                                Interests:
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
                                <br />
                                Business plan: <a href="/api/file/${request.id}?type=REQUEST">download</a>
                            </td>
                            <td>
                                <spring:message code="request.status.${request.status}" />
                            </td>
                            <td>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                You still have no requests. You can create it <a href="#">here</a>.
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>