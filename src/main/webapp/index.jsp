<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<head>
<body>
    <div id="login_main">
        <section id="section_slide">dasdas</section>
        <section id="section_login">
            <h2>Sensual University</h2>
            <div>
                <h6>학생용 학번과 비밀번호를 입력해주세요.</h6>
                <div id="input_container">
                    <input type="text" name="input_id" id="input_id"> 
                    <input type="password" name="input_pw" id="input_pw">
                </div>
                <button type="button" class="btn btn-light">로그인</button>
            </div>
            <button type="button" class="btn btn-light">비밀번호 찾기</button>
            <button type="button" class="btn btn-light">회원가입</button>
            <hr>
            <h6>서울시 강남구 테헤란로 감각대 TEL 02-1235-1235 FAX 02-321-321</h6>
            <h6>sensual university</h6>
        </section>
    </div>
</body>
</html>