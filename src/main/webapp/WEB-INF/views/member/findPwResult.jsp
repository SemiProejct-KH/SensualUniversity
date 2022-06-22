<%@page import="member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member) session.getAttribute("member");
	String msg = (String) session.getAttribute("msg");
	if(msg != null)
		session.removeAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
  <title>비밀번호 찾기</title>
  <!-- jQuery -->
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
  <!-- css -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/findPwResult.css?after" />
  <!-- favicon -->
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico">
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<section class="section_pw_result">
    <div class="wrapper">
        <div class="content">
            <div class="title">
                <h3>비밀번호 수정</h3>
            </div>
            <form 
                name="findPwResult" 
                action="<%=request.getContextPath()%>/member/findPwResult"
                method="post" >
            
                <div class="form-floating mb-2">
                  <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="비밀번호" required>
                  <label for="newPassword">새로운 비밀번호</label>
                </div>

                <div class="form-floating mb-2">
                  <input type="password" class="form-control" id="newPasswordCheck" placeholder="비밀번호" required>
                  <label for="newPasswordCheck">비밀번호 확인</label>
                </div>
                
                <div class="btn_bar">
                  <button type="submit" class="btn_bar1">변경</button>
                  <button type="button" class="btn_bar2" onclick="location.href='<%= request.getContextPath() %>/';">취소하기</button>
                </div>
                <input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
            </form>
        </div>
    </div>
</section>
</body>
</html>
<script>
newPasswordCheck.onblur = () => {
	if(newPassword.value !== newPasswordCheck.value){
		swal('비밀번호 불일치', "두 비밀번호가 일치하지 않습니다.", 'warning');
		return false; // 폼 유효성 검사에서 사용
	}	
	return true;
};
/*비밀번호 유효성검사*/
document.findPwResult.onsubmit = () => {
	
	if(!/^[A-Za-z0-9!@#$%^&*()]{4,}$/.test(newPassword.value)){
		swal('유효성 검사[비밀번호]', "새 비밀번호는 영문,숫자 조합 4 ~ 16글자 이상어야 합니다.", 'warining');
		return false;
	}
	if(!newPasswordCheck.onblur()){
		return false; 
	}
	return true;
}	
</script>