<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>${info_page.meta_title}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <h1>${info_page.header}</h1>
    <h3>${info_page.sub_header}</h3>
    <br />
    ${info_page.text}

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>