<%@page import="dto.BM_MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if (loginId == null || loginId.equals("")) {
		response.sendRedirect(request.getContextPath() + "/fowardlogin");
	}

	BM_MemberDTO dto = (BM_MemberDTO) request.getAttribute("dto");

	int reviewCount = (Integer) request.getAttribute("reviewCount");
	int qnaCount = (Integer) request.getAttribute("qnaCount");
	int resvCount = (Integer) request.getAttribute("resvCount");

	System.out.println(reviewCount + " / " + qnaCount + " / " + dto.getId());
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
				<br>
				<input type="text" disabled=""
					style="float: left; width: 70%; height: 1px; margin-top: 25px;">
				<div class="mypage-content" align="center">
					<form action="mypageupdate" method="get">
						<div class="mytable" style="width: 62%;">
							<table class="table table-active">
								<col width="100">
								<col width="200">
								<tr>
									<th>아이디</th>
									<td id="myid"><%=dto.getId()%></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><%=dto.getName()%></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><%=dto.getPhoneNum()%></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><%=dto.getEmail()%></td>
								</tr>
							</table>
							<button type="submit" class="btn btn-outline-primary"
								style="margin-bottom: 15px;">회원정보 수정하기</button>
						</div>
						<div class="clfix"></div>
						<div class="mylist" style="width: 62%">

							<ul class="list-group">
								<li id="reviewCount"
									class="list-group-item d-flex justify-content-between align-items-center list-group-item list-group-item-action">
									내가 쓴 리뷰 보기 <span class="badge badge-pill badge-primary"><%=reviewCount%></span>
								</li>


								<li id="qnaCount"
									class="list-group-item d-flex justify-content-between align-items-center list-group-item list-group-item-action">
									내가 쓴 QnA 보기 <span class="badge badge-pill badge-primary"><%=qnaCount%></span>
								</li>
								<li id="resvCount"
									class="list-group-item d-flex justify-content-between align-items-center list-group-item list-group-item-action">
									예약 내역 보기 및 리뷰 쓰기<span class="badge badge-pill badge-primary"><%=resvCount%></span>
								</li>
							</ul>
							<input type="hidden" value="" class="js-currDateTmp"> <input
								type="hidden" name="command" value="mypageUpdate"> <input
								type="hidden" name="id" value="<%=dto.getId()%>">
						</div>
					</form>
				</div>
				<br><br>

			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/mypage.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>