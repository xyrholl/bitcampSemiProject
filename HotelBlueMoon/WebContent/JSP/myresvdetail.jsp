<%@page import="dto.ResvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if (loginId == null || loginId.equals("")) {
		response.sendRedirect(request.getContextPath() + "/fowardlogin");
	}
	String hotelName = (String) request.getAttribute("hotelName");
	String roomName = (String) request.getAttribute("roomName");
	String memName = (String) request.getAttribute("memName");

	ResvDTO dto = (ResvDTO) request.getAttribute("dto");
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
				style="float: right; padding-top: 15px; position: fixed; margin-left: 62rem;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>
				<div class="Reviewcontent" style="width: 90%;">
					<br>
					<div class="detailbox">
						<table class="table table-active">
							<thead>
							</thead>
							<tbody>
								<tr>
									<th scope="row">예약자</th>
									<td><%=memName%></td>
								</tr>
								<tr>
									<th scope="row">호텔</th>
									<td><%=hotelName%></td>
								</tr>
								<tr>
									<th scope="row">방</th>
									<td><%=roomName%></td>
								</tr>
								<tr>
									<th scope="row">예약일</th>
									<td><%=dto.getResvDate()%></td>
								</tr>
								<tr>
									<th scope="row">체크인</th>
									<td><%=dto.getCheckIn()%></td>
								</tr>
								<tr>
									<th scope="row">체크아웃</th>
									<td><%=dto.getCheckOut()%></td>
								</tr>
							</tbody>
						</table>
					</div>
					<button id="payBtn" type="button"
						style="float: right; border-radius: 5px;"
						class="btn btn-outline-success">결제하기</button>
					<button id="cancelBtn" type="button"
						style="float: right; border-radius: 5px;"
						class="btn btn-outline-danger">취소하기</button>
					<button id="listBtn" type="button"
						style="float: left; border-radius: 5px;"
						class="btn btn-outline-info">목록으로</button>
					<input type="hidden" id="detail_seq" value="<%=dto.getSeq()%>">
				</div>

			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/myresvcancel.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>