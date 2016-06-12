'use strict'
AppProd.directive('fillproducts', function(){
	 //define the directive object
	   var directive = {};
	   
	   //restrict = E, signifies that directive is Element directive
	   directive.restrict = 'E';
	   
	   directive.scope = {
		   ctrl: '=controller'
	   }
	   
	   //template replaces the complete element with its text. 
	   directive.template = '<div class="tab-pane active" id="blockView">'
			+ '<ul class="thumbnails">'
			+ '<li ng-repeat="u in ctrl.products"  class="span3">'
			+ '<div class="thumbnail">'
			+ '<a href="product_details.html">'
			+ '	<img  src="assets/themes/images/products/3.jpg" alt=""/>'
			+ '</a>'
			+ '<div class="caption">'
			+ '	<h5>{{u.name}}</h5>'
			+ '	<p>{{u.description}}</p>'
			+ '   <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>'
			+ '<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button> '
            +' <button type="button" ng-click="ctrl.deleteProduct(u.id)" class="btn btn-danger custom-width">Remove</button>'
			+ '</div>'
			+ ' </div>'
			+ '</li>'
			+ ' </ul>'
			+ '<hr class="soft"/>'
			+ '</div>';
	   
	   return directive;
});