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
            <div class="no-info no-info-icon text-center">No such post</div>
        </section>
        <div class="clearfix"></div>
        <!-- Post navigation
        <div class="bottom-nav">
            <div class="col-xs-6"><a href="#"><span class="post-nav nav-left"><< Prev</span><span class="text-ellipsis hidden-xs">Title prev post</span></a></div>
            <div class="col-xs-6 text-right"><a href="#"><span class="post-nav nav-right">Next >></span><span class="text-ellipsis hidden-xs">Title next post</span></a></div>
            <div class="clearfix"></div>
        </div> -->
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