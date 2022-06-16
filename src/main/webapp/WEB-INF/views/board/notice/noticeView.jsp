<%@page import="notice.model.dto.NoticeExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<% 
	NoticeExt notice = (NoticeExt)request.getAttribute("notice");	
%>
<section id="notice_container_view" class="section">
	<div class="view_btn">
		<a href="<%= request.getContextPath() %>/chat/chatroom" id="go_chat" class="btn btn-outline-primary">1:1채팅</a>	
	</div>
	<div class="container">
		<div class="row">
			<p>공지사항</p>	
			<table id="tbl_n_view" class="table table-bordered table-striped">
				<tbody>
					<tr>
						<td style="width: 20%;">글번호</td>
						<td colspan="2"><%= notice.getNoticeNo() %></td>
					</tr>
					<tr>
						<td style="width: 20%;">제목</td>
						<td colspan="2"><%= notice.getNoticeTitle() %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= notice.getMemberName() %></td>
					</tr>
					<tr>
						<td>조회수</td>
						<td colspan="2"><%= notice.getNoticeReadCount() %></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="2">
							<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
					
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td id="td_view_content" colspan="2"><%= notice.getNoticeContent() %></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>