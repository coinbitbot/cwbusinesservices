<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Profile</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>
    <div class="container">
        <section>
            <h1 class="col-xs-12">User Profile</h1>
            <div class="clearfix"></div>
            <div class="col-sm-9 col-sm-push-3">
                <div class="row">

                </div>
            </div>
            <div class="col-sm-3 col-sm-pull-9">
                <jsp:include page="user_menu.jsp" />
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <script>
        $(document).ready(function() {
            // Active menu
            $('#user_profile').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>