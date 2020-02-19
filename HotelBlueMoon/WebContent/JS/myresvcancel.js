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
	var detail_seq = $("#detail_seq").val();
	$(document).on("click", "#cancelBtn", function(){
		alert("JS: 취소seq= "+detail_seq);
		location.href= getContextPath()+"/myresvcancel?command=cancelAf&nowTime="+nowTime+"&detail_seq="+detail_seq;
	});
	$(document).on("click", "#payBtn", function(){
		alert("JS: 결제seq= "+detail_seq);
		location.href= getContextPath()+"/myresvcancel?command=paymentAf&nowTime="+nowTime+"&detail_seq="+detail_seq;
	});
	
	$(document).on("click", "#listBtn", function(){
	
		location.href= getContextPath()+"/fowardmyresvhistory?nowTime="+nowTime;
	});
	
});

function getContextPath() {
		   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
		   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

	
