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
		<header style="height: 2vh;">
			<div class="btn-group" role="group" aria-label="Basic example"
				style="float: right; padding-top: 15px; position: fixed; margin-left: 62rem;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-primary js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article style="box-shadow: 5px 5px 5px 5px rgb(0,0,0,0.2);">
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>MyPage</h1>
				</div>

				<input type="hidden" value="" class="js-currDateTmp">
				<div class="input-group-append" style="margin-top: 14vh;">

					<select class="custom-select js-search-select"
						style="width: 20%; border-bottom-left-radius: 5px; border-top-left-radius: 5px; height: 2.22rem; margin-top: 0.08rem; cursor: pointer;">
						<option selected>검색</option>
						<option value="1">호텔이름</option>
						<option value="2">지역</option>
					</select>

					<div class="input-group input-group-append mb-3">
						<input type="text" class="form-control js-searchText"
							placeholder="Search" aria-label="Recipient's username"
							aria-describedby="basic-addon2">

						<div class="input-group-append">
							<span class="input-group-text js-searchBtn" id="basic-addon2" style="cursor: pointer;">검색</span>
							<span class="input-group-text js-allListBtn" id="basic-addon2"
								style="border-bottom-right-radius: 5px; border-top-right-radius: 5px; cursor: pointer;">전체목록</span>
						</div>
					</div>
				</div>

				<table class="table table-active">
					<thead>
						<tr>
							<th scope="col" style="width: 3%">#</th>
							<th scope="col" style="width: 3%">평점</th>
							<th scope="col">호텔명</th>
							<th scope="col">방이름</th>
							<th scope="col" style="width: 11%;">체크인</th>
							<th scope="col" style="width: 11%;">체크아웃</th>
							<th scope="col" style="width: 11.6%;">취소여부</th>
							<th scope="col" style="width: 11.6%;">결제여부</th>
							<th scope="col" style="width: 11.6%;">리뷰여부</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (list == null || list.size() == 0) {
						%>
						<tr>
							<td colspan="8"><ul>
									<li class="list-group-item" style="float: none; text-align: center;">예약을 신청한
										이력이 없습니다.</li>
								</ul></td>
						</tr>
						<%
							} else {
								for (int i = 0; i < list.size(); i++) {
									ResvDTO dto = list.get(i);
									System.out.println(list.get(i).toString());

									String checkInstr = dto.getCheckIn();
									SimpleDateFormat simpledataFormat = new SimpleDateFormat("yyyy-MM-dd");
									Date nowCheckIn = simpledataFormat.parse(checkInstr);

									String checkOutstr = dto.getCheckOut();
									Date nowCheckOut = simpledataFormat.parse(checkOutstr);

									int compare_in = nowCheckIn.compareTo(nowDate);
									int compare_out = nowCheckOut.compareTo(nowDate);
						%>
						<tr class="row<%=i%> list-group-item-action"
							onclick="location.href='<%=request.getContextPath()%>/myresvcancel?command=pay&detail_seq=<%=dto.getSeq()%>'">
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
							<td onclick="event.cancelBubble = true;"><input
								type="hidden" value="" class="js-currDateTmp"> <%
 	////////////////////////////// 취소버튼  ////////////////////////////////////

 			if (dto.getCancel() == 0) { // 취소안함

 				if (compare_in <= 0 && compare_out > 0) { // 체크인 지남, 체크아웃 안지남
 					System.out.println(i + "취소버튼: 취소안함, 체크인 안지남 = 취소하기 비활성화 ");
 %><button type="button" class="btn btn-outline-danger"
									disabled="disabled">취소하기</button> <%
 	} else if (compare_in <= 0 && compare_out <= 0 && dto.getPaymentIs() == 0) { // 체크인 지남, 체크아웃 지남
 					System.out.println(i + "취소버튼: 취소안함, 체크아웃 지남, 결제 안함 = 자동취소");
 					dto.setCancel(1);
 %><button type="button" class="btn btn-secondary">자동취소</button>
								<%
									} else if (compare_in <= 0 && compare_out <= 0 && dto.getPaymentIs() == 1) { // 체크인 지남, 체크아웃 지남
													System.out.println(i + "취소버튼: 취소안함, 체크아웃 지남, 결제 함 = 이용완료");
								%><button type="button" class="btn btn-success">이용완료</button>
								<%
									} else if (compare_in > 0) { // 취소안함, 체크인 안지남 = 취소하기);
													System.out.println(i + "취소버튼: 취소안함, 체크인 안지남, 결제 안함 = 취소하기");
								%><button type="button" class="btn btn-outline-danger">취소하기</button>
								<%
									}

											} else if (dto.getCancel() == 1) {
												System.out.println(i + "취소버튼: CANCEL=1 취소완료");
								%><button type="button" class="btn btn-danger">취소완료</button> <%
 	}
 %></td>

							<!-- /////////////////////////////  결제 버튼   ////////////////////////////////// -->

							<td onclick="event.cancelBubble = true;">
								<%
									if (dto.getPaymentIs() == 0 && dto.getCancel() == 1) { // 결제 안함, 취소함

												System.out.println(i + "결제버튼: 취소함  결제 안함 = 결제비활성");
								%><button type="button" class="btn btn-outline-warning"
									disabled="disabled">결제하기</button> <%
 	} else if (dto.getPaymentIs() == 1 && dto.getCancel() == 0) { // 결제함, 취소안함

 				System.out.println(i + "결제버튼: 결제함, 취소안함 = 결제완료");
 %><button type="button" class="btn btn-warning">결제완료</button> <%
 	} else if (dto.getPaymentIs() == 1 && dto.getCancel() == 1) { // 결제함, 취소안함

 				System.out.println(i + "결제버튼: 결제함, 취소함 = 결제완료");
 %><button type="button" class="btn btn-warning"
									disabled="disabled">결제완료</button> <%
 	} else if (dto.getPaymentIs() == 0 && dto.getCancel() == 0) { // 결제안하고. 취소 안함

 				System.out.println(i + "결제버튼: 결제안함, 취소 안함 = 결제하기");

 				if (compare_in <= 0 && compare_out > 0) { // 체크인 지남, 체크아웃 안지남

 					System.out.println(i + "결제버튼: 체크인지남, 체크아웃 안지남 = 결제하기");
 %><button type="button" class="btn btn-outline-warning">결제하기</button>
								<%
									} else if (compare_out <= 0) { // 체크인 지남, 체크아웃 지남

													System.out.println(i + "결제버튼: 체크인지남, 체크아웃 지남 = 기간만료");
								%><button type="button" class="btn btn-secondary"
									disabled="disabled">기간만료</button> <%
 	} else if (compare_in > 0) { // 체크인 안지남
 					System.out.println(i + "결제버튼: 체크인 안지남 = 결제하기");
 %><button type="button" class="btn btn-outline-warning">결제하기</button>
								<%
									}
											}
								%>
							</td>

							<!-- ///////////////////////////////   리뷰 버튼    /////////////////////////////////// -->
							<td onclick="event.cancelBubble = true;">
								<%
									if (dto.getReviewIs() == 1 && dto.getCancel() == 0) { // 리뷰쓰고 취소안함
												System.out.println(i + "리뷰버튼: 리뷰쓰고 취소안함 = 리뷰 보기");
								%><button type="button" class="btn btn-info">리뷰보기</button> <%
 	} else if (dto.getReviewIs() == 0 && dto.getCancel() == 1) { // 리뷰안쓰고 취소함
 				System.out.println(i + "리뷰버튼: 리뷰안쓰고 취소함 = 리뷰 비활성");
 %><button type="button" class="btn btn-outline-info"
									disabled="disabled">리뷰쓰기</button> <%
 	} else if (dto.getReviewIs() == 0 && dto.getCancel() == 0 && dto.getPaymentIs() == 0) { // 리뷰 안쓰고 취소안함 결제 안함
 				if (compare_out <= 0) { // 체크아웃 지남
 					System.out.println(i + "리뷰버튼: 취소안함, 결제안함, 체크아웃지남 = 리뷰 비활성");
 %><button type="button" class="btn btn-outline-info"
									disabled="disabled">리뷰쓰기</button> <%
 	} else if (compare_in <= 0 && compare_out > 0) { // 체크인 지나고  체크아웃 안지남
 					System.out.println(i + "리뷰버튼: 취소안함, 결제안함,체크인 지남, 체크아웃 안지남 = 리뷰 비활성");
 %><button type="button" class="btn btn-outline-info"
									disabled="disabled">리뷰쓰기</button> <%
 	} else if (compare_in > 0) { // 체크인 안지남
 					System.out.println(i + "리뷰버튼: 취소안함, 결제안함, 체크인 안지남 = 리뷰 비활성");
 %><button type="button" class="btn btn-outline-info"
									disabled="disabled">리뷰쓰기</button> <%
 	}
 			} else if (dto.getReviewIs() == 0 && dto.getCancel() == 0 && dto.getPaymentIs() == 1) { // 리뷰 안쓰고 취소안함 결제 함
 				if (compare_out <= 0) { // 체크아웃 지남
 					System.out.println(i + "리뷰버튼: 취소안함, 결제함, 체크아웃지남 = 리뷰쓰기");
 %><button type="button" class="btn btn-outline-info"
									onclick="location.href='<%=request.getContextPath()%>/fowardreviewwrite?seq=<%=dto.getSeq()%>'">리뷰쓰기</button>
								<%
									} else if (compare_in <= 0 && compare_out > 0) { // 체크인 지나고  체크아웃 안지남
													System.out.println(i + "리뷰버튼: 취소안함, 결제함, 체크인 지남, 체크아웃 안지남 = 리뷰 비활성");
								%><button type="button" class="btn btn-outline-info">리뷰쓰기</button>
								<%
									} else if (compare_in > 0) { // 체크인 안지남
													System.out.println(i + "리뷰버튼: 취소안함, 체크인 안지남 = 리뷰 비활성");
								%><button type="button" class="btn btn-outline-info"
									disabled="disabled">리뷰쓰기</button> <%
 	}
 			}

 		} // for문 끝
 	} // else문 끝
 %>

							</td>
						</tr>
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