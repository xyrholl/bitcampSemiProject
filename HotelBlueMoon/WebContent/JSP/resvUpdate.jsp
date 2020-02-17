<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

%>
<%
	String loginId = (String) session.getAttribute("loginId");
	request.setCharacterEncoding("utf-8");
	//hotelArea=서울&checkin=2020-02-14&checkout=2020-02-15&guest=1
	String Area = request.getParameter("hotelArea");
	String checkin = request.getParameter("checkin");
	String checkout = request.getParameter("checkout");
	String sguest = request.getParameter("guest");
	int guest = Integer.parseInt(sguest);
	System.out.println("예약변경jsp:"+Area+checkin+checkout+guest);
	
	String InDates[] = checkin.split("-");
	String checkInYear = InDates[0];
	String checkInMonth = InDates[1];
	String checkInday = InDates[2]; 
	
	String OutDates[] = checkout.split("-");
	String checkOutYear = OutDates[0];
	String checkOutMonth = OutDates[1];
	String checkOutday = OutDates[2];
	
	
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">





<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/web/assets/mobirise-icons/mobirise-icons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/dropdown/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/theme/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/mobirise/css/mbr-additional.css"
	type="text/css">
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
				style="float: right;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
			<div class="mainDiv" style="float: left; width:500px;">
				<div class="col-3 input-wrap area" style="float: left;">
					<p class="label mbr-fonts-style align-center display-4"></p>	
					<select class="custom-select custom-select-lg mb-2 js-areaBox" id="area">
						<option selected><%=Area %></option>
						<option value="서울">서울</option>
						<option value="경기">경기</option>
						<option value="인천">인천</option>
						<option value="충청">충청</option>
					</select>
				</div>
				
				<div class="col-3 input-wrap checkin" style="float: left;">
					<p class="label mbr-fonts-style align-center display-4"></p>
					
					<div class="custom-select custom-select-lg mb-2 js-inBox">
      					
      					<input type="text" value="<%=checkInMonth %>-<%=checkInday %>" style="border: none; background: transparent;" name="checkin" id="checkin" size="3">
    				</div>
				</div>
				
				<div class="col-3 input-wrap checkout" style="float: left;">
					<p class="label mbr-fonts-style align-center display-4"></p>
					
					<div class="custom-select custom-select-lg mb-2 js-outBox">
      					
      					<input type="text" value="<%=checkOutMonth %>-<%=checkOutday %>" style="border: none; background: transparent;" name="checkout" id="checkout" size="3">
    				</div>
				</div>
				
				<div class="col-3 input-wrap guest" style="float: left;">
					<p class="label mbr-fonts-style align-center display-4"></p>
					<select class="custom-select custom-select-lg mb-2 js-guestBox" id="guest">
						<option selected><%=guest %></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>

			</div>
			<br><br><br><br>
			<div class="mbr-section-btn">
										<span class="input-group-btn">
											<button onclick="goToAjax()" type="submit"
												class="btn btn-form btn-secondary mbr-lighter display-8">
												Search</button>
										</span>
									</div>

		</header>

		<main class="hotelcontent">

			<article style="overflow-y: scroll; height: 500px;">


				<div class="mainSpan" >

				</div>

			</article>

		</main>
		<footer>Footer</footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/web/assets/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/formoid/formoid.min.js"></script>
	<script src="<%=request.getContextPath()%>/JS/main.js"></script>
	<script src="<%=request.getContextPath()%>/JS/hotelList.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/JS/datepicker-ko.js"></script>
</body>
</html>