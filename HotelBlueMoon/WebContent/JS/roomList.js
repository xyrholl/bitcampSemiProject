$(document).ready(function() {

		var checkin = getParameterByName('checkin');
		var checkout = getParameterByName('checkout');
		var guest = getParameterByName('guest');
		var hotelSeq = getParameterByName('hotelSeq');
		var hotelName = getParameterByName('hotelName');
		ajax(checkin, checkout, guest, hotelSeq, hotelName);
});

function ajax(checkin, checkout, guest, hotelSeq, hotelName){
		
/*		$("#checkin").val(checkin.substring(5,10));
		$("#checkout").val(checkout.substring(5,10));
		$("#area").val(area);
		$("#guest").val(guest);
		$(".card").remove();
		$(".result").remove();
*/
		
		$.ajax({
					url : "../roomList",
					type : "post",
					dataType : "json",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					data : {
						"checkin" : checkin,
						"checkout" : checkout,
						"guest" : guest,
						"hotelSeq" : hotelSeq
					},
					success : function(data) {
						console.log(data[0].name);
						$("#hotelName").val(hotelName);

						for (var i = 0; i < data.length; i++) {
							
							$(".mainSpan").append(
											"<div class=\"card\" style=\"width: 18rem;\">"
													+ "<img class=\"card-img-top\" src=\"../image/image/" + data[i].image + "\" alt=\"Card image cap\">"
													+ "<div class=\"card-body\">"
													+ "<h5 class=\"card-title\">"
													+ data[i].name
													+ "</h5>"
													+ "<p class=\"card-text\">"
												    + "<br>[최대정원 : " + data[i].guest + "]</p>"
													+ "<a href=\"../hotelResvInfo?hotelSeq=" + data[i].hotelSeq + "&checkin=" + checkin + "&checkout=" + checkout 
													+ "&guest=" + guest + "&roomSeq=" + data[i].seq + "\" class=\"btn btn-primary\">Reservation</a>"
													+ "</div>"
													+ "</div>");

							console.log(data[i].name);
						}
					},
					error : function() {
						alert("실패");
					}
				})
};

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}

function goToBefore(){
	history.back();
}