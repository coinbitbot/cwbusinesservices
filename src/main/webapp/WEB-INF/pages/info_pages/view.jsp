<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>${info_page.meta_title}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section class="page-content">
            <article>
                <h1>${info_page.header}</h1>
                <h3>${info_page.sub_header}</h3>
                <div class="text-desc">
                ${info_page.text}
                </div>
            </article>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>