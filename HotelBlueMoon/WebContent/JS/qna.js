$(document).ready(function () {
	var _choice = $("#choice").val();
	var _searchWord = $("#search").val();
	if(_choice != '' && _choice != 'sel'){		
		if(_searchWord != ""){			
			$("#choice").val(_choice);
			$("#search").val(_searchWord);
		}
	}
});

function searchQnA() {
	
	var choice = $("#choice").val();
	var word = $("#_search").val();
	
	

	if(word == ""){
		document.getElementById("choice").value = 'sel';
	}
	
	location.href = getContextPath() +"/qnafoward?choice=" + choice + "&searchWord=" + word;
}

function goPage( pageNum ){
	alert(pageNum);
	
	var choice = $("#choice").val();
	var word = $("#search").val();
	
	if(word == ""){
		document.getElementById("choice").value = 'sel';
	}
	if(choice != 'sel' && word != ""){
		
	}
	location.href = getContextPath() +"/qnafoward?searchWord=" + word + "&choice=" + choice +"&pageNumber=" + pageNum;
}
function getContextPath() {
	   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	};