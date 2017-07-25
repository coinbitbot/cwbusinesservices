(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        tinymce.init({
            selector:'#info_page_text',
            height: 400
        });

        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get('/api/infopage/' + params.id + '?fields=id,url,header,sub_header,text,active,meta_title,meta_description,meta_keywords')
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        tinymce.activeEditor.setContent($scope.entity.text);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/info_pages/all');
                    }
                })
        }

        save($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $scope.entity.text = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/infopage/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/info_pages/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();