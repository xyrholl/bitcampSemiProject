var nowTime = "";

currDate();

function currDate() {
	   
	   const currDate = new Date();
	   const yyyy = currDate.getFullYear();
	   const MM = currDate.getMonth() + 1;
	   const dd = currDate.getDate();
	   nowTime = yyyy + "-" + MM + "-" + dd;
	   
	  
	};


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
		// alert("JS time: "+nowTime);
		var loginId = $("#myid").text().trim();
		location.href= getContextPath()+"/fowardmyresvhistory?loginId="+loginId+"&nowTime="+nowTime;
	});
});

function getContextPath() {
	   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	   
	   
	};
