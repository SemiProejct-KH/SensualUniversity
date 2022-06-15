<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
  <!-- jQuery -->
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
  <!-- css -->
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#duplicated{color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
	<% if(available) { %>
		<p>[<%= memberId %>]는 사용가능합니다.</p>
		<p><button type="button" onclick="closePopup();">닫기</button></p>
		<script>
		const closePopup = () => {
			// opener 부모창 window객체
			const frm = opener.document.memberEnrollFrm;
			frm.memberId.value = '<%= memberId %>';
			frm.idValid.value = 1;
			
			self.close(); // 현재창 닫기
		};
		</script>
	<% } else { %>
		<p>[<span id="duplicated"><%= memberId %></span>]는 이미 사용중입니다.</p>
		<form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate">
			<input type="text" name="memberId" />
			<input type="submit" value="중복검사" />
		</form>
	<% } %>
	</div>
</body>
</html>
