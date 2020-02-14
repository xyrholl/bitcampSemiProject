

const qnaListBtn = document.querySelector("#qnaListBtn");
const qnaDeleteBtn = document.querySelector("#qnaDeleteBtn");
const qnaCommentBtn = document.querySelector("#qnaCommentBtn");

$(document).ready(function() {
var loginId = $("#loginId").val();
var dtoId = $("#dtoId").val();
	
	if (loginId !== null && loginId !== "") {
		alert("loginId: "+loginId +" / dtoId: "+ dtoId);
		if ( dtoId.trim() !== loginId.trim() || loginId.trim() !== "admin" ){
				$(".Idcheck").attr("disabled", true);
		}
	}
});

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

function qnalist(){
	location.href= getContextPath()+"/qnafoward";
}

function qnadelete(){
	var seq = document.getElementById("qnaDeleteBtn").value;
	
	//alert("delete:" + seq);
	location.href= getContextPath()+"/qnadelete?seq="+seq;
}

function qnacomment() {
	var seq = document.getElementById("qnaCommentBtn").value;
	
	location.href = getContextPath() + "/qnacomment?seq=" + seq
			+ "&command=qnacomment";
}

function init(){
	qnaListBtn.addEventListener('click', qnalist);
	qnaDeleteBtn.addEventListener('click', qnadelete);
	qnaCommentBtn.addEventListener('click', qnacomment);
	
}

init();
