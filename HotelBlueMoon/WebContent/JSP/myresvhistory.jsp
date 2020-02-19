<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.ReviewDTO"%>
<%@page import="dto.ResvDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");
	String nowStr = (String) request.getParameter("nowTime");

	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDate = date.parse(nowStr);

	System.out.println("history jsp timeStr: " + nowStr + " / date: " + nowDate);

	List<ResvDTO> list = (List<ResvDTO>) request.getAttribute("resvList");
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

				<input type="hidden" value="" class="js-currDateTmp">
				<div class="input-group-append">

					<select class="custom-select js-search-select"
						style="width: 20%; border-bottom-left-radius: 5px; border-top-left-radius: 5px; height: 2.22rem; margin-top: 0.08rem;">
						<option selected>검색</option>
						<option value="1">호텔이름</option>
						<option value="2">지역</option>
					</select>

					<div class="input-group input-group-append mb-3">
						<input type="text" class="form-control js-searchText"
							placeholder="Search" aria-label="Recipient's username"
							aria-describedby="basic-addon2">

						<div class="input-group-append">
							<span class="input-group-text js-searchBtn" id="basic-addon2">검색</span>
							<span class="input-group-text js-allListBtn" id="basic-addon2"
								style="border-bottom-right-radius: 5px; border-top-right-radius: 5px;">전체목록</span>
						</div>
					</div>
				</div>

				<table class="table table-active">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">평점</th>
							<th scope="col">호텔명</th>
							<th scope="col">방이름</th>
							<th scope="col">체크인</th>
							<th scope="col">체크아웃</th>
							<th scope="col">취소여부</th>
							<th scope="col">리뷰여부</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (list == null || list.size() == 0) {
						%>
						<tr>
							<td colspan="8"><ul>
									<li class="list-group-item" style="float: none;">작성된 글이
										없습니다</li>
								</ul></td>
						</tr>
						<%
							} else {
								for (int i = 0; i < list.size(); i++) {
									ResvDTO dto = list.get(i);
									System.out.println(list.get(i).toString());
						%>
						<tr class="row<%=i%> list-group-item-action">
							<th scope="row"><%=i + 1%></th>
							<td><input type="hidden" value="<%=dto.getSeq()%>"
								class="js-resvSeq"> <%
 	if (dto.getHotelRating() >= 4) {
 %>
								<button class="btn btn-primary"><%=dto.getHotelRating()%></button>
								<%
									} else if (dto.getHotelRating() < 4 && dto.getHotelRating() >= 3) {
								%>
								<button class="btn btn-info"><%=dto.getHotelRating()%></button>
								<%
									} else {
								%>
								<button class="btn btn-secondary"><%=dto.getHotelRating()%></button>
								<%
									}
								%></td>
							<td><a class="list-group-item"><%=dto.getHotelName()%></a></td>
							<td><a class="list-group-item"><%=dto.getRoomName()%></a></td>
							<td><%=dto.getCheckIn()%></td>
							<td><%=dto.getCheckOut()%></td>
							<td><input type="hidden" value="" class="js-currDateTmp">
								<%
									if (dto.getCancel() == 0) {
												String checkInstr = dto.getCheckIn();
												SimpleDateFormat simpledataFormat = new SimpleDateFormat("yyyy-MM-dd");
												Date nowCheckIn = simpledataFormat.parse(checkInstr);

												int compare = nowCheckIn.compareTo(nowDate);

												if (compare > 0) {
													System.out.println(i + ": CANCEL=0 체크인 안지남");
								%>
								<button type="button" class="btn btn-outline-danger"
									onclick="location.href='<%=request.getContextPath()%>/myresvcancel?command=cancel&seq=<%=dto.getSeq()%>'">
									취소하기</button> <%
 	} else {
 					System.out.println(i + ": CANCEL=0 체크인 지남");
 					String checkOutstr = dto.getCheckOut();
 					Date nowCheckOut = simpledataFormat.parse(checkOutstr);
 					int compare_out = nowCheckOut.compareTo(nowDate);

 					if (compare_out > 0) { // 체크아웃  안지남
 						System.out.println(i + ": CANCEL=0 체크아웃 안지남");
 %>
								<button type="button" class="btn btn-warning">취소불가</button> <%
 	} else {
 						System.out.println(i + ": CANCEL=0 체크아웃 지남");
 %>
								<button type="button" class="btn btn-secondary">이용완료</button> <%
 	}
 				}
 			} else {
 				System.out.println(i + ": CANCEL=1 취소완료");
 %>
								<button type="button" class="btn btn-danger">취소완료</button> <%
 	}
 %></td>
							<td>
								<%
									if (dto.getReviewIs() == 0) {

												String checkOutstr = dto.getCheckOut();
												SimpleDateFormat simpledataFormat = new SimpleDateFormat("yyyy-MM-dd");
												Date nowCheckOut = simpledataFormat.parse(checkOutstr);

												int compare_out = nowCheckOut.compareTo(nowDate);
												System.out.println(i + ": REVIEWIS=0 comout " + compare_out);
												if (compare_out < 0) { // 체크아웃 지남 && dto.getPayMent == 1
								%>
								<button type="button" id="reviewBtn"
									class="btn btn-outline-info js-review-write"
									onclick="location.href= '<%=request.getContextPath()%>/fowardreviewwrite?seq=<%=dto.getSeq()%>'">
									리뷰 쓰기</button> <%
 	} else { // 체크아웃 안지남  disabled="disabled" 추가해야 제대로 작동함.
 %>
								<button type="button" id="reviewBtn"
									class="btn btn-outline-info js-review-write"
									onclick="location.href= '<%=request.getContextPath()%>/fowardreviewwrite?seq=<%=dto.getSeq()%>'">
									리뷰 쓰기</button> <%
 	}
 			} else {
 %>
								<button type="button" class="btn btn-info"
									onclick="location.href='<%=request.getContextPath()%>/mypagereviewdetail?seq=<%=dto.getSeq()%>'">리뷰
									보기</button> <%
 	}
 %>
							</td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>

			</article>
		</main>
		<footer></footer>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/myresvhistory.js"></script>
</body>
</html>