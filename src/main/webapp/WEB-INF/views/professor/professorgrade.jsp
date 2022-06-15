<%@page import="professorgrade.model.dto.ProfessorGrade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	List<ProfessorGrade> list = (List<ProfessorGrade>) request.getAttribute("list");
%>

<section class="section">
		<form name="memberUpdateFrm" method="post" action="<%= request.getContextPath() %>/professor/professorgrade">
		<table>
		<tr>
			<th><button>현학기 성적관리</button></th>
			<th><button>지난학기 성적관리</button></th>
		</tr>
		</table>
		<table id="record">
			<thead>
				<tr>
	                <th class="line1">년도, 학기</th>
	                <th class="line1">학년</th>
	                <th class="line1">이름</th>
	                <th class="line1">아이디</th>
	                <th class="line1">학과</th>
	                <th class="line1">중간</th>
	                <th class="line1">기말</th>
	                <th class="line1">과제</th>
	                <th class="line1">출석</th>
	                <th class="line1">백분위</th>
	                <th class="line1">학점</th>
				</tr>
			</thead>
			<tbody>
<%
			if(list != null && !list.isEmpty()) {
				for(ProfessorGrade professorgrade : list)
				{
%>
					<tr>
						<td class="line2"><%= professorgrade.getSubjectTerm() %></td>
			            <td class="line2"><%= professorgrade.getSubjectLebel() %></td>
			            <td class="line2"><%= professorgrade.getSubjectName() %></td>
			            <td class="line2"><%= professorgrade.getMemberId() %></td>
			            <td class="line2"><%= professorgrade.getSubjectName() %></td>
			            <td class="line2"><%= professorgrade.getDepartmentName() %></td>
			            <td class="line2"><%= professorgrade.getGrademiddle() %></td>
			            <td class="line2"><%= professorgrade.getGradefinal() %></td>
			            <td class="line2"><%= professorgrade.getGradeassignment() %></td>
			            <td class="line2"><%= professorgrade.getGradeattend() %></td>
			            <td class="line2"><%= professorgrade.getGradeattend() %></td>
			            <td class="line2"><%= professorgrade.getGradeattend() %></td>
					</tr>
<%
				}
			}
			else
			{
%>			
				<tr>
					<td colspan="9">이전 목록이 없습니다.</td>
				</tr>
<%				
			}
%>
			</tbody>
		</table>
	</form>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>