<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Forbidden</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>
        <div class="container" style="text-align: center;">
            <section class="page-content">
                <h2>
                    We faced with some error. Please try later to access this resource
                </h2>
                <p style="margin-bottom: 40px;">
                    While we fix the problem show us how good you are at breakout
                </p>
                <br />
                <div id="game" style="display: inline-block;"></div>
            </section>
        </div>
        <jsp:include page="../common/footer.jsp" />

        <script src="/resources/js/game/phaser.min.js"></script>
        <script src="/resources/js/game/game.js"></script>
    </body>
    </html>
</compress:html>