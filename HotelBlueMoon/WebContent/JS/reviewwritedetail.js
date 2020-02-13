const insertReview = document.querySelector(".js-insert-review");
const returnList = document.querySelector(".js-returnList");

const loginId = document.querySelector(".js-input-id");

const reviewRating = document.querySelector(".js-input-rating");
const reviewTitle = document.querySelector(".js-input-title");
const reviewContent = document.querySelector(".js-input-content");

const reviewResvSeq = document.querySeletor(".js-input-review-resvseq");
const reviewHotelSeq = document.querySeletor(".js-input-review-hotelseq");
const reviewRoomSeq = document.querySeletor(".js-input-review-roomseq");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
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

function insert() {
	location.href = getContextPath() + "/reviewInsert?loginId=" + loginId
			+ "&resvSeq=" + reviewResvSeq.value + "&hotelSeq="
			+ reviewHotelSeq.value + "&roomSeq=" + reviewRoomSeq.value
			+ "&rating=" + reviewRating.value + "&title=" + reviewTitle
			+ "&content=" + reviewContent;
}

function init() {
	insertReview.addEventListener("click", ratingCheck);
	returnList.addEventListener("click", returnReviewList);
}

init();