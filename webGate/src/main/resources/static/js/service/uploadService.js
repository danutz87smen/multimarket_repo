'use strict'
AppProd.service('FileUpload', [ '$http', function($http) {
	return {
		uploadFileToUrl : function(prodId, file, uploadUrl) {
		   	var fd = new FormData();
			fd.append('file', file);
			fd.append('prodId',prodId);

			return $http.post(uploadUrl, fd, {
		          headers: {'Content-Type': undefined}
			}).success(function() {
			}).error(function() {
			});
		}
	}
} ]);