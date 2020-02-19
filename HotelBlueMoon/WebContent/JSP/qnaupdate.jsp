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
.qnaup li{ list-style: none}
.qnaup li:first-child{
float: left;

}


</style>
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

				<form action="qnaupdate" method="get">
					<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
					<input type="hidden" name="command" value="QnAUpdateAf">
					<div class="qnaupdatebox" align="center">
						<ul class="qnaup">
							<li >작성자</li>
							<li><%=dto.getMemberId()%></li>
						</ul>

						<ul class="qnaup">
							<li>제목</li>
							<li><input type="text" name="title"
								value="<%=dto.getTitle()%>"></li>

						</ul>
						<ul class="qnaup">
							<li>작성일</li>
							<li><%=dto.getWriteDate()%></li>
						</ul>
						<ul class="qnaup">
							<li>내용</li>
							<li><textarea rows="20" cols="50" name=content><%=dto.getContent()%></textarea>
							</li>
						</ul>
					</div><br>
					<div class="buttonbox" align="center">
						<button type="submit" id="qnaUpdateBtn">수정하기</button>
						<button type="button" onclick="location.href='<%=request.getContextPath()%>/qnafoward'">목록으로</button>
					</div>
				</form>

			</article>

		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>