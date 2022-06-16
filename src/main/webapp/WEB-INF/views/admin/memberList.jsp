<%@page import="member.model.dto.MemberExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<MemberExt> list = (List<MemberExt>) request.getAttribute("list");
	MemberExt memberExt = (MemberExt) request.getAttribute("memberExt");
	String pagebar = (String) request.getAttribute("pagebar");
%>
<section class="section" style="width: 83%;">
	<div class="content-body">
	    <div class="container-fluid">
	        <h2 align="center" style="margin-top:100px;">회원관리</h2>	
	        
	        <div class="row">
	            <div class="col-12">
	                <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/admin/memberList" style="text-decoration-line: none;">홈</a></li>
	                        <li class="breadcrumb-item active" aria-current="page"><a href="<%= request.getContextPath() %>/admin/studentList" style="text-decoration-line: none;">학생관리</a></li>
	                        <li class="breadcrumb-item active" aria-current="page"><a href="<%= request.getContextPath() %>/admin/professorList" style="text-decoration-line: none;">교사관리</a></li>
	                    </ol>
	                </nav>
	                
	                <!-- 검색 -->
	                <div class="row g-2">
					  <div class="col-md-3">
					    <div class="form-floating">
					      <select class="form-select" id="floatingSelectGrid" aria-label="Floating label select example">
					        <option>학과 목록</option>
					        <option value="1">컴퓨터소프트웨어학과</option>
					        <option value="2">정보통신공학과</option>
					        <option value="3">전자공학과</option>
					        <option value="4">생활체육과</option>
					        <option value="5">경영학과</option>
					      </select>
					      <label for="floatingSelectGrid">학과</label>
					    </div>
					  </div>
					  <div class="col-md-5">
					    <div class="form-floating">
					      <input type="text" class="form-control" id="floatingInputGrid" placeholder="홍길동">
					      <label for="floatingInputGrid">이름검색</label>
					    </div>
					  </div>
					</div>
	                <!-- 리스트 -->
	                <div class="card">
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
	                                        <% if(member.getDepartmentNo().equals("D1")) { %>
		                                        <td>컴퓨터소프트웨어학과</td>
	                                        <% 	} %>
	                                        <% if(member.getDepartmentNo().equals("D2")) { %>
		                                        <td>정보통신공학과</td>
	                                        <% 	} %>
	                                        <% if(member.getDepartmentNo().equals("D3")) { %>
		                                        <td>전자공학과</td>
	                                        <% 	} %>
	                                        <% if(member.getDepartmentNo().equals("D4")) { %>
		                                        <td>생활체육과</td>
	                                        <% 	} %>
	                                        <% if(member.getDepartmentNo().equals("D5")) { %>
		                                        <td>경영학과</td>
	                                        <% 	} %>
	                                        <td><%= member.getMemberLevel() != null ? member.getMemberLevel() : "교수" %>  </td>
	                                        <td><%= member.getMemberBirth() %></td>
	                                        <td><%= member.getMemberPhone() %></td>
	                                        <td><%= member.getMemberEmail() %></td>
	                                        <td><%= member.getEnrollDate() %></td>
	                                        <td>
	                                        <form 
												name="memberDelFrm" id="memberDelFrm"
												action="<%= request.getContextPath() %>/admin/memberDelete" 
												method="POST">
												<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
											</form>
	                                        <button type="submit" class="btn btn-primary" onclick="deleteMember();">회원탈퇴</button>
			                            	
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
	</div>
	
	<div class="page_bar"id="pagebar">
		<%= pagebar %>
	</div>
</section>

<script>
const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")){
		$("#memberDelFrm").submit();
	}
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
