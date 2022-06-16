<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section class="section">
	<h2 align="center" style="margin-top:100px;">비밀번호수정</h2>	
	<form 
		name="passwordUpdateFrm" 
		action="<%=request.getContextPath()%>/member/passwordUpdate" 
		method="post" >
			
		<div class="form-floating mb-3">
		  <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="비밀번호" required>
		  <label for="password">현재 비밀번호</label>
		</div>
		
		<div class="form-floating mb-3">
		  <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="비밀번호" required>
		  <label for="password">변경할 비밀번호</label>
		</div>
		
		<div class="form-floating mb-3">
		  <input type="password" class="form-control" id="newPasswordCheck" placeholder="비밀번호" required>
		  <label for="password">비밀번호 확인</label>
		</div>
		
		<button type="submit" class="btn btn-primary">변경</button>
			
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
	</form>
</section>
<script>
newPasswordCheck.onblur = () => {
	if(newPassword.value !== newPasswordCheck.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		return false; // 폼 유효성 검사에서 사용
	}	
	return true;
};
/*비밀번호 유효성검사*/
document.passwordUpdateFrm.onsubmit = () => {
	// password 영문자/숫자/특수문자!@#$%^&*()
	if(!/^[A-Za-z0-9!@#$%^&*()]{4,}$/.test(oldPassword.value)){
		alert("기존 비밀번호가 일치하지 않습니다.");
		return false;
	}
	if(!/^[A-Za-z0-9!@#$%^&*()]{4,}$/.test(newPassword.value)){
		alert("새 비밀번호는 영문,숫자 조합 4 ~ 16글자 이상어야 합니다.");
		return false;
	}
	if(!newPasswordCheck.onblur()){
		return false; 
	}
}	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>