(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get('/api/block/' + params.id + '?fields=id,title,code')
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/blocks/all');
                    }
                })
        } else {
            showErrorMessage('blocks can not be created');
            redirectAfterFreeze('/admin/blocks/all');
        }
    });
})();