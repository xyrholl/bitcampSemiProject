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

			<article style="background-color: rgba(170, 166, 157, 0.33); overflow-y:scroll; height: 500px;">

				<form action="qnaupdate" method="get">
					<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
					<input type="hidden" name="command" value="QnAUpdateAf">
					<div class="qnaupdatebox">
						<ul class="id">
							<li>작성자</li>
							<li><%=dto.getMemberId()%></li>
						</ul>

						<ul class="title">
							<li>제목</li>
							<li><input type="text" name="title"
								value="<%=dto.getTitle()%>"></li>

						</ul>
						<ul class="time">
							<li>작성일</li>
							<li><%=dto.getWriteDate()%></li>
						</ul>
						<ul class="content">
							<li>내용</li>
							<li><textarea rows="20" cols="50" name=content><%=dto.getContent()%></textarea>
							</li>
						</ul>
					</div>
					<div class="buttonbox">
						<button type="submit" id="qnaUpdateBtn">수정하기</button>
						<button type="button" id="qnaListBtn">목록으로</button>
					</div>
				</form>

			</article>

		</main>
		<footer>Footer</footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>