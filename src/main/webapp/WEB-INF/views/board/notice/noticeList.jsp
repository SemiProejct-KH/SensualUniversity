<%@page import="member.model.dto.MemberExt"%>
<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<NoticeExt> list = (List<NoticeExt>) request.getAttribute("list");

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<div class="board_menu">
	<li><a href="#">공지사항&nbsp|&nbsp</a></li>
	<li><a href="#">학과묻고답하기&nbsp|&nbsp</a></li>
	<li><a href="#">스터디&nbsp|&nbsp</a></li>
	<li><a href="#">분실물</a></li>
</div>
<section id="notice_container" class="section">
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) { %>
		<input type="button" value="글쓰기" onclick="location.href='<%= request.getContextPath() %>/notice/noticeEnroll';"/>
	<% } %>
	<table id="tbl_n_list" class="table table-striped">
		<thead class="thead-light">
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
		<%
			if(list != null && !list.isEmpty()) {
			  for(NoticeExt noticeExt : list) {
			
		%>
			<tr>
				<td><%= noticeExt.getNoticeNo() %></td>
				<td><%= noticeExt.getNoticeTitle() %></td>
				<td><%= noticeExt.getMemberName() %></td>
				<td><%= noticeExt.getNoticeDate() %></td>
				<td>
		<%
				if(noticeExt.getNoticeAttachCount() > 0) {
					
		%>
		<%
				} 
		%>
				</td>
				<td><%= noticeExt.getNoticeReadCount() %></td>
			</tr>
		<%
			  }
			} else {
		
		%>
			   <tr>
				  <td colspan="6"> 조회된 정보가 없습니다.</td>
				</tr>
		<%
			}
		%>
		</tbody>
	</table>

	<nav aria-label="Page navigation example" class="text-center">
	  <ul class="pagination justify-content-center">
	    <li class="page-item disabled">
	      <a class="page-link" href="#" tabindex="-1">&laquo;</a>
	    </li>
	    <li class="page-item"><a class="page-link" href="#">1</a></li>
	    <li class="page-item"><a class="page-link" href="#">2</a></li>
	    <li class="page-item"><a class="page-link" href="#">3</a></li>
	    <li class="page-item"><a class="page-link" href="#">4</a></li>
	    <li class="page-item"><a class="page-link" href="#">5</a></li>
	    <li class="page-item">
	      <a class="page-link" href="#">&raquo;</a>
	    </li>
	  </ul>
	</nav>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>