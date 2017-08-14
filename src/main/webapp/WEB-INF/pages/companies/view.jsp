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

        <div class="container">
            <section class="page-content">
                <div class="text-center">
                    <img src="/api/file/${company.id}?type=COMPANY" class="col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 img-responsive company-logo">
                </div>
                <div class="clearfix"></div>
                <h1 class="text-center">${company.name}</h1>
                <div class="text-desc">
                    ${company.text}
                </div>
            </section>
        </div>
        <jsp:include page="../common/footer.jsp" />

        <script>
            $(document).ready(function() {
                // Active menu
                $('#menu_companies').addClass('active');
            });
        </script>
    </body>
    </html>
</compress:html>