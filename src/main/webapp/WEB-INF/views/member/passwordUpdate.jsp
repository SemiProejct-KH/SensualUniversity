<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myPagePassword.css?after" />
<section class="section">
    <div class="content">
        <div class="title">
            <h3>비밀번호 변경</h3>
        </div>	
        <form 
            name="passwordUpdateFrm" 
            action="<%=request.getContextPath()%>/member/passwordUpdate" 
            method="post" >

            <div class="form-floating mb-2">
              <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="비밀번호" required>
              <label for="password">기존 비밀번호</label>
            </div>

            <div class="form-floating mb-2">
              <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="비밀번호" required>
              <label for="password">새로운 비밀번호</label>
            </div>

            <div class="form-floating mb-2">
              <input type="password" class="form-control" id="newPasswordCheck" placeholder="비밀번호" required>
              <label for="password">비밀번호 확인</label>
            </div>

            <div class="btn_bar">
              <button type="submit" class="btn_bar1">변경</button>
              <button type="button" class="btn_bar2" onclick="location.href='<%= request.getContextPath() %>/member/memberMyPage';">취소하기</button>
            </div>
            <input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
        </form>
   </div>
</section>
<script>
newPasswordCheck.onblur = () => {
	if(newPassword.value !== newPasswordCheck.value){
		swal('비밀번호 불일치', "두 비밀번호가 일치하지 않습니다.", 'warning');
		return false; // 폼 유효성 검사에서 사용
	}	
	return true;
};
/*비밀번호 유효성검사*/
document.passwordUpdateFrm.onsubmit = () => {
	// password 영문자/숫자/특수문자!@#$%^&*()
	if(!/^[A-Za-z0-9!@#$%^&*()]{4,}$/.test(oldPassword.value)){
		swal('비밀번호 변경', "기존 비밀번호가 일치하지 않습니다.", 'error');
		return false;
	}
	if(!/^[A-Za-z0-9!@#$%^&*()]{4,}$/.test(newPassword.value)){
		swal('비밀번호 변경', "새 비밀번호는 영문,숫자 조합 4 ~ 16글자 이상어야 합니다.", 'error');
		return false;
	}
	if(!newPasswordCheck.onblur()){
		return false; 
	}
}	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>