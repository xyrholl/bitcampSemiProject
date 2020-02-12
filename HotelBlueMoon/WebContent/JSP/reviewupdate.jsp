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

			<article
				style="background-color: rgba(170, 166, 157, 0.33); overflow-y: scroll; height: 500px;">

				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">리뷰평점</th>
							<td scope="col"><input type="text"
								class="form-control js-rating" value="<%=dto.getRating()%>">
							</td>
							<th scope="col">작성자</th>
							<td scope="col"><%=dto.getMemberId()%></td>
							<th scope="col">이용인원</th>
							<td scope="col"><%=dto.getCurrent_guest()%>명</td>
						</tr>
						<tr>
							<th scope="col">호텔평점</th>
							<td scope="col">
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
							</td>
							<th scope="col">호텔</th>
							<td scope="col"><%=dto.getHotelName()%></td>
							<th scope="col">방이름</th>
							<td scope="col"><%=dto.getRoomName()%></td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td colspan="3"><input type="text"
								class="form-control js-title" value="<%=dto.getTitle()%>"></td>
							<th scope="row">작성시간</th>
							<td><%=dto.getWriteDate()%></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row" colspan="6"><div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">내용</span>
									</div>
									<textarea class="form-control js-content"
										aria-label="With textarea" style="background-color: #fff"><%=dto.getContent()%></textarea>
								</div></th>
						</tr>
					</tbody>
				</table>

				<button type="button" class="btn btn-outline-info js-returnList">글로
					돌아가기</button>
				<%
					if (dto.getMemberId().equals(loginId)) {
				%>
				<input type="hidden" value="<%=dto.getSeq()%>" class="js-update-seq">
				<button type="button"
					class="btn btn-outline-success js-update-review-success"
					style="float: right;">리뷰수정완료</button>
				<%
					}
				%>
			</article>

		</main>
		<footer>Footer</footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/reviewupdate.js"></script>
</body>
</html>