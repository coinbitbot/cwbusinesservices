<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>All info pages</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section>
            <h1 class="col-xs-12">Info pages</h1>
            <c:if test="${info_pages ne null}">
                <c:forEach var="page" items="${info_pages}">
                    <div class="page-content col-xs-12 info-page-item">
                        <h3><a href="/info_pages/${page.url}">${page.header}</a></h3>
                        <h4>${page.sub_header}</h4>
                        <div class="text-desc">
                        ${page.text}
                        </div>
                        <div class="text-right col-xs-12"><a href="/info_pages/${page.url}" class="btn-theme btn-transp">Read more</a></div>
                        <div class="clearfix"></div>
                    </div>
                </c:forEach>
            </c:if>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>