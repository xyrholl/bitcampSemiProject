<%@page import="dto.QnADTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	QnADTO dto = (QnADTO) request.getAttribute("dto");
	String loginId = (String) session.getAttribute("loginId");

	request.setAttribute("dto", dto);
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

				<h1>QnA detail</h1>
				<form action="qnaupdate" method="post">
					<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
					<input type="hidden" name="command" value="QnAUpdate">

					<div class="detailbox">
						<table class="table table-sm">
							<thead>
								<tr>
									<th scope="col" colspan="2">detail</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">작성자</th>
									<td><%=dto.getMemberId()%></td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td><%=dto.getTitle()%></td>
								</tr>
								<tr>
									<th scope="row">조회수</th>
									<td><%=dto.getReadcount()%></td>
								</tr>
								<tr>
									<th scope="row">작성일</th>
									<td><%=dto.getWriteDate()%></td>
								</tr>
								<tr>
									<th scope="row">내용</th>
									<td></td>
								</tr>
							</tbody>
						</table>

						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3" readonly="readonly" style="background-color: #fff"><%=dto.getContent()%></textarea>
					</div>


					<br>
					<div class="buttonbox" align="center">
						
						<button class="Idcheck" type="submit" id="qnaUpdateBtn" disabled="disabled">수정하기</button>
						<button class="Idcheck" type="button" id="qnaDeleteBtn" value="<%=dto.getSeq()%>" disabled="disabled">삭제하기</button>
						<input type="hidden" id="loginId" value="<%=loginId %>">
						<input type="hidden" id="dtoId" value="<%=dto.getMemberId() %>">
						
						<button type="button" id="qnaListBtn">목록보기</button>
						<button type="button" id="qnaCommentBtn" value="<%=dto.getSeq()%>">답글달기</button>

					</div>
				</form>

			</article>

		</main>
		<footer>Footer</footer>
	</div>

	<script src="<%=request.getContextPath()%>/JS/qnadetail.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>