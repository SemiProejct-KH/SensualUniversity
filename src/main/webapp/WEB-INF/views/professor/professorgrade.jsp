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
<section class="section" >
<div style="margin-top:100px;"></div>
	<select class="custom-select form-select form-select-sm" aria-label=".form-select-sm example" id="selectlecture" name="selectlecture">
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

	<table id="record" class ="table table-striped table-hover" style="margin:auto">
		<thead id="recordthead" class="table-primary">
			<tr>
				<th class="line1">강의년도, 학기</th>
				<th class="line1">학생학년</th>
				<th class="line1">학생이름</th>
				<th class="line1">학생아이디</th>
				<th class="line1">학생학과</th>
				<th class="line1">선택</th>

			</tr>
		</thead>
		<tbody id="recordtbody">
		</tbody>
	</table>

	<table id="grade" class ="table table-striped table-hover" style="margin:auto">
		<thead class="table-primary">
			<tr>
				<th>중간고사</th>
				<th>기말고사</th>
				<th>과제점수</th>
				<th>출석점수</th>
				<th>제출</th>
			</tr>
		</thead>
		<tbody id="gradetbody">
			<tr id="gradetr" name="gradetr" style="display: none">
				<td><input type="number" id="middle" name="middle" placeholder="30" style="text-align:center" min="0" max="30" /></td>
				<td><input type="number" id="final" name="final" placeholder="40" style="text-align:center" min="0" max="40" /></td>
				<td><input type="number" id="assignment" name="assignment" placeholder="20" style="text-align:center" min="0" max="20" /></td>
				<td><input type="number" id="attend" name="attend" placeholder="10" style="text-align:center" min="0" max="10" /></td>
				<td><input type="button" id="button" class="button" value="성적입력" /></td>
			</tr>
		</tbody>
	</table>
<div style="margin-bottom:100px;"></div>
</section>


<script>
$("#selectlecture").change(function(e){
	
	 const selected = $("#selectlecture").val();
	    console.log(selected);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/professor/grade/select",
		type:'get',
		dataType:'json',
		data:{
			subjectNo : selected
		},
		success: function(result){           
			if (result) {
				console.log(result);
				receivelist(selected);
			} else {
				alert("전송된 값 없음");           
			}       
		},       
		error: function() {           
			alert("에러 발생");
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
	                const btn = document.createElement("button");
	                btn.textContent = "출력";
	                btn.onclick = (e) => {
	                	e.stopPropagation(); // 이벤트 전파 중지
	                	resisterNo(selected, memberId);
	                }
	                tdChoice.append(btn);
	                
	                
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
	
const resisterNo = (subjectNo, memberId) => {
	
	console.log(subjectNo, memberId);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/professor/grade/resisterNo",
		type:'get',
		dataType:'json',
		data:{
			subjectNo,
			memberId
		},
		success: function(result){
			if(result){
				//alert("result : " +  JSON.stringify(result) );
				/* alert(result['resisterNo']);
				alert("resisterNo : "+result[0]['resisterNo']); */
				resisterTempNo = result[0]['resisterNo'];
				//alert("resisterTempNo : " + resisterTempNo);
				console.log("세번째 ajax" + result);
				
				const row = document.getElementById('gradetr');
				row.style.display = '';
				
				}
		}, error: function(){
			alert("에러 발생");
		}
	});
};

var resisterTempNo = 0;

$(".button").click(function(){
	

	
		const gradeMiddle = document.getElementById('middle').value;
		const gradeFinal = document.getElementById('final').value;
		const gradeAssignment = document.getElementById('assignment').value;
		const gradeAttend = document.getElementById('attend').value;
		
		
		
		
		
		
			
		$.ajax
		({
			url:"<%=request.getContextPath()%>/professor/grade/gradeInput",
			type:"get",
			data:
			{
				resisterTempNo,
				gradeMiddle,
				gradeFinal,
				gradeAssignment,
				gradeAttend
			},
			
			async: false,
			success: function()
			{
					location.reload();
			}
		});
	//};
});
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>