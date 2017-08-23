$(function(){

    $('#sign_up_form').submit(function(e){
        e.preventDefault();

        var file = $('#request_file')[0].files[0];
        if (!file) {
            showErrorMessage('please select file');

            return;
        }

        var data = {
            role: 'user'
        };

        $('#sign_up_form .form-field').each(function(){
            var $self = $(this),
                name = $self.attr('name'),
                val = $self.val();

            if ($self.attr('type') === 'checkbox') {
                if ($self.is(':checked')) {
                    val = parseInt(val);
                    if (data[name]) {
                        data[name].push(val);
                    } else {
                        data[name] = [val];
                    }
                }
            } else {
                data[name] = $self.val();
            }
        });

        Ajax.put({
            url: '/api/users/registration',
            data: data,
            success: function(response) {
                if (response.result) {
                    var request_id = response.result.request_id;

                    Ajax.uploadFile(
                        '/api/file/',
                        {
                            id: request_id,
                            file: file,
                            type: 'REQUEST'
                        },
                        function(response) {
                            if (response.result) {
                                showSuccessMessage('you successfully register an request');
                                setTimeout(function(){
                                    location.href = '/profile/requests';
                                }, 1000);
                            } else {
                                var e = response.error;
                                showErrorMessage(e.message + buildValidationErrors(e.errors));
                            }
                        }
                    )
                } else {
                    showErrorMessage(response.error);
                }
            }
        });
    });

    $('#login_facebook').click(function(){
        FaceBook.getUserData(function(user){
            if (user) {
                for (var key in user) {
                    $('[name=' + key + ']').val(user[key]);
                }
            }
        });
    });
});