<%@page import="dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String loginId = (String) session.getAttribute("loginId");
	List<ReviewDTO> list = (List<ReviewDTO>) request.getAttribute("list");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				style="float: right; padding-top: 15px;">
				<button type="button" class="btn btn-info js-foward-regi">회원가입</button>
				<button type="button" class="btn btn-success js-foward-login">로그인</button>
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
						<option value="2">제목</option>
						<option value="3">내용</option>
						<option value="4">작성자</option>
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
							<th scope="col">이미지</th>
							<th scope="col">평점</th>
							<th scope="col">리뷰</th>
							<th scope="col">호텔명</th>

						</tr>
					</thead>
					<tbody>
						<%
							if (list == null || list.size() == 0) {
						%>
						<tr>
							<td colspan="6" align="center"><ul>
									<li class="list-group-item" style="float: none;">작성된 글이
										없습니다</li>
								</ul></td>
						</tr>
						<%
							} else {
								for (int i = 0; i < list.size(); i++) {
									ReviewDTO dto = list.get(i);
						%>
						<tr class="row<%=i%>">
							<th scope="row"><%=i + 1%></th>
							<td><img alt=""
								src="http://localhost:9000/HotelBlueMoon/UPload/<%=dto.getFileRealName()%>"
								width="40px" height="40px"></td>
							<td>
								<%
									if (dto.getRating() >= 4) {
								%>
								<button class="btn btn-primary"><%=dto.getRating()%></button> <%
 	} else if (dto.getRating() < 4 && dto.getRating() >= 3) {
 %>
								<button class="btn btn-info"><%=dto.getRating()%></button> <%
 	} else {
 %>
								<button class="btn btn-secondary"><%=dto.getRating()%></button>
								<%
									}
								%>
							</td>
							<td><a
								href="<%=request.getContextPath()%>/reviewdetailfoward?seq=<%=dto.getSeq()%>"
								class="list-group-item list-group-item-action"><%=dto.getTitle()%></a>
							</td>
							<td><a class="list-group-item list-group-item-action"><%=dto.getHotelName()%></a></td>
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
		src="<%=request.getContextPath()%>/JS/review.js"></script>
</body>
</html>