'use strict'
AppProd.controller('ProdController', ['$scope','Product', 'FileUpload', function($scope, Product, FileUpload){
	var self = this;
	self.product = new Product();
	self.products=[];
	self.files=[];
	self.uploadFile = function(prodId){
        // var file = $scope.myFile;
         var uploadUrl = "http://localhost:8090/ProductManager/uploadFiles/";
         return FileUpload.uploadFileToUrl(prodId, self.files, uploadUrl);
      };
	
	self.getAllProducts = function(){
		self.products = Product.query();
	};

	self.createProduct = function(){
		self.product.$save(function(prod){
			self.uploadFile(prod.id).then(
                   function(d) {
                	  self.getAllProducts();
                   },
                    function(errResponse){
                        console.error('Error while uploading photos');
                    }
              );
		});
	};

	self.updateProduct = function(){
		self.product.$update(function(){
			self.getAllProducts();
		});
	};

	self.deleteProduct = function(id){
		var product =Product.get({id:id},function(){
			   product.$delete(function(){
				console.log('product deleted');
				self.getAllProducts();
			});
		});
	};
	self.createStringArray = function(arr, prop) {
		   var result = [];
		   for (var i = 0; i < arr.length; i += 1) {
		      result.push(arr[i][prop]);
		      result.push(i);
		      result.push(';');
		   }
		   return result;
		}
	
	self.submit = function(){
		if(self.product.id==null){
			self.product.photos = JSON.stringify(self.createStringArray(self.files, 'name'));
			self.createProduct();
		}else{
			self.updateProduct();
		}
		self.clear();
	};

	self.edit = function(id){
		for (var i =0; i < self.products.length; i++) {
			if(self.products[i].id===id){
				self.product = angular.copy(self.products[i]);
				break;
			}
		};
	};

	self.delete = function(id){
		if(self.product.id === id){
			self.clear();
		}
		self.deleteProduct(id);
	};

	self.clear = function(){
		self.product = new Product();
		$scope.prodForm.$setPristine();
	};
	self.getAllProducts();

}])