const insertReview = document.querySelector(".js-insert-review");
const returnList = document.querySelector(".js-returnList");

const loginId = document.querySelector(".js-input-id");

const reviewRating = document.querySelector(".js-input-rating");
const reviewTitle = document.querySelector(".js-input-title");
const reviewContent = document.querySelector(".js-input-content");

const reviewResvSeq = document.querySelector(".js-input-review-resvseq");
const reviewHotelSeq = document.querySelector(".js-input-review-hotelseq");
const reviewRoomSeq = document.querySelector(".js-input-review-roomseq");

var nowTime = "";

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function currDate() {

	const currDate = new Date();
	const yyyy = currDate.getFullYear();
	const MM = currDate.getMonth() + 1;
	const dd = currDate.getDate();
	nowTime = yyyy + "-" + MM + "-" + dd;

};

function ratingCheck() {
	if (reviewRating.value.trim() !== "") {
		if (reviewRating.value.trim() > 5) {
			alert("평점은 5점보다 클수 없습니다.")
		} else if (reviewRating.value.trim() < 0) {
			alert("평점은 0점보다 작을 수 없습니다.");
		} else {
			insertCheck()
		}
	} else {
		alert("평점을 입력해주세요.")
	}
}

function insertCheck() {
	if (reviewTitle.value.trim() !== "") {
		if (reviewContent.value.trim() !== "") {
			insert()
		} else {
			alert("내용을 입력해주세요.")
		}
	} else {
		alert("제목을 입력해주세요.")
	}
}

function returnReviewList() {
	location.href = getContextPath() + "/fowardmyresvhistory?loginId="
			+ loginId.value + "&nowTime=" + nowTime;
}

function insert() {
	location.href = getContextPath() + "/reviewInsert?loginId=" + loginId.value
			+ "&resvSeq=" + reviewResvSeq.value + "&hotelSeq="
			+ reviewHotelSeq.value + "&roomSeq=" + reviewRoomSeq.value
			+ "&rating=" + reviewRating.value + "&title=" + reviewTitle.value
			+ "&content=" + reviewContent.value + "&nowTime=" + nowTime;
}

function test() {
	alert("test")
}

function init() {
	insertReview.addEventListener("click", ratingCheck);
	insertReview.addEventListener("click", test);
	returnList.addEventListener("click", returnReviewList);
	currDate();
}

init();