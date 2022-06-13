<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chatroom.css" />

<section class="section">
	<div>
		<div id="chatroom_btn_container">
			<button type="button" class="btn btn-light">Light</button>
			<button type="button" class="btn btn-light">Light</button>
		</div>
		<div id="chatroom_list_container">
			<div id="chatroom_list">
				test
			</div>
		</div>
		<div id="chatroom_chat_container">
			<div id="chatroom_chat">
			
			</div>
			<div id="chatroom_chat_input">
				<input type="text" />
				<button type="button" class="btn btn-secondary">전송</button>		
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>