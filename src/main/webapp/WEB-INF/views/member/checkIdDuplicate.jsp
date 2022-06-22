<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
  <!-- jQuery -->
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
  <!-- css -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/checkidDuplicate.css?after" />
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#duplicated{color:red; font-weight:bold;}
</style>
</head>
	<div class="checkId-container"> <!--//컨테이너-->
      <div class="popup-wrap" id="popup"> <!--//모달을 감쌀 박스-->
        <div class="popup">	<!--//실질적 모달팝업-->
         
          <div class="popup-head">	<!--//로고 영역-->
              <span class="head-title">아이디 중복확인</span>
          </div>
          <% if(available) { %>
          <div class="popup-body">	<!--//컨텐츠 영역-->
            <div class="body-content">
              <div class="body-titlebox">
                <h3>사용 가능</h3>
              </div>
              <div class="body-contentbox">
                <p>[<%= memberId %>]는 사용가능합니다.</p>
              </div>
            </div>
          </div>
          <div class="popup-foot"> <!--//푸터 버튼 영역-->
            <span class="pop-btn confirm" onclick="closePopup();">확인</span>
            <span class="pop-btn close" onclick="closePopup();">창 닫기</span>
          </div>
          <script>
            const closePopup = () => {
                // opener 부모창 window객체
                const frm = opener.document.memberEnrollFrm;
                frm.memberId.value = '<%= memberId %>';
                frm.idValid.value = 1;

                self.close(); // 현재창 닫기
            };
          </script>
          <% } else { %>
          
          <div class="popup-body">	<!--//컨텐츠 영역-->
            <div class="body-content">
              <div class="body-titlebox">
                <h3>사용 불가</h3>
              </div>
              <div class="body-contentbox">
                <p>[<span id="duplicated"><%= memberId %></span>]는 이미 사용중입니다.</p>
                <form name="checkIdDuplicateFrm" action="<%= request.getContextPath() %>/member/checkIdDuplicate">
                    <input type="text" name="memberId" />
                    <input type="submit" class="btn_submit" value="중복검사" />
                </form>
              </div>
            </div>
          </div>
          <% } %>
        </div>
       </div>
    </div>
<script>
const closePopup = () => {
    // opener 부모창 window객체
    const frm = opener.document.memberEnrollFrm;
    frm.memberId.value = '<%= memberId %>';
    frm.idValid.value = 1;

    self.close(); // 현재창 닫기
};
document.checkIdDuplicateFrm.onsubmit = () => {
	if(!/^[a-z]+[a-z0-9]{5,11}$/g.test(memberId.value)){
		swal('유효성 검사[아이디]', "아이디는 영문자/숫자로 4글자 이상이어야 합니다.", 'warning');
		return false;
	}
};
</script>
</html>
