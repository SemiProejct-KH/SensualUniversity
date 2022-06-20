<%@page import="board.model.dto.BoardAttachment"%>
<%@page import="board.model.dto.BoardExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%

	BoardExt board = (BoardExt) request.getAttribute("board");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board_container_enroll" class="section">
<%-- 입력 폼 --%>
<main class="mt-5 pt-5">
<div class="container-fluid px-4">
	<h3 class="mt-4" >교내묻고답하기 수정</h3>
	<div class="card mb-4">
		<div class="card-body">
			<form 
				name="boardUpdateFrm"
				action="<%=request.getContextPath()%>/board/questionUpdate" 
				method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="no" value="<%=board.getBoardNo()%>" />
				<div style="display:none" class="mb-3">
					<label for="writer" class="form-label">작성자번호</label> <input 
						type="text" class="form-control" id="writerId" name="writerId" 
						value="<%=loginMember.getMemberId()%>">
				</div>
				<div class="mb-3">
					<label for="title" class="form-label">제목</label> <input 
						type="text" class="form-control" id="title" name="title"
						value="<%=board.getBoardTitle()%>">
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용</label> 
					<textarea class="form-control" id="content" name="content" cols="30" rows="10" style="resize:none"><%=board.getBoardContent()%></textarea>
				</div>
				<div class="mb-3">
				<%
					List<BoardAttachment> attachments = board.getBoardAttachments();
						if(attachments != null && !attachments.isEmpty()){
							for(int i = 0; i < attachments.size(); i++){
							BoardAttachment boardAttach = attachments.get(i);
				%>
							<img src="<%= request.getContextPath() %>/images/file.png" width="16px">
							<%= boardAttach.getOriginalFilename()%>
							<input type="checkbox" name="delFile" id="delFile<%= i + 1 %>" value="<%= boardAttach.getBoardAttachmentNo() %>"/>
							<label for="delFile<%= i + 1 %>">삭제</label>
							<br />
					<%
							}
						}
					%>
				
					<input type="file" name="upFile1">
					<br>
					<input type="file" name="upFile2">
				</div>		
				<div id="update_btn">
					<button class="btn btn-outline-primary">수정하기</button>
					<button class="btn btn-outline-primary" value="취소" onclick="history.go(-1)">취소</button>
				</div>
			</form>
		</div>
	</div>
</div>
</main>
</section>
<script>
/**
 * 업로드 가능한 첨부파일 수 제한하기
 */
const len = document.querySelectorAll("[name=delFile]").length
for(let i = 0; i < len; i++)
	document.querySelectorAll("input[type=file]")[i].style.display = "none";
	
/**
 * [name=delFile] 체크/체크해제시 input[type=file] 노출/감춤처리
 */
document.querySelectorAll("[name=delFile]").forEach((delFile) => {
	delFile.onchange = (e) => {
		const {id, checked} = e.target;
		// console.log(id, checked);
		const n = id.replaceAll(/[^0-9]/g, "");
		const file = document.querySelector(`[name=upFile\${n}]`);
		file.style.display = checked ? "inline" : "none"; // 노출/감춤 처리
		checked || (file.value = ""); // 지정한 파일을 제거 
	};
});


document.boardUpdateFrm.onsubmit = (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	const titleVal = frm.title.value.trim(); // 좌우공백
	if(!/^.+$/.test(titleVal)){
		alert("제목을 작성해주세요.");
		frm.title.select(e);
		return false;
	}		
	
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>