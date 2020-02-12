const inputId = document.querySelector(".input-id");
const inputPwd = document.querySelector(".input-pwd");
const loginBtn = document.querySelector(".loginBtn");
const regiBtn = document.querySelector(".regiBtn");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function trimCheckLogin() {
	if (inputId.value === "") {
		alert("아이디를 입력하세요.")
	} else {
		if (inputPwd.value === "") {
			alert("비밀번호를 입력하세요.")
		} else {
			login();
		}
	}

}

function login() {
	$.ajax({
		method : "post",
		url : `${getContextPath()}` + "/login",
		data : {
			"id" : `${inputId.value}`,
			"pw" : `${inputPwd.value}`
		},
		success : function(data) {
			if (data === "success") {
				alert("login success")
				location.href = getContextPath() + "/mainfoward?id="
						+ `${inputId.value}`;
			} else if (data === "id false") {
				alert("아이디가 맞지 않습니다.")
			} else if (data === "pw false") {
				alert("비밀번호가 맞지 않습니다.")
			}
		},
		error : function() {
			alert("실패")
		}
	})
}

function regiFoward() {
	location.href = getContextPath() + "/regifoward";
}

function init() {
	loginBtn.addEventListener("click", trimCheckLogin);
	regiBtn.addEventListener("click", regiFoward);
}

init();