<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	List<NoticeExt> list = (List<NoticeExt>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainPage.css" />
		<section id="section_slide">
			<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
			  </div>
			  <div class="carousel-inner">
			    <div class="carousel-item active" data-bs-interval="10000">
			      <img src="<%= request.getContextPath() %>/ class="d-block w-100" alt="">
			    </div>
			    <div class="carousel-item" data-bs-interval="2000">
			      <img src="<%= request.getContextPath() %>/images/flash.PNG" class="d-block w-100" min-height: 100vh; alt="">
			    </div>
			    <div class="carousel-item">
			      <img src="<%= request.getContextPath() %>/images/facility.png" class="d-block w-100" alt="">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
</section>
<section class="section" >
	<div class="info">
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) { %>
		<img id="student_image" src="/semi/images/student.gif" alt="">
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