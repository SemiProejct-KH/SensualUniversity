<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>로그인</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<head>
<body>
    <div id="backdiv">
        <div id="login">
            <h1>Sensual University</h1>
            <span>학생용</span>&nbsp<span>학번과 비밀번호를 입력해주세요.</span>
            <form id="loginFrm" name="loginFrm" method="POST" action="">
                <table>
                    <tr>
                        <td><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1" value=""></td>
                        <td><input type="submit" value="로그인" tabindex="3"></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="비밀번호찾기" id="findPassword"/>
                            <input type="button" value="회원가입">
                        </td>
                    </tr>
                </table>
                <hr>
                <p>서울시 강남구 테헤란로 감각대 TEL 02-1235-1235 FAX 02-321-321</p>
                <p>sensual university</p>
            </form>
        </div>
         <li><a href="<%= request.getContextPath() %>/main/test1.jsp">헤더 풋터 테스트</a></li>
    </div>
   
</body>
</html>