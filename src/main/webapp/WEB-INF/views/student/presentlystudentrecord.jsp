<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/records.css" />
<%
	int StudentNo = loginMember.getMemberNo();
%> 
<section class="section">
		<table>
		<tr>
			<th><a href="<%= request.getContextPath() %>/student/presentlystudentrecord">현학기 성적조회</a></th>
			<th><a href="<%= request.getContextPath() %>/student/studentrecord">지난학기 성적조회</a></th>
		</tr>
		</table>
		<table id="record">
			<thead>
				<tr>
	                <th class="line1">학년</th>
	                <th class="line1">개설학과</th>
	                <th class="line1">교과목코드</th>
	                <th class="line1">담당교수</th>
	                <th class="line1">출석</th>
	                <th class="line1">중간</th>
	                <th class="line1">기말</th>
	                <th class="line1">과제</th>
	                <th class="line1">백분위</th>
	                <th class="line1">학점</th>
				</tr>
			</thead>
			<tbody>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
		            <td class="line2">테스트</td>
			</tbody>
		</table>
		<br />
		<br />
			<a href="<%= request.getContextPath() %>/chat/chatroom"><button>채팅으로 이의제기하기</button></a>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>