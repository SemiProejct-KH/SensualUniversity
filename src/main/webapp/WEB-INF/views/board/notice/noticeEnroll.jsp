<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script>
<%-- 글쓰기 폼 유효성 검사 --%>
window.onload = () => { // form보다 상단에 위치하므로 onload 속성에 추가
	document.noticeEnrollFrm.onsubmit = (e) => {
		const frm = e.target;
		
		// 제목을 작성하지 않은 경우 폼제출할 수 없음.
		const titleVal = frm.title.value.trim(); // 좌우공백
		if(!/^.+$/.test(titleVal)) {
			alert("제목을 작성해주세요.");
			frm.title.select();
			return false;
		}
		
		// 내용을 작성하지 않은 경우 폼제출할 수 없음.
		const contentVal = frm.content.value.trim();
		if(!/^(.|\n).+$/.test(contentVal)) {
			alert("내용을 작성해주세요.");
			frm.title.select();
			return false;
		}
		
		return true;
	}
	
}
</script>
<section id="notice_container_enroll" class="section">
<%-- 입력 폼 --%>
<main class="mt-5 pt-5">
<div class="container-fluid px-4">
	<h3 class="mt-4" >공지사항 등록</h3>
	<div class="card mb-4">
		<div class="card-body">
			<form name="noticeEnrollFrm" action="<%= request.getContextPath() %>/notice/noticeEnroll" method="post">
				<div class="mb-3">
					<label for="writer" class="form-label">작성자</label> <input 
						type="text" class="form-control" id="writer" name="writer"
						value="<%= loginMember.getMemberName() %>" readonly>
				</div>
				<div class="mb-3">
					<label for="title" class="form-label">제목</label> <input 
						type="text" class="form-control" id="title" name="title"
						value="">
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용</label> 
					<textarea class="form-control" id="content" name="content"></textarea>
				</div>
				<div class="mb-3">
					<input type="file" name="upFile1">
					<br>
					<input type="file" name="upFile2">
				</div>
				<button id="btn_enroll" class="btn btn-outline-primary">등록하기</button>
			</form>
		</div>
	</div>
</div>
</main>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>