<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Facebook success</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <form action="/facebook_success" method="post" id="facebook_token">
        <input type="hidden" name="access_token">
    </form>

    <script>
        $(function(){
            var hash = location.hash;
            if (hash.length) {
                var split = hash.substr(1).split('&'),
                    access_token = null;

                for (var i = 0; i < split.length; ++i) {
                    if (split[i].indexOf('access_token=') === 0) {
                        access_token = split[i].replace('access_token=', '');
                    }
                }

                if (access_token) {
                    var $form = $('#facebook_token');
                    Ajax.appendCSRFtoForm($form);
                    $form.find('[name=access_token]').val(access_token);
                    $form.submit();
                }
            }
        });
    </script>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>