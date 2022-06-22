<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myPage.css?after" />
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
     <div class="content">
<!--------------------------------타이틀----------------------------->
          <div class="title">
                <h3>회 원 정 보</h3>
          </div>
<!--------------------------------입력폼시작----------------------------->
          <form name="memberUpdateFrm" method="POST" action="<%= request.getContextPath() %>/member/memberUpdate">
             <div class="content_input">
                  <!-- 아이디, 이름, 생년월일, 이메일, 핸드폰, 학년드롭박스, 학과드롭박스 -->
                  <div class="mb-2 row">
                    <label for="memberId" class="col-sm-3 col-form-label">아이디</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control-plaintext" id="memberId" name="memberId"value="<%= memberId %>" readonly>
                    </div>
                  </div>

                  <div class="mb-2 row">
                    <label for="memberName" class="col-sm-3 col-form-label">이름</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="memberName" name="memberName" value="<%= memberName %>">
                    </div>
                  </div>

                  <div class="mb-2 row">
                    <label for="memberBirth" class="col-sm-3 col-form-label">생년월일</label>
                    <div class="col-sm-9">
                      <input type="date" class="form-control" id="memberBirth" name="memberBirth" value="<%= memberBirth %>">
                    </div>
                  </div>

                  <div class="mb-2 row">
                    <label for="memberEmail" class="col-sm-3 col-form-label">이메일</label>
                    <div class="col-sm-9">
                      <input type="email" class="form-control" id="memberEmail" name="memberEmail" value="<%= memberEmail %>">
                    </div>
                  </div>

                  <div class="mb-2 row">
                    <label for="memberPhone" class="col-sm-3 col-form-label">핸드폰</label>
                    <div class="col-sm-9">
                      <input type="tel" class="form-control" id="memberPhone" name="memberPhone" value="<%= memberPhone %>">
                    </div>
                  </div>
<!--------------------------------드롭다운----------------------------->
                  <div class="row g-2">
                     <label for="memberLevel" class="col-sm-3 col-form-label" style="margin-right:4px;">학년</label>
                     <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) {%>
                      <div class="col-sm-3">
                        <select id="memberLevel" name="memberLevel" class="form-select" >
                          <option value="1" <%= memberLevel == "1" ? "selected" : "" %>>1</option>
                          <option value="2" <%= memberLevel == "2" ? "selected" : "" %>>2</option>
                          <option value="3" <%= memberLevel == "3" ? "selected" : "" %>>3</option>
                          <option value="4" <%= memberLevel == "4" ? "selected" : "" %>>4</option>
                        </select>
                      </div>
                      <% } %>
                      <label for="departmentName" class="col-sm-1 col-form-label" style="text-align: center; margin-left:40px; margin-right:15px;">학과</label>
                      <div class="col-sm-4 departmentName">
                        <select id="departmentName" name="departmentName" class="form-select" >
                          <option value="컴퓨터소프트웨어학과" selected>컴퓨터소프트웨어학과</option>
                          <option value="정보통신공학과">정보통신공학과</option>
                          <option value="전자공학과">전자공학과</option>
                          <option value="생활체육과">생활체육과</option>
                          <option value="경영학과">경영학과</option>
                        </select>
                      </div>
                  </div>
<!--------------------------------버튼----------------------------->
                  <div class="btn_bar">
                      <button type="submit" class="btn_bar1" onclick="updateMember();">정보수정</button>
                      <button type="button" class="btn_bar2" onclick="location.href='<%= request.getContextPath() %>/member/passwordUpdate';">비밀번호수정</button>
                      <button type="button" class="btn_bar3" id="confirmStart">회원탈퇴</button>
                  </div>
              </div>
          </form>
      </div>
</section>
<form 
    name="memberDelFrm" 
    action="<%= request.getContextPath() %>/member/memberDelete" 
    method="POST">
    <input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>
<script>
$().ready(function () {
    $("#confirmStart").click(function () {
        Swal.fire({
            title: '정말로 탈퇴하시겠습니까?',
            text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                document.memberDelFrm.submit();
            }
        })
    });
});
/* 회원수정폼 유효성 검사 */
document.memberUpdateFrm.onsubmit = () => {
	// memberName
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		swal('유효성 검사[이름]', "이름은 한글 2글자이상 입력해주세요.", 'warning');
		return false;
	}
	
	// phone
	if(!/^010\d{8}$/.test(memberPhone.value)){
		swal('유효성 검사[전화번호]', "유효한 전화번호를 입력하세요.", 'warning');
		return false;
	}
	// email
	if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(memberEmail.value)){
		swal('유효성 검사[이메일]', "유효한 이메일을 입력하세요.", 'warning');
		return false;
	}
};
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>