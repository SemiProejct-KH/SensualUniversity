<%@page import="notice.model.dto.NoticeAttachment"%>
<%@page import="java.util.List"%>
<%@page import="notice.model.dto.NoticeExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<% 
	NoticeExt notice = (NoticeExt)request.getAttribute("notice");	

	boolean canEdit = loginMember != null 
	&& (loginMember.getMemberNo() == (notice.getMemberNo())
			|| loginMember.getMemberRole() == MemberRole.A);
%>
<section id="notice_container_view" class="section">
	<% if(loginMember != null && loginMember.getMemberRole() != MemberRole.A) { %>
		<input type="button" value="1:1채팅" class="btn btn-outline-primary" onclick="location.href='<%= request.getContextPath() %>/chat/chatroom';"/>
	<% } %>
	<div class="container">
		<div class="row">
			<span>공지사항</span>	
			<table id="tbl_n_view" class="table table-bordered">
				<tbody>
					<tr>
						<th style="width: 20%;">No.</th>
						<td colspan="2"><%= notice.getNoticeNo() %></td>
					</tr>
					<tr>
						<th style="width: 20%;">제목</th>
						<td colspan="2"><%= notice.getNoticeTitle() %></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td colspan="2"><%= loginMember.getMemberName() %></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td colspan="2"><%= notice.getNoticeReadCount() %></td>
					</tr>
					<tr>
						<th>내용</th>
						<td id="td_view_content" colspan="2"><%= notice.getNoticeContent() %></td>
					</tr>
					<tr id="th_file" style="text-align:right" >
					<% 
						List<NoticeAttachment> attachments = notice.getNoticeAttachments();
							if(attachments != null && !attachments.isEmpty()){
							for(NoticeAttachment attach : attachments){ 
					%>
						<td colspan="3">
						
							<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
							<a href="<%= request.getContextPath() %>/notice/fileDownload?no=<%= attach.getNoticeAttachmentNo() %>"><%= attach.getOriginalFilename() %></a>
						</td>
					</tr>
					<%
							}
						}
					%>
					
					</tbody>
					<% if(canEdit){ %>
					<tr>
						<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
						<th colspan="2">
							<input type="button" id="delete_btn" class="view_btn btn btn-outline-primary" value="삭제하기" onclick="deleteNotice()">
							<input type="button" class="view_btn btn btn-outline-primary" value="수정하기" onclick="updateNotice()">
						</th>
					</tr>
					<% } %>
			</table>
		</div>
	</div>
</section>
<% if(canEdit){ %>
<form action="<%= request.getContextPath() %>/notice/noticeDelete" name="noticeDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= notice.getNoticeNo() %>" />
</form>
<script>
/**
 * POST /board/boardDelete
 * - no전송
 * - 저장된 파일 삭제 : java.io.File 
 */
const deleteNotice = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.noticeDeleteFrm.submit();
};	

const updateNotice = () => {
	location.href = "<%= request.getContextPath() %>/notice/noticeUpdate?no=<%= notice.getNoticeNo() %>";
}
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>