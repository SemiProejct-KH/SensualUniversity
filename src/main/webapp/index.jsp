<%@page import="member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) request.getAttribute("loginMember");

	String msg = (String) session.getAttribute("msg");
	if(msg != null)
		session.removeAttribute("msg");

	String saveId = null; // 아이디저장을 체크한 경우, memberId값이 담길 변수
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie : cookies){
			System.out.println("Cookie{" + cookie.getName() + "=" + cookie.getValue() + "}");
			if("saveId".equals(cookie.getName())){
				saveId = cookie.getValue();
			}
		}
	}
	else {
		// System.out.println("> 이번 요청에 전달된 cookie가 없습니다.");
	}
%>
<!DOCTYPE html>
<html>
<title>로그인</title>
<!-- jQuery -->
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css" />

<!-- script -->
<script>
// 로그인폼 정규표현식
window.onload = () => {
<% if(msg != null){ %>
alert("<%= msg %>");
<% } %>
<% if(loginMember == null) { %>
	document.loginFrm.onsubmit = (e) => {
		const memberIdVal = memberId.value;
		const passwordVal = password.value;
		//const idRegExp = /^[a-z]+[a-z0-9]{5,11}$/g; // 영문자로 시작하는 영문자 또는 숫자 6 ~ 12자
		const idRegExp = /^.{4,}$/; // 4글자이상
		//const pwRegExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/; // 4 ~ 16자 영문,숫자 조합
		const pwRegExp = /^.{4,}$/; // 4글자이상
		if(!idRegExp.test(memberIdVal)){
			alert("유효한 아이디를 입력해주세요.");
			memberId.select();
			return false;
		}
		if(!pwRegExp.test(passwordVal)){
			alert("유효한 비밀번호를 입력해주세요.");
			password.select();
			return false;
		}
	}
<% } %>
}
</script>
<head>
<body>
	<div id="login_main">
		<!-- slide -->
		<section id="section_slide">
			<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			      <img src="<%= request.getContextPath() %>/images/University.jpg" class="d-block w-100" alt="">
			    </div>
			    <div class="carousel-item">
			      <img src="..." class="d-block w-100" alt="">
			    </div>
			    <div class="carousel-item">
			      <img src="..." class="d-block w-100" alt="">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</section>
		<!-- 로그인 -->
		<section id="section_login">
			<h2>Sensual University</h2>
			<form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/member/login">
				<div>
					<h6>학생용 학번과 비밀번호를 입력해주세요.</h6>
					<div id="input_container">
						<input type="text" name="memberId" id="memberId" placeholder="아이디" value="<%= saveId != null ? saveId : "" %>"> 
						<input type="password" name="password" id="password" placeholder="비밀번호">
					</div>
					<button type="submit" class="btn btn-light">로그인</button>
				</div>
				<input class="form-check-input" type="checkbox" id="saveId" name="saveId" <%= saveId != null ? "checked" : "" %>>
				<label class="form-check-label" for="saveId">아이디저장</label>
				<button type="button" class="btn btn-light" onclick="location.href='<%= request.getContextPath() %>/member/findPw';">비밀번호 찾기</button>
				<button type="button" class="btn btn-light" onclick="location.href='<%= request.getContextPath() %>/member/signupAgree';">회원가입</button>
			</form>
			<hr>
			<h6>서울시 강남구 테헤란로 감각대 TEL 02-1235-1235 FAX 02-321-321</h6>
			<h6>sensual university</h6>
		</section>
	</div>
</body>
</html>