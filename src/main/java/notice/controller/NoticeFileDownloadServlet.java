package notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.dto.NoticeAttachment;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeFileDownloadServlet
 */
@WebServlet("/notice/fileDownload")
public class NoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 업무로직
		NoticeAttachment noticeAttach = noticeService.findAttachmentByNo(no);
		System.out.println(noticeAttach);
		
		// 응답처리
		// 헤더작성
		// 3. 응답처리
		// 헤더작성
		response.setContentType("application/octet-stream"); // 응답데이터 타입 - 이진데이터
		// Content-Disposition 첨부파일인 경우, 브라우저 다운로드(Save as)처리 명시
		response.setHeader("Content-Disposition", "attachment=" + noticeAttach.getOriginalFilename());
		
		// 파일을 읽어서(input) 응답메세지에 쓰기(output)
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		File file = new File(saveDirectory, noticeAttach.getRenameFilename());
		
		// 기본스트림 - 대상과 연결
		// 보조스트림 - 기본스트림과 연결, 보조스트림 제어
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());) {
			byte[] buffer = new byte[8192];
			int len = 0; // 읽어낸 byte 수
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len); // buffer의 0번지부터 len(읽은 개수)까지 출력
			}
		}
	}

}
