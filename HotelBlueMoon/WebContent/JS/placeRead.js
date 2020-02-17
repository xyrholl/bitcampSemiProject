$(document).ready(function() {
	$.ajax({
		url : "../placeRead",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			console.log(data[0].count);

			for (var i = 0; i < data.length; i++) {
				var star = "";
				$(".js-areaBox").append(
								"<option value=" + data[i].name + ">" + data[i].name + "</option>"
				)
				console.log(data[i].name);
			}
		},
		error : function() {
		}
	})
	
});