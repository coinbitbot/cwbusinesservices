(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get('/api/service/' + params.id + '?fields=id,name,description,active,has_icon')
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        if ($scope.entity.has_icon) {
                            $scope.service_icon = 'http://www.iageproject.eu/publish/pages/111858/iage.png';
                        }
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/services/all');
                    }
                })
        }

        save($scope, $http);
        uploadFile($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/service/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/services/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }

    function uploadFile($scope, $http) {
        $scope.uploadFile = function() {
            var element = angular.element('#image')[0];
            var file = element.files[0];

            if (!file) {
                showErrorMessage('select file');
                return;
            }

            var payload = new FormData();
            payload.append('id', $scope.entity.id);
            payload.append('file', file);

            var request = new XMLHttpRequest();
            request.open('POST', '/api/service/storage');

            for (var key in HEADERS) {
                if (key.toLowerCase().indexOf('content') < 0) {
                    request.setRequestHeader(key, HEADERS[key]);
                }
            }

            request.onload = function () {
                var response = request.response;
                if (typeof response === 'string') {
                    response = JSON.parse(response);
                }

                if (response.result) {
                    showSuccessMessage('uploaded');
                    $scope.service_icon = 'http://www.iageproject.eu/publish/pages/111858/iage.png';

                    $http.post('/api/service/', JSON.stringify({
                        id: $scope.entity.id,
                        has_icon: true
                    }), {headers: HEADERS});
                } else {

                }
            };
            request.send(payload);
        };
    }
})();