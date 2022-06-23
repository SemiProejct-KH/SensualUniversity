<%@page import="studentclass.model.dto.PresentlyStudentClass"%>
<%@page import="studentclass.model.dto.StudentClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<PresentlyStudentClass> list = (List<PresentlyStudentClass>) request.getAttribute("list");
%>

<section class="section" style="height:750px">
   <div class="btns2" style="margin-top: 100px">
         <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#" onclick="location.href='<%= request.getContextPath() %>/class/presentlystudentclass';" style="text-decoration: none; color: black;">현학기 강의조회</a>
            </li>
            <li class="breadcrumb-item">
                <a href="#" onclick="location.href='<%= request.getContextPath() %>/class/studentclass';" style="text-decoration: none; color: black;">지난학기 강의조회</a>
            </li>
          </ol>
        </nav>
    </div>
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
					<td colspan="9" style="text-align: center">현재 신청한 강의가 없습니다.</td>
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
            	
            	swal({
            		title : "성공적으로 취소되었습니다.",
            	    	icon  : "success",
            	    	closeOnClickOutside : false
            	}).then(function(){
            		window.location.reload();
            	});
            },
            error: function(msg, error) {
            	swal('수강 취소', "취소에 실패하였습니다.", 'error');
            }
     
		});
		
	});
	</script>
		<div style="margin-bottom:100px;">
		</div>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>