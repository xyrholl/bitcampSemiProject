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
	$(document).on("click", "#cancelBtn", function(){

		var cancel_seq = $("#cancel_seq").val();
		location.href= getContextPath()+"/myresvcancel?command=cancelAf&nowTime="+nowTime+"&cancel_seq="+cancel_seq;
	});
	$(document).on("click", "#listBtn", function(){
	
		location.href= getContextPath()+"/fowardmyresvhistory?nowTime="+nowTime;
	});
	
	
	
});

function getContextPath() {
		   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
		   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

	
