<%@ page import="professorlecture.model.dto.ProfessorLecture"%>
<%@ page import="professorlecture.model.dto.PresentLecture"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/record.css" />
<%
	List<PresentLecture> list2 = (List<PresentLecture>) request.getAttribute("list2");	
%>

<section class="section">
		
		<table>
			<tr>
				<th>
				<select class="form-select form-select-lg mb-3" id="selectlecture" aria-label=".form-select-lg example" onchange="function(this)">
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

<script>
$("#selectlecture").change(function(e){
	
	var selected = $("#selectlecture option:selected");
	
	$.ajax({
		url:"data.lecture",
		type:'post',
		dataType:"json",
		data:{selected},
		success:function(data){
			console.log('success');
		}
		error:function(){
			console.log('error');
		}
	})
	
	
	
})
</script>

	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>