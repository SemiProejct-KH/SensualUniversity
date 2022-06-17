package notice.controller;

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

import common.BoardFileRenamePolicy;
import notice.model.dto.NoticeAttachment;
import notice.model.dto.NoticeExt;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/noticeUpdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeService();
	// 주석추가
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2.업무로직
		NoticeExt notice = noticeService.findByNo(no);
		
		// 3.view단처리
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeUpdate.jsp")
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
		int writerNo = Integer.parseInt(multiReq.getParameter("writerNo"));
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
		
		NoticeExt notice = new NoticeExt();
		notice.setNoticeNo(no);
		notice.setNoticeTitle(title);
		notice.setMemberNo(writerNo);
		notice.setNoticeContent(content);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<NoticeAttachment> noticeAttachments = new ArrayList<>();
			if(upFile1 != null)
				noticeAttachments.add(getNoticeAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				noticeAttachments.add(getNoticeAttachment(multiReq, no, "upFile2"));
			notice.setNoticeAttachments(noticeAttachments);
		}
		
		// 4. 업무로직 - 
		// db board(update), attachment(insert) 레코드 등록
		int result = noticeService.updateNotice(notice);
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				System.out.println("attach=" + attachNo);
				NoticeAttachment attach = noticeService.findAttachmentByNo(attachNo);
				System.out.println("attach=" + attachNo);
				// a. 파일 삭제
				File delFile = new File(saveDirectory, attach.getRenameFilename());
				if(delFile.exists()) delFile.delete();

				// b. db record 삭제
				result = noticeService.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenameFilename() + ") 삭제!");
			}
		}
		// 5. redirect
		response.sendRedirect(request.getContextPath() + "/notice/noticeView?no=" + no);
		
	}

	private NoticeAttachment getNoticeAttachment(MultipartRequest multiReq, int noticeNo, String name) {
		NoticeAttachment attach = new NoticeAttachment();
		attach.setNoticeNo(noticeNo);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenameFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}
