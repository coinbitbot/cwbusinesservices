(function(){

    var sing_in_popup_template = new EJS({url: '/resources/template/auth/sign_in_popup.ejs'});

    $(function(){
        $('#sign_in').click(renderSignInPopup);

        $('#logout').click(function(){
            Ajax.post({
                url: '/api/users/logout',
                success: function(){
                    location.reload(true);
                }
            })
        });

        $('#subscribe_form').submit(function(e){
            e.preventDefault();

            var $email = $(this).find('[name=email]');

            Ajax.put({
                url: '/api/subscription/',
                data: {
                    email: $email.val()
                },
                success: function(response) {
                    if (response.result) {
                        showSuccessMessage('you are now subscribed');
                        $email.val('');
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });
    });

    function renderSignInPopup(){
        var popup = $(sing_in_popup_template.render({}));

        popup.find('.close').click(function(){
            popup.remove();
        });

        popup.find('#sign_in_form').submit(function(e){
            e.preventDefault();

            var self = $(this);

            Ajax.post({
                url: '/api/users/sign_in',
                data: {
                    email: self.find('[name=email]').val(),
                    password: md5(self.find('[name=password]').val())
                },
                success: function(response){
                    if(response.result){
                        location.reload(true);
                    } else if(response.error){
                        showErrorMessage(response.error);
                    } else {
                        showErrorMessage('service error');
                    }
                },
                error: function(xhr){
                    showErrorMessage('service error');
                    console.log(xhr);
                }
            })
        });

        $('body').append(popup);
    }

})(window);