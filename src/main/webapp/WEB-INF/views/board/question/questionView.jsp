<%@page import="board.model.dto.BoardComment"%>
<%@page import="board.model.dto.BoardAttachment"%>
<%@page import="board.model.dto.BoardExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<%
 
	BoardExt board = (BoardExt)request.getAttribute("board");	
	List<BoardComment> comments = board.getBoardComments();

	boolean canEdit = loginMember != null 
	&& (loginMember.getMemberId().equals(board.getMemberId())
	|| loginMember.getMemberRole() == MemberRole.A);
	
	System.out.println(loginMember.getMemberId());
	System.out.println(board.getMemberId());
	System.out.println(loginMember.getMemberRole());
	System.out.println(canEdit);
%>
<section class="notice_container_view section">
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
							<input type="button" id="delete_btn" class="view_btn btn btn-primary" value="삭제하기" onclick="deleteNotice()">
							<input type="button" class="view_btn btn btn-primary" value="수정하기" onclick="updateNotice()">
						</th>
					</tr>
					<% } %>
			</table>
		</div>
	</div>
	 
	<div class="comment_container">
        <div class="comment_editor">
            <form
				action="<%=request.getContextPath()%>/board/boardComment" 
				method="post" 
				class= "boardCommentFrm" 
				name="boardCommentFrm"
				style="text-align: center;">
                <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
                <input type="hidden" name="memberId" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" /> 
				<textarea 
				name="content" cols="60" rows="3"></textarea>
                <button type="submit" 
                	class="btn_comment_enroll btn btn-outline-primary"
                	style="line-height:18px; margin-top:-80px; width: 58px; height:31px;">등록</button>
            </form>
        </div>
       </div>
		<!--table#tbl-comment-->
		<% if (comments != null && !comments.isEmpty()) { %>
		<table class="tbl_comment">
			<tbody>
			<%
				for(BoardComment bc : comments) {
			%>
				<tr>
					<td>
						<sub class="comment_writer"><%= bc.getMemberId() %></sub>
						<sub class="comment_date"><%= bc.getRegDate() %></sub>
						<br />
						<sub class="comment_content"><%= bc.getContent() %></sub>
					</td>
					<td>
						<button class="reply_go_chat" onclick="location.href='<%= request.getContextPath() %>/chat/chatroom'">채팅</button>
					</td>
				</tr>
			<% } 
			}
			%>
			</tbody>
		</table>
</section>
<% if(canEdit){ %>
<form action="<%= request.getContextPath() %>/board/questionDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getBoardNo() %>" />
</form>
<script>
document.querySelector("textarea[name=content]").onfocus = (e) => {
	if(<%= loginMember == null %>)
		loginAlert();
};
const loginAlert = () => {
	alert("로그인 후 이용할 수 있습니다.");
	document.querySelector("memberId").focus();
};
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