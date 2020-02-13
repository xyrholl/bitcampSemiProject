<%@page import="dto.HotelDTO"%>
<%@page import="dto.ResvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	System.out.println(loginId);
	
%>

<%
	ResvDTO resv = (ResvDTO) request.getAttribute("resv");

	System.out.println(resv.toString());

	HotelDTO hotel = new HotelDTO();
	hotel.setSeq(resv.getSeq());
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
<style>
img {
	height: auto;
	max-width: 100%;
	vertical-align: middle;
}

.profile {
	background-color: white;
	box-shadow: 0 20px 40px -14px rgba(0, 0, 0, 0.25);
	overflow: hidden;
	padding: 1rem 0;
}

.profile::after {
	content: "";
	display: block;
	clear: both;
}

.profile-image {
	float: left;
	width: calc(33.333% - 1rem);
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 3rem;
}

.profile-image img {
	border-radius: 50%;
}

.profile-user-settings, .profile-stats, .profile-bio {
	float: left;
	width: calc(66.666% - 2rem);
}

.profile-user-name {
	display: inline-block;
	font-size: 5.2rem;
	font-weight: 300;
}

.profile-user-area {
	display: inline-block;
	font-size: 2.0rem;
	font-weight: 100;
}

.profile-stats {
	margin-top: 2.3rem;
	width: 100%;
}

.profile-stats li {
	display: inline-block;
	font-size: 1.6rem;
	line-height: 1.5;
	margin-right: 4rem;
	cursor: pointer;
}

.profile-stats li:last-of-type {
	margin-right: 0;
}

.profile-user-settings li {
	display: inline-block;
	font-size: 1.6rem;
	line-height: 1.5;
	margin-right: 4rem;
	cursor: pointer;
}

.profile-stat-count {
	font-weight: 600;
}

.profile-stat-hotel {
	font-size: 15px;
}

.profile-stats ul li:last-child {
	width: 50%
}

.hotelbtm {
	
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
			Header
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>


				<h3>예약정보</h3>
				<div class="profile">
				
					<div class="profile-image">
						<img
							src="https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces"
							alt="">
					</div>
					<div class="profile-user-settings">
						<h1 class="profile-user-name"><%=resv.getHotelName()%></h1>
						<br>
						<p class="profile-user-area"><%=resv.getHotelAddr()%></p>
						<ul>
							<li><span class="profile-stat-hotel">호텔등급</span><%=resv.getHotelRating()%></li>
							<li><span class="profile-stat-hotel">이용수</span><%=resv.getHotelUseCount()%></li>
							<li><span class="profile-stat-hotel">평점</span> <%=resv.getHotelRating()%></li>
						</ul>
					</div>
					<div class="profile-stats">
						<ul>
							<li><span class="profile-stat-count">CHECK-IN</span><%=resv.getCheckIn()%></li>
							<li><span class="profile-stat-count">CHECK-OUT</span><%=resv.getCheckOut()%></li>
							<br>
							<li><span class="profile-stat-count">PRICE</span> <%=resv.getTotalPrice()%></li>
							<br>
							<li class="hotelbtm">
								<button type="button" class="btn btn-outline-danger">예약취소</button>
								<button type="button" class="btn btn-outline-success">예약수정</button>
							</li>
						</ul>
					</div>

					<div align="center">
						<form id="frm" action="resvAdd">
							<input type="hidden" name="seq" value="<%=resv.getSeq()%>">
							<input type="hidden" name="memberSeq" value="<%=resv.getMemberSeq()%>">
							 <input type="hidden" name="roomSeq" value="<%=resv.getRoomSeq()%>">
							 <input type="hidden" name="hotelSeq" value="<%=resv.getHotelSeq()%>">
						   	<input type="hidden" name="checkIn" value="<%=resv.getCheckIn()%>">
							 <input type="hidden" name="checkOut" value="<%=resv.getCheckOut()%>"> 
							<input type="hidden" name="totalPrice" value="<%=resv.getTotalPrice()%>"> 
							 <input type="hidden" name="current_guest" value="<%=resv.getCurrent_guest()%>"> 
							 <input type="hidden" id="loginId" name="loginId" value="<%=loginId%>"> 
							<input type="button" id="btn" class="btn btn-primary btn-lg" value="예약하기">
						</form>
					</div>
				</div>

			</article>

		</main>
		<footer>Footer</footer>
	</div>
	<script type="text/javascript">	
	$('#btn').click(function() { 
 		if($("#loginId").val() == 'null'){
 			alert("로그인시 이용가능합니다.\n로그인페이지로 이동합니다.");
				location.href= getContextPath()
 					 		+"/resvNullCheck?"
 					 		+"roomSeq="+<%=resv.getHotelSeq()%>
 					 		+"&hotelSeq="+<%=resv.getHotelSeq()%>
 							+"&checkin="+"<%=resv.getCheckIn()%>"
 							+"&checkout="+"<%=resv.getCheckOut()%>"
 							+"&totalPrice="+<%=resv.getTotalPrice()%>
 							+"&guest="+<%=resv.getCurrent_guest()%>; 
		}else{
			alert("정보있음");
			$("#frm").submit();
		} 
		
	});
	</script>
	
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>