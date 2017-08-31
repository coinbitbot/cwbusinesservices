<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Login fail</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>
    <div class="container">
        <section>
            <h2 class="no-info no-info-icon text-center">We can not login you using social network..</h2>
        </section>
        <div class="clearfix"></div>
    </div>
    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>