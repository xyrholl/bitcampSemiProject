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
<title>My Page Update</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>

				<h1>MyPage Update</h1>

				<form action="mypageupdate" method="post">
					<table class="table table-bordered"
						style="background-color: rgba(255, 255, 255, 0.77)">
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
								value="<%=dto.getPwd()%>" required="required"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="pwdCheck"
								value="<%=dto.getPwd()%>"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" name="phoneNum"
								value="<%=dto.getPhoneNum()%>" required="required"></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="text" name="email"
								value="<%=dto.getEmail()%>" required="required"></td>
						</tr>
					</table>
					<div align="center" style="float: left; width: 80%">
						<input type="hidden" id="loginId" name="loginId"
							value="<%=loginId%>"> <input type="hidden" name="command"
							value="mypageupdateAf">
						<button type="submit">수정하기</button>
						<button type="button"
							onclick="location.href='<%=request.getContextPath()%>/mypagefoward'">취소</button>
					</div>
					<button id="getout" type="button">회원 탈퇴</button>

				</form>



			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/mypageupdate.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>