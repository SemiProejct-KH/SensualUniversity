<%@page import="chat.model.dto.Register"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Register> list = (List<Register>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chat.css" />

<section class="section">
	<div>
		<div class="modal" id="chatroom_member_modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">DM</h5>
					</div>
					<div class="modal-body">
						<form name="register_find_frm" method="get" action="<%=request.getContextPath()%>/chatroom">
							
							<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
								<%
									if(list == null || list.isEmpty()) {
								%>
								<option selected>조회된 강의가 없습니다.</option>
								<%
									} else {
								%>	
								<option selected>강의 선택</option>
								<%	
										for(Register register : list){
								%>
								<option value="register_name"><%= register.getSubjectName() %></option>
								<%	
										}
									}
								%>
							</select> 					
						</form>
						<form>
							<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
								<option selected>회원 선택</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
							</select> 						
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" id="chatroom_member_modal_close" data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">DM</button>
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