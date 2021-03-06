<%@page import="dto.BM_MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String s_loginId = (String) session.getAttribute("loginId");

	BM_MemberDTO dto = (BM_MemberDTO) request.getAttribute("dto");
	String loginId = dto.getId();

	System.out.println("s_loginId: " + s_loginId + " / loginId: " + loginId);
	if (s_loginId == null || s_loginId.equals("") || !s_loginId.equals(loginId)) {
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
				style="float: right; padding-top: 15px; position: fixed; margin-left: 62rem;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article style="box-shadow: 5px 5px 5px 5px rgb(0,0,0,0.2);">
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>MyPage</h1>
				</div>
				<input type="text" disabled=""
					style="float: left; width: 70%; height: 1px; margin-top: 25px;">
				<div class="mypage-content" align="center" style="width: 90%">
					<form action="mypageupdate" method="post" class="frm">
						<table class="table table-active">
							<col width="100">
							<col width="200">
							<tr>
								<th>아이디</th>
								<td><%=dto.getId()%></td>
							</tr>
							<tr>
								<th>이름</th>
								<td><%=dto.getName()%></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="pwd"
									value="<%=dto.getPwd()%>" required="required" class="updatePwd"></td>
							</tr>
							<tr>
								<th>비밀번호 확인</th>
								<td><input type="password" name="pwdCheck"
									value="<%=dto.getPwd()%>" class="updatePwds"></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="phoneNum"
									value="<%=dto.getPhoneNum()%>" required="required"
									class="updatePhoneNum"></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input type="text" name="email"
									value="<%=dto.getEmail()%>" required="required"
									class="updateEmails"></td>
							</tr>
						</table>
						<div>
							<input type="hidden" id="loginId" name="loginId"
								value="<%=loginId%>"> <input type="hidden"
								name="command" value="mypageupdateAf">
							<button id="getout" type="button" class="btn btn-outline-danger"
								style="float: right; border-radius: 5px;">회원 탈퇴</button>
							<button type="button" class="btn btn-outline-warning submit-btn"
								style="float: right; border-radius: 5px;">수정하기</button>
							<button type="button"
								onclick="location.href='<%=request.getContextPath()%>/mypagefoward'"
								class="btn btn-outline-info"
								style="float: left; border-radius: 5px;">마이페이지로 돌아가기</button>
						</div>

					</form>
				</div>
				<br>
			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/mypageupdate.js"></script>
</body>
</html>