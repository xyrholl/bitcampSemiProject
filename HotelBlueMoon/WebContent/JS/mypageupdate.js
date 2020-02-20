const updateSubmitBtn = document.querySelector(".submit-btn");

const updatePwd = document.querySelector(".updatePwd");
const updatePwds = document.querySelector(".updatePwds");
const updatePhoneNum = document.querySelector(".updatePhoneNum");
const updateEmailsub = document.querySelector(".updateEmails");
const from = document.querySelector(".frm");

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
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
	var checkPhone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4,4}$/;
	if (!checkPhone.test(str)) {
		return false;
	} else {
		return true;
	}
}

$(function() {
	$("#getout").click(
			function() {

				var checkdel = confirm("정말로 탈퇴하시겠습니까?");

				if (checkdel == true) {

					var loginId = $("#loginId").val();
					location.href = getContextPath() + "/memberdelete?loginId="
							+ loginId;
				}
			});
});

function formCheck() {
	if (CheckIdPwd(updatePwd.value)) {
		if (CheckIdPwd(updatePwds.value)) {
			if (PhoneNumberCheck(updatePhoneNum.value)) {
				if (CheckEmail(updateEmailsub.value)) {
					from.submit();
				} else {
					alert("이메일 형식이 맞지 않습니다.")
				}
			} else {
				alert("전화번호 000-0000-0000 형식에 맞지 않습니다.")
			}
		} else {
			alert("비밀번호 확인에 형식이 맞지 않습니다.")
		}
	} else {
		alert("비밀번호 형식이 맞지 않습니다.")
	}
}

function init() {
	updateSubmitBtn.addEventListener("click", formCheck);
}

init();