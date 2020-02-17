<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String loginId = (String) session.getAttribute("loginId");

	Calendar cal = Calendar.getInstance();
	
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;
	int day = cal.get(Calendar.DATE);
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/web/assets/mobirise-icons/mobirise-icons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/assets/bootstrap-datepicker/css/bootstrap-datepicker3.css">
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

				<section
					class="header1 cid-qJ2VZmtvSe mbr-fullscreen mbr-parallax-background"
					id="header1-3">


					<div class="container">


						<h1
							class="mbr-section-title align-center mbr-fonts-style mbr-bold mbr-white pb-4 display-1">
							호텔<br>블루문
						</h1>

						<div class="row justify-content-center">
							<div class="col-lg-8 booking-datepicker pt-5"
								data-form-type="formoid" data-start="<%=month%>/<%=day%>/<%=year%>"
								data-end="<%=month+2%>/<%=day%>/<%=year%>">
								<form class="mbr-form" action="https://mobirise.com/"
									method="get" data-form-title="My Mobirise Form">
									<input type="hidden" name="email" data-form-email="true"
										value="aIvXKlmLd9r5qHaLw/S9ghX3QUdX9nJVgLhofMLZDiSWaYpITl+m4SVH138CkxEBb1Ex1M6A9yDJEkC/hdeLcDbcBtId9z4RoS19aGV/z6zG0ZMywgVyVGEiDsbdX0tP">

									<div class="col-3 input-wrap check-in" data-for="check-in">
										<input type="text" class="check-in-input" name="check-in"
											data-form-field="Check-In" readonly=""
											id="check-in-header1-3">
										<p class="label mbr-fonts-style align-center display-4">
											CHECK-IN</p>
										<div class="date-row">
											<span class="day display-2 mbr-bold js-ci-dayVal"></span>
											<div class="date-col">
												<span class="month display-4 js-ci-monthVal"></span> <span
													class="mbr-iconfont mbri-arrow-down"> </span>
											</div>
										</div>
									</div>

									<div class="col-3 input-wrap check-out" data-for="check-out">
										<input type="text" class="check-out-input" name="check-out"
											data-form-field="Check-Out" readonly=""
											id="check-out-header1-3">
										<p class="label mbr-fonts-style align-center display-4">
											CHECK-OUT</p>
										<div class="date-row">
											<span class="day display-2 mbr-bold js-co-dayVal"></span>
											<div class="date-col">
												<span class="month display-4 js-co-monthVal"></span> <span
													class="mbr-iconfont mbri-arrow-down"></span>
											</div>
										</div>
									</div>

									<div class="col-3 input-wrap guests" data-for="guests">
										<input type="text" class="guests-input" name="guests"
											data-form-field="Guests" readonly="" id="guests-header1-3">
										<p class="label mbr-fonts-style align-center display-4">
											GUESTS</p>
										<div class="date-row">
											<span class="guests-count display-2 mbr-bold js-guest">1</span>
											<div class="date-col">
												<span class="mbr-iconfont mbri-arrow-up"></span> <span
													class="mbr-iconfont mbri-arrow-down"></span>
											</div>
										</div>
									</div>

									<div class="col-3 input-wrap">
										<p class="label mbr-fonts-style align-center display-4">
											AREA</p>
										<select class="custom-select custom-select-lg mb-2 js-areaBox">
											<option selected>Area</option>
										</select>
									</div>

									<div class="mbr-section-btn">
										<span class="input-group-btn">
											<button onclick="hotelListPage() " type="submit"
												class="btn btn-form btn-secondary mbr-lighter display-8">
												NEXT</button>
										</span>
									</div>
								</form>
							</div>
						</div>
					</div>

				</section>

			</article>

		</main>
		<footer>Footer</footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/web/assets/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/mbr-booking-datepicker/mbr-booking-datepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/css/assets/formoid/formoid.min.js"></script>
	<script src="<%=request.getContextPath()%>/JS/main.js"></script>
	<script src="<%=request.getContextPath()%>/JS/placeRead.js"></script>

</body>
</html>