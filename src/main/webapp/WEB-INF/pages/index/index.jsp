<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Template</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div>
        <c:if test="${services ne null}">
            <h2>services</h2>
            <c:forEach var="service" items="${services}">
                <div>
                    <img src="/api/image/${service.id}?type=SERVICE" style="width: 300px;">
                    ${service.name}
                </div>
            </c:forEach>
        </c:if>
        <br />
        <c:if test="${companies ne null}">
            <h2>companies</h2>
            <c:forEach var="company" items="${companies}">
                <div>
                    <img src="/api/image/${company.id}?type=COMPANY" style="width: 300px;">
                    <a href="/companies/${company.id}">${company.name}</a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>