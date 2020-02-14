$(function(){
	$(document).on("click", "ul li#qnaCount", function(){
		var searchWord = $("#myid").text().trim();
		var choice="writer";
		
		location.href=getContextPath()+"/qnafoward?searchWord="+searchWord+"&choice="+choice;
	});
	
	$(document).on("click", "ul li#reviewCount", function(){
		var searchText = $("#myid").text().trim();
		var selectIndex="4";
		
		location.href=getContextPath()+"/reviewfoward?selectIndex="+selectIndex+"&searchText="+searchText;
	});
	
	$(document).on("click", "ul li#resvCount", function(){
		var loginId = $("#myid").text().trim();
		location.href= getContextPath()+"/fowardmyresvhistory";
	});
});

function getContextPath() {
	   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	   
	   
	};
	