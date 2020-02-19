<%@page import="dto.ResvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");

	ResvDTO resv = (ResvDTO) request.getAttribute("resv");
	
	System.out.println(resv.toString());
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

<style>
body {
	margin: 0 auto;
	padding: 0;
	background-color: #EFE8E2;
	font-family: tahoma;
}

.paid {
	display: none;
	width: 300px;
	margin: 0 auto;
	background-color: #fff;
	text-align: center;
	padding-top: 25px;
	border-radius: 0px 0px 10px 10px;
	padding-bottom: 25px;
	color: #00773D;
	line-height: 30px;
}

.paid p {
	background-image:
		url("http://dc455.4shared.com/download/VILITso0/tsid20130720-183900-9216b81f/check.png");
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 30px;
	width: 190px;
	margin: 0 auto;
}

.container {
	width: 500px;
	margin: 50px;
}

.tab {
	width: 500px;
	height: 10px;
	background-color: #2B2929;
	border-radius: 50px
}

.receipt {
	background-color: #FAFAF9;
	padding-top: 20px;
	width: 500px;
	height: 300px;
	border-radius: 5px 5px 50px 50px;
	-moz-box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
}

.paper {
	border-top: 1px dashed #ccc;
	width: 96%;
	margin: 0 auto;
}

.title {
	color: #00773D;
	margin-top: 20px;
	margin-left: 10px;
	font-weight: bold;
	float: left;
	font-size: 16px;
	line-height: 30px;
}

.date {
	color: #00773D;
	margin-top: 20px;
	margin-right: 10px;
	font-weight: lighter;
	float: right;
	font-size: 12px;
	line-height: 30px;
}

table {
	clear: both;
	width: 95%;
	margin: auto;
	color: #5B5B5B;
	font-size: 12px;
	padding-top: 10px;
	padding-bottom: 20px;
	border-bottom: 1px dashed #ccc;
}

.right {
	text-align: right;

}

.center {
	text-align: center;
	padding-top: 20px;
}

.kakao {
	background-color: #ffd400;
	outlie: none;
	border: 1px solid #ccc;
	padding: 10px;
	border-radius: 5px;
	font-weight: bold;
	color: #000;
	padding-left: 10px;
	background-image: url("kakao.JPG");
	background-repeat: no-repeat;
	background-position: 5px center;
}

.kakao:hover {
	-moz-box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	cursor: pointer;
	color: #f0f0f0;
}

.sign {
	text-align: center;
}

.thankyou {
	line-height: 14px;
	font-size: 10px;
	margin-top: 5px;
	color: #5B5B5B;
	width: 100%;
}

.container .dim-layer {
	width: 50%
}

h1 {
	text-align: center;
	font-family: Tahoma, Arial, sans-serif;
	color: #06D85F;
	margin: 80px 0;
}

.box {
	width: 40%;
	margin: 0 auto;
	background: rgba(255, 255, 255, 0.2);
	padding: 35px;
	border: 2px solid #fff;
	border-radius: 20px/50px;
	background-clip: padding-box;
	text-align: center;
}

.overlay {
	position: absolute;
	top: 130px;
	bottom: 0;
	left: 800px;
	right: 0;
	transition: opacity 500ms;
	visibility: hidden;
	opacity: 0;
}

.overlay:target {
	visibility: visible;
	opacity: 1;
}

.popup {
	margin: 60px auto;
	padding: 20px;
	border-radius: 5px;
	width: 500px;
	position: relative;
	transition: all 5s ease-in-out;
}

.popup h2 {
	margin-top: 0;
	color: #333;
	font-family: Tahoma, Arial, sans-serif;
}

.popup .close {
	position: absolute;
	top: 20px;
	right: 30px;
	transition: all 200ms;
	font-size: 30px;
	font-weight: bold;
	text-decoration: none;
	color: #333;
}

.popup .close:hover {
	color: #06D85F;
}

.popup .content {
	max-height: 400px;
	overflow: auto;
}

.fs-title {
	font-size: 15px;
	text-transform: uppercase;
	color: #2C3E50;
	margin-bottom: 10px;
}

.fs-subtitle {
	font-weight: normal;
	font-size: 13px;
	color: #666;
	margin-bottom: 20px;
}

#msform fieldset {
	background: #ffd400;
	border: 0 none;
	border-radius: 3px;
	padding: 20px 30px;
	box-sizing: border-box;
	width: 80%;
	margin: 0 10%;
	box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);
	border-radius: 10px;
	position: relative;
}

#msform input {
	padding: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
	margin-bottom: 10px;
	width: 100%;
	box-sizing: border-box;
	font-family: montserrat;
	color: #2C3E50;
	font-size: 13px;
}

#msform .action-button {
	background: #230000;
	font-weight: bold;
	font-size: 20px;
	color: white;
	border: 0 none;
	border-radius: 5px;
	cursor: pointer;
	padding: 10px 5px;
}

#msform .action-button:hover, #msform .action-button:focus {
	box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
}
.logo{
  width: 30px;
  height: 30px;
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

				<div class="container">
					<div class="tab"></div>
					<div class="paid">
						<p>Receipt Paid successfully</p>
					</div>
					<div class="receipt">
						<div class="paper">
							<div class="title">Receipt</div>

						</div>
						<table>
							<tbody>
								<tr>
									<td>호텔명</td>
									<td class="right"><%=resv.getHotelName() %></td>
								</tr>
								<tr>
									<td>CheckIn</td>
									<td class="right"><%=resv.getCheckIn() %></td>
								</tr>
								<tr>
									<td>CheckOut</td>
									<td class="right"><%=resv.getCheckOut() %></td>
								</tr>
								<tr>
									<td>totlaPrice</td>
									<td class="right"><%=resv.getTotalPrice() %></td>
								</tr>
								<tr>
									<td colspan="2" class="center"></td>
								</tr>
							</tbody>
						</table>
						<div class="sign center">

							<br /> <a class="kakao" href="#popup1">
							<img alt="" class="logo" src="<%=request.getContextPath()%>/image/kakao.JPG">KaKaoPay
							</a> <br />
							<div class="thankyou">Thank you for your business</div>
						</div>
					</div>

					<div></div>
				</div>
				<div id="popup1" class="overlay">
					<div class="popup">
						<a class="close" href="#">&times;</a>
						<div class="content">
							<form id="msform" action="resvPay">
								<fieldset>
									<h3 class="fs-subtitle">Payment Information</h3>
									<input type="text" name="name" placeholder="Name" value="<%=resv.getMemName() %>"/>
									 <input type="text" name="phone" placeholder="Phone" value="<%=resv.getPhoneNum()%>"/> 
									<input type="text" name="email" placeholder="E-mail" value="<%=resv.getEmail() %>" /> 
									<input type="hidden" name="totalprice" value="<%=resv.getTotalPrice() %>"> 
									<input	type="submit" class="submit action-button" value="결제하기" />
								</fieldset>
							</form>
						</div>
					</div>
				</div>

			</article>

		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>