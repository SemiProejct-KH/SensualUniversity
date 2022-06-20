<%@ page import="professorgrade.model.dto.ProfessorGrade"%>
<%@ page import="professorlecture.model.dto.PresentLecture"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/records.css" />
<%
	List<ProfessorGrade> list = (List<ProfessorGrade>) request.getAttribute("list");
	List<PresentLecture> list2 = (List<PresentLecture>) request.getAttribute("list2");	
%>

<section class="section">
		
		<table>
			<tr>
				<th>
				<select class="form" id="selectlecture" name="selectlecture" onchange="function(this)">
<%
				if(list2 == null || list2.isEmpty()) {
%>
				<option selected>조회된 강의가 없습니다.</option>
<%
				} else {
%>	
				<option selected>강의 선택</option>
<%
				for(PresentLecture presentlecture : list2) {
%>
				<option value="register_name"><%=presentlecture.getPresentLecture()%></option>
<%
					}
				}
%>
				</select> 
				
				</th>
				
			</tr>
			<tr>
				<th><button type="button" onclick="location.href='<%= request.getContextPath() %>/professor/professorlecture';">현재학기 강의 및 학생조회</button></th>
				<th><button type="button" onclick="location.href='<%= request.getContextPath() %>/professor/professorlecture/past';">지난학기 강의 및 학생조회</button></th>
			</tr>
		</table>
</section>

		<table id="record">
			<thead>
				<tr>
	                <th class="line1">강의이름</th>
	                <th class="line1">강의년도, 학기</th>
	                <th class="line1">학생학년</th>
	                <th class="line1">학생이름</th>
	                <th class="line1">학생아이디</th>
	                <th class="line1">학생학과</th>
	                <th class="line1">중간고사</th>
	                <th class="line1">기말고사</th>
	                <th class="line1">과제점수</th>
	                <th class="line1">출석점수</th>
	                <th class="line1">제출</th>
				</tr>
			</thead>
			<tbody>
<%
			if(list != null && !list.isEmpty()) {
				for(ProfessorGrade professorgrade : list)
				{
%>
					<tr>
						<td class="line2"><%= professorgrade.getSubjectNo() %></td>
						<td class="line2"><%= professorgrade.getSubjectTerm() %></td>
			            <td class="line2"><%= professorgrade.getMemberLevel() %></td>
			            <td class="line2"><%= professorgrade.getMemberName() %></td>
			            <td class="line2"><%= professorgrade.getMemberNo() %></td>
			            <td class="line2"><%= professorgrade.getDepartmentNo() %></td>
			            <td class="line2"><input type="text" id="middle" name="middle" /></td>
			            <td class="line2"><input type="text" id="final" name="final"/></td>
			            <td class="line2"><input type="text" id="assignment" name="assignment"/></td>
			            <td class="line2"><input type="text" id="attend" name="attend"/></td>
			            <td class="line2"><button type="button" >제출버튼</button></td>
			            
					</tr>
<%
				}
			}
			else
			{
%>			
				<tr>
					<td colspan="11" style="text-align:center">강의수강중인 학생이 없습니다.</td>
				</tr>
<%				
			}
%>
			</tbody>
		</table>
		
<script>
$("#selectlecture").change(function(e){
	
	var selected = $("#selectlecture option:selected").text();
	
	$.ajax({
		url:"<%= request.getContextPath() %>/professor/lectureselect",
		type:'get',
		dataType:'text',
		processType:false,
		processData: false,
		data:{selected},
		success: function(result){           
			if (result) {
				alert("완료");
			} else {
				alert("전송된 값 없음");           
			}       
		},       
		error: function() {           
			alert("에러 발생");
		  }
	})
})
</script>

	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>