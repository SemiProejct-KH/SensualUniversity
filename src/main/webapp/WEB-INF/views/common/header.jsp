<%@page import="member.model.dto.MemberExt"%>
<%@page import="member.model.dto.MemberRole"%>
<%@page import="member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberExt loginMember = (MemberExt) session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학사정보시스템</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css?after" />
<!-- jQuery -->
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<!-- favicon -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico">
<body>
	<section>
		<div id="top_bar">
			<div id="top_bar_btn">
				<div class="btns2">
                     <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
                      <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#" onclick="location.href='<%= request.getContextPath() %>/member/memberMyPage';" style="text-decoration: none; color:white;">마이페이지</a></li>
                        <li class="breadcrumb-item"><a href="#" onclick="location.href='<%= request.getContextPath() %>/member/logout';" style="text-decoration: none; color:white;">로그아웃</a></li>
                      </ol>
                    </nav>
                </div>
			</div>
		</div>
		<div id="uni_name">
			<a href="#" class="title" onclick="location.href='<%= request.getContextPath() %>/main/mainPage';" style="text-decoration: none;">Sensual University</a>
		</div>
		<div id="navbar">
			<ul class="nav justify-content-center">
		<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) {%>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%= request.getContextPath() %>/class/presentlystudentclass">강의조회</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/student/presentlystudentrecord">성적조회</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/enrol/enrolment">수강신청</a></li>
		<% } else if(loginMember != null && loginMember.getMemberRole() == MemberRole.P){%>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/professor/professorlecture">강의관리</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/professor/professorgrade">성적관리</a></li>
		<% } else {%>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/admin/memberList">회원관리</a></li>
		<% } %>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/notice/noticeList">게시판</a></li>
				<li class="nav-item"><a class="nav-link" href='<%= request.getContextPath() %>/chat/chatroom'>메세지</a></li>
			</ul>
		</div>
	</section>