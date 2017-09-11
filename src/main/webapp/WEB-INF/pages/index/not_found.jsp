<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Not found</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>
        <div class="container">
            <section class="page-content text-center">
                <h2 class="no-info no-info-icon">We are sorry, we can’t seem to find this page</h2>
                <br />
                <div id="game" style="display: inline-block;"></div>
            </section>
            <div class="clearfix"></div>
        </div>
        <jsp:include page="../common/footer.jsp" />

    <script src="/resources/js/game/phaser.min.js"></script>
        <script src="/resources/js/game/game.js"></script>
    </body>
    </html>
</compress:html>