
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chat.css?after" />

<section class="section">
	<div>
		<div id="chatroom_btn_container">
			<select class="custom-select form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="dm-client">
				<option value="" id="option_member" disabled selected>접속자 목록</option>
			</select>
		</div>
		<div id="chatroom_chat_container">
			<label for="">현재 채팅 가능 인원 : </label>
			<span id="clientCnt"></span>
			<div id="chatroom_chat">
				<div id="msg-container" style="border: 1px solid red;">
					<ul></ul>
				</div>
			</div>
			<div id="chatroom_chat_input">
				<input type="text" id="dm-msg" onkeyup="enterkey()"/>
				<button type="submit" class="btn btn-secondary" id="dm-send">전송</button>		
			</div>
		</div>
	</div>
</section>
<script>
//$('#modal_block').on('click', function() {
//	$('#chatroom_member_modal').css('display', 'block');
//})
//$('#chatroom_member_modal_close').on('click', function() {
//	$('#chatroom_member_modal').css('display', 'none');	
//})

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
	case "dm": messageHandler(payload); break;

	}
};

const messageHandler = (payload) => {
	const {type, sender, msg, time, clientCnt} = payload;
	if(<%= loginMember.getMemberId() == loginMember.getMemberId() %>){
		const html = `
		<li class="\${type !== 'chat' ? 'center' : ''}" style="list-style:none;">
			<span class="badge" style="color:black; background-color:yellow">\${sender}</span>
			\${msg}
		</li>`;
		document.querySelector("#msg-container ul").insertAdjacentHTML('beforeend', html);
	}

	// 스크롤해서 하단부 노출!
	const container = document.querySelector("#msg-container");
	container.scrollTop = container.scrollHeight;
	
	// 채팅인원수 관리
	clientCnt && (document.querySelector("#clientCnt").innerHTML = clientCnt);
};

ws.onerror = (e) => {
	console.log('error', e);
};
ws.onclose = (e) => {
	console.log('close', e);
};

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
			textarea.value = "";
		}
	});
});

function enterkey() {
    if (window.event.keyCode == 13) {
      var tt = $('#dm-msg').val().trim();
      if (tt.trim() == '') {
        alert('빈 값입니다!');
      } else {
        $('#dm-send').click();
      }
    }
  }
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>