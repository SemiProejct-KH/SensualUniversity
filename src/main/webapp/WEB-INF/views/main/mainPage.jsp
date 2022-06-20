<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	List<NoticeExt> list = (List<NoticeExt>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainPage.css" />
<section class="section" >
	<div class="info">
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) { %>
		<img id="student_image" src="/semi/images/students.gif" alt="">
		<div id="s_info">
			<p id="p_welcome"><%= loginMember.getMemberName() %>님, 안녕하세요!</p>
			<p>▫ 아이디 : <%= loginMember.getMemberId() %></p>
			<p>▫ 학번 : <%= loginMember.getMemberNo() %></p><p>▫ <%= loginMember.getMemberLevel() %>학년 재학중</p>
		</div>
	<% } %>
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) { %>
		<img class="a_p_image" src="/semi/images/admin.png" alt="">
		<div class="a_p_welcome">
			<p>관리자님, 안녕하세요!</p>
		</div>
	<% } %>
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.P) { %>
		<img class="a_p_image" src="/semi/images/professor.png" alt="">
		<div class="a_p_welcome">
			<p><%= loginMember.getMemberName() %>교수님, 안녕하세요!</p>
		</div>
	<% } %>
	</div>
	<div class="calendar">
		 <input type="text" id="datepicker">
	</div>
		<div class="main_notice_list">
			<h5><a href="<%= request.getContextPath()%>/notice/noticeList">공지사항 >></a></h5>
			
			<table id="" class="tbl_list table table-striped">
			<thead class="thead-light">
				<tr>
					<th></th>			
					<th>제목</th>
					<th>&nbsp&nbsp&nbsp작성일</th>
				 	<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(list != null && !list.isEmpty()) {
				for(NoticeExt notice : list) {
			%>
			<tr onclick="location.href='<%= request.getContextPath() %>/notice/noticeView?no=<%= notice.getNoticeNo() %>';">
				<td>▪</td>
				<td><%= notice.getNoticeTitle() %></td>
				<td><%= notice.getNoticeDate() %></td>
				<td>&nbsp&nbsp&nbsp<%= notice.getNoticeReadCount() %></td>
			</tr>
			<%
				}
			} else {
			%>
				<p> 조회된 정보가 없습니다.</p>
			<%
				}
			%>
			</tbody>
		</table>
		</div>
</section>


<script>
		$(function() {
		//input을 datepicker로 선언
		    $("#datepicker").datepicker({
		                dateFormat: 'yy-mm-dd' //Input Display Format 변경
		                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
		                ,changeYear: true //콤보박스에서 년 선택 가능
		                ,changeMonth: true //콤보박스에서 월 선택 가능                
		                ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
		                ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
		                ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
		                ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
		                ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
		                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
		                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
		                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
		                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
		                ,minDate: "-100Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		                ,maxDate: "+100Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
		            });                    
		            
		            //초기값을 오늘 날짜로 설정
		            $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
		        });
		        
		        $.datepicker.setDefaults({
		            dateFormat: 'yy-mm-dd' //Input Display Format 변경
		        });
		        $.datepicker.setDefaults({
		            showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		        });
		        $.datepicker.setDefaults({
		            showOn: "button" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시      
		        });
		        $.datepicker.setDefaults({
		            changeYear: true //콤보박스에서 년 선택 가능
		            ,changeMonth: true //콤보박스에서 월 선택 가능            
		        });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>