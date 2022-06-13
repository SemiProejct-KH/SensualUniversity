<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String memberId = loginMember.getMemberId();
	String memberName = loginMember.getMemberName();
	Date memberBirth = loginMember.getMemberBirth();// null값이어도 input:datetime에서 무시함.
	String memberEmail = loginMember.getMemberEmail() != null ? loginMember.getMemberEmail() : "";
	String memberPhone = loginMember.getMemberPhone();
	String memberLevel = loginMember.getMemberLevel();
	String departmentNo = loginMember.getDepartmentNo();

%>

<section class="section">
  <h2 align="center" style="margin-top:100px;">마이페이지</h2>	
  <form name="memberUpdateFrm" method="POST" action="<%= request.getContextPath() %>/member/memberUpdate">
	  <!-- 아이디, 이름, 생년월일, 이메일, 핸드폰, 학년드롭박스, 학과드롭박스 -->
	  <div class="mb-3 row">
	    <label for="memberId" class="col-sm-2 col-form-label">아이디</label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="memberId" value="<%= memberId %>">
	    </div>
	  </div>
	  
	  <div class="mb-3 row">
	    <label for="memberName" class="col-sm-2 col-form-label">이름</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="memberName" value="<%= memberName %>">
	    </div>
	  </div>
	  
	  <div class="mb-3 row">
	    <label for="memberBirth" class="col-sm-2 col-form-label">생년월일</label>
	    <div class="col-sm-10">
	      <input type="date" class="form-control" id="memberBrith" value="<%= memberBirth %>">
	    </div>
	  </div>
	  
	  <div class="mb-3 row">
	    <label for="memberEmail" class="col-sm-2 col-form-label">이메일</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" id="memberEmail" value="<%= memberEmail %>">
	    </div>
	  </div>
	  
	  <div class="mb-3 row">
	    <label for="memberPhone" class="col-sm-2 col-form-label">핸드폰</label>
	    <div class="col-sm-10">
	      <input type="tel" class="form-control" id="memberPhone" value="<%= memberPhone %>">
	    </div>
	  </div>
	  <div class="row g-3">
		  <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) {%>
		  <div class="col-sm">
		    <select id="inputState" class="form-select">
		      <option selected><%= memberLevel %></option>
		      <option>...</option>

		    </select>
		  </div>
		  <% } %>
		  
		  <div class="col-sm">
		    <select id="inputState" class="form-select">
		      <option selected><%= departmentNo %></option>
		      <option>...</option>
		    </select>
		  </div>
	  </div>
	  
	  <button type="submit" class="btn btn-primary">정보수정</button>
	  <button type="button" class="btn btn-primary" onclick="location.href='<%= request.getContextPath() %>/member/passwordUpdate';" >비밀번호수정</button>
	  <button type="button" class="btn btn-primary" onclick="deleteMember();">회원탈퇴</button>
  </form>
</section>
<form 
	name="memberDelFrm" 
	action="<%= request.getContextPath() %>/member/memberDelete" 
	method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>
<script>

const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")){
		document.memberDelFrm.submit();
	}
}


/*회원수정폼 유효성 검사*/
document.memberUpdateFrm.onsubmit = () => {
	// memberName
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자이상 입력해주세요.");
		return false;
	}
	
	// phone
	if(!/^010\d{8}$/.test(phone.value)){
		alert("유효한 전화번호를 입력하세요.");
		return false;
	}
};
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>