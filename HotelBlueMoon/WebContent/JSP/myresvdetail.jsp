<%@page import="dto.ResvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if(loginId == null || loginId.equals("")){
	response.sendRedirect(request.getContextPath()+"/fowardlogin");
	}
	String hotelName = (String) request.getAttribute("hotelName");
	String roomName = (String) request.getAttribute("roomName");
	String memName = (String) request.getAttribute("memName");

	ResvDTO dto = (ResvDTO)request.getAttribute("dto");
		
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
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
			Header
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article style="background-color: white">
			
			<div class="detailbox">
						<table class="table table-sm">
							<thead>
								<tr>
									<th scope="col" colspan="2">Resv Detail</th>
								</tr>
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
					<button id="cancelBtn" type="button">취소하기</button>
					<button id="listBtn" type="button">목록으로</button>
					<input type="hidden" id="cancel_seq" value="<%=dto.getSeq()%>">
			
			</article>

		</main>
		<footer>Footer</footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/myresvcancel.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>