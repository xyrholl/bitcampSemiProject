<%@page import="dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	ReviewDTO dto = (ReviewDTO) request.getAttribute("reviewDTO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
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

			<article>
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>Review</h1>
				</div>
				<input type="text" disabled=""
					style="float: left; width: 70%; height: 1px; margin-top: 25px;">
				<div class="Reviewcontent" style="width: 90%;">
					<br>
					<form action="insertreview" method="post"
						enctype="multipart/form-data" class="js-file-form">

						<table class="table table-active">
							<thead>
								<tr>
									<th scope="col">리뷰평점</th>
									<th scope="col"><input type="text"
										class="form-control js-rating" value="<%=dto.getRating()%>"
										name="rating"></th>
									<th scope="col">작성자</th>
									<th scope="col"><%=dto.getMemberId()%></th>
									<th scope="col">이용인원</th>
									<th scope="col"><%=dto.getCurrent_guest()%>명</th>
								</tr>
								<tr>
									<th scope="col">호텔평점</th>
									<th scope="col">
										<%
											if (dto.getHotelRating() >= 4) {
										%>
										<button class="btn btn-primary"><%=dto.getHotelRating()%></button>
										<%
											} else if (dto.getHotelRating() < 4 && dto.getHotelRating() >= 3) {
										%>
										<button class="btn btn-info"><%=dto.getHotelRating()%></button>
										<%
											} else {
										%>
										<button class="btn btn-secondary"><%=dto.getHotelRating()%></button>
										<%
											}
										%>
									</th>
									<th scope="col">호텔</th>
									<th scope="col"><%=dto.getHotelName()%></th>
									<th scope="col">방이름</th>
									<th scope="col"><%=dto.getRoomName()%></th>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<th colspan="2"><input type="text"
										class="form-control js-title" value="<%=dto.getTitle()%>"
										name="title"></th>
									<th scope="row">작성시간</th>
									<th><%=dto.getWriteDate()%></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">내용</span>
							</div>
							<textarea class="form-control js-content"
								aria-label="With textarea"
								style="background-color: #fff; height: 18rem;" name="content"><%=dto.getContent()%></textarea>
						</div>
						<br>
						<button type="button" class="btn btn-outline-info"
							style="border-radius: 5px; float: left;"
							onclick="location.href='<%=request.getContextPath()%>/reviewfoward'">목록으로</button>
						<%
							if (dto.getMemberId().equals(loginId)) {
						%>
						<input type="hidden" value="<%=dto.getSeq()%>"
							class="js-update-seq">
						<button type="button"
							class="btn btn-outline-warning js-update-review-success"
							style="float: right; border-radius: 5px;">수정완료</button>
						<button type="button"
							class="btn btn-outline-danger js-delete-review"
							style="float: right; border-radius: 5px;">삭제하기</button>
						<%
							}
						%>
					</form>
				</div>
				<br> <br> <br>
			</article>

		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/reviewupdate.js"></script>
</body>
</html>