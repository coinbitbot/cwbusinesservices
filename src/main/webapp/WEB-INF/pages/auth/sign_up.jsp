<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Реєстрація</title>
    <jsp:include page="../common/include_resources.jsp" />
    <link rel="stylesheet" href="/resources/css/sign_up.css">

    <script>
        $(document).ready(function(){
            $('#sign_up_form').submit(function(e){
                e.preventDefault();

                var self = $(this);
                var password = self.find('[name=password]').val();
                var repeat_password = self.find('[name=password_repeat]').val();

                if(password.length < 8){
                    showErrorMessage('password should have at least 8 characters');
                    return;
                }

                if(password != repeat_password){
                    showErrorMessage('passwords must be equals');
                    return;
                }

                Ajax.put({
                    url: '/api/users/',
                    data: JSON.stringify({
                        email: self.find('[name=email]').val(),
                        password: md5(password),
                        role: 'user'
                    }),
                    dataType: 'json',
                    success: function(response){
                        if(response.result){
                            location.href = '/';
                        } else if(response.error){
                            var error = response.error;
                            showErrorMessage(error.message + buildValidationErrors(error.errors));
                        } else {
                            showErrorMessage('service error');
                        }
                    },
                    error: function(xhr){
                        showErrorMessage('service error');
                        console.log(xhr);
                    }
                });
            });
        });
    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div>
    <form id="sign_up_form">
        <label>Електронна пошта</label>
        <input type="email" name="email" placeholder="email@example.com">
        <br />
        <label>Пароль</label>
        <input type="password" name="password" placeholder="My super secret password">
        <br />
        <label>Повторіть пароль</label>
        <input type="password" name="password_repeat" placeholder="My super secret password(again)">
        <br />
        <button type="submit">Зареєструватись</button>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
</body>
</html>
</compress:html>

