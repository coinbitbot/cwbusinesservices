<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>Set your password</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section class="page-content">
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h3 class="text-uppercase text-center">Your password</h3>
                <div class="register-block">
                    <div class="row">
                        <form id="set_password">
                            <div class="col-xs-12 col-sm-12"><input type="password" name="password" placeholder="Password" class="form-field col-xs-12"></div>
                            <div class="col-xs-12 col-sm-12"><input type="password" name="password_repeat" placeholder="Password repeat" class="form-field col-xs-12"></div>
                            <div class="text-center"><button class="btn-theme btn-1">Set password</button></div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />

    <script>
        $('#set_password').submit(function(e){
            e.preventDefault();

            var password = $(this).find('[name=password]').val(),
                password_repeat = $(this).find('[name=password_repeat]').val();

            if (!password) {
                showErrorMessage('please enter the password');

                return;
            }

            if (password !== password_repeat) {
                showErrorMessage('passwords are not equals');

                return;
            }

            Ajax.post({
                url: '/api/users/password',
                data: {
                    password_new: md5(password)
                },
                success: function(response) {
                    if (response.result) {
                        showSuccessMessage('You set Your password');

                        setTimeout(function(){
                            location.href = '/profile';
                        }, 1000);
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });
    </script>
    </body>
    </html>
</compress:html>