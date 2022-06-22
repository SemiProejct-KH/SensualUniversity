<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>회원가입 - 학생</title>
  <!-- jQuery -->
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
  <!-- css -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/signup.css?after" />
  <!-- favicon -->
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico">
  <!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<section class="section_signup">
	<div class="content">
       <div class="title">
           <h3>회 원 가 입</h3>
       </div>

<!------------------------- 학생 & 교수 가입분기처리 ------------------------>
        <div class="btn_bar">
              <button class="btn_bar1" onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll';">학생</button>
              <button class="btn_bar2" onclick="location.href='<%= request.getContextPath() %>/member/professorEnroll';">교수</button>
        </div>
<!------------------------- 회원가입 폼 시작 ------------------------>
        <form name="memberEnrollFrm" method="POST">
            <!-- 아이디, 비번, 비번확인, 성명, 생일, 학과(드롭다운), 학년, 휴대폰, 이메일 -->
            <div class="enrolle_content">
                <div class="row g-2">
                    <div class="col-md-8">
                        <div class="form-floating mb-2">
                          <input type="text" class="form-control input-sm" name="memberId" id="_memberId" placeholder="아이디" required>
                          <label for="memberId">아이디</label>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="form-floating">
                          <input type="button" class="form-control" value="중복검사" onclick="checkIdDuplicate();" style="padding: 18px"/>
                          <input type="hidden" id="idValid" value="0" />
                        </div>
                    </div>
                </div>

                <div class="form-floating mb-2">
                  <input type="password" class="form-control" name="password" id="_password" placeholder="비밀번호" required>
                  <label for="password">비밀번호</label>
                </div>

                <div class="form-floating mb-2">
                  <input type="password" class="form-control" name="passwordCheck" id="passwordCheck" placeholder="비밀번호확인" required>
                  <label for="passwordCheck">비밀번호확인</label>
                </div>

                <div class="form-floating mb-2">
                  <input type="text" class="form-control" name="memberName" id="memberName" placeholder="성명" required>
                  <label for="memberName">성명</label>
                </div>

                <div class="form-floating mb-2">
                  <input type="date" class="form-control" name="memberBirth" id="memberBirth" value="1994-07-07">
                  <label for="memberBirth">생년월일</label>
                </div>

<!------------------------- 학과 드롭 다운 ------------------------>
	            <div class="row g-2">
	              <div class="col-md">
	                <div class="form-floating mb-2">
	                  <select class="form-select" id="memberLevel" name="memberLevel" aria-label="Floating label select example">
	                    <option selected>1</option>
	                    <option>2</option>
	                    <option>3</option>
	                    <option>4</option>
	                  </select>
	                  <label for="memberLevel">학년</label>
	                </div>
	              </div>
	              <div class="col-md">
	                <div class="form-floating mb-2">
	                  <select class="form-select" id="departmentName" name="departmentName" aria-label="Floating label select example">
	                    <option selected>컴퓨터소프트웨어학과</option>
	                    <option>정보통신공학과</option>
	                    <option>전자공학과</option>
	                    <option>생활체육과</option>
	                    <option>경영학과</option>
	                  </select>
	                  <label for="departmentName">학과</label>
	                </div>
	              </div>
	            </div>
	            
	            <div class="form-floating mb-2">
	              <input type="text" class="form-control" name="memberPhone" id="memberPhone" placeholder="핸드폰" value="" required>
	              <label for="memberPhone">핸드폰</label>
	            </div>
	
	            <div class="form-floating mb-2">
	              <input type="text" class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일" value="" required>
	              <label for="memberEmail">이메일</label>
	            </div>
<!------------------------- 가입&취소 버튼------------------------>
                <div class="btn_bar">
                  <button type="submit" class="btn_bar1">가입하기</button>
                  <button type="button" class="btn_bar2" onclick="location.href='<%= request.getContextPath() %>/';">취소하기</button>
                </div>
            </div>
		</form>
	</div>
</section>
<form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate">
	<input type="hidden" name="memberId" />
</form>
</body>
</html>
<script>
const checkIdDuplicate = () => {
	// ^[A-Za-z]{1}[A-Za-z0-9]{3,19}$; // 4~20자리 영(대, 소),숫자 - 첫글자는 숫자 사용불가
	if(!/^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/.test(_memberId.value)){
		swal('유효성 검사[아이디]', "아이디는 영문자/숫자로 4글자 이상이어야 합니다.", 'warning');
	}
	else{	
		const title = "checkIdDuplicatePopup";
		const spec = "width=400px, height=300px";
		const popup = open("", title, spec);
		
		const frm = document.checkIdDuplicateFrm;
		frm.target = title; // 해당팝업에서 폼을 제출!
		frm.memberId.value = _memberId.value;
		frm.submit();
	}
};
passwordCheck.onblur = () => {
	if(_password.value !== passwordCheck.value){
		swal('비밀번호 불일치', "두 비밀번호가 일치하지 않습니다.", 'warning');
		return false;
	}	
	return true;
};

 
/*회원가입폼 유효성 검사*/
document.memberEnrollFrm.onsubmit = () => {
	// memberId
	// ^[A-Za-z]{1}[A-Za-z0-9]{3,19}$ // 4~20자리 영(대, 소),숫자 - 첫글자는 숫자 사용불가
	if(!/^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/.test(_memberId.value)){
		swal('유효성 검사[아이디]', "아이디는 영문자/숫자로 4글자 이상이어야 합니다.", 'warning');
		return false;
	}
	if(idValid.value !== "1") {
		swal('유효성 검사[아이디]', "아이디 중복검사 해주세요.", 'warning');
		return false;
	}
	// password
	if(!/^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/.test(_password.value)){
		swal('유효성 검사[비밀번호]', "비밀번호는 영문,숫자 조합 4 ~ 16글자 이상어야 합니다.", 'warning');
		return false;
	}
	if(!passwordCheck.onblur()){
		return false;		
	}
	// memberName
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		swal('유효성 검사[이름]', "이름은 한글 2글자이상 입력해주세요.", 'warning');
		return false;
	}
	// phone
	if(!/^010\d{8}$/.test(memberPhone.value)){
		swal('유효성 검사[전화번호]', "유효한 전화번호를 입력하세요.", 'warning');
		return false;
	}
	// email
	if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(memberEmail.value)){
		swal('유효성 검사[이메일]', "유효한 이메일을 입력하세요.", 'warning');
		return false;
	}
	return true;
}
</script>
