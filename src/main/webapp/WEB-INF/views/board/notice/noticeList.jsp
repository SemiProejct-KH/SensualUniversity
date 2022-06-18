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
	<li><a href="#" style="font-weight: bold">공지사항&nbsp|&nbsp</a></li>
	<li><a href="<%=request.getContextPath()%>/board/questionList">교내묻고답하기&nbsp|&nbsp</a></li>
	<li><a href="<%=request.getContextPath()%>/board/studyList">스터디&nbsp|&nbsp</a></li>
	<li><a href="<%=request.getContextPath()%>/board/lastPropertyList">분실물</a></li>
</div>
<section id="notice_container" class="section">
	<%
			if(loginMember != null && loginMember.getMemberRole().equals('A'))
	%>
		<input type="button" value="글쓰기" class="btn btn-outline-primary" onclick="location.href='<%=request.getContextPath()%>/notice/noticeEnroll';"/>
	<%
	%>
	<table id="" class="tbl_list table table-striped">
		<thead class="thead-light">
			<tr>
				<th></th>			
				<th>제목</th>
				<th>작성자</th>
				<th>&nbsp&nbsp&nbsp작성일</th>
			 	<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<%
				if(list != null && !list.isEmpty()) {
			  for(NoticeExt noticeExt : list) {
		%>
			<tr>
				<td>▪</td>
				<td><a href="<%= request.getContextPath() %>/notice/noticeView?no=<%= noticeExt.getNoticeNo() %>"><%= noticeExt.getNoticeTitle() %></a></td>
				<td><%= noticeExt.getMemberId() %></td>
				<td><%= noticeExt.getNoticeDate() %></td>
				<td>&nbsp&nbsp&nbsp<%= noticeExt.getNoticeReadCount() %></td>
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
	<div style="text-align:center" id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>