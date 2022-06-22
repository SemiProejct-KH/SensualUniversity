<%@page import="studentrecord.model.dto.Record"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Record> list = (List<Record>) request.getAttribute("list");
%>
<section class="section" style="height:750px">
   <div class="btns2" style="margin-top: 100px">
         <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#" onclick="location.href='<%= request.getContextPath() %>/student/presentlystudentrecord';" style="text-decoration: none; color: black;">현학기 성적조회</a>
            </li>
            <li class="breadcrumb-item">
                <a href="#" onclick="location.href='<%= request.getContextPath() %>/student/studentrecord';" style="text-decoration: none; color: black;">지난학기 성적조회</a>
            </li>
          </ol>
        </nav>
    </div>
		<table id="record" class="table table-striped table-hover">
			<thead class="table-primary">
				<tr>
	                <th class="line1">학년</th>
	                <th class="line1">개설학과</th>
	                <th class="line1">교과목코드</th>
	                <th class="line1">담당교수</th>
	                <th class="line1">출석</th>
	                <th class="line1">중간</th>
	                <th class="line1">기말</th>
	                <th class="line1">과제</th>
	                <th class="line1">백분위</th>
	                <th class="line1">학점</th>
				</tr>
			</thead>
			<tbody>
<%
				if(list != null && !list.isEmpty()) {
					for(Record record : list)
					{
%>
						<tr>
				            <td class="line2"><%= record.getSubjectLebel() %></td>
				            <td class="line2"><%= record.getDepartmentName() %></td>
				            <td class="line2"><%= record.getSubjectNo() %></td>
				            <td class="line2"><%= record.getMemberName() %></td>
				            <td class="line3"><%= record.getGradeAttend() %></td>
				            <td class="line3"><%= record.getGradeMiddle() %></td>
				            <td class="line3"><%= record.getGradeFinal() %></td>
				            <td class="line3"><%= record.getGradAssignment() %></td>
				            <td class="line4" id="result"></td>
				            <td class="line5" id="result2"></td>
						</tr>
<%
					}
				}
				else
				{
%>			
					<tr>
						<td colspan="10" style="text-align: center">성적이 등록된 수업이 없습니다.</td>
					</tr>
<%				
				}
%>
			</tbody>
		</table>
		<script>
		    $(document).ready(function () {
		        
		        $(".line4").each(function () {
		            var $this = $(this);
		            var sum_value = 0;
		            $(".line3",$this.closest('tr')).each(function (i, e) {
		                sum_value+=parseInt($(e).text());
		            })
		            $this.text(sum_value);
		        })
		        $(".line5").each(function () {
		            var tr = $(this).parents();
		            var td = tr.children();
		            var $this = $(this);
	
		            var test = td.eq(8).text();
		            console.log(test);
	
		            if(test >= 90)
		            {
		                $this.text("A");
		            }
		            else if(test >= 80)
		            {
		                $this.text("B");
		            }
		            else if(test >= 70)
		            {
		                $this.text("C");
		            }
		            else if(test >= 60)
		            {
		                $this.text("D");
		            }
		            else
		            {
		                $this.text("F");
		            }
		        })
		    })
		</script>
		<div style="margin-bottom:100px;">
		</div>
</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>