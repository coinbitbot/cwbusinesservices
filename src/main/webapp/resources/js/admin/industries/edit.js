(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get('/api/industry/' + params.id + '?fields=id,name')
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/industries/all');
                    }
                })
        }

        save($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/industry/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');

                        if (!id) {
                            redirectAfterFreeze('/admin/industries/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();