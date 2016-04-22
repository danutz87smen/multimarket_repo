'use strict'
AppProd.factory('Product', ['$resource', function($resource){
	return $resource('http://localhost:8090/ProductManager/products/:id',
		{id: '@id'}, 
		{
			update: {
				method: 'PUT'
			}
		},
		{
			 stripTrailingSlashes: false
		});
}]);