const returnList = document.querySelector(".js-returnList");
var nowTime = document.querySelector(".js-nowTime");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function currDate() {
	nowTime.value = "";
	const currDate = new Date();
	const yyyy = currDate.getFullYear();
	const MM = currDate.getMonth() + 1;
	const dd = currDate.getDate();
	nowTime.value = yyyy + "-" + MM + "-" + dd;

};

function returnReviewList() {
	location.href = getContextPath() + "/fowardmyresvhistory?loginId="
			+ loginId.value + "&nowTime=" + nowTime.value;
}

function init() {
	returnList.addEventListener("click", returnReviewList);
	currDate();
}

init();