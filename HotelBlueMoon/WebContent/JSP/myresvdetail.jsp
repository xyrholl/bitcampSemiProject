<%@page import="dto.HotelDTO"%>
<%@page import="dto.RoomDTO"%>
<%@page import="dto.ResvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	if (loginId == null || loginId.equals("")) {
		response.sendRedirect(request.getContextPath() + "/fowardlogin");
	}
	HotelDTO hotelDto = (HotelDTO) request.getAttribute("hotelDto");
	RoomDTO roomDto = (RoomDTO) request.getAttribute("roomDto");
	String memName = (String) request.getAttribute("memName");

	String ctype = (String) request.getAttribute("ctype");
	String ptype = (String) request.getAttribute("ptype");
	
	ResvDTO dto = (ResvDTO) request.getAttribute("dto");
	System.out.println(dto.getHotel_img());
	
	System.out.println("detail JSP: ctype= "+ctype+" / ptype= "+ptype);
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
		<ul class="list-group">
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
						<h3 class="card-header">예약 내역</h3>
						<div class="card-body">
							<h5 class="card-title"><%=hotelDto.getName()%></h5>
							<h6 class="card-subtitle text-muted"><%=roomDto.getName()%></h6>
						</div>
						<img style="height: 15rem; width: 100%; display: block;"
							src="<%=request.getContextPath()%>/image/image/<%=roomDto.getRoom_img()%>"
							alt="Card image">
						<ul class="list-group list-group-flush">
							<li class="list-group-item active"><%=memName%> / 예약일 <%=dto.getResvDate()%></li>
							<li class="list-group-item">
								<h6 class="card-subtitle text-muted"><%=hotelDto.getAddr()%></h6>
							</li>
							<li class="list-group-item">CHECK-IN <%=dto.getCheckIn()%></li>
							<li class="list-group-item">CHECK-OUT <%=dto.getCheckOut()%></li>
						</ul>
						<div class="card-body">
							<a class="card-link" style="color: #DF691A;">Price <%=dto.getTotalPrice()%>
								WON
							</a>
						</div>
						<div class="card-footer text-muted">
							<form id="frm" action="resvAdd">
								<input type="hidden" id="loginId" name="loginId"
									value="<%=loginId%>">
								<button id="listBtn" type="button" class="btn btn-outline-info">목록으로</button>
								<% if(ctype.equals("1c")){ %>
									<button type="button" class="btn btn-outline-danger" disabled="disabled" value="1c">취소하기</button>
								<%}else if(ctype.equals("2c")){ %>
									<button type="button" class="btn btn-secondary" value="2c">자동취소</button>
								<%}else if(ctype.equals("3c")){ %>
									<button type="button" class="btn btn-success" value="3c">이용완료</button>
								<%}else if(ctype.equals("4c")){ %>
									<button type="button" id="cancelBtn" class="btn btn-outline-danger" value="4c">취소하기</button>
								<%}else if(ctype.equals("5c")){ %>
									<button type="button" class="btn btn-danger" value="5">취소완료</button>
								<%} %>
								
								
								<% if(ptype.equals("1p")){ %>
									<button type="button" class="btn btn-outline-warning" disabled="disabled" value="1p">결제하기</button>
								<%}else if(ptype.equals("2p")){ %>
									<button type="button" class="btn btn-success" value="2p">결제완료</button>
								<%}else if(ptype.equals("3p")){ %>
									<button type="button" class="btn btn-success" disabled="disabled" value="3p">결제완료</button>
								<%}else if(ptype.equals("4p")){ %>
									<button type="button" id="payBtn" class="btn btn-outline-warning" value="4p">결제하기</button>
								<%}else if(ptype.equals("5p")){ %>
									<button type="button" class="btn btn-secondary" disabled="disabled" value="5p">기간만료</button>
								<%} %>
								
								<input type="hidden" id="detail_seq" value="<%=dto.getSeq()%>">
							</form>
						</div>
					</div>
				</div>
			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/myresvdetail.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>