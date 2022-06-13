<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/record.css" />
<%
	int StudentNo = loginMember.getMemberNo();
%> 
<section class="section">
			<form name="memberUpdateFrm" method="post" action="<%= request.getContextPath() %>/studentrecord">
		<table>
		<tr>
			<th><button>현학기 성적조회</button></th>
			<th><button>지난학기 성적조회</button></th>
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
	                <th class="line1">이의제기</th>
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
		            <td class="line2"><input type="checkbox"></td>
			</tbody>
		</table>
	</form>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>