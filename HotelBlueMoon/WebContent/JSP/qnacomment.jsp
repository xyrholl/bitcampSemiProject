<%@page import="dto.QnADTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	QnADTO dto = (QnADTO) request.getAttribute("dto");
	String loginId = session.getAttribute("loginId") + "";
	if (loginId == null || loginId.equals("")) {
		response.sendRedirect(request.getContextPath() + "/fowardlogin");
	}
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

				<h1>QnA Comment</h1>


				<div class="detailbox">
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col" colspan="2">detail</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">제목</th>
								<td><%=dto.getTitle()%></td>
							</tr>

							<tr>
								<th scope="row" colspan="6">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">내용</span>
										</div>
										<textarea class="form-control" aria-label="With textarea"
											readonly="readonly" style="background-color: #fff"><%=dto.getContent()%></textarea>
									</div>
								</th>
							</tr>
						</tbody>
					</table>

				</div>

				<br> <br>

				<form action="qnacomment" method="get">
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col" colspan="2">comment</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">제목</th>
								<td><input type="text" name="title" required="required"></td>
							</tr>
							<tr>
								<th scope="row">작성자</th>
								<td><%=loginId%><input type="hidden" name="loginId"
									value="<%=loginId%>"></td>
							</tr>
							<tr>
								<th scope="row" colspan="6">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">내용</span>
										</div>
										<textarea class="form-control" name="content" aria-label="With textarea"
											 style="background-color: #fff"></textarea>
									</div>
								</th>
											 
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="command" value="qnacommentAf">
					<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
					<button type="submit">답글 달기</button>
					<button type="button" onclick="location.href='<%=request.getContextPath()%>/qnafoward'">목록으로</button>
				</form>

			</article>


		</main>
		<footer>Footer</footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>