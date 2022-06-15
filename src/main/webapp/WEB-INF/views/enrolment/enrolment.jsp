<%@page import="enrolment.model.dto.Enrol"%>
<%@page import="studentclass.model.dto.StudentClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/record.css" />
<%
	List<Enrol> list = (List<Enrol>) request.getAttribute("list");
%>

<section class="section">
	<form name="memberUpdateFrm" action="<%= request.getContextPath() %>/enrol/enrolment">
	<h3>수강신청목록</h3>
		<div style="width:100%;height:700px;overflow-y:auto;overflow-x:hidden">
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
		                <th class="line1">선택</th>
					</tr>
				</thead>
				<tbody>
<%
				if(list != null && !list.isEmpty()) {
					for(Enrol enrol : list)
					{
%>
						<tr>
							<td class="line2" name="test1"><%= enrol.getSubjectTerm() %></td>
				            <td class="line2" name="test2"><%= enrol.getSubjectLebel() %></td>
				            <td class="line2" name="test3"><%= enrol.getDepartmentName() %></td>
				            <td class="line2" name="test4"><%= enrol.getSubjectNo() %></td>
				            <td class="line2" name="test5"><%= enrol.getSubjectName() %></td>
				            <td class="line2" name="test6"><%= enrol.getMemberName() %></td>
				            <td class="line2" name="test7"><%= enrol.getSubjectTime() %></td>
				            <td class="line2" name="test8"><%= enrol.getSubjectClassroom() %></td>
				            <td class="line2" name="test9"><%= enrol.getSubjectCredit() %></td>
				            <td class="line2"><input type="button" class="checkBtn" value="수강신청" /></td>
						</tr>
<%
					}
				}
				else
				{
%>			
					<tr>
						<td colspan="9">강의가 없습니다.</td>
					</tr>
<%				
				}
%>
				</tbody>
			</table>
		</div>
	</form>
	<script>
		$(".checkBtn").click(function(){ 
			
			let str = ""
			let tdArr = new Array();	// 배열 선언
			let checkBtn = $(this);
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			let tr = checkBtn.parent().parent();
			let td = tr.children();
			
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			let a = td.eq(0).text();
			let b = td.eq(1).text();
			let c = td.eq(2).text();
			let d = td.eq(3).text();
			let e = td.eq(4).text();
	        let f = td.eq(5).text();
	        let g = td.eq(6).text();
	        let h = td.eq(7).text();
			
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){	
				tdArr.push(td.eq(i).text());
			});
			
			console.log("배열에 담긴 값 : "+tdArr);
			
			str +=	a + b + c + d + e + f + g + h;	
		});
	</script>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>