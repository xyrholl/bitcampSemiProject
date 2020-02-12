$(function() {
	$("#_check-in").datepicker();
});

function hotelListPage() {

	var ci_monthCheck = document.querySelector(".js-ci-monthVal").innerHTML;
	var co_monthCheck = document.querySelector(".js-co-monthVal").innerHTML;
	var guestCount = document.querySelector(".js-guest").innerHTML;
	var area = document.querySelector(".js-areaBox");
	var flag = "yes";
	area = area.options[area.selectedIndex].text;

	if ( area == "Area") {
		alert("지역을 선택해주세요.");
	} else {

		var ci_date = "2020-";
		var co_date = "2020-";

		if (ci_monthCheck == "Feb") {
			ci_date += "02-";
		} else if (ci_monthCheck == "Mar") {
			ci_date += "03-";
		}

		if (co_monthCheck == "Feb") {
			co_date += "02-";
		} else if (co_monthCheck == "Mar") {
			co_date += "03-";
		}

		if (document.querySelector(".js-ci-dayVal").innerHTML < 10) {
			ci_date += "0"
		}

		if (document.querySelector(".js-co-dayVal").innerHTML < 10) {
			co_date += "0"
		}

		ci_date += document.querySelector(".js-ci-dayVal").innerHTML;
		co_date += document.querySelector(".js-co-dayVal").innerHTML;
		
		location.href="../main?checkin=" + ci_date + "&checkout=" + co_date + "&guest=" + guestCount + "&area=" + area + "&flag=" + flag;
	}
}
