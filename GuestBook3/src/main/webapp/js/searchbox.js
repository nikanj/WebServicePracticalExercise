$(document).ready(function(){
    
    // When the user finishes typing 
    // a character in the text box...
    $('#searchTextId').keyup(function(){
        
        // Call the function to handle the AJAX.
        // Pass the value of the text box to the function.
       var str = $(this).val();
       fetchKeywords(str);
   }); 

    $('#displayForm').submit(function() {
    	  return true;
    });

    $( "#searchTextId" ).on( "autocompleteselect", function( event, ui ) {
    	 $(this).val(ui.item.value);
    	 $("#displayForm").submit();
    	 return false;
    });
    
    $( "#searchTextId" ).on( "autocompletesearch", function( event, ui ) {
    } );
    
});

function fetchKeywords(str){
	$.post("./searchkeyword.app",
  	{ keywordInput : str },
    function(data,status){
  		
  	   var keywordsList = ['Searching...'];

  	  	keywordsList =  $('li',data).map(function() {
  		  // For each <li> in the list, return its inner text and let .map()
  		  //  build an array of those values.
  		  return $(this).text();
  		}).get();
  	  	
	  	  $( "#searchTextId" ).autocomplete({
	          source:  function( request, response ) {
	        	  //var matcher = new RegExp($.trim(request.term).replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&"), "i" );
	              response($.grep(keywordsList, function(value) {
	                  return true;
	              }));},
	          minLength: 0,
	          delay: 0,
	          select: function( event, ui ) {},
	  	  	  search: function( event, ui ) {}
	      });

    });	
}
