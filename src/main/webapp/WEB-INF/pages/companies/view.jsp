<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>${company.name}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <h1>${company.name}</h1>
    <img src="/api/file/${company.id}?type=COMPANY" style="width: 500px;">
    <br />
    ${company.text}

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>