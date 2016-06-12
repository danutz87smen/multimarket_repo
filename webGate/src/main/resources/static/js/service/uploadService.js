'use strict'
AppProd.service('FileUpload', [ '$http', function($http) {
	return {
		uploadFileToUrl : function(prodId, file, uploadUrl) {
			var fd = new FormData();
			for (var i = 0; i < file.length; i++) {
				fd.append('uploaded_file_' + i, file[i]);
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