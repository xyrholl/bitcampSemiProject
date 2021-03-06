
$(document).ready(function() {
   if(getParameterByName('flag') == 'yes'){
      var tempYear = tempMonth = tempDay = "";
      var checkin = getParameterByName('checkin');
      var checkout = getParameterByName('checkout');
      if(checkin == checkout){
         tempYear = checkout.substring(0,4)
         tempMonth = checkout.substring(5,7);
         tempDay = checkout.substring(8);
         tempDay = String(parseInt(tempDay) + 1);
         if(tempDay < 10){
            checkout = tempYear + "-" + tempMonth + "-0" + tempDay;
         }
         else {
            checkout = tempYear + "-" + tempMonth + "-" + tempDay;
            if(checkout == "2020-02-30"){
               checkout = "2020-03-01";
            }
         }
         
      }
      var guest = getParameterByName('guest');
      var area = getParameterByName('area');
      ajax(checkin, checkout, guest, area);
   }
   else{
      goToAjax();
   }
});

function ajax(checkin, checkout, guest, area){
		
		$("#checkin").val(checkin.substring(5,10));
		$("#checkout").val(checkout.substring(5,10));
		$("#area").val(area);
		$("#guest").val(guest);
		$(".card").remove();
		$(".result").remove();

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
							var tmpNumber = Math.floor(data[i].rating);
							for (var j = 0; j < tmpNumber; j++) {
								star += "★";
							}
							if (data[i].rating - tmpNumber > 0) {
								star += "☆";
							}
							$(".mainSpan").append(
									"<div class=\"card mb-3\" style=\"box-shadow: 5px 5px 5px 5px rgb(0,0,0,0.2);\"><h5 class=\"card-header\">" + data[i].name + "</h5>"
									+ "<img style=\"height: 200px; width: 100%; display: block;\" src=\"../image/hotel/" + data[i].hotelImg + "\" alt=\"Card image\">"
									+ "<div class=\"card-body\"><p class=\"card-text\">"
									+ data[i].addr + "</p><p class=\"card-text\">[평점 : " + star + " / " + data[i].rating + "점]</p></div>"
									+ "<div class=\"card-footer text-muted\" style=\"text-align: center;\"><a href=\"../goRoom?hotelSeq=" + data[i].seq + "&checkin=" + checkin + "&checkout=" + checkout + "&guest=" + guest + "&hotelName=" + data[i].name 
									+ "\" class=\"btn btn-primary\">Reservation</a></div></div>");

							console.log(data[i].name);
						}
					},
					error : function() {
						$(".mainSpan")
						.append("<span style=\"color:white;\" class = \"result\"><br><br><br><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
								"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp해당 조건과 일치하는 검색 결과가 없습니다.</span>");
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


$(function (){

    $("#checkin").datepicker({
    	minDate: 0,
    	onClose: function( selectedDate ) {
            $("#checkout").datepicker( "option", "minDate", selectedDate );
            $("#checkout").datepicker("show");
        }
    	
    });
    
    $("#checkout").datepicker();
});


function goToAjax(){
	   var ci_monthDay = $('#checkin').val();
	   var co_monthDay = $('#checkout').val();
	   
	   if(ci_monthDay == "in" || ci_monthDay == "" || co_monthDay == "out" || co_monthDay == ""){
	      alert("CheckIn / CheckOut 시간을 입력해주세요.");
	   }
	   else{
	      var guestCount = document.querySelector(".js-guestBox");
	      var area = document.querySelector(".js-areaBox");
	      area = area.options[area.selectedIndex].text;
	      guestCount = guest.options[guest.selectedIndex].text;

	      if(guestCount == 'Guest'){
	         guestCount = 1;
	      }
	      
	      if(ci_monthDay == co_monthDay){
	         tempMonth = co_monthDay.substring(0,2);
	         tempDay = co_monthDay.substring(3);
	         tempDay = String(parseInt(tempDay) + 1);
	         if(tempDay < 10){
	            co_monthDay = tempMonth + "-0" + tempDay;
	         }
	         else{
	            co_monthDay = tempMonth + "-" + tempDay;
	            
	            if(co_monthDay == "02-30"){
	               co_monthDay = "03-01";
	            }
	         }
	      }

	   
	      var ci_date = "2020-" + ci_monthDay;
	      var co_date = "2020-" + co_monthDay;

	      ajax(ci_date, co_date, guestCount, area);
	   }
	}
