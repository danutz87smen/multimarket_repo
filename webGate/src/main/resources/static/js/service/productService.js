'use strict'
AppProd.factory('Product', ['$resource', function($resource){
	return $resource('http://localhost:8080/AngularSpringRest1/products/:id',
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