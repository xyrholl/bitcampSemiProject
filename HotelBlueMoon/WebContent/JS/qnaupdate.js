
const qnaListBtn = document.querySelector("#qnaListBtn");


function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

function qnaList(){
	alert("목록으로");
	location.href=getContextPath()+"/qnafoward";
}

function init(){
	
	qnaListBtn.addEventListener('click', qnaList);
	
}


init();
