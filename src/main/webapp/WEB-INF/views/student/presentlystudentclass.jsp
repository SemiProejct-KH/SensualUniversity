<%@page import="studentclass.model.dto.StudentClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/record.css" />

<section class="section">
			<form name="memberUpdateFrm" action="<%= request.getContextPath() %>/class/presentlystudentclass">
		<table>
		<tr>
			<th><a href="<%= request.getContextPath() %>/class/presentlystudentclass">현학기 강의조회</a></th>
			<th><a href="<%= request.getContextPath() %>/class/studentclass">지난학기 강의조회</a></th>
		</tr>
		</table>
		<table id="record">
			<thead>
				<tr>
	                <th class="line1">년도, 학기</th>
	                <th class="line1">학년</th>
	                <th class="line1">개설학과</th>
	                <th class="line1">교과목코드</th>
	                <th class="line1">교과목명</th>
	                <th class="line1">담당교수</th>
	                <th class="line1">강의교시</th>
	                <th class="line1">강의실</th>
	                <th class="line1">취득학점</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>