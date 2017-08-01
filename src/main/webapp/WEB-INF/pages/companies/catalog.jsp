<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>All companies</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <c:if test="${companies ne null}">
        <c:forEach var="company" items="${companies}">
            <div>
                <h1><a href="/companies/${company.id}">${company.name}</a></h1>
                <br />
                <img src="/api/file/${company.id}?type=COMPANY" style="width: 500px;">
            </div>
        </c:forEach>
    </c:if>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>