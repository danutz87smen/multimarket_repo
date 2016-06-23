'use strict'
AppProd.directive('fileModel', ['$parse', function ($parse) {
		var directive = {};
		directive.restrict = 'E',
		directive.scope = false,
		directive.template='<input type="button" class="uploadButton btn btn-file " value="Browse" />'
			               +'<input type="file" class="hidden" file-model = "myFile" multiple="true" id="uploadPhotos"  />'
		directive.link = function(scope, element, attrs) {
          // var model = $parse(attrs.fileModel);
          // var modelSetter = model.assign;
           
           element.bind('change', function(){
              scope.$apply(function(){
            	  $.each($(element).find('input[type=file]').prop('files'), function(i,val){
            		  scope.ctrl.files.push(val);
            	  })
                 directive.addUploadItems(scope, element);
              });
           });
        },
    	
        directive.addUploadItems = function(scope, element){
        	$(element).next(['.uploadContainer']).remove();
    		var itemContainer = $('<div class=".uploadContainer"></div>');
    		for(var i = 0; i< scope.ctrl.files.length; i++){
    			$(itemContainer).append('<div class="uploadItem"><p class="uploadText">'+ scope.ctrl.files[i].name+'</p><p data-file-idx="'+i+'" class="uploadItemDelete">X</p></div>');
    		}
    		$(element).after(itemContainer);
    		$('.uploadItemDelete').bind('click', function(){
    			var fileIdx = $(this).attr('data-file-idx');
    			scope.ctrl.files.splice(fileIdx, 1);
    			$(this).parent().remove();
    		});
    	}
        return directive;
  }]);