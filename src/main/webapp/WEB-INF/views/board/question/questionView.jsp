<%@page import="board.model.dto.BoardAttachment"%>
<%@page import="board.model.dto.BoardExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<%
 
	BoardExt board = (BoardExt)request.getAttribute("board");	
	boolean canEdit = loginMember != null 
	&& (loginMember.getMemberId().equals(board.getMemberId())
	|| loginMember.getMemberRole() == MemberRole.A);
	
	System.out.println(loginMember.getMemberId());
	System.out.println(board.getMemberId());
	System.out.println(loginMember.getMemberRole());
	System.out.println(canEdit);
%>
<section id="notice_container_view" class="section">
	<% if(loginMember != null) { %>
		<input type="button" value="1:1채팅" class="btn btn-outline-primary" onclick="location.href='<%= request.getContextPath() %>/chat/chatroom';"/>
	<% } %>
	<div class="container">
		<div class="row">
			<span class="span_view" style="font-weight:bold">교내묻고답하기</span>	
			<table id="tbl_view" class="tbl_view table table-bordered">
				<tbody>
					<tr>
						<th style="width: 20%;">제목</th>
						<td colspan="2"><%= board.getBoardTitle() %></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td colspan="2"><%= board.getMemberId() %></td>
					</tr>
					<tr>
						<th>내용</th>
						<td nowrap height="200" colspan="2"><%= board.getBoardContent() %></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td colspan="2"><%= board.getBoardReadCount() %></td>
					</tr>
					<tr id="th_file" style="text-align:right" >
					<%

						List<BoardAttachment> attachments = board.getBoardAttachments();
							if(attachments != null && !attachments.isEmpty()){
								for(BoardAttachment attach : attachments){
					%>
						<td colspan="3">
						
							<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
							<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getBoardAttachmentNo() %>"><%= attach.getOriginalFilename() %></a>
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
<form action="<%= request.getContextPath() %>/board/questionDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getBoardNo() %>" />
</form>
<script>
/**
 * POST /board/boardDelete
 * - no전송
 * - 저장된 파일 삭제 : java.io.File 
 */
const deleteNotice = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.boardDeleteFrm.submit();
};	
const updateNotice = () => {
	location.href = "<%= request.getContextPath() %>/board/questionUpdate?no=<%= board.getBoardNo() %>";
}
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>