<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if (loginId == null || loginId.equals("")) {
		response.sendRedirect(request.getContextPath() + "/fowardlogin");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔 블루문</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<nav class="hotelcol-1">
		<ul class="list-group" style="cursor: pointer;">
			<li class="list-group-item js-main-foward">메인</li>
			<li class="list-group-item js-search-foward">검색</li>
			<li class="list-group-item js-qna-foward">Q&A</li>
			<li class="list-group-item js-review-foward">Review</li>
			<li class="list-group-item js-mypage-foward">MyPage</li>
		</ul>
	</nav>
	<div class="hotelcol-2">
		<header style="height: 2vh;">
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right; padding-top: 15px; position: fixed; margin-left: 80rem;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>MyPage</h1>
				</div>
				<input type="text" disabled=""
					style="float: left; width: 70%; height: 1px; margin-top: 25px;">
				<br>

				<div style="width: 40%">
					<form action="../mypageupdate" method="post">
						<table class="table table-active" style="margin-top: 200px;">
							<tr>
								<th>아이디</th>
								<td><%=loginId%></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="pwd"></td>
							</tr>
						</table>
						<input type="hidden" name="command" value="mypagePwdChecked">
						<input type="hidden" name="loginId" value="<%=loginId%>">
						<button type="button"
							onclick="location.href='<%=request.getContextPath()%>/mypagefoward'"
							class="btn btn-outline-info" style="border-radius: 5px;">돌아가기</button>
						<button type="submit" class="btn btn-outline-success submit-btn"
							style="border-radius: 5px;">확인</button>

					</form>
				</div>
			</article>

		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>