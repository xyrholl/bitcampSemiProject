<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	/* req.setAttribute("checkin", checkin);
	req.setAttribute("checkout", checkout);
	req.setAttribute("current_guest", current_guest);
	req.setAttribute("hotelseq", hotelSeq); */
	String loginId = (String) session.getAttribute("loginId");
	String shotelSeq = request.getParameter("hotelseq");
	int hotelseq = Integer.parseInt(shotelSeq);
	String checkin = request.getParameter("checkin");
	String checkout = request.getParameter("checkout");

	String sguest = request.getParameter("geust");
	int guest = Integer.parseInt(sguest);
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
		<header>
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right; padding-top: 15px;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">ID</span>
					</div>
					<input type="text" class="form-control input-id" placeholder="ID"
						aria-label="Username" aria-describedby="basic-addon1">
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">PASSWORD</span>
					</div>
					<input type="password" class="form-control input-pwd"
						placeholder="PASSWORD" aria-label="Username"
						aria-describedby="basic-addon1">
					<!-- 	String shotelSeq = request.getParameter("hotelseq");
						int hotelseq = Integer.parseInt(shotelSeq);
						String checkin = request.getParameter("checkin");
						String checkout= request.getParameter("checkout");
						
						String sguest = request.getParameter("geust");
						int guest = Integer.parseInt(sguest); -->
					<input type="hidden" id="hotelseq" value="<%=hotelseq%>">
					<input type="hidden" id="checkin" value="<%=checkin%>"> <input
						type="hidden" id="checkout" value="<%=checkout%>"> <input
						type="hidden" id="guest" value="<%=guest%>">
				</div>

				<div>
					<div>
						<span class="checkbox"></span>
					</div>
				</div>

				<div class="btn-group" role="group" aria-label="Basic example"
					style="float: right; padding-top: 15px; position: fixed; margin-left: 62rem;">
					<button type="button" class="btn btn-info regiBtn">회원가입</button>
					<button type="button" class="btn btn-primary loginBtn">로그인</button>
				</div>

			</article>

		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script src="<%=request.getContextPath()%>/JS/resvLogin.js"></script>


</body>
</html>