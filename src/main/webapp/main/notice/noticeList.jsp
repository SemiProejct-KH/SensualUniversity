<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>

<div class="board-menu">
	<li><a id="a" href="#">공지사항&nbsp|&nbsp</a></li>
	<li><a href="#">학과묻고답하기&nbsp|&nbsp</a></li>
	<li><a href="#">스터디&nbsp|&nbsp</a></li>
	<li><a href="#">분실물</a></li>
</div>
<section id="notice-container">
	<input 
		type="button" value="글쓰기" id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/main/notice/noticeEnroll.jsp';"/>
	<table id="tbl-notice">
		<thead>
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="6"> 조회된 정보가 없습니다.</td>
		</tr>
		</tbody>
	</table>

	<div class='board-pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
</body>
</html>
