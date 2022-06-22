<%@page import="member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 주석 추가 주석추가 한번 더 추가 한번 더더 추가
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
<head>
<title>로그인</title>
<!-- jQuery -->
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Noto+Sans:ital,wght@0,300;0,400;0,500;0,600;1,200;1,300&display=swap" rel="stylesheet">
<!-- favicon -->
<link rel="shortcut icon" href="images/favicon.ico">
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css?after" />
<!-- SweetAlert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- script -->
<script>
// 로그인폼 정규표현식
window.onload = () => {
<% if(msg != null){ %>
	//로그인 실패
	<% if("memberLogin".equals(msg)) {%>
		swal('로그인 실패', "아이디 또는 비밀번호가 일치하지 않습니다.", 'error');
	<% } %>
	// 비밀번호 찾기 성공
	<% if("findPwRusultSuccess".equals(msg)) {%>
	swal('비밀번호 변경 성공', "변경된 비밀번호로 로그인해주세요.", 'success');
	<% } %>
	// 회원탈퇴
	<% if("memberDelete".equals(msg)) {%>
	swal('회원탈퇴 성공', "성공적으로 회원탈퇴되었습니다.", 'success');
	<% } %>
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
			swal('로그인 실패', "유효한 아이디를 입력해주세요.", 'warning');
			memberId.select();
			return false;
		}
		if(!pwRegExp.test(passwordVal)){
			swal('로그인 실패', "유효한 비밀번호를 입력해주세요.", 'warning');
			password.select();
			return false;
		}
	}
<% } %>
}
</script>
</head>
<body>
	<div id="login_main">
		<!-- slide -->
		<section id="section_slide">
			<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
			  </div>
			  <div class="carousel-inner">
			    <div class="carousel-item active" data-bs-interval="10000">
			      <img src="<%= request.getContextPath() %>/images/main1.png" class="d-block w-100" alt="" style="min-height: 100vh;">
			    </div>
			    <div class="carousel-item" data-bs-interval="2000">
			      <img src="<%= request.getContextPath() %>/images/main2.png" class="d-block w-100" alt="" style="min-height: 100vh;">
			    </div>
			    <div class="carousel-item">
			      <img src="<%= request.getContextPath() %>/images/main3.png" class="d-block w-100" alt="" style="min-height: 100vh;">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</section>
		<!-- 로그인 -->
		<section id="section_login">
			<div class="login_wrp">
                <h3 class="logo_title">
  					<span></span><!-- logo img -->
				</h3>
                <form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/member/login">
                    <div class="login_content">
                        <h2 class="login_title">
                            <span class="text_hide"></span>
                            아이디와 비밀번호를 입력해 주세요.
                        </h2>
                        <div id="input_container">
                            <input type="text" name="memberId" id="memberId" placeholder=" 아이디" value="<%= saveId != null ? saveId : "" %>"> 
                            <input type="password" name="password" id="password" placeholder=" 비밀번호">
                        </div>
                        <div>
                            <button type="submit" class="login_btn">로그인</button>
                        </div>
                       <br>
                        <div class="btns1">
                            <input class="form-check-input" type="checkbox" id="saveId" name="saveId" <%= saveId != null ? "checked" : "" %>>
                            <label class="form-check-label" for="saveId">아이디저장</label>
                        </div>
                        <div class="btns2">
                             <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
                              <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                	<a href="#" onclick="location.href='<%= request.getContextPath() %>/member/findPw';" style="text-decoration: none;">비밀번호 찾기</a>
                               	</li>
                                <li class="breadcrumb-item">
                                	<a href="#" onclick="location.href='<%= request.getContextPath() %>/member/signupAgree';" style="text-decoration: none;">회원가입</a>
                               	</li>
                              </ol>
                            </nav>
                        </div>
                        <hr>
                        <h6  align="center">서울시 강남구 테헤란로 감각대 TEL 02-1235-1235 FAX 02-321-321</h6>
                        <h6 align="center">sensual university</h6>
                    </div>
                </form>
            </div>
		</section>
	</div>
</body>
</html>