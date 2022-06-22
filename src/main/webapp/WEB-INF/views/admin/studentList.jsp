<%@page import="member.model.dto.MemberExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<MemberExt> list = (List<MemberExt>) request.getAttribute("list");
	String pagebar = (String) request.getAttribute("pagebar");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<style>
#pagebar > a {
    color: black;
    text-decoration: none;
}
</style>
<section class="section" style="width: 83%; height:100%;">
	<div class="content-body">
	    <div class="container-fluid" style="margin-top:100px;">	
	        
	        <div class="row">
	            <div class="col-12">
	                <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/admin/memberList" style="text-decoration-line: none; color:black;">홈</a></li>
	                        <li class="breadcrumb-item active" aria-current="page"><a href="<%= request.getContextPath() %>/admin/studentList" style="text-decoration-line: none; color:black;">학생 관리</a></li>
	                        <li class="breadcrumb-item active" aria-current="page"><a href="<%= request.getContextPath() %>/admin/professorList" style="text-decoration-line: none; color:black;">교수 관리</a></li>
	                    </ol>
	                </nav>
	                
	                <!-- 검색 -->
	                <div class="row g-2">
					  <form action="<%=request.getContextPath()%>/admin/studentFinder">
					    <input type="hidden" id="searchType" value="member_id" <%="member_id".equals(searchType)?"selected":""%>/>
					    </form>
					  </div>
					  <div class="col-md-5">
					    <div class="form-floating" id="search-memberId">
					      	<form action="<%=request.getContextPath()%>/admin/studentFinder">
				                <input type="hidden" name="searchType" value="member_id"/>
				                <input type="text" name="searchKeyword"  size="25" class="form-control" placeholder="검색할 아이디를 입력하세요." 
					                style="width: 80%; float:left;" id="floatingInputGrid" 
					                value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
				                <button type="submit" class="btn btn-primary" style="float:right; width: 20%">검색</button>	
			            	</form>	
					    </div>
					  </div>
					</div>
	                <!-- 리스트 -->
	                <div class="card" style="margin-top:20px;">
	                    <div class="card-header" style="background-color:white">
	                        <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb" style="margin-top: 20px">
	                            <h4 class="card-title" style="font-weight:bold;">학생 목록</h4>
	                        </nav>
	                    </div>
	                    <div class="card-body">
	                        <div class="table-responsive">
	                            <table id="example2" class="table" style="width: 100%">
	                                <thead class="table-light" style="border-bottom:1px solid gray; padding:10px;">
	                                    <tr style="border-bottom:1px solid gray; padding:10px;">
	                                        <th>아이디</th>
	                                        <th>이름</th>
	                                        <th>학과</th>
	                                        <th>학년</th>
	                                        <th>생년월일</th>
	                                        <th>핸드폰</th>
	                                        <th>이메일</th>
	                                        <th>가입일</th>
	                                        <th>탈퇴여부</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                    <!-- 리스트 반복문 -->
	                              <% if(list != null && !list.isEmpty()) { 
	                            	   	for(MemberExt member : list) {
	                            	   		String memberId = member.getMemberId();
	                              %>
	                                    <tr>
	                                        <td><%= member.getMemberId() %></td>
	                                        <td><%= member.getMemberName() %></td>
	                                        <td><%= member.getDepartmentName() %></td>
	                                        <td><%= member.getMemberLevel() != null ? member.getMemberLevel() : "교수" %>  </td>
	                                        <td><%= member.getMemberBirth() %></td>
	                                        <td><%= member.getMemberPhone() %></td>
	                                        <td><%= member.getMemberEmail() %></td>
	                                        <td><%= member.getEnrollDate() %></td>
	                                        <td>
	                                        <form 
												name="memberDelFrm" id="memberDelFrm"
												action="<%= request.getContextPath() %>/admin/studentDelete" 
												method="POST">
												<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
												<button type="submit" class="btn btn-primary" id="confirmStart">회원탈퇴</button>
											</form>
			                            	
	                                        </td>
	                                    </tr>
	                              <% 	} %>
	                              <% } else { %>
	                                    <tr>
	                                        <td colspan="8">조회된 회원이 없습니다.</td>
	                                    </tr>
	                              <% } %>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	<div class="page_bar"id="pagebar">
	<% if(pagebar != null) { %>
		<%= pagebar %>
	<% } %>
	</div>
	<div class="container-fluid" style="margin-bottom:100px;"></div>
</section>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
$().ready(function () {
    $("#confirmStart").click(function () {
        Swal.fire({
            title: '탈퇴 처리 중 입니다.',
            icon: 'warning',
        }).then((result) => {
            if (result.isConfirmed) {
                document.memberDelFrm.submit();
            }
        })
    });
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>