const regiBtn = document.querySelector(".submitBtn");

const idcheck = document.querySelector(".idcheck");

const inputId = document.querySelector(".input-id");
const inputPwd = document.querySelector(".input-pwd");
const inputName = document.querySelector(".input-name");
const inputPhone = document.querySelector(".input-phone");
const inputEmail = document.querySelector(".input-email");

const fromSubmit = document.querySelector(".frm");

const checkBbox = document.querySelector(".checkbox");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
};

function checkBtn() {
	if (inputId.value === "") {
		alert("아이디를 입력해주세요.")
	} else if (!CheckIdPwd(inputId.value)) {
		checkBbox.innerHTML = "아이디 형식이 맞지 않습니다.";
		checkBbox.style.visibility = 'visible';
		checkBbox.classList.remove("alert-danger");
		checkBbox.classList.remove("alert-success");
		checkBbox.classList.remove("alert-light");
		checkBbox.classList.add("alert-warning");
	} else {
		$.ajax({
			url : `${getContextPath()}` + "/idcheck",
			method : "post",
			data : {
				"id" : `${inputId.value}`
			},
			success : function(data) {
				if (data.trim() === 'yes') {
					checkBbox.innerHTML = "사용중인 아이디입니다.";
					checkBbox.style.visibility = 'visible';
					checkBbox.classList.remove("alert-warning");
					checkBbox.classList.remove("alert-success");
					checkBbox.classList.remove("alert-light");
					checkBbox.classList.add("alert-danger");
				} else if (data.trim() === 'no') {
					checkBbox.style.visibility = 'visible';
					checkBbox.innerHTML = "사용가능한 아이디 입니다.";
					checkBbox.classList.remove("alert-warning");
					checkBbox.classList.remove("alert-light");
					checkBbox.classList.remove("alert-danger");
					checkBbox.classList.add("alert-success");
				}
			},
			error : function() {
				alert("실패")
			}

		})
	}
}

function ajaxSubmit() {
	if (checkBbox.innerHTML === "") {
		alert("ID확인 버튼을 눌러주세요.")
	} else if (checkBbox.innerHTML === "아이디 형식이 맞지 않습니다.") {
		alert("형식에 맞지 않는 아이디 입니다. 다시 확인해 주세요.")
	} else if (checkBbox.innerHTML === "사용중인 아이디입니다.") {
		alert("사용중인 아이디 입니다. 다시 확인해주세요.")
	} else if (checkBbox.innerHTML === "사용가능한 아이디 입니다.") {
		$.ajax({
			url : `${getContextPath()}` + "/regiservlet",
			method : "post",
			data : {
				"id" : `${inputId.value}`,
				"pw" : `${inputPwd.value}`,
				"name" : `${inputName.value}`,
				"phone" : `${inputPhone.value}`,
				"email" : `${inputEmail.value}`
			},
			success : function(data) {
				location.href = `${getContextPath()}` + "/fowardlogin";
			},
			error : function() {
				alert("실패")
			}
		})
	}
}

function CheckEmail(str) {
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	if (!reg_email.test(str)) {
		return false;
	} else {
		return true;
	}
}

function CheckIdPwd(str) {
	var checkIdPw = /^[a-zA-Z0-9]{4,12}$/;
	if (!checkIdPw.test(str)) {
		return false;
	} else {
		return true;
	}
}

function PhoneNumberCheck(str) {
	var checkPhone = /^[0-9]{9,12}$/;
	if (!checkPhone.test(str)) {
		return false;
	} else {
		return true;
	}
}

function subBtn() {
	if (inputId.value !== "") {
		if (CheckIdPwd(inputId.value)) {
			if (inputPwd.value !== "") {
				if (CheckIdPwd(inputPwd.value)) {
					if (inputName.value !== "") {
						if (inputPhone.value !== "") {
							if (PhoneNumberCheck(inputPhone.value)) {
								if (inputEmail.value !== "") {
									if (CheckEmail(inputEmail.value)) {
										ajaxSubmit()
									} else {
										alert("이메일 형식이 맞지 않습니다.")
									}
								} else {
									alert("이메일을 입력해주세요")
								}
							} else {
								alert("전화번호를 9자리에서 12자리 숫자로 입력해주세요.")
							}
						} else {
							alert("전화번호를 입력해주세요")
						}
					} else {
						alert("이름을 입력해주세요")
					}
				} else {
					alert("비밀번호 형식이 맞지않습니다.")
				}
			} else {
				alert("비밀번호를 입력해주세요")
			}
		} else {
			alert("아이디 형식이 맞지 않습니다.")
		}
	} else {
		alert("아이디를 입력해주세요")
	}
}

function init() {
	regiBtn.addEventListener("click", subBtn);
	idcheck.addEventListener("click", checkBtn);
}

init();