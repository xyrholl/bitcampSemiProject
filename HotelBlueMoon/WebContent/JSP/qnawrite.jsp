<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if(loginId == null || loginId.equals("")){
		out.println("로그인이 필요한 페이지입니다");
	response.sendRedirect(request.getContextPath()+"/fowardlogin");
	}

 	int memberseq = Integer.parseInt(request.getParameter("memberseq"));

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
			
						<h1>Q n A</h1>
			<form action="<%=request.getContextPath()%>/qnawrite" method="get">
			<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">제목</span>
						</div>
						<input name="title" type="text" class="form-control input-id"
							placeholder="제목을 입력하세요." aria-label="Username"
							aria-describedby="basic-addon1" required="required">
			</div>
			<div class="form-group">
			    <label for="exampleFormControlTextarea1">질문 사항</label>
			    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content"></textarea>
			</div>
			<input type="hidden" name="command" value="qnawriteAf">
			<input type="hidden" name="memberseq" value="<%=memberseq %>">
			<button type="submit" class="btn btn-primary">글 쓰기</button>
			<button type="button" class="btn btn-info" onclick="location.href='<%=request.getContextPath()%>/qnafoward'">목록으로</button>
			</form>
			
			</article>

		</main>
		<footer>Footer</footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>