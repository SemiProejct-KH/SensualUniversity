<%@page import="chat.model.dto.Register"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
List<Register> registerlist = (List<Register>) request.getAttribute("registerlist");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chat.css" />

<section class="section">
	<div>
		<div class="modal" id="chatroom_member_modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">DM</h5>
					</div>
					<div class="modal-body">
						<form name="register_find_frm" method="post" action="<%=request.getContextPath()%>/chatroom/chatroommaker">
							<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
								<%
								if(registerlist == null || registerlist.isEmpty()) {
								%>
								<option selected>조회된 강의가 없습니다.</option>
								<%
								} else {
								%>	
								<option selected>강의 선택</option>
								<%
								for(Register register : registerlist){
								%>
								<option value="register_name"><%=register.getSubjectName()%></option>
								<%
									}
								}
								%>
							</select> 					
						</form>
						<form name="member_find_frm" method="post" action="<%=request.getContextPath()%>/chatroom/chatroommaker">
							<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
								<%
								if(registerlist == null || registerlist.isEmpty()) {
								%>
								<option selected>조회된 회원이 없습니다.</option>
								<%
								} else {
								%>
								<option selected>회원 선택</option>
								<%
								for(Register register : registerlist){
								%>
								<option value="member_name"><%= register.getMemberName() %></option>
								<%	
										}
									}
								%>
							</select> 						
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" id="chatroom_member_modal_close" data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="chatroom_create">DM</button>
					</div>
				</div>
			</div>
		</div>
		<div id="chatroom_btn_container">
			<button type="button" class="btn btn-primary" id="modal_block">회원 선택</button>
		</div>
		<div id="chatroom_list_container">
			<div id="chatroom_list">
				채팅방 리스트
			</div>
		</div>
		<div id="chatroom_chat_container">
			<div id="chatroom_chat">
				
			</div>
			<div id="chatroom_chat_input">
				<input type="text" id="input_chat"/>
				<button type="submit" class="btn btn-secondary" id="send_chat">전송</button>		
			</div>
		</div>
	</div>
</section>
<script>
$('#modal_block').on('click', function() {
	$('#chatroom_member_modal').css('display', 'block');
})
$('#chatroom_member_modal_close').on('click', function() {
	$('#chatroom_member_modal').css('display', 'none');	
})

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>