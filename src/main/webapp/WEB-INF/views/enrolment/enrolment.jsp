<%@page import="enrolment.model.dto.Enrol"%>
<%@page import="studentclass.model.dto.StudentClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Enrol> list = (List<Enrol>) request.getAttribute("list");
%>
<section class="section" style="height:750px">
	
	<h3 style="text-align:center; margin-top: 60px; margin-bottom: 30px; font-size:20px; font-weight:600; color: rgb(51, 51, 51);">수강 신청 목록</h3>
		<div style="width:100%;height:550px;overflow-y:auto;overflow-x:hidden">
			<table id="record" class="table table-striped table-hover">
				<thead class="table-primary">
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
							<td class="line2"><%= enrol.getSubjectTerm() %></td>
				            <td class="line2"><%= enrol.getSubjectLebel() %></td>
				            <td class="line2"><%= enrol.getDepartmentName() %></td>
				            <td class="line2"><%= enrol.getSubjectNo() %></td>
				            <td class="line2"><%= enrol.getSubjectName() %></td>
				            <td class="line2"><%= enrol.getMemberName() %></td>
				            <td class="line2"><%= enrol.getSubjectTime() %></td>
				            <td class="line2"><%= enrol.getSubjectClassroom() %></td>
				            <td class="line2"><%= enrol.getSubjectCredit() %></td>
				            <td class="line2"><input type="button" class="checkBtn" value="수강신청"></td>
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
		
//		td.each(function(i){	
//			tdArr.push(td.eq(i).text());
//		});
		console.log("배열에 담긴 값 : "+tdArr);
		console.log(tdArr[3]);
		
		$.ajax({
            url: "<%= request.getContextPath() %>/enrol/enrolment",
            type: "POST",           
            data: {
	            	term : tdArr[0],
	                lebel : tdArr[1],
	                mname : tdArr[2],
	                sno : tdArr[3]
            	},
            
            async: false,
            success: function(data) {
            	swal('수강 신청', "수강신청이 성공적으로 완료되었습니다.", 'success');
            },
            error: function(msg, error) {
            	swal('수강 실패', "수강 신청에 실패했습니다", 'error');
            }
		});
	});
	</script>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>