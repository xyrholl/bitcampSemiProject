const select = document.querySelector(".js-search-select");
const searchBtn = document.querySelector(".js-searchBtn");
const searchText = document.querySelector(".js-searchText");
const loginId = document.querySelector(".js-session");

const allListBtn = document.querySelector(".js-allListBtn");

var nowTime = "";

function currDate() {
	const currDate = new Date();
	const yyyy = currDate.getFullYear();
	const MM = currDate.getMonth() + 1;
	const dd = currDate.getDate();
	nowTime = yyyy + "-" + MM + "-" + dd;
};

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function searchCheck() {
	if (select.value === "검색") {
		alert("검색 태그가 설정 되지 않았습니다.")
	} else if (searchText.value.trim() === "") {
		alert("검색어가 없습니다.")
	} else {
		searchEvent()
	}
}

function searchEvent() {
	$.ajax({
		method : "get",
		url : `${getContextPath()}` + "/fowardmyresvhistory?loginId="
				+ loginId.value + "&nowTime=" + nowTime,
		data : {
			"selectIndex" : `${select.value}`,
			"searchText" : `${searchText.value}`
		},
		success : function(data) {
			location.href = `${getContextPath()}`
					+ "/fowardmyresvhistory?loginId=" + loginId.value
					+ "&nowTime=" + nowTime + "&selectIndex="
					+ `${select.value}` + "&searchText="
					+ `${searchText.value}`
		},
		error : function() {
			alert("Ajax 실패")
		}
	})
}

function allSearch() {
	location.href = `${getContextPath()}` + "/fowardmyresvhistory?loginId="
			+ loginId.value + "&nowTime=" + nowTime;
}

$(function(){
	$(".clink").click(function(){
		var ctype = $(this).closest(".ctype").val();
		var ptype = $(this).parent().parent().children().children(".ptype").val();	
		var detail_seq = $(this).parent().parent().children().children(".resv_seq").val();
		location.href= getContextPath()+"/myresvdetail?command=detail&detail_seq="+detail_seq+"&ctype="+ctype+"&ptype="+ptype;
	});
	$(".plink").click(function(){
		var ctype = $(this).parent().parent().children().children(".ctype").val();	
		var ptype = $(this).closest(".ptype").val();
		var detail_seq = $(this).parent().parent().children().children(".resv_seq").val();
		location.href= getContextPath()+"/myresvdetail?command=detail&detail_seq="+detail_seq+"&ctype="+ctype+"&ptype="+ptype;
	});
});






function init() {
	searchBtn.addEventListener("click", searchCheck);
	allListBtn.addEventListener("click", allSearch);
	currDate();
}

init();