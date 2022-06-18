<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null)
		session.removeAttribute("msg");
	
%>
<!DOCTYPE html>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/findPw.css?after" />
<!-- favicon -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico">
</head>
<body>
	<section class="section_signup">
	<div class="title">
	    <h2>비밀번호 찾기</h2>
    </div>
<!------------------------- 비밀번호 찾기 폼 시작 ------------------------>
	<form name="findPasswordFrm" method="POST">
        <div class="form-floating mb-2">
          <input type="text" class="form-control" id="memberId" name="memberId"placeholder="아이디">
          <label for="memberId">아이디</label>
        </div>
        <div class="form-floating mb-2">
          <input type="text" class="form-control" id="memberName" name="memberName"placeholder="성명">
          <label for="memberName">성명</label>
        </div>
        <div class="form-floating mb-2">
          <input type="text" class="form-control" id="memberBirth" name="memberBirth" placeholder="생년월일">
          <label for="memberBirth">생년월일 (예:2000-10-15, 1997-01-01)</label>
        </div>
        <p>*아이디, 이름, 생년월일은 필수 입력 항목입니다.</p>
<!------------------------- 찾기 버튼------------------------>
        <div class="align_btn">
            <div class="role_btn">
              <button type="submit" onClick="pw_search()">찾기</button>
            </div>
        </div>
	</form>
</section>	
</body>
</html>
<script>
window.onload = () => {
	<% if(msg != null){ %>
	alert("<%= msg %>");
	<% } %>
}
document.findPasswordFrm.onsubmit = () => {
	// memberId
	// /^[a-z]+[a-z0-9]{5,11}$/g; // 영문자로 시작하는 영문자 또는 숫자 6 ~ 12자
	if(!/^[a-z]+[a-z0-9]{5,11}$/g.test(memberId.value)){
		alert("올바른 아이디를 입력해주세요.");
		return false;
	}
	// memberName
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름을 입력해주세요.");
		return false;
	}

	// 생일
	if(!/^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/.test(memberBirth.value)){
		alert("올바른 생년월일을 입력해주세요.");
		return false;
	}
	return true;
}
</script>