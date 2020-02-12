
$(document).ready(function() {
	var checkin = getParameterByName('checkin');
	var checkout = getParameterByName('checkout');
	var guest = getParameterByName('guest');
	var area = getParameterByName('area');
	var flag = getParameterByName('flag');
	ajax(checkin, checkout, guest, area, flag);

});

function ajax(checkin, checkout, guest, area, flag){
	if (flag == 'yes') {
		
		$("#checkin").val(checkin.substring(5,10));
		$("#checkout").val(checkout.substring(5,10));
		$("#area").val(area);
		$("#guest").val(guest);
		$(".card").remove();

		$.ajax({
					url : "../hotelList",
					type : "post",
					dataType : "json",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					data : {
						"checkin" : `${checkin}`,
						"checkout" : `${checkout}`,
						"guest" : `${guest}`,
						"area" : `${area}`
					},
					success : function(data) {
						
						console.log(data[0].count);

						for (var i = 0; i < data.length; i++) {
							var star = "";
							var tmpNumber = Math.floor(data[i].count);
							for (var j = 0; j < tmpNumber; j++) {
								star += "★";
							}
							if (data[i].count - tmpNumber > 0) {
								star += "☆";
							}
							$(".mainSpan").append(
											"<div class=\"card\" style=\"width: 18rem;\">"
													+ "<img class=\"card-img-top\" src=\"../css/assets/images/01.jpg\" alt=\"Card image cap\">"
													+ "<div class=\"card-body\">"
													+ "<h5 class=\"card-title\">"
													+ data[i].name
													+ "</h5>"
													+ "<p class=\"card-text\">"
													+ data[i].addr
													+ "<br>[평점 : "
													+ star
													+ " / "
													+ data[i].count
													+ "점]</p>"
													+ "<a href=\"#\" class=\"btn btn-primary\">Reservation</a>"
													+ "</div>"
													+ "</div>");

							console.log(data[i].name);
						}
					},
					error : function() {
						$(".mainSpan")
						.append("<br><br><br><br><span style=\"color:white;\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp해당 조건과 일치하는 검색 결과가 없습니다.</span>");
					}
				})
	}
};

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}


$(function (){

    $("#checkin").datepicker({
    	minDate: 0,
    	onClose: function( selectedDate ) {    
            $("#checkout").datepicker( "option", "minDate", selectedDate );
        }
    	
    });

});

$(function (){

    $("#checkout").datepicker();

});

function goToAjax(){
	
	var ci_monthDay = $('#checkin').val();
	var co_monthDay = $('#checkout').val();
	var guestCount = document.querySelector(".js-guestBox");
	var area = document.querySelector(".js-areaBox");
	var flag = "yes";
	area = area.options[area.selectedIndex].text;
	guestCount = guest.options[guest.selectedIndex].text;

	if(guestCount == 'Guest'){
		guestCount = 1;
	}
	
	var ci_date = "2020-" + ci_monthDay;
	var co_date = "2020-" + co_monthDay;

	ajax(ci_date, co_date, guestCount, area, flag);
	
}
