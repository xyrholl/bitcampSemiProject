const updateSeq = document.querySelector(".js-update-seq");
const upadateRating = document.querySelector(".js-rating");
const updateTitle = document.querySelector(".js-title");
const updateContent = document.querySelector(".js-content");
const updateBtn = document.querySelector(".js-update-review-success");


const deleteBtn = document.querySelector(".js-delete-review");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function updateAjax() {
	$.ajax({
		method : "post",
		url : getContextPath() + "/updatesubmit",
		data : {
			"seq" : `${updateSeq.value}`,
			"rating" : `${upadateRating.value}`,
			"title" : `${updateTitle.value}`,
			"content" : `${updateContent.value}`
		},
		success : function(data) {
			if (data === "servsuccess") {
				location.href = getContextPath() + "/reviewfoward";
			} else if (data === "servfail") {
				alert("글 수정에 실패하였습니다.")
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Ajax Fail")
			alert(jqXHR.status);
			alert(jqXHR.statusText);
			alert(jqXHR.responseText);
			alert(jqXHR.readyState);
		}
	})
}

function ratingCheck() {
	if (upadateRating.value.trim() !== "") {
		if (upadateRating.value.trim() > 5) {
			alert("평점은 5점보다 클수 없습니다.")
		} else if (upadateRating.value.trim() < 0) {
			alert("평점은 0점보다 작을 수 없습니다.");
		} else {
			updateCheck()
		}
	} else {
		alert("평점을 입력해주세요.")
	}
}

function updateCheck() {
	if (updateTitle.value.trim() !== "") {
		if (updateContent.value.trim() !== "") {
			updateAjax();
		} else {
			alert("내용을 입력해주세요.")
		}
	} else {
		alert("제목을 입력해주세요.")
	}
}


function deleteCheck() {
	alert("리뷰가삭제됩니다.")
	location.href = getContextPath() + "/reviewdelete?seq=" + updateSeq.value;
}

function init() {
	updateBtn.addEventListener("click", ratingCheck);

	deleteBtn.addEventListener("click", deleteCheck);
}

init();