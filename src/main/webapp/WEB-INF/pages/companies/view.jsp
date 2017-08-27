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
                    <h1>
                        <img src="/api/file/${company.id}?type=COMPANY" class="company-logo">
                        <span class="company-name">${company.name}</span>
                    </h1>
                <div class="clearfix"></div>
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