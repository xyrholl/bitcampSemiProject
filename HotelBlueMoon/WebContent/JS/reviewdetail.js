const returnList = document.querySelector(".js-returnList");
const updateReview = document.querySelector(".js-update-review");
const updateSeq = document.querySelector(".js-update-seq");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function returnReview() {
	location.href = getContextPath() + "/reviewfoward"
}

function updatePage() {
	location.href = getContextPath() + "/updatereviewfoward?seq=" + updateSeq.value;
}

function init() {
	returnList.addEventListener("click", returnReview);
	updateReview.addEventListener("click", updatePage);
}

init();