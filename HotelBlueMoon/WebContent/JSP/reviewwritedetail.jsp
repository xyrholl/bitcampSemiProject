<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.ResvDTO"%>
<%@page import="dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	ResvDTO resvDto = (ResvDTO) request.getAttribute("resvDTO");
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
				style="background-color: rgba(255, 255, 255); overflow-y: scroll; height: 500px;">
				<form action="insertreview" method="post"
					enctype="multipart/form-data" class="js-file-form">
					<input type="hidden" value="<%=resvDto.getSeq()%>"
						class="js-input-review-resvseq" name="resvSeq"> <input
						type="hidden" value="<%=resvDto.getHotelSeq()%>"
						class="js-input-review-hotelseq" name="hotelSeq"> <input
						type="hidden" value="<%=resvDto.getRoomSeq()%>"
						class="js-input-review-roomseq" name="roomSeq"> <input
						type="hidden" class="js-input-id" value="<%=loginId%>"
						name="loginId"> <input type="hidden" class="js-nowTime"
						value="" name="nowTime">
					<table class="table table-bordered">
						<tr>
							<th scope="col">리뷰평점</th>
							<td scope="col"><input type="text" class="js-input-rating"
								name="rating"></td>
							<th scope="col">작성자</th>
							<td scope="col"><%=loginId%></td>
							<th scope="col">이용인원</th>
							<td scope="col"><%=resvDto.getCurrent_guest()%></td>
						</tr>
						<tr>
							<th scope="col">호텔평점</th>
							<td scope="col"><%=resvDto.getHotelRating()%></td>
							<th scope="col">호텔</th>
							<td scope="col"><%=resvDto.getHotelName()%></td>
							<td colspan="2"><input type="file" name="imageFile">
							</td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td colspan="5"><input type="text" class="js-input-title"
								name="title"></td>
						</tr>
						<tr>
							<th scope="row" colspan="6"><div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">내용</span>
									</div>
									<textarea class="form-control js-input-content"
										aria-label="With textarea" style="background-color: #fff"
										name="content"></textarea>
								</div></th>
						</tr>
					</table>
					<button type="button" class="btn btn-outline-info js-returnList">돌아가기</button>
					<button type="button" class="btn btn-outline-info js-insert-review"
						style="float: right;">리뷰작성완료</button>
				</form>
			</article>

		</main>
		<footer>Footer</footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/reviewwritedetail.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/reviewsubmit.js"></script>
</body>
</html>