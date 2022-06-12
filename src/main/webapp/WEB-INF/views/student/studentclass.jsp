<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/record.css" />

<section class="section">
			<form name="memberUpdateFrm" method="post" action="<%= request.getContextPath() %>/studentclass">
		<table>
		<tr>
			<th><button>현학기 강의조회</button></th>
			<th><button>지난학기 강의조회</button></th>
		</tr>
		</table>
		<table id="record">
			<thead>
				<tr>
	                <th class="line1">년도</th>
	                <th class="line1">학기</th>
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
	</form>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>