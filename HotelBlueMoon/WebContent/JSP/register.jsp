<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<nav class="hotelcol-1">
		<ul class="list-group">
			<li class="list-group-item js-main-foward">메인</li>
			<li class="list-group-item js-search-foward">검색</li>
			<li class="list-group-item js-qna-foward">Q&A</li>
			<li class="list-group-item js-review-foward">Review</li>
			<li class="list-group-item js-mypage-foward">MyPage</li>
		</ul>
	</nav>
	<div class="hotelcol-2">
		<header>
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right; padding-top: 15px;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-success js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">
			<article style="background-color: rgb(0, 0, 0, 0);">
				<div style="display: table; width: 40%; margin: auto;">
					<form action="../regiservlet" class="frm" method="post">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">ID</span>
							</div>
							<input name="id" type="text" class="form-control input-id"
								placeholder="4자리  이상 (영문 대소문자, 숫자)" aria-label="Username"
								aria-describedby="basic-addon1">
							<button type="button" class="btn btn-secondary idcheck">ID확인</button>
						</div>

						<div>
							<div class="alert alert-light checkbox" role="alert" style="visibility: hidden;"></div>
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">PASSWORD</span>
							</div>
							<input name="pwd" type="password" class="form-control input-pwd"
								placeholder="4자리  이상 (영문 대소문자, 숫자)" aria-label="Username"
								aria-describedby="basic-addon1">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">NAME</span>
							</div>
							<input name="name" type="text" class="form-control input-name"
								placeholder="이름 입력하세요." aria-label="Username"
								aria-describedby="basic-addon1" required="required">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">PHONE</span>
							</div>
							<input name="phone" type="text" class="form-control input-phone"
								placeholder="전화번호를 입력하세요." aria-label="Username"
								aria-describedby="basic-addon1">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">e-mail</span>
							</div>
							<input name="email" type="email" class="form-control input-email"
								placeholder="이메일 형식에 맞춰 입력해주세요" aria-label="Username"
								aria-describedby="emailHelp">
						</div>

						<div>
							<div style="margin-left: 190px;">
								<button type="button" class="btn btn-info submitBtn">회원가입</button>
							</div>
						</div>
					</form>
				</div>
			</article>
		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script src="<%=request.getContextPath()%>/JS/register.js"></script>

</body>
</html>