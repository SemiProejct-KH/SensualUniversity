<%@page import="studentclass.model.dto.PresentlyStudentClass"%>
<%@page import="studentclass.model.dto.StudentClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/record.css" />
<%
	List<PresentlyStudentClass> list = (List<PresentlyStudentClass>) request.getAttribute("list");
%>

<section class="section">
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
	                <th class="line1">취소 여부</th>
				</tr>
			</thead>
			<tbody>
<%
			if(list != null && !list.isEmpty()) {
				for(PresentlyStudentClass presentlyStudentClass : list)
				{
%>
					<tr>
						<td class="line2"><%= presentlyStudentClass.getSubjectTerm() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectLebel() %></td>
			            <td class="line2"><%= presentlyStudentClass.getDepartmentName() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectNo() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectName() %></td>
			            <td class="line2"><%= presentlyStudentClass.getMemberName() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectTime() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectClassroom() %></td>
			            <td class="line2"><%= presentlyStudentClass.getSubjectCredit() %></td>
			            <td class="line2"><input type="button" class="checkBtn" value="수강취소"></td>
					</tr>
<%
				}
			}
			else
			{
%>			
				<tr>
					<td colspan="9">현재 신청한 강의가 없습니다.</td>
				</tr>
<%				
			}
%>
			</tbody>
		</table>
	<script>
	$(".checkBtn").click(function(){ 
		let tdArr = new Array();	
		let checkBtn = $(this);
		var string = ["abc","def"];
		
		let tr = checkBtn.parent().parent();
		let td = tr.children();
		
		
		console.log("클릭한 Row의 모든 데이터 : "+tr.text());

		tdArr.push(td.eq(0).text());
		tdArr.push(td.eq(1).text());
		tdArr.push(td.eq(2).text());
		tdArr.push(td.eq(3).text());
		tdArr.push(td.eq(4).text());
		tdArr.push(td.eq(5).text());
		tdArr.push(td.eq(6).text());
		tdArr.push(td.eq(7).text());
		tdArr.push(td.eq(8).text());
		
		console.log("배열에 담긴 값 : "+tdArr);
		console.log(tdArr[3]);
		
		$.ajax({
            url: "<%= request.getContextPath() %>/delete/deleteEnrol",
            type: "POST",           
            data: {
	            	term : tdArr[0],
	                lebel : tdArr[1],
	                mname : tdArr[2],
	                sno : tdArr[3]
            	},
            
            async: false,
            success: function(data) {
            	alert("취소 성공");
            },
            error: function(msg, error) {
                alert(error);
            }
		});
		
		window.location.reload();
	});
	</script>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>