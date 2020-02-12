<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String loginId = (String) session.getAttribute("loginId");
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
<style type="text/css">
/* Menu Styles */

.primary-nav {
	position: fixed;
	z-index: 999;
}

.menu {
	position: relative;
	background-color:#fff;
	
}

.menu ul {
  margin: 0;
  padding: 0;
  list-style: none;
	
}

.open-panel {
  border: none;
  background-color:#fff;
  padding: 0;
}

.hamburger {
	background: #fff;
	position: relative;
	display: block;
	text-align: center;
	padding: 13px 0;
	width: 50px;
  height: 73px;
	left: 0;
  top: 0;
	z-index: 1000;
  cursor: pointer;
}

.hamburger:before {
	content:"\2630"; /* hamburger icon */
	display: block;
  color: #000;
  line-height: 32px;
  font-size: 16px;
}

.openNav .hamburger:before {
	content:"\2715"; /* close icon */
	display: block;
  color: #000;
  line-height: 32px;
  font-size: 16px;
}

.hamburger:hover:before {
  color: #777;
}

.primary-nav .menu li {
	position: relative;
}

.menu .icon {
	position: absolute;
	top: 12px;
	right: 10px;
	pointer-events: none;
  width: 24px;
  height: 24px;
  color: #fff;
}

.menu,
.menu a,
.menu a:visited {
  color: #aaa;
  text-decoration: none!important;
	position: relative;
}

.menu a {
  display: block;
  white-space: nowrap;
  padding: 1em;
  font-size: 14px;
}

.menu a:hover {
	color: #fff;
}

.menu {
	margin-bottom: 3em;
}

.menu-dropdown li .icon {
	color: #777;
}

.menu-dropdown li:hover .icon {
	color: #fff;
}



.menu label {
  margin-bottom: 0;
  display: block;
}

.menu label:hover {
  cursor: pointer;
}

.menu input[type="checkbox"] {
  display: none;
}

input#menu[type="checkbox"] {
  display: none;
}






.sub-menu-dropdown {
	display: none;
}

.new-wrapper {
	position: absolute;
	left: 50px;
  width: calc(100% - 50px);
  transition: transform .45s cubic-bezier(0.77, 0, 0.175, 1);
}

#menu:checked + ul.menu-dropdown {
    
		left: 0;
    -webkit-animation: all .45s cubic-bezier(0.77, 0, 0.175, 1);
            animation: all .45s cubic-bezier(0.77, 0, 0.175, 1);
}

.sub-menu-checkbox:checked + ul.sub-menu-dropdown {
    display: block!important;
    -webkit-animation: grow .45s cubic-bezier(0.77, 0, 0.175, 1);
            animation: grow .45s cubic-bezier(0.77, 0, 0.175, 1);
}


.openNav .new-wrapper {
  position: absolute;
  transform: translate3d(200px, 0, 0);
  width: calc(100% - 250px);
  transition: transform .45s cubic-bezier(0.77, 0, 0.175, 1);
}


.downarrow {
  background: transparent;
	position: absolute;
	right: 50px;
	top: 12px;
  color: #777;
  width: 24px;
  height: 24px;
  text-align: center;
  display: block;
}

.downarrow:hover {
  color: #fff;
}

.menu {
	position: absolute;
	display: block;
	left: -200px;
  top: 0;
	width: 250px;
  transition: all 0.45s cubic-bezier(0.77, 0, 0.175, 1);
  background-color: #000;
	z-index: 999;
}

.menu-dropdown {
  top: 0;
  overflow-y: auto;
}

.overflow-container {
  position: relative;
  height: calc(100vh - 73px)!important;
  overflow-y: auto;
  border-top: 73px solid #fff;
  z-index: -1;
  display:block;
}

.menu a.logotype {
  position: absolute!important;
  background-color: #000;
  font-family: 'Work Sans', sans-serif;
  padding: 10px;
}

.menu a.logotype > img.logo{
	width: 200px;
	height: 60px;
}


.menu a.logotype span {
  font-weight: 400;
}

.menu a.logotype:hover {
  color: #777;
}

.sub-menu-dropdown {
	background-color: #333;
}

.menu:hover {
	position: absolute;
	left: 0;
	top: 0;
}

.openNav .menu:hover {
	position: absolute;
	left: -200px;
	top 73px;
}

.openNav .menu {
  top 73px;
		transform: translate3d(200px, 0, 0);
    transition: transform .45s cubic-bezier(0.77, 0, 0.175, 1);
}
</style>
</head>
<body>
	<div class="primary-nav">

	<nav role="navigation" class="menu">

		<a href="" class="logotype">
			<img alt="" class="logo" src="<%=request.getContextPath()%>/image/hotelsB.png">
		</a>

		<div class="overflow-container">

			<ul class="menu-dropdown">

				<li><a href="#">Dashboard</a><span class="icon"><i class="fa fa-dashboard"></i></span></li>

				<li class="menu-hasdropdown">
					<a href="#">Settings</a><span class="icon"><i class="fa fa-gear"></i></span>

					<label title="toggle menu" for="settings">
        <span class="downarrow"><i class="fa fa-caret-down"></i></span>
      </label>
					<input type="checkbox" class="sub-menu-checkbox" id="settings" />

					<ul class="sub-menu-dropdown">
						<li><a href="">Profile</a></li>
						<li><a href="">Security</a></li>
						<li><a href="">Account</a></li>
					</ul>
				</li>

				<li><a href="#">Favourites</a><span class="icon"><i class="fa fa-heart"></i></span></li>

				<li><a href="#">Messages</a><span class="icon"><i class="fa fa-envelope"></i></span></li>

			</ul>

		</div>

	</nav>

</div>
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
							HOTEL<br>RESERVATION
						</h1>

						<div class="row justify-content-center">
							<div class="col-lg-8 booking-datepicker pt-5"
								data-form-type="formoid" data-start="02/01/2020"
								data-end="03/31/2020">
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
											<option value="seoul">서울</option>
											<option value="Gyungi">경기</option>
											<option value="Incheon">인천</option>
											<option value="ChoongChung">충청</option>
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

</body>
</html>