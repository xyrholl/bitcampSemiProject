<%@page import="dto.QnADTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String loginId = (String) session.getAttribute("loginId");
	QnADTO dto = (QnADTO) request.getAttribute("dto");
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
<style type="text/css">
.qnaup li {
	list-style: none
}

.qnaup li:first-child {
	float: left;
}
</style>
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

			<article style="box-shadow: 5px 5px 5px 5px rgb(0, 0, 0, 0.2);">
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>Q&A</h1>
				</div>
				<input type="text" disabled=""
					style="float: left; width: 70%; height: 1px; margin-top: 25px;">
				<div class="QNAcontent" style="width: 90%;">
					<br>
					<form action="qnaupdate" method="get">
						<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
						<input type="hidden" name="command" value="QnAUpdateAf">
						<table class="table table-active">
							<thead>
								<tr>
									<th scope="row" style="width: 10%;">제목</th>
									<th style="widows: 60%;"><input type="text" name="title"
										value="<%=dto.getTitle()%>" style="width: 28rem;"></th>
									<th scope="row" style="width: 10%;">조회수</th>
									<th style="width: 20%;"><%=dto.getReadcount()%></th>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<th><%=dto.getMemberId()%></th>
									<th scope="row">작성일</th>
									<th><%=dto.getWriteDate()%></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="input-group">
							<div class="input-group-prepend" style="height: 18rem;">
								<span class="input-group-text">내용</span>
							</div>
							<textarea class="form-control" aria-label="With textarea"
								id="exampleFormControlTextarea1" style="background-color: #fff"
								name=content><%=dto.getContent()%></textarea>
						</div>
						<br>
						<button type="submit" id="qnaUpdateBtn"
							class="btn btn-outline-warning"
							style="float: right; border-radius: 5px;">수정하기</button>
						<button type="button"
							onclick="location.href='<%=request.getContextPath()%>/qnafoward'"
							style="float: left; border-radius: 5px;"
							class="btn btn-outline-info">목록으로</button>
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