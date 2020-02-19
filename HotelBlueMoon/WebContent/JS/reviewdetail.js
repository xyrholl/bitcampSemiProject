
const updateReview = document.querySelector(".js-update-review");
const updateSeq = document.querySelector(".js-update-seq");

const deleteBtn = document.querySelector(".js-delete-review");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};



function updatePage() {
	location.href = getContextPath() + "/updatereviewfoward?seq="
			+ updateSeq.value;
}

function deleteCheck() {
	alert("리뷰가삭제됩니다.")
	location.href = getContextPath() + "/reviewdelete?seq=" + updateSeq.value;
}

function init() {
	
	updateReview.addEventListener("click", updatePage);
	deleteBtn.addEventListener("click", deleteCheck);
}

init();