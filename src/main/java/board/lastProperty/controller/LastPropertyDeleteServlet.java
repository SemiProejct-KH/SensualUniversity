package board.lastProperty.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.BoardAttachment;
import board.model.service.LastPropertyService;

/**
 * Servlet implementation class LastPropertyDeleteServlet
 */
@WebServlet("/board/lastPropertyDelete")
public class LastPropertyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LastPropertyService lastPropertyService = new LastPropertyService();
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		// 사용자입력처리
		int no = Integer.parseInt(request.getParameter("no"));

		// 업무로직
		// 첨부파일 존재시 삭제
		List<BoardAttachment> attachments = lastPropertyService.findByNo(no).getBoardAttachments();
		if(attachments != null && !attachments.isEmpty())
			for(BoardAttachment attach : attachments) {
				String saveDirectory = getServletContext().getRealPath("/upload/board");
				File delFile = new File(saveDirectory, attach.getRenameFilename());
				if(delFile.exists()) {
					delFile.delete();
					System.out.println("> " + attach.getRenameFilename() + "파일 삭제!");						
				}
			}
				
		
		// board 레코드(행) 삭제 (attachment는 on delete cascade에 의해 자동으로 제거된다.)
		int result = lastPropertyService.deleteBoard(no);
		
		// 3. redirect : /mvc/board/boardList로 이동
		request.getSession().setAttribute("msg", "게시글을 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/board/lastPropertyList");

	} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
	}


}
