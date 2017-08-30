<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Forbidden</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>
        We faced with some error. Please try later to access this resource
        <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>