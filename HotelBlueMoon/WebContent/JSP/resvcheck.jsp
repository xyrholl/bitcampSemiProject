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

	System.out.println("resvCheck.jsp" + resv.toString());
	System.out.println("호텔이미지" + resv.getHotel_img() + "룸이미지" + resv.getRoom_img());
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
	width: 50px;
	height: 50px;
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
		<ul class="list-group" style="cursor: pointer;">
			<li class="list-group-item js-main-foward">메인</li>
			<li class="list-group-item js-search-foward">검색</li>
			<li class="list-group-item js-qna-foward">Q&A</li>
			<li class="list-group-item js-review-foward">Review</li>
			<li class="list-group-item js-mypage-foward">MyPage</li>
		</ul>
	</nav>
	<div class="hotelcol-2">
		<header style="height: 0vh;">
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right; padding-top: 15px; position: fixed; margin-left: 62rem;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article style="background-color: rgb(0, 0, 0, 0);">
				<div class="resvcontent" style="width: 40%">
					<div class="card mb-3" style="box-shadow: 5px 5px 5px 5px rgb(0,0,0,0.2);">
						<h3 class="card-header">예약 확인</h3>
						<div class="card-body">
							<h5 class="card-title"><%=resv.getHotelName()%></h5>
							<h6 class="card-subtitle text-muted"><%=resv.getHotelAddr()%></h6>
						</div>
						<img style="height: 15rem; width: 100%; display: block;"
							src="<%=request.getContextPath()%>/image/image/<%=resv.getRoom_img()%>"
							alt="Card image">
						<ul class="list-group list-group-flush">
							<li class="list-group-item active">호텔등급 <%=resv.getHotelRating()%>
								/ 평점 <%=resv.getHotelRating()%> / 이용수 <%=resv.getHotelUseCount()%></li>
							<li class="list-group-item">CHECK-IN <%=resv.getCheckIn()%></li>
							<li class="list-group-item">CHECK-OUT <%=resv.getCheckOut()%></li>
						</ul>
						<div class="card-body">
							<a href="#" class="card-link">Price <%=resv.getTotalPrice()%>
								WON
							</a>
						</div>
						<div class="card-footer text-muted">
							<form id="frm" action="resvAdd">
								<input type="hidden" name="seq" value="<%=resv.getSeq()%>">
								<input type="hidden" name="memberSeq"
									value="<%=resv.getMemberSeq()%>"> <input type="hidden"
									name="roomSeq" value="<%=resv.getRoomSeq()%>"> <input
									type="hidden" name="hotelSeq" value="<%=resv.getHotelSeq()%>">
								<input type="hidden" name="checkIn"
									value="<%=resv.getCheckIn()%>"> <input type="hidden"
									name="checkOut" value="<%=resv.getCheckOut()%>"> <input
									type="hidden" name="totalPrice"
									value="<%=resv.getTotalPrice()%>"> <input type="hidden"
									name="current_guest" value="<%=resv.getCurrent_guest()%>">
								<input type="hidden" id="loginId" name="loginId"
									value="<%=loginId%>">
								<button type="button" id="cancleBtn"
									class="btn btn-outline-danger">예약취소</button>
								<button type="button" id="updateBtn"
									class="btn btn-outline-warning">예약변경</button>
								<input type="button" id="btn" class="btn btn-success"
									value="예약하기">
							</form>
						</div>
					</div>
				</div>
			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript">	
	$( document ).ready(function() {
	$('#btn').click(function() { 
 		if($("#loginId").val() == 'null'){
 			alert("로그인시 이용가능합니다.\n로그인페이지로 이동합니다.");

				location.href= getContextPath()
 					 		+"/resvlogin?"
 					 		+"&hotelSeq="+<%=resv.getHotelSeq()%>
 					 		+"&roomSeq="+<%=resv.getRoomSeq()%>
 							+"&checkin="+"<%=resv.getCheckIn()%>"
 							+"&checkout="+"<%=resv.getCheckOut()%>"
 							+"&guest="+<%=resv.getCurrent_guest()%>; 
		}else{
			$("#frm").submit();
		} 
		
	});
	$('#updateBtn').click(function() { 
		location.href= getContextPath()
	 		+"/resvUpdate?"
	 		+"hotelArea="+"<%=resv.getHotelPlace()%>"
			+"&checkin="+"<%=resv.getCheckIn()%>"
			+"&checkout="+"<%=resv.getCheckOut()%>"
			+"&guest="+<%=resv.getCurrent_guest()%>;
			});

					$('#cancleBtn').click(function() {
						alert("메인화면으로 이동합니다.");
						location.href = getContextPath() + "/JSP/main.jsp";

					});
				});
	</script>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>