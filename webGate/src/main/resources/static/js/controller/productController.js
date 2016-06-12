'use strict'
AppProd.controller('ProdController', ['$scope','Product', 'FileUpload', function($scope, Product, FileUpload){
	var self = this;
	self.product = new Product();
	self.products=[];
	
	self.template = 'templates/html/header.html';

	self.getAllProducts = function(){
		self.products = Product.query();
	};

	self.createProduct = function(){
		self.product.$save(function(){
			self.getAllProducts();
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

	self.submit = function(){
		if(self.product.id==null){
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