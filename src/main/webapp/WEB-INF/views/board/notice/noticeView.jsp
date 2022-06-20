<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="notice.model.dto.NoticeAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
//  주석추가
NoticeExt notice = (NoticeExt)request.getAttribute("notice");	
	boolean canEdit = loginMember != null 
	&& (loginMember.getMemberId().equals(notice.getMemberId())
	|| loginMember.getMemberRole() == MemberRole.A);
%>
<section id="" class="notice_container_view section">
	<%
			if(loginMember != null && loginMember.getMemberRole() != MemberRole.A) {
	%>
		<input type="button" value="1:1채팅" class="btn btn-outline-primary" onclick="location.href='<%=request.getContextPath()%>/chat/chatroom';"/>
	<%
			}
	%>
	<div class="container">
		<div class="row">
			<span style="font-weight:bold">공지사항</span>	
			<table id="" class="tbl_view table table-bordered">
				<tbody>
					<tr>
						<th style="width: 20%;">제목</th>
						<td colspan="2"><%=notice.getNoticeTitle()%></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td colspan="2"><%=notice.getMemberId()%></td>
					</tr>
					<tr>
						<th>내용</th>
						<td nowrap height="200" colspan="2"><%=notice.getNoticeContent()%></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td colspan="2"><%=notice.getNoticeReadCount()%></td>
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
						<th colspan="2">
							<input type="button" id="delete_btn" class="view_btn btn btn-primary" value="삭제하기" onclick="deleteNotice()">
							<input type="button" class="view_btn btn btn-primary" value="수정하기" onclick="updateNotice()">
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