
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chat.css?after" />

<section class="section">
	<div>
		<div class="modal" id="chatroom_member_modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">접속자 목록</h5>
					</div>
					<div class="modal-body">
						<div id="dm-container">
							<select onchange="select_change(this.value);" class="custom-select form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="dm-client">
								<option value="" id="option_member" disabled selected>접속자 목록</option>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="chatroom_member_modal_close" data-bs-dismiss="modal">DM</button>
					</div>
				</div>
			</div>
		</div>
		<div id="chatroom_btn_container">
			<button type="button" class="btn btn-primary" id="modal_block">회원 선택</button>
			<input type="text" id="input_member" disabled/>
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
				<input type="text" id="dm-msg"/>
				<button type="submit" class="btn btn-secondary" id="dm-send">전송</button>		
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

const host = location.host; // 접속하는 있는 서버 도메인
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/ws`);

ws.onopen = (e) => {
	console.log('open', e);
};

ws.onmessage = (e) => {
	console.log('message', e);
	const payload = JSON.parse(e.data);
	const {type, sender, receiver, msg, time} = payload;
	console.log(type, sender, receiver, msg, time);
	
	let html;
	switch(type){
	case "welcome":
	case "goodbye": 
	case "chat": messageHandler(payload); break;
	case "dm":
		alert(`\${sender}로부터 DM이 도착했습니다.
----------------------------------------
발신자 : \${sender}
수신자 : \${receiver}
내용 : \${msg}`);
		break;
	}
	
};

ws.onerror = (e) => {
	console.log('error', e);
};
ws.onclose = (e) => {
	console.log('close', e);
};

const dmclient = document.querySelector("#dm-client");
// DM
document.querySelector("#dm-client").addEventListener('focus', (e) => {
	$.ajax({
		url : "<%= request.getContextPath() %>/chat/clients",
		success(clients){
			console.log(clients);
			e.target.innerHTML = "";
			clients.forEach((client) => {
				const option = `<option value="\${client}">\${client}</option>`;
				e.target.insertAdjacentHTML('beforeend', option);
			});
			
		},
		error : console.log
	});
});

var select_change = function(value) {
	console.log(value);
	$("#input_member").val(value);
}

document.querySelector("#dm-send").addEventListener('click', (e) => {
	const receiver = document.querySelector("#dm-client");
	const textarea = document.querySelector("#dm-msg");
	
	console.log(receiver.value, textarea.value);
	
	if(!receiver.value || !textarea.value) return;
	
	const dm = {
		type : "dm",
		msg : textarea.value,
		sender : "<%= loginMember.getMemberId() %>",
		receiver : receiver.value,
		time : Date.now()
	};
	
	$.ajax({
		url : "<%= request.getContextPath() %>/chat/sendDM",
		method : "POST",
		data : {
			dm : JSON.stringify(dm)
		},
		success(resp){
			console.log(resp)
		},
		error : console.log,
		complete(){
			receiver.innerHTML = "<option value='' disabled selected>접속자목록</option>";
			textarea.value = "";
		}
	});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>