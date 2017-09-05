<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>Contact us</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="feedback">
        <div class="container">
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h3 class="text-uppercase text-center">You can write us a message:</h3>
                <div class="register-block">
                    <div class="row">
                        <form id="contact_us">
                            <div class="col-xs-12 col-sm-6"><input type="text" name="name" placeholder="Your Name" class="form-field col-xs-12"></div>
                            <div class="col-xs-12 col-sm-6"><input type="email" name="email" placeholder="Your Email" class="form-field col-xs-12"></div>
                            <div class="col-xs-12"><textarea name="text" placeholder="Your Message" class="form-field col-xs-12"></textarea></div>
                            <div class="text-center"><button class="btn-theme btn-2">Send message</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script src="/resources/js/info_page/contact_us.js"></script>
    <script>
        (function(){
            $('#menu_contacts').addClass('active');
        })();
    </script>
    </body>
    </html>
</compress:html>