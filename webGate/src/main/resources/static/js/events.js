$(document).ready(function() {
	$('.uploadButton').click(function(e) {
		$(this).next().click();
	});
});