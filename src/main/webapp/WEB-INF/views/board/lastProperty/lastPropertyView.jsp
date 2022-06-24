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
%>
<section class="notice_container_view section">
<div style="margin-top:100px;"></div>
	<div class="container">
		<div class="row">
			<span class="span_view" style="font-weight:bold">분실물 게시판</span>	
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
						<td nowrap height="200" colspan="2"><textarea cols="116" rows="20" style="border: none; resize: none;" readonly><%= board.getBoardContent() %></textarea></td>
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
						<th colspan="2" style="text-align: end; border:none;">
							<input type="button" id="delete_btn" class="view_btn btn btn-primary" value="삭제하기" onclick="deleteBoard()">
							<input type="button" class="view_btn btn btn-primary" value="수정하기" onclick="updateBoard()">
						</th>
					</tr>
					<% } %>
			</table>
		</div>
	</div>
	 
	<div class="comment_container">
        <div class="comment_editor">
            <form
				action="<%=request.getContextPath()%>/board/lastPropertyComment" 
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
					boolean canDelete = loginMember != null 
							&& (loginMember.getMemberId().equals(bc.getMemberId()) 
									|| loginMember.getMemberRole() == MemberRole.A);
			%>
				<tr>
					<td>
						<sub class="comment_writer"><%= bc.getMemberId() != null ? bc.getMemberId() : "(탈퇴회원)" %></sub>
						<sub class="comment_date"><%= bc.getRegDate() %></sub>
						<br />
						<sub class="comment_content"><%= bc.getContent() %></sub>
					</td>
					<td>
						<% if(canDelete) { %>
							<button class="btn_delete" value="<%= bc.getCommentNo() %>">삭제</button>
						<% } %>
						<% if(!loginMember.getMemberId().equals(bc.getMemberId())) { %>
						<button class="reply_go_chat" onclick="location.href='<%= request.getContextPath() %>/chat/chatroom'">채팅</button>
						<% } %>

					</td>
				</tr>
			<% } 
			}
			%>
			</tbody>
		</table>
<div style="margin-bottom:100px;"></div>
</section>
<form action="<%= request.getContextPath() %>/board/lastPropertyCommentDelete"
	 name="boardCommentDelFrm"
	 method="post">
	 <input type="hidden" name="commentNo" />
	 <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/>
</form>
<script>
document.querySelector("textarea[name=content]").onfocus = (e) => {
	if(<%= loginMember == null %>)
		loginAlert();
};

const commentSubmitHandler = (e) => {
	if(<%= loginMember == null %>){
		loginAlert();
		return false; 		
	}
	
	const contentVal = e.target.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
	   swal.fire('댓글 작성 실패', "댓글 내용을 작성해주세요.", 'warning');
	   e.target.content.focus();
	   return false;
	};

	document.boardCommentFrm.onsubmit = commentSubmitHandler;

	const loginAlert = () => {
	   swal.fire('로그인을 하세요.', "로그인후 이용하실 수 있습니다\.", 'warning');
	   document.querySelector("#memberId").focus();
	};

</script>


<form action="<%= request.getContextPath() %>/board/lastPropertyDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getBoardNo() %>" />
</form>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
document.querySelectorAll(".btn_delete").forEach((button) => {
    button.onclick = (e) => {
        document.querySelector("input[name=commentNo]").value = e.target.value;
        Swal.fire({
            title: '정말로 삭제하시겠습니까?',
            text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                document.boardCommentDelFrm.submit();
            }
        })
    }
})
$().ready(function () {
    $("#delete_btn").click(function () {
        Swal.fire({
            title: '정말로 삭제하시겠습니까?',
            text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
            	document.boardDeleteFrm.submit();
            }
        })
    });
});
const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/board/lastPropertyUpdate?no=<%= board.getBoardNo() %>";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
