<%@page import="member.model.dto.MemberRole"%>
<%@page import="member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) request.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학사정보시스템</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" />
<!-- jQuery -->
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<body>
	<section>
		<div id="top_bar">
			<div id="top_bar_btn">
				<button type="button" class="btn btn-primary">마이페이지</button>
				<button type="button" class="btn btn-primary">로그아웃</button>
			</div>
		</div>
		<div id="uni_name">
			<h1>Sensual University</h1>
		</div>
		<div id="navbar">
			<ul class="nav justify-content-center">
		<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) {%>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#">강의조회</a></li>
				<li class="nav-item"><a class="nav-link" href="#">성적조회</a></li>
				<li class="nav-item"><a class="nav-link" href="#">수강신청</a></li>
		<% } else if(loginMember != null && loginMember.getMemberRole() == MemberRole.P){%>
				<li class="nav-item"><a class="nav-link" href="#">강의관리</a></li>
				<li class="nav-item"><a class="nav-link" href="#">성적관리</a></li>
		<% } else {%>
				<li class="nav-item"><a class="nav-link" href="#">회원관리</a></li>
		
		<% } %>
				<li class="nav-item"><a class="nav-link">게시판</a></li>
				<li class="nav-item"><a class="nav-link">메세지</a></li>
			</ul>
		</div>
	</section>