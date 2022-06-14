<%@ page import="professorlecture.model.dto.ProfessorLecture"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<ProfessorLecture> list = (List<ProfessorLecture>) request.getAttribute("list");
%>

<section class="section">
	<h2 align="center" style="margin-top:200px;">헤더 풋터 테스트</h2>	
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>