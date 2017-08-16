(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/menu/' + params.id,
                {
                    params: {
                        fields: 'id,name'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/menu/all');
                    }
                })
        } else {
            showErrorMessage('menus can not be created');
            redirectAfterFreeze('/admin/menu/all');
        }

        save($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $http.post('/api/menu/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();