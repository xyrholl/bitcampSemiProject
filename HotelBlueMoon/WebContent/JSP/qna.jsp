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
		String rs = "<img src='./image/arrow.png' width='20px' heigh='20px'/>";
		String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";

		String ts = "";
		for (int i = 0; i < depth; i++) {
			ts += nbsp;
		}
		return depth == 0 ? "" : ts + rs;
	}%>
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
</head>
<style type="text/css">
table tr td ul li {
	float: left;
	list-style: none;
}

.list-group-item1 {
	position: relative;
	display: block;
	padding: .75rem 1.25rem;
	margin-bottom: -1px;
	background-color: #fff;
}
</style>

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

			<article style="background-color: rgba(170, 166, 157, 0.33); overflow-y:scroll; height: 500px;">

				<h1>Q & A</h1>
				<div align="center">

					<table class="table table-sm">
						<thead>
							<tr align="center">
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
										<li class="list-group-item">작성된 글이 없습니다</li>
									</ul></td>
							</tr>
							<%
								} else {
									for (int i = 0; i < list.size(); i++) {
										QnADTO qna = list.get(i);
							%>
							<tr>
								<th align="center"><%=i + 1%></th>
								<td>
									<%
										if (qna.getDel() == 0) {
									%>

									<ul>
										<li><%=arrow(qna.getDepth())%></li>
										<li><a
											href="<%=request.getContextPath()%>/qnadetail?seq=<%=qna.getSeq()%>"
											class="list-group-item1 list-group-item-action"><%=qna.getTitle()%></a>
										</li>
									</ul> <%
 	} else {
 %> <font color="#ff0000">-----작성자에 의해 삭제된 게시글입니다-----</font> <%
 	}
 %>
								</td>
								<td align="center"><ul>
										<li class="list-group-item"><%=qna.getMemberId()%></li>
									</ul></td>
							</tr>

							<%
								}
							%>
							<%
								}
							%>
						</tbody>
					</table>


					<%
						for (int i = 0; i < qnaPage; i++) {
							if (pageNumber == i) { // 현재 페이지
					%>
					<span style="font-size: 15pt; color: #0000ff; font-weight: bold;">
						<%=i + 1%>
					</span>&nbsp;

					<%
						} else {
					%>

					<a href="#none" title="<%=i + 1%>페이지" onclick="goPage(<%=i%>)"
						style="font-size: 15pt; color: #000; font-weight: bold;"> [<%=i + 1%>]
					</a>&nbsp;

					<%
						}
					%>
					<%
						}
					%>

					<br> <a
						href="<%=request.getContextPath()%>/qnawrite?command=qnawrite&loginId=<%=loginId%>">QnA
						글쓰기</a>
				</div>


				<div class="input-group">
					<select id="choice" class="custom-select" id="inputGroupSelect04">
						<option value="sel">선 택</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>
				</div>

				<div class="input-group mb-3">
					<input type="text" id="_search" class="form-control"
						aria-label="Recipient's username" aria-describedby="basic-addon2">
					<div class="input-group-append">
						<div class="input-group-append">
							<button type="button" onclick="searchQnA()">검색</button>
						</div>
					</div>
				</div>

			</article>

		</main>
		<footer>Footer</footer>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/qna.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JS/main-form.js"></script>
</body>
</html>