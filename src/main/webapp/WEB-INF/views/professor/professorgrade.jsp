<%@ page import="professorgrade.model.dto.ProfessorGrade"%>
<%@ page import="professorgrade.model.dto.ProfessorGradeDropbox"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/records.css" />
<%
List<ProfessorGradeDropbox> GradeDropbox = (List<ProfessorGradeDropbox>) request.getAttribute("GradeDropbox");
List<ProfessorGrade> list = (List<ProfessorGrade>) request.getAttribute("list");
%>

<section class="section">
	<select class="form" id="selectlecture" name="selectlecture">
		<%
		if (GradeDropbox == null || GradeDropbox.isEmpty()) {
		%>
		<option selected>조회된 강의가 없습니다.</option>
		<%
		} else {
		%>
		<option selected>강의 선택</option>
		<%
		for (ProfessorGradeDropbox professorgradedropbox : GradeDropbox) {
		%>
		<option value="<%=professorgradedropbox.getSubjectNo()%>"><%=professorgradedropbox.getPresentLecture()%></option>
		<%
		}
		}
		%>
	</select>

	<table id="record">
		<thead id="recordthead">
			<tr>
				<th class="line1">강의년도, 학기</th>
				<th class="line1">학생학년</th>
				<th class="line1">학생이름</th>
				<th class="line1">학생아이디</th>
				<th class="line1">학생학과</th>
				<th class="line1"></th>

			</tr>
		</thead>
		<tbody id="recordtbody">
		</tbody>
	</table>

	<table id="grade">
		<thead>
			<tr>
				<th>중간고사</th>
				<th>기말고사</th>
				<th>과제점수</th>
				<th>출석점수</th>
				<th>제출</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="middle" name="middle"
					style="text-align: center" /></td>
				<td><input type="text" id="final" name="final"
					style="text-align: center" /></td>
				<td><input type="text" id="assignment" name="assignment"
					style="text-align: center" /></td>
				<td><input type="text" id="attend" name="attend"
					style="text-align: center" /></td>
				<td><input type="button" id="gradesummit" class="button"
					value="성적제출" /></td>
			</tr>
		</tbody>
	</table>

</section>


<script>
$("#selectlecture").change(function(e){
	
	 var selected = $("#selectlecture").val();
	    console.log(selected);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/professor/grade/select",
		type:'get',
		dataType:'json',
		data:{
			subjectNo : selected
		},
		success: function(result){           
			if (result) {
				console.log(result);
				receivelist(selected);
			} else {
				alert("전송된 값 없음");           
			}       
		},       
		error: function() {           
			alert("에러 발생");
		  }
	});
});

const receivelist = (selected) => {
	$.ajax({
	url:"<%=request.getContextPath()%>/professor/grade/professorlist",
	type:'get',
	dataType:'json',
	data:{subjectNo : selected},
	success: function(result){
		if (result){
			 console.log("두번째 ajax" + result);
	            console.log("test");    
	            
	            const tbody = document.querySelector("#recordtbody");
	            tbody.innerHTML = "";
	            
	            result.forEach((studentlist, index) => {
	                console.log(index, studentlist);
	                const {subjectTerm, memberLevel, memberName, memberId, departmentName} = studentlist;
	                const tr = document.createElement("tr");
	                
	                const tdSubjectTerm = document.createElement("td");
	                tdSubjectTerm.append(subjectTerm);
	                const tdMemberLevel = document.createElement("td");
	                tdMemberLevel.append(memberLevel);
	                const tdMemberName = document.createElement("td");
	                tdMemberName.append(memberName);
	                const tdMemberId = document.createElement("td");
	                tdMemberId.append(memberId);
	                const tdDepartmentName = document.createElement("td");
	                tdDepartmentName.append(departmentName);
	                const tdChoice = document.createElement("td");
	                tdChoice.append();
	                
	                tr.append(tdSubjectTerm, tdMemberLevel, tdMemberName, tdMemberId, tdDepartmentName, tdChoice);
	                tbody.append(tr);
	            });
	           
			
		} else {
			alert("전송된 값 없음"); 
		}
	}, error: function() {           
    	alert("에러 발생");
    }
});
};



</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>