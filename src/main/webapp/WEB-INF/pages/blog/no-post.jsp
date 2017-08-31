<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="dateValue" class="java.util.Date"/>

    <!DOCTYPE html>
    <html>
    <head>
        <title>No post</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section class="page-content">
            <h2 class="no-info no-info-icon text-center">No such post</h2>
        </section>
        <div class="clearfix"></div>
    </div>

    <jsp:include page="../common/footer.jsp" />

    <script>
        $(document).ready(function() {
            // Active menu
            $('#menu_blog').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>