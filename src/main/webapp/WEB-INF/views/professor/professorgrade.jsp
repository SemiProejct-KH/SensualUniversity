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
				<th class="line1">선택</th>

			</tr>
		</thead>
		<tbody id="recordtbody">
		</tbody>
	</table>

	<table id="grade">
		<thead>
			<tr>
				<th>수강번호</th>
				<th>중간고사</th>
				<th>기말고사</th>
				<th>과제점수</th>
				<th>출석점수</th>
				<th>제출</th>
			</tr>
		</thead>
		<tbody id="gradetbody">

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
	                	registerNo(selected, memberId);
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
	
const registerNo = (subjectNo, memberId) => {
	
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
				console.log("세번째 ajax" + result);
				
				const tbody = document.querySelector("#gradetbody")
				tbody.innterHTML = "";
				result.forEach((gradelist, index) => {
					console.log(index, gradelist);
					const {registerNo} = gradelist;
					const tr = document.createElement("tr");
					
					// 이부분은 지우셔도 됨
					for(let i in gradelist) {
						console.log("registerNo 1 = ", gradelist[i])
					}
					console.log("registerNo 2 = ", gradelist)
					//
					
					const tdRegisterNo = document.createElement("td");
					for(let i in gradelist) {
						tdRegisterNo.append(gradelist[i]);
					}
					const tdGradeMiddle = document.createElement("td");
					const grademiddle = document.createElement("input");
					tdGradeMiddle.append(grademiddle);
					const tdGradeFinal = document.createElement("td");
					const gradefinal = document.createElement("input");
					tdGradeFinal.append(gradefinal);
					const tdGradeAssignment = document.createElement("td");
					const gradeassignment = document.createElement("input");
					tdGradeAssignment.append(gradeassignment);
					const tdGradeAttend = document.createElement("td");
					const gradeattend = document.createElement("input");
					tdGradeAttend.append(gradeattend);
					const tdButton = document.createElement("td");
					const button = document.createElement("button");
					button.textContent = "성적입력";
					button.onclick = (e) => {
						e.stopPropagation();
						gradeInput(registerNo, grademiddle, gradefinal, gradeassignment, gradeattend);
					}
					tdButton.append(button);
					
					tr.append(tdRegisterNo, tdGradeMiddle, tdGradeFinal, tdGradeAssignment, tdGradeAttend, tdButton);
					tbody.append(tr);
					
				})
			} else {
				alert("전송된 값 없음");
			}
		}, error: function(){
			alert("에러 발생");
		}
	});
};

const gradeInput = (registerNo, grademiddle, gradefinal, gradeassignment, gradeattend) => {
	
	console.log(registerNo)
	
	$.ajax({
		url:"<%=request.getContextPath()%>/professor/grade/gradeInput",
		type:"get",
		data:{
			registerNo, 
			grademiddle, 
			gradefinal, 
			gradeassignment, 
			gradeattend
		},
		
		async: false,
		success: function(data){
			alert("네번째 ajax 성공")
		},
		error: function(){
		}
	});
	
}


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>