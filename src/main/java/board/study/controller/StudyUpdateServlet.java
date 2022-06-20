package board.study.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.dto.BoardAttachment;
import board.model.dto.BoardExt;
import board.model.service.StudyService;
import common.BoardFileRenamePolicy;

/**
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet("/board/studyUpdate")
public class StudyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyService studyService = new StudyService(); 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2.업무로직
		BoardExt board = studyService.findByNo(no);
		
		// 3.view단처리
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/study/studyUpdate.jsp")
			.forward(request, response);
	}


	/**
	 * DB 수정 요청
	 * 
	 * 파일업로드 절차
	 * - 1. 제출폼 enctype="multipart/form-data"
	 * - 2. MultipartRequest객체 생성 - 파일저장완료
	 * 		a. HttpServletRequest
	 * 		b. saveDirectory
	 * 		c. maxPostSize 최대업로드크기
	 * 		d. encoding
	 * 		e. FileRenamePolicy객체 
	 * - 3. 사용자입력값 처리 - HttpServletRequest가 아닌 MultipartRequest에서 값을 가져오기
	 * - 4. 업무로직 - db board, attachment 레코드 등록
	 * - 5. redirect
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. MultipartRequest객체 생성 - 파일저장완료
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		FileRenamePolicy policy = new BoardFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		// 3. 사용자입력값 처리 - HttpServletRequest가 아닌 MultipartRequest에서 값을 가져오기
		// update board set title = ?, content = ? where no = ?
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String writerId = multiReq.getParameter("writerId");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
		
		BoardExt board = new BoardExt();
		board.setBoardNo(no);
		board.setBoardTitle(title);
		board.setMemberId(writerId);
		board.setBoardContent(content);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<BoardAttachment> boardAttachments = new ArrayList<>();
			if(upFile1 != null)
				boardAttachments.add(getBoardAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				boardAttachments.add(getBoardAttachment(multiReq, no, "upFile2"));
			board.setBoardAttachments(boardAttachments);
		}
		
		// 4. 업무로직 - 
		// db board(update), attachment(insert) 레코드 등록
		int result = studyService.updateBoard(board);
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				BoardAttachment attach = studyService.findAttachmentByNo(attachNo);
				// a. 파일 삭제
				File delFile = new File(saveDirectory, attach.getRenameFilename());
				if(delFile.exists()) delFile.delete();

				// b. db record 삭제
				result = studyService.deleteAttachment(attachNo);

				System.out.println("attach=" + attachNo);
				System.out.println(result);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenameFilename() + ") 삭제!");
			}
		}
		// 5. redirect
		response.sendRedirect(request.getContextPath() + "/board/studyView?no=" + no);
		
	}

	private BoardAttachment getBoardAttachment(MultipartRequest multiReq, int boardNo, String name) {
		BoardAttachment attach = new BoardAttachment();
		attach.setBoardNo(boardNo);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenameFilename(multiReq.getFilesystemName(name));
		return attach;
	}


}
