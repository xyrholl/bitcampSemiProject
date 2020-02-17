const inputId = document.querySelector(".input-id");
const inputPwd = document.querySelector(".input-pwd");
const loginBtn = document.querySelector(".loginBtn");
const regiBtn = document.querySelector(".regiBtn");

//hotelSeq=4&checkin=2020-03-06&checkout=2020-03-07&guest=1
//<input type="hidden" class="hotelseq" value="<%=hotelseq %>">
//<input type="hidden" class="checkin" value="<%=checkin %>">
//<input type="hidden" class="checkout" value="<%=checkout %>">
//<input type="hidden" class="guest" value="<%=guest %>">

const hotelseq = document.getElementById("hotelseq").value;
const checkin = document.getElementById("checkin").value;
const checkout = document.getElementById("checkout").value;
const guest = document.getElementById("guest").value;

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
			"pw" : `${inputPwd.value}`,
			
		},
		success : function(data) {
			if (data === "success") {
				alert("login success")
				$.ajax({
					method: "post", 
					url: `${getContextPath()}` +"/JSP/resvLogin.jsp",
					data: {
						 "id" : `${inputId.value}`,
						 "hotelseq" : `${hotelseq}`, 
						  "checkin" : `${checkin}`, 
						  "checkout" : `${checkout}`, 
						  "guest" : `${guest}`},
						  success: function (data){
							  location.href = getContextPath() + "/resvInfo?id="
								+ `${inputId.value}`+"&hotelseq="+`${hotelseq}`
								+"&checkin="+`{checkin}`
								+"&checkout="+`${checkout}`
								+"&guest="+`{guest}`;
						  }, 
						  error: function(){
							  alert("if문 끝나고 실패")
							  alert(`${checkout}`)
						  }
				})
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