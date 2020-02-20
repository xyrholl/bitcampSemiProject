<%@page import="singleton.Singleton"%>
<%@page import="dto.QnADTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginId = (String) session.getAttribute("loginId");

	List<QnADTO> list = (List<QnADTO>) request.getAttribute("list");
	int qnaPage = (int) request.getAttribute("qnaPage");
	int pageNumber = (int) request.getAttribute("pageNumber");
	String choice = (String) request.getAttribute("choice");
	String searchWord = (String) request.getAttribute("searchWord");
%>
<%!// 댓글의 여백과 이미지 추가하는 함수
	public String arrow(int depth) {
		String rs = "<button class='source-button btn btn-primary btn-xs' tabindex='0' style='border-radius: 10px; margin-right: 20px; padding: 6px; padding-top: inherit; padding-bottom: inherit;'>&gt;</button>";
		String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		String ts = "";
		for (int i = 0; i < depth; i++) {
			ts += nbsp + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return depth == 0 ? "" : ts + rs;
	}%>
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
		<ul class="list-group" style="cursor: pointer;">
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
				<button type="button" class="btn btn-success js-foward-login">로그인</button>
			</div>
			<input type="hidden" value="<%=loginId%>" class="js-session">
		</header>
		<main class="hotelcontent">

			<article>
				<img alt="" src="<%=request.getContextPath()%>/image/bluemoon.png"
					style="width: 25%; float: left;">
				<div style="float: left; margin-top: 5vh;">
					<h1>Q&A</h1>
				</div>
				<div class="input-group-append" style="margin-top: 14vh;">
					<select id="choice" class="custom-select js-search-select"
						style="width: 20%; border-bottom-left-radius: 5px; border-top-left-radius: 5px; height: 2.22rem; margin-top: 0.08rem;">
						<option value="sel" selected>선 택</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>

					<div class="input-group input-group-append mb-3">
						<input id="_search" type="text" class="form-control js-searchText"
							placeholder="Search" aria-label="Recipient's username"
							aria-describedby="basic-addon2">

						<div class="input-group-append">
							<button type="button" class="btn btn-secondary"
								onclick="searchQnA()">검색</button>
							<button type="button" class="btn btn-secondary"
								style="border-bottom-right-radius: 5px; border-top-right-radius: 5px;"
								onclick="location.href='<%=request.getContextPath()%>'+'/qnafoward'">전체목록</button>
						</div>
					</div>
				</div>


				<div align="center">

					<table class="table table-active">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (list == null || list.size() == 0) {
							%>
							<tr>
								<td colspan="3" align="center"><ul>
										<li class="list-group-item" style="float: none;">작성된 글이
											없습니다</li>
									</ul></td>
							</tr>
							<%
								} else {
									for (int i = 0; i < list.size(); i++) {
										QnADTO qna = list.get(i);
							%>
							<tr>
								<th align="center"><%=qna.getRnum()%></th>
								<td>
									<%
										if (qna.getDel() == 0) {
									%> <a
									href="<%=request.getContextPath()%>/qnadetail?seq=<%=qna.getSeq()%>"
									class="list-group-item list-group-item-action"><span><%=arrow(qna.getDepth())%></span><%=qna.getTitle()%></a>
									<%
										} else {
									%> <a class="list-group-item list-group-item-action disabled"
									style="color: rgba(255, 255, 255, 0.4);">-----작성자에 의해 삭제된
										게시글입니다-----</a> <%
 	}
 %>
								</td>
								<td align="center"><a class="list-group-item"><%=qna.getMemberId()%></a>
								</td>
							</tr>

							<%
								}
							%>
							<%
								}
							%>
						</tbody>
					</table>
				</div>

				<style type="text/css">
.list-paging {
	text-align: center;
}

.list-paging span {
	display: inline-block;
	margin: 0 2px;
}

.list-paging span a {
	font-size: 20px;
	padding: 5px 10px;
	cursor: pointer;
	text-decoration: none;
	border-radius: 8px;
}

.list-paging span.now a {
	font-size: 20px;
	padding: 5px 10px;
	cursor: pointer;
	text-decoration: none;
	border-radius: 8px;
	color: white;
}

a {
	background-color: #fff;
}
</style>
				<div class="list-paging" align="center"
					style="float: left; width: 80%">
					<%
						for (int i = 0; i < qnaPage; i++) {
							if (pageNumber == i) { // 현재 페이지
					%>
					<span class="now"><a href="#"
						class="list-group-item list-group-item-action active"><%=i + 1%></a></span>
					<%
						} else {
					%>
					<span><a href="#" onclick="goPage(<%=i%>)"><%=i + 1%></a></span>
					<%
						}
					%>
					<%
						}
					%>
				</div>
				<button type="button" class="btn btn-secondary"
					style="float: right; border-radius: 5px;"
					onclick="location.href='<%=request.getContextPath()%>/qnawrite?command=qnawrite&loginId=<%=loginId%>'">QnA
					글쓰기</button>

			</article>

		</main>
		<footer></footer>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/qna.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>