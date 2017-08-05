$(function(){

    var user_id = null,
        request_id = null;

    $('#sign_up_form').submit(function(e){
        e.preventDefault();

        registerUser(function(){
            registerRequest(function(){
                var file = $('#request_file')[0].files[0];

                if (!file) {
                    showErrorMessage('please select file');

                    return;
                }

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
                                location.href = '/';
                            }, 1000);
                        } else {
                            var e = response.error;
                            showErrorMessage(e.message + buildValidationErrors(e.errors));
                        }
                    }
                )
            });
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
    function registerUser(callback) {
        if (user_id) {
            callback();

            return;
        }


        var data = {
            role: 'user'
        };

        $('#user_fields .form-field').each(function(){
            var $self = $(this);

            data[$self.attr('name')] = $self.val();
        });

        Ajax.put({
            url: '/api/users/',
            data: data,
            success: function(response) {
                if (response.result) {
                    user_id = response.result;
                    callback();
                } else {
                    showErrorMessage(response.error);
                }
            }
        })
    }

    function registerRequest(callback) {
        if (request_id) {
            callback();

            return;
        }

        var data = {};

        $('#company_fields .form-field').each(function(){
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

        data.user_id = user_id;

        Ajax.put({
            url: '/api/request/',
            data: data,
            success: function(response) {
                if (response.result) {
                    request_id = response.result;
                    callback();
                } else {
                    var e = response.error;
                    showErrorMessage(e.message + buildValidationErrors(e.errors));
                }
            }
        });
    }
});