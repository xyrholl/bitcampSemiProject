const reviewSubmit = document.querySelector(".js-insert-review");

const resvSeq = document.querySelector(".js-input-review-resvseq");
const roomSeq = document.querySelector(".js-input-review-roomseq");
const hotelSeq = document.querySelector(".js-input-review-hotelseq");
const loginId = document.querySelector(".js-input-id");
const rating = document.querySelector(".js-input-rating");
const title = document.querySelector(".js-input-title");
const content = document.querySelector(".js-input-content");

const form = document.querySelector(".js-file-form");

function ratingCheck() {
	if (rating.value.trim() !== "") {
		if (rating.value.trim() > 5) {
			alert("평점은 5점보다 클수 없습니다.")
		} else if (rating.value.trim() < 0) {
			alert("평점은 0점보다 작을 수 없습니다.");
		} else {
			return true;
		}
	} else {
		alert("평점을 입력해주세요.")
	}
	return false;
}

function nullCheck() {
	if (ratingCheck() === true) {
		if (title.value.trim() !== "") {
			if (content.value.trim() !== "") {
				form.submit();
			} else {
				alert("내용을 입력해주세요.")
			}
		} else {
			alert("제목을 입력해주세요")
		}
	}
}

function init() {
	reviewSubmit.addEventListener("click", nullCheck)
}

init();