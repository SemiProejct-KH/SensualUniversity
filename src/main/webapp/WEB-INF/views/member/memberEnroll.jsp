<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<section class="section">
	<h2 align="center" style="margin-top:100px;">회원가입</h2>	
	<form name="memberEnrollFrm" method="POST">
		<!-- 아이디, 비번, 비번확인, 성명, 생일, 학과(드롭다운), 학년, 휴대폰, 이메일 -->
		<div class="row g-2">
			<div class="col-md-10">
		    	<div class="form-floating mb-3">
				  <input type="text" class="form-control" name="memberId" id="_memberId" placeholder="아이디" required>
				  <label for="memberId">아이디</label>
				</div>
			</div>
			<div class="col-md">
	   			<div class="form-floating" >
				  <input type="button" class="form-control" value="중복검사" onclick="checkIdDuplicate();" />
				  <input type="hidden" id="idValid" value="0" />
				</div>
  			</div>
		</div>
		
		<div class="form-floating mb-3">
		  <input type="password" class="form-control" name="password" id="_password" placeholder="비밀번호" required>
		  <label for="password">비밀번호</label>
		</div>

		<div class="form-floating mb-3">
		  <input type="password" class="form-control" name="passwordCheck" id="passwordCheck" placeholder="비밀번호확인" required>
		  <label for="passwordCheck">비밀번호확인</label>
		</div>
		
		<div class="form-floating mb-3">
		  <input type="text" class="form-control" name="memberName" id="memberName" placeholder="성명" required>
		  <label for="memberName">성명</label>
		</div>
	    
	    <div class="form-floating mb-3">
      	  <input type="date" class="form-control" id="memberBrith" value="" required>
	      <label for="memberBrith">생년월일</label>
		</div>
	  	<!-- 학과, 학년드롭다운 -->
	  	
	  	
	  	<div class="form-floating mb-3">
      	  <input type="text" class="form-control" name="memberPhone" id="memberPhone" placeholder="핸드폰" value="" required>
	      <label for="memberPhone">핸드폰</label>
		</div>
		
		<div class="form-floating mb-3">
      	  <input type="text" class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일" value="" required>
	      <label for="memberEmail">이메일</label>
		</div>
	   
	    
	    <div class="btn-group" role="group" aria-label="Basic example">
		  <button type="submit" class="btn btn-primary">가입하기</button>
		  <button type="reset" class="btn btn-primary">취소하기</button>
		</div>
		
	</form>
</section>
<form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate">
	<input type="hidden" name="memberId" />
</form>
<script>
const checkIdDuplicate = () => {
	const title = "checkIdDuplicatePopup";
	const spec = "width=300px, height=200px";
	const popup = open("", title, spec);
	
	const frm = document.checkIdDuplicateFrm;
	frm.target = title; // 해당팝업에서 폼을 제출!
	frm.memberId.value = _memberId.value;
	frm.submit();
};

passwordCheck.onblur = () => {
	if(_password.value !== passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}	
	return true;
};
	
/*회원가입폼 유효성 검사*/
document.memberEnrollFrm.onsubmit = () => {
	// memberId
	// /^[a-z]+[a-z0-9]{5,11}$/g; // 영문자로 시작하는 영문자 또는 숫자 6 ~ 12자
	if(!/^[a-z]+[a-z0-9]{5,11}$/g.test(_memberId.value)){
		alert("아이디는 영문자/숫자로 4글자 이상이어야 합니다.");
		return false;
	}
	if(idValid.value !== "1") {
		alert("아이디 중복검사 해주세요.");
		return false;
	}
	// password
	if(!/^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/.test(_password.value)){
		alert("비밀번호는 영문,숫자 조합 4 ~ 16글자 이상어야 합니다.");
		return false;
	}
	if(!passwordCheck.onblur()){
		return false;		
	}
	// memberName
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자이상 입력해주세요.");
		return false;
	}
	// phone
	if(!/^010\d{8}$/.test(memberPhone.value)){
		alert("유효한 전화번호를 입력하세요.");
		return false;
	}
	// email
	if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(memberEmail.value)){
		alert("유효한 이메일을 입력하세요.");
		return false;
	}
	return true;
}

// input 입력하지 않았을 때
(function () {
	  'use strict'
	  var forms = document.querySelectorAll('.needs-validation')

	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>