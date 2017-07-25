<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>All info pages</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <c:if test="${info_pages ne null}">
        <c:forEach var="page" items="${info_pages}">
            <div>
                <h1><a href="/info_pages/${page.url}">${page.header}</a></h1>
                <h2>${page.sub_header}</h2>
                <br />
                ${page.text}
            </div>
        </c:forEach>
    </c:if>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>