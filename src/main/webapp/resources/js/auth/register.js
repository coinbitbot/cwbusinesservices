$(function(){

    var user_id = null,
        request_id = null;

    $('#sign_up_form').submit(function(e){
        e.preventDefault();

        registerUser(function(){
            registerRequest(function(){
                Ajax.uploadFile(
                    '/api/file/',
                    {
                        id: request_id,
                        file: $('#request_file')[0].files[0],
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