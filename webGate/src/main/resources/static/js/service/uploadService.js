'use strict'
AppProd.service('FileUpload', [ '$http', function($http) {
	return {
		uploadFileToUrl : function(prodId, files, uploadUrl) {
			var fd = new FormData();
			for (var i = 0; i < files.length; i++) {
				fd.append('uploaded_file_' + i, files[i]);
			}
			// fd.append('file', file);
			fd.append('prodId', prodId);

			return $http.post(uploadUrl, fd, {
				headers : {
					'Content-Type' : undefined
				}
			}).success(function() {
			}).error(function() {
			});
		}
	}
} ]);